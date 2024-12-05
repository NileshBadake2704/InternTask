import java.util.ArrayList;
import java.util.Scanner;

// BankAccount Class
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private String password;
    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    private double annualInterestRate = 0.03; // 3% annual interest rate

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, String password, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.password = password;
        this.balance = initialDeposit;
        transactionHistory.add("Account created with initial deposit: $" + initialDeposit);
    }

    // User Authentication
    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdrawal Method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    // Check Balance
    public double getBalance() {
        return balance;
    }

    // View Transaction History
    public void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Calculate Interest
    public void calculateInterest(int months) {
        double interest = balance * (annualInterestRate / 12) * months;
        System.out.printf("Interest for %d month(s): $%.2f%n", months, interest);
    }
}

// Main Class
public class BankAccountManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating an account
        System.out.println("Welcome to Bank Account Management System!");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Set Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Initial Deposit Amount: ");
        double initialDeposit = scanner.nextDouble();

        BankAccount account = new BankAccount(accountNumber, accountHolderName, password, initialDeposit);
        System.out.println("Account created successfully!");

        // Menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.printf("Current Balance: $%.2f%n", account.getBalance());
                    break;
                case 4:
                    account.viewTransactionHistory();
                    break;
                case 5:
                    System.out.print("Enter the number of months to calculate interest: ");
                    int months = scanner.nextInt();
                    account.calculateInterest(months);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}

