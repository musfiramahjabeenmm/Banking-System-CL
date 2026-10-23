import models.*;

import services.BankServices;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankMenu {
    private final BankServices bank = new BankServices();
    private final Scanner scan = new Scanner(System.in);
    private int lastCustomerId = 0;

    public void run() {
        System.out.println("**************************************************");
        System.out.println("Welcome to SD BANK");
        System.out.println("**************************************************");

        while (true) {                               // only 0 leaves this loop
            printMenu();
            try {
                int choice = readInt("");
                if (choice == 0) {
                    System.out.println("Thank you for banking with SD BANK");
                    break;
                }
                handle(choice);
            } catch (NoSuchElementException e) {     // input stream closed, avoid an endless loop
                break;
            } catch (Exception e) {                  // any other error: print it, then the loop repeats
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handle(int choice) {
        switch (choice) {
            case 1 -> newCustomer();
            case 2 -> existingCustomer();
            case 3 -> deposit();
            case 4 -> withdraw();
            case 5 -> transfer();
            case 6 -> checkBalance();
            case 7 -> printStatement();
            case 8 -> monthEnd();
            default -> System.out.println("Invalid option, choose 0 to 8");
        }
    }

    private void newCustomer() {
        String name = readText("Enter the customer name: ");
        String contact = readText("Enter the customer contact: ");
        int accType = readInt("Account type (1 = Savings, 2 = Current): ");
        String accName = readText("Enter Account Name: ");
        double balance = readDouble("Enter the opening balance: ");

        int newId = lastCustomerId + 1;
        Customer customer = new Customer(newId, name, contact);
        Account account = createAccount(accType, accName, balance);   // may throw, nothing saved yet
        customer.addAccount(account);
        bank.addCustomer(customer);
        lastCustomerId = newId;                                       // only moves on success

        System.out.println("Customer ID: " + newId + " (do not forget it)");
        System.out.println("Account created. Account Number: " + account.getAccountNumber());
    }

    private void existingCustomer() {
        int id = readInt("Enter the customer ID: ");
        Customer customer = bank.findCustomer(id);
        if (customer == null) throw new IllegalArgumentException("Customer not found: " + id);
        int accType = readInt("Account type (1 = Savings, 2 = Current): ");
        String accName = readText("Enter Account Name: ");
        double balance = readDouble("Enter the opening balance: ");

        Account account = createAccount(accType, accName, balance);
        customer.addAccount(account);
        System.out.println("Account created. Account Number: " + account.getAccountNumber());
    }

    private void deposit() {
        int accNo = readInt("Enter Account Number: ");
        double amount = readDouble("Enter deposit amount: ");
        bank.findAccount(accNo).deposit(amount);
        System.out.println("Amount deposited successfully.");
    }

    private void withdraw() {
        int accNo = readInt("Enter Account Number: ");
        double amount = readDouble("Enter withdraw amount: ");
        bank.findAccount(accNo).withdraw(amount);
        System.out.println("Amount withdrawn successfully.");
    }

    private void transfer() {
        int from = readInt("Enter your Account Number: ");
        int to = readInt("Enter the destination Account Number: ");
        double amount = readDouble("Enter amount to transfer: ");
        bank.transferAmount(from, to, amount);
        System.out.println("Amount transferred successfully.");
    }

    private void checkBalance() {
        int accNo = readInt("Enter Account Number: ");
        System.out.printf("Account Balance : %.2f%n", bank.findAccount(accNo).getBalance());
    }

    private void printStatement() {
        int id = readInt("Enter the Customer ID: ");
        bank.printStatement(id);
    }

    private void monthEnd() {
        bank.runMonthEnd();
        System.out.println("Month end completed.");
    }


    private Account createAccount(int type, String name, double balance) {
        return switch (type) {
            case 1 -> new SavingAccount(name, balance);
            case 2 -> new CurrentAccount(name, balance);
            default -> throw new IllegalArgumentException("Invalid account type: " + type);
        };
    }

    private String readText(String prompt) {
        System.out.println(prompt);
        return scan.nextLine();
    }

    private int readInt(String prompt) {
        if (!prompt.isEmpty()) System.out.println(prompt);
        String line = scan.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid whole number");
        }
    }

    private double readDouble(String prompt) {
        System.out.println(prompt);
        String line = scan.nextLine().trim();
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid amount");
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1 -> New customer (opens first account)");
        System.out.println("2 -> Existing customer opens another account");
        System.out.println("3 -> Deposit");
        System.out.println("4 -> Withdraw");
        System.out.println("5 -> Transfer");
        System.out.println("6 -> Check balance");
        System.out.println("7 -> Print statement");
        System.out.println("8 -> Run month end");
        System.out.println("0 -> Exit");
    }
}