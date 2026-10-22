package models;

import java.time.LocalDate;

public abstract class Account implements Transactable
{
    private final int accountNumber;
    private final String accountName;
    private final LocalDate createdAt;
    private static int lastAccountNumber = 1000;
    private double balance = 0;

    public abstract String getAccountType();
    public abstract double minimumAllowedBalance();
    public  Account(String accountName, double openingBalance)
    {
        if(accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("Account Name is invalid");
        }
        this.accountNumber = ++lastAccountNumber;
        this.accountName = accountName;
        this.createdAt = LocalDate.now();
        this.balance = openingBalance;
    }
    public int getAccountNumber()
    {
        return accountNumber;
    }
    public String getAccountName()
    {
        return accountName;
    }
    public LocalDate getCreated_at() {
        return createdAt;
    }
    public double  getBalance()
    {
        return balance;
    }
    @Override
    public void deposit(double amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Amount is invalid");
        }
        balance += amount;
    }

    @Override
    public void withdraw(double amount)
    {
        if(amount <= 0)
        {
            throw new IllegalArgumentException("Amount is invalid");
        }
        if(balance - amount < minimumAllowedBalance())
        {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }
    protected void addMonthlyInterest(double interest)
    {
        balance += interest;
    }
    protected void addMonthlyCharges(double charge)
    {
        balance -= charge;
    }




}
