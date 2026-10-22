import models.Account;
import models.CurrentAccount;
import models.Customer;
import models.SavingAccount;
import services.BankServices;

import java.time.LocalDate;
import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        BankServices bank = new BankServices();

            System.out.println("**************************************************");
            System.out.println("Welcome to SD BANK");
            System.out.println("**************************************************");


            Scanner scan = new Scanner(System.in);
            int choice;
            int customerId = 0;

            do {
                System.out.println("If you are a new customer and what to open a account type ->1");
                System.out.println("If you are a existing customer and what to open a account type ->2");
                System.out.println("If you want to deposit amount type ->3");
                System.out.println("If you want to withdraw amount type ->4");
                System.out.println("If you want to transfer amount type ->5");
                System.out.println("If you want to check balance type ->6");
                System.out.println("If you want to print Statement type ->7");
                System.out.println("If you want to run monthend type ->8");
                System.out.println("Enter 0 to exit");

                choice = scan.nextInt();
                scan.nextLine();

                String accName;
                int accType;
                int accNumber;
                double openingBalance;

                switch (choice) {
                    case 1:

                        System.out.println("Creating Customer ID.....");
                        customerId += 1;
                        System.out.println("The customer ID :" + customerId);
                        System.out.println("**---dont forget the customer ID---**");
                        System.out.println("Enter the customer name: ");
                        String name = scan.nextLine();
                        System.out.println("Enter the customer contact: ");
                        String contactNumber = scan.nextLine();

                        Customer customer = new Customer(customerId, name, contactNumber);
                        bank.addCustomer(customer);

                        System.out.println("Processing to create Account....");
                        System.out.println("***************************************");
                        System.out.println("Choose an account type : ");
                        System.out.println("Enter 1 : Savings Account");
                        System.out.println("Enter 2 : Current Amount");

                        accType = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter Account Name / Holder Name as Account Name : ");
                        accName = scan.nextLine();
                        System.out.println("Enter the opening Balance Amount : ");
                        openingBalance = scan.nextDouble();
                        scan.nextLine();

                        switch (accType) {
                            case 1:
                                Account savingAcc = new SavingAccount(accName, openingBalance);
                                customer.addAccount(savingAcc);
                                System.out.println("Savings Account Created Successfully...");
                                System.out.println("The Account Number : "+savingAcc.getAccountNumber());
                                System.out.println("*****************************THANK YOU*****************************");
                                break;

                            case 2:
                                Account currAcc = new CurrentAccount(accName, openingBalance);
                                customer.addAccount(currAcc);
                                System.out.println("Current Account Created Successfully...");
                                System.out.println("The Account Number : "+currAcc.getAccountNumber());
                                System.out.println("*****************************THANK YOU*****************************");
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;

                        }
                        break;

                    case 2:
                        System.out.println("Enter the customerId: ");
                        int id = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter the Account name: ");
                        accName = scan.nextLine();
                        System.out.println("Enter the account Type ou prefer to open: ");
                        System.out.println("Savings Account -> 1");
                        System.out.println("Current Account -> 2");
                        accType = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter Opening Balance Amount : ");
                        openingBalance = scan.nextDouble();
                        scan.nextLine();
                        Customer exisitingCustomer = bank.findCustomer(id);

                        switch (accType) {
                            case 1:
                                Account savingAcc = new SavingAccount(accName, openingBalance);
                                exisitingCustomer.addAccount(savingAcc);
                                System.out.println("Savings Account Created Successfully...");
                                System.out.println("The Account Number : "+savingAcc.getAccountNumber());
                                System.out.println("*****************************THANK YOU*****************************");
                                break;
                            case 2:
                                Account currAcc = new CurrentAccount(accName, openingBalance);
                                exisitingCustomer.addAccount(currAcc);
                                System.out.println("Current Account Created Successfully...");
                                System.out.println("The Account Number : "+ currAcc.getAccountNumber());
                                System.out.println("*****************************THANK YOU*****************************");
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;

                        }
                        break;
                    case 3:
                        System.out.println("--------------Customer Amount Desposition-----------");
                        System.out.println("Enter Account Number: ");
                        accNumber = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter Deposite Amount");
                        double depositeAmount = scan.nextDouble();
                        scan.nextLine();
                        Account despositeAccount = bank.findAccount(accNumber);
                        despositeAccount.deposit(depositeAmount);
                        System.out.println("Amount Deposited Successfully...");
                        break;
                    case 4:
                        System.out.println("--------------Customer Amount Withdrawal-----------");
                        System.out.println("Enter Account Number: ");
                        accNumber = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter WithDraw Amount");
                        double withDrawAmount = scan.nextDouble();
                        scan.nextLine();
                        Account withDrawalAccount = bank.findAccount(accNumber);
                        withDrawalAccount.withdraw(withDrawAmount);
                        System.out.println("Amount Withdrawal Successfully...");
                        break;
                    case 5:
                        System.out.println("--------------Amount Transfer Mode-----------");
                        System.out.println("Enter  Account Number: ");
                        accNumber = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter the tranfer Account Number: ");
                        int tranferAccountNumber = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter amount to transfer: ");
                        double transferAmount = scan.nextDouble();
                        scan.nextLine();
                        bank.transferAmount(accNumber, tranferAccountNumber, transferAmount);
                        System.out.println("Amount Transfer Successfully...");
                        break;
                    case 6:
                        System.out.println("--------------Checking for Account Balance-----------");
                        System.out.println("Enter Account Number: ");
                        accNumber = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Checking for Balance....");
                        Account accountBalance = bank.findAccount(accNumber);
                        double balance = accountBalance.getBalance();
                        System.out.println("Account Balance : " + balance);
                        System.out.println("Balance Calculated Successfully...");
                        break;
                    case 7:
                        System.out.println("Enter the Customer Number: ");
                        int customerID = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Printing the Statement...");
                        bank.printStatement(customerID);
                        System.out.println("---------------------------------");
                        System.out.println("Statement Printed Successfully...");
                        System.out.println("---------------------------------");
                        break;
                    case 8:
                        System.out.println("Running month end...");
                        bank.runMonthEnd();
                        System.out.println("Month end completed.");
                        break;
                    default:
                        System.out.println("Invalid Input");
                        break;

                }
            }while(choice!=0);
            System.exit(0);











    }
}

