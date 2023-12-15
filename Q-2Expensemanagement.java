import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Expense {
    String category;
    double amount;
    String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
}

class ExpenseManager {
    private List<Expense> expenses;
    private Map<String, Double> budgets;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.budgets = new HashMap<>();
    }

    public void logExpense(String category, double amount, String date) {
        expenses.add(new Expense(category, amount, date));
        System.out.println("Expense logged successfully.");
    }

    public void setBudget(String category, double budgetAmount) {
        budgets.put(category, budgetAmount);
        System.out.println("Budget set for category: " + category);
    }

    public void visualizeExpenses() {
        System.out.println("Expense Visualization:");
        // Implement graphical charts or graphs here based on your preferred visualization library.
        // For simplicity, we'll just print the expenses for each category.
        for (Map.Entry<String, Double> entry : budgets.entrySet()) {
            String category = entry.getKey();
            double budget = entry.getValue();
            double totalExpense = calculateTotalExpense(category);
            System.out.println(category + " - Budget: $" + budget + " - Total Expense: $" + totalExpense);
        }
    }

    public void remindAboutBudget() {
        for (Map.Entry<String, Double> entry : budgets.entrySet()) {
            String category = entry.getKey();
            double budget = entry.getValue();
            double totalExpense = calculateTotalExpense(category);

            if (totalExpense > budget) {
                System.out.println("Warning: You've exceeded the budget for category " + category);
            } else {
                System.out.println("Amount left for category " + category + ": $" + (budget - totalExpense));
            }
        }
    }

    private double calculateTotalExpense(String category) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            if (expense.category.equals(category)) {
                totalExpense += expense.amount;
            }
        }
        return totalExpense;
    }
}

public class ExpenseManagementApp {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    logExpense(scanner, expenseManager);
                    break;
                case 2:
                    setBudget(scanner, expenseManager);
                    break;
                case 3:
                    expenseManager.visualizeExpenses();
                    break;
                case 4:
                    expenseManager.remindAboutBudget();
                    break;
                case 5:
                    System.out.println("Exiting Expense Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. Log Expense\n2. Set Budget\n3. Visualize Expenses\n4. Remind About Budget\n5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void logExpense(Scanner scanner, ExpenseManager expenseManager) {
        System.out.print("Enter expense category: ");
        String expenseCategory = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double expenseAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter expense date: ");
        String expenseDate = scanner.nextLine();
        expenseManager.logExpense(expenseCategory, expenseAmount, expenseDate);
    }

    private static void setBudget(Scanner scanner, ExpenseManager expenseManager) {
        System.out.print("Enter category to set budget for: ");
        String budgetCategory = scanner.nextLine();
        System.out.print("Enter budget amount: ");
        double budgetAmount = scanner.nextDouble();
        expenseManager.setBudget(budgetCategory, budgetAmount);
    }
}
