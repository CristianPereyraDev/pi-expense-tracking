import dao.dto.CategoryDto;
import dao.dto.ExpenseDto;
import exceptions.InvalidExpenseException;
import interfaces.ExpenseAmountValidator;
import service.CategoryService;
import service.ExpenseService;
import util.Utilities;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) throws InvalidExpenseException {
        Scanner scanner = new Scanner(System.in);

        ExpenseService expenseService = new ExpenseService();
        CategoryService categoryService = new CategoryService();

        List<ExpenseDto> expenseDtoList = new ArrayList<>();
        Map<String, Integer> countCategoryMap = new HashMap<>();

        // Implement interface functional
        ExpenseAmountValidator expenseAmountValidator = amount -> {
            if (amount < 0) throw new InvalidExpenseException("Invalid amount");
            else return true;
        };

        boolean userWantAddExpense;
        System.out.print("Do you want add a expense? y/n: ");
        userWantAddExpense = scanner.nextLine().toLowerCase().trim().equals("y");


        // Create expenses with user input
        while (userWantAddExpense){
            // Create Category
            System.out.println("---CATEGORY CREATION---");
            CategoryDto categoryDto = new CategoryDto();
            System.out.print("Enter the category's name: ");
            String categoryName = scanner.nextLine().toLowerCase().trim();
            categoryDto.setName(categoryName);
            Integer newCategoryId = categoryService.createCategory(categoryDto);

            countCategoryMap.put(categoryName, countCategoryMap.getOrDefault(categoryName, 0) + 1);

            // Create Expense
            System.out.println("---EXPENSE CREATION---");
            ExpenseDto expenseDto = new ExpenseDto();

            System.out.print("Enter the expense amount: ");
            double expenseAmount = scanner.nextDouble();

            if (expenseAmountValidator.validateAmount(expenseAmount)) {
                System.out.println("The amount is valid");
            } else {
                System.out.println("The amount is not valid, default to 0.0");
                expenseAmount = 0.0;
            }

            scanner.nextLine();
            System.out.print("Enter the expense date (dd/mm/yyyy): ");
            String expenseDate = scanner.nextLine();

            expenseDto.setAmount(expenseAmount);
            expenseDto.setDescription("Default description");
            expenseDto.setCategoryId(newCategoryId);
            expenseDto.setDate(expenseDate);

            expenseDtoList.add(expenseDto);

            System.out.print("Do you want continue? y/n: ");
            userWantAddExpense = scanner.nextLine().toLowerCase().trim().equals("y");
        }

        BinaryOperator<ExpenseDto> expenseAccumulator = (e1, e2) -> {
            ExpenseDto e = new ExpenseDto();
            e.setAmount(e1.getAmount() + e2.getAmount());
            return e;
        };
        ExpenseDto totalExpense = expenseDtoList.stream().reduce(new ExpenseDto(), expenseAccumulator);

        System.out.println("Total expenses amount = " + totalExpense.getAmount());

        System.out.println("Category counter:");
        countCategoryMap.forEach((category, count) -> System.out.println(category + ": " + count));

        System.out.println("----EXPENSES----");
        Utilities.printElements(expenseDtoList);

        // Stream API
        System.out.println("Top 3 of expenses");
        List<Double> amounts = expenseDtoList.stream()
                .map(ExpenseDto::getAmount)
                .limit(3).toList();
        amounts.forEach(System.out::println);

        // Save expenses and categories to database
        for (ExpenseDto expenseDto : expenseDtoList) {
            if (expenseService.createExpense(expenseDto) > -1) {
                System.out.println("Expense saved!");
            } else {
                System.out.println("Expense could not be saved in database!");
            }
        }
    }
}