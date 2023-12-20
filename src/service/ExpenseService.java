package service;

import dao.ExpenseDao;
import dao.dto.ExpenseDto;
import dao.impl.ExpenseDaoImplH2;
import entities.Expense;
import interfaces.ExpenseCalculator;
import interfaces.ExpenseCalculatorImpl;

import java.util.List;

public class ExpenseService {
    private final ExpenseDao expenseDao;

    public ExpenseService() {
        expenseDao = new ExpenseDaoImplH2();
    }

    public Integer createExpense(ExpenseDto expense) {
        return expenseDao.insert(expense);
    }

    public ExpenseDto getExpenseById(Integer id) {
        return expenseDao.read(id);
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseDao.getAll();
    }

    public List<Expense> getExpensesByCategory(Integer idCategory) {
        return expenseDao.getExpensesByCategory(idCategory);
    }

    public Double getAllExpensesAmount() {
        List<ExpenseDto> allExpenses = expenseDao.getAll();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        return expenseCalculator.calculateTotalExpenses(allExpenses);
    }
}
