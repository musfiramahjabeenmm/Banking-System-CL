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
        new BankMenu().run();

    }
}

