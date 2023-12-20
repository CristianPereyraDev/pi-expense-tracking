package dao;

import dao.dto.ExpenseDto;
import entities.Expense;

import java.util.List;

public interface ExpenseDao extends Dao<ExpenseDto, Integer> {
    List<Expense> getExpensesByCategory(Integer idCategory);
}
