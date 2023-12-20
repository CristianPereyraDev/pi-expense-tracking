package dao.impl;

import config.DBConnection;
import dao.ExpenseDao;
import dao.dto.ExpenseDto;
import entities.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImplH2 implements ExpenseDao {
    private final Connection connection;

    public ExpenseDaoImplH2() {
        connection = DBConnection.getConnection();
    }

    @Override
    public Integer insert(ExpenseDto expenseDto) {
        String sql = "INSERT INTO expense (date, amount, description, categoryId) values (?, ?, ?, ?)";
        try {
            int generatedKey = -1;
            Expense newExpense = new Expense();
            newExpense.setAmount(expenseDto.getAmount());
            newExpense.setDate(expenseDto.getDate());
            newExpense.setDescription(expenseDto.getDescription());
            newExpense.setCategoryId(expenseDto.getCategoryId());

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newExpense.getDate());
            preparedStatement.setDouble(2, newExpense.getAmount());
            preparedStatement.setString(3, newExpense.getDescription());
            preparedStatement.setInt(4, newExpense.getCategoryId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedKey = generatedKeys.getInt(1);
            }

            preparedStatement.close();

            return generatedKey;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
            return -1;
        }
    }

    @Override
    public ExpenseDto read(Integer id) {
        String sql = "SELECT * FROM expense WHERE id=?";

        try {
            Expense expense = null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                expense = new Expense(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getInt("categoryId")
                        );
            }
            preparedStatement.close();
            resultSet.close();

            if (expense != null) {
                return new ExpenseDto(
                    expense.getId(),
                    expense.getDate(),
                    expense.getAmount(),
                    expense.getDescription(),
                    expense.getCategoryId()
                );
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error getting a register from database: " + e.getMessage());
            return null;
        } finally {}
    }

    @Override
    public boolean update(ExpenseDto expenseDto) {
        String sql = "UPDATE expense SET date=?, amount=?, description=?, categoryId=? WHERE id=?";
        try {
            // ExpenseDto map to Expense
            Expense expense = new Expense(
                    expenseDto.getId(),
                    expenseDto.getDate(),
                    expenseDto.getAmount(),
                    expenseDto.getDescription(),
                    expenseDto.getCategoryId()
            );

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expense.getDate());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setString(3, expense.getDescription());
            preparedStatement.setInt(4, expense.getCategoryId());
            preparedStatement.setInt(5, expense.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM expense WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<ExpenseDto> getAll() {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        String sql = "SELECT * FROM expense";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Expense expense = new Expense(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getInt("categoryId")
                );
                expenseDtoList.add(new ExpenseDto(
                        expense.getId(),
                        expense.getDate(),
                        expense.getAmount(),
                        expense.getDescription(),
                        expense.getCategoryId())
                );
            }
            preparedStatement.close();
            resultSet.close();

            return expenseDtoList;
        } catch (SQLException e) {
            System.out.println("Error getting a register from database: " + e.getMessage());
            return null;
        } finally {}
    }

    @Override
    public List<Expense> getExpensesByCategory(Integer idCategory) {
        return null;
    }
}
