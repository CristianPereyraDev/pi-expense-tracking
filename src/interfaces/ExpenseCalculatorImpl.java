package interfaces;

import dao.dto.ExpenseDto;

import java.util.List;

public class ExpenseCalculatorImpl implements ExpenseCalculator {
    @Override
    public double calculateTotalExpenses(List<ExpenseDto> expenses) {
        double totalExpense = 0;

        for (ExpenseDto expense: expenses) {
            totalExpense += expense.getAmount();
        }

        return totalExpense;
    }
}
