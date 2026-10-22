package models;

public interface Transactable
{
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    void monthEnd();
}
