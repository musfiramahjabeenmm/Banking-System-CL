package services;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class BankServices
{
    List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer)
    {
        if(customer == null)
        {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        for(Customer c : customers)
        {
            if(customer.getCustomerId() == c.getCustomerId())
            {
                throw new IllegalArgumentException("Customer ID already exists");
            }
        }
        customers.add(customer);
    }
    public Customer findCustomer(int customerId)
    {
        for(Customer c : customers)
        {
            if (c.getCustomerId() == customerId)
            {
                return c;
            }
        }
        throw new IllegalArgumentException("Customer ID does not exist");

    }
    public Account findAccount(int AccountId)
    {
        for(Customer c : customers)
        {
            List<Account> accounts = c.getAccounts();
            for(Account a : accounts)
            {
                if(a.getAccountNumber() == AccountId)
                {
                    return a;
                }
            }
        }
        throw new IllegalArgumentException("Account ID does not exist");
    }
    public void openAccount(int customerId,String accountName ,int openingBalance, String accountType)
    {
        if(accountType.equals("Savings Account"))
        {
            Customer c = findCustomer(customerId);
            Account account = new SavingAccount(accountName, openingBalance);
            c.addAccount(account);
            System.out.println("Savings Account Created");
        }
        else {
            Customer c = findCustomer(customerId);
            Account account = new CurrentAccount(accountName, openingBalance);
            c.addAccount(account);
            System.out.println("Current Account Created");
        }
        return;
    }
    public void printStatement(int customerId)
    {
        if(customers == null)
        {
            throw new IllegalArgumentException("Customers cannot be null, pls enter the valid customer ID");
        }
        for(Customer c : customers)
        {
            if(c.getCustomerId() == customerId)
            {
                double overallBalance = 0;
                List<Account> accounts = c.getAccounts();
                System.out.println("Printing Bank Statement for Customer : "+c.getCustomerName());
                System.out.println("**************************************************************");
                for(Account a  : accounts)
                {
                    System.out.println("**************************************************************");
                    System.out.println("Account Number: "+a.getAccountNumber());
                    System.out.println("Account Name: "+a.getAccountName());
                    System.out.println("Account Holder : "+c.getCustomerName());
                    System.out.println("Account Type : "+a.getAccountType());
                    System.out.println("Account Balance : "+a.getBalance());
                    System.out.println("**************************************************************");
                    overallBalance += a.getBalance();
                }
                System.out.println(" OverAll Account Balance : "+overallBalance);
            }
        }
    }
    public void transferAmount(int fromAccount , int toAccount , double amount)
    {
        Account from = findAccount(fromAccount);
        Account to = findAccount(toAccount);
        from.withdraw(amount);
        to.deposit(amount);
    }
    public void runMonthEnd()
    {
        for(Customer c : customers)
        {
            for(Account a : c.getAccounts())
            {
                a.monthEnd();
            }
        }
    }

}
