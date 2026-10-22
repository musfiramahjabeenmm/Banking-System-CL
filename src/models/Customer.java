package models;
import  java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private  final int customerId;
    private final String customerName;
    private final String customerContactNumber;
    List<Account> accounts = new ArrayList<Account>();

    public  Customer(int customerId, String customerName, String customerContactNumber)
    {
        if(customerName == null || customerName.isBlank())
        {
            throw new IllegalArgumentException("Customer Name cannot be null or blank");
        }
        if(customerContactNumber == null || !customerContactNumber.matches("\\d{10}"))
        {
            throw new IllegalArgumentException("Customer Contact Number cannot be null and it should be exactly 10 digits");
        }
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContactNumber = customerContactNumber;
    }
    public void addAccount(Account account)
    {
        if(account == null)
        {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
    }
    public int getCustomerId()
    {
        return customerId;
    }
    public String getCustomerName()
    {
        return customerName;
    }
    public String getCustomerContactNumber()
    {
        return customerContactNumber;
    }
    public List<Account> getAccounts()
    {
        return accounts;
    }

}
