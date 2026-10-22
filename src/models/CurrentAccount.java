package models;

public class CurrentAccount extends Account
{
    private static final double overDraftLimit = 5000;
    private static final double monthlyMaintenanceCharge = 100;

    public CurrentAccount(String accountName,double openingBalance)
    {
        super(accountName,openingBalance);
    }
    @Override
    public String getAccountType()
    {
        return "Current Account";
    }
    @Override
    public double minimumAllowedBalance()
    {
        return -overDraftLimit;
    }
    public void monthEnd()
    {
        addMonthlyCharges(monthlyMaintenanceCharge);
    }

}
