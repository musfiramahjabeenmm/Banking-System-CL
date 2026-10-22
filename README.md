# Banking System CLI

A menu-driven console banking application written in Java. It manages customers and their Savings and Current accounts, and demonstrates the four OOP pillars (encapsulation, inheritance, polymorphism, abstraction) in a layered design.

## Features

- Create new customers and open multiple accounts per customer
- Two account types, each with its own rules:
  - **Savings Account**: minimum balance of ₹500, earns 4% annual interest (credited monthly)
  - **Current Account**: overdraft limit of ₹5,000, ₹100 monthly maintenance charge
- Deposit, withdraw and transfer between accounts
- Balance check and full customer statement
- Month-end processing for all accounts through a single loop
- Input validation (positive amounts, valid names, 10-digit contact number, duplicate customer IDs)

## Project Structure

```
src/
├── Main.java                    # Menu-driven entry point (UI layer)
├── models/
│   ├── Transactable.java        # Interface: deposit, withdraw, monthEnd
│   ├── Account.java             # Abstract base class
│   ├── SavingAccount.java       # Extends Account
│   ├── CurrentAccount.java      # Extends Account
│   └── Customer.java            # Owns a list of accounts
└── services/
    └── BankServices.java        # Lookups, account opening, transfer, month-end, statements
```

## OOP Concepts Used

| Pillar | Where it appears |
|---|---|
| **Encapsulation** | `Account.balance` is private and changes only through validated `deposit`/`withdraw` and protected month-end helpers. `Customer.getAccounts()` returns a read-only view. |
| **Inheritance** | `SavingAccount` and `CurrentAccount` extend the abstract `Account` and reuse its fields and logic. |
| **Polymorphism** | `minimumAllowedBalance()` and `monthEnd()` are overridden per account type. `BankServices.runMonthEnd()` calls `a.monthEnd()` on every account with no type checks. |
| **Abstraction** | Abstract class `Account` plus the `Transactable` interface define what an account can do without fixing how. |

Other concepts: `static` account number counter (starts at 1001), `static final` constants for rates and limits, composition/aggregation between `Customer` and `Account`, and a service layer that coordinates objects without holding business rules.

## Design Decisions

- **Rules live in the model.** Minimum balance, overdraft and month-end behaviour belong to the account types, not the service.
- **The service coordinates.** `BankServices` finds customers and accounts, validates preconditions and delegates.
- **Transfers are all-or-nothing.** The withdrawal runs first, so if it fails the deposit never happens.

## How to Run

Requires JDK 17 or later.

```bash
# from the project root
javac -d out src/Main.java src/models/*.java src/services/*.java
java -cp out Main
```

Or open the project in IntelliJ IDEA and run `Main`.

## Menu

```
1 -> New customer (creates customer and first account)
2 -> Existing customer opens another account
3 -> Deposit
4 -> Withdraw
5 -> Transfer
6 -> Check balance
7 -> Print statement (by customer ID)
8 -> Run month end
0 -> Exit
```

## Sample Run

```
Enter the customer name: Musfira
Choose an account type: 1 (Savings)
Enter the opening Balance Amount: 2000
Savings Account Created Successfully...
The Account Number : 1001

Run month end...
Account Balance : 2006.67   (2000 x 0.04 / 12 interest added)
```

## What I Would Improve

- Use an `enum AccountType` instead of string labels
- Route all account creation through the service layer and generate customer IDs there
- Use `BigDecimal` instead of `double` for money
- Add a custom `InsufficientFundsException` and consistent error handling with `try/catch` in the menu
- Add transaction history per account
- Persist data in a file or database
- Add unit tests

## Author

Musfira Mahjabeen M
