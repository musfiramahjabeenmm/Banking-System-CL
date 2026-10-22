package models;

public class SavingAccount extends Account
{
    private static final double minimum_balance = 500;

    public SavingAccount(String accountName , double openingBalance)
    {
        super(accountName,openingBalance);
        if(openingBalance < minimum_balance)
        {
            throw new IllegalArgumentException("Opening Balance cannot be less than Minimum Balance of "+minimum_balance);
        }

    }
    public double minimumAllowedBalance()
    {
        return minimum_balance;
    }
    @Override
    public String getAccountType()
    {
        return "Savings Account";
    }
    @Override
    public void monthEnd()
    {
        double interest = getBalance() * 0.04/12;
        addMonthlyInterest(interest);

    }
}
