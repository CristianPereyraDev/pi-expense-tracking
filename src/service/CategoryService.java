package service;

import dao.CategoryDao;
import dao.dto.CategoryDto;
import dao.impl.CategoryDaoImplH2;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService() {
        categoryDao = new CategoryDaoImplH2();
    }

    public Integer createCategory(CategoryDto categoryDto) {
        return categoryDao.insert(categoryDto);
    }
}
