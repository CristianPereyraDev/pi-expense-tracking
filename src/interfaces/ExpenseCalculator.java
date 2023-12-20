package interfaces;

import dao.dto.ExpenseDto;

import java.util.List;

public interface ExpenseCalculator {
    double calculateTotalExpenses(List<ExpenseDto> expenses);
}
