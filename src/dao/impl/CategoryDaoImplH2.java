package dao.impl;

import config.DBConnection;
import dao.CategoryDao;
import dao.dto.CategoryDto;
import entities.Category;

import java.sql.*;
import java.util.List;

public class CategoryDaoImplH2 implements CategoryDao {
    private final Connection connection;

    public CategoryDaoImplH2() {
        connection = DBConnection.getConnection();
    }
    @Override
    public Integer insert(CategoryDto categoryDto) {
        String sql = "INSERT INTO category (name) values (?)";
        try {
            int generatedId = -1;
            Category newCategory = new Category();
            newCategory.setName(categoryDto.getName());

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newCategory.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }

            preparedStatement.close();

            return generatedId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
            return -1;
        }
    }

    @Override
    public CategoryDto read(Integer id) {
        return null;
    }

    @Override
    public boolean update(CategoryDto categoryDto) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<CategoryDto> getAll() {
        return null;
    }
}
