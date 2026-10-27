import java.util.ArrayList;
import java.util.Scanner;

class BankAccount
{
    private int accountNumber;
    private String ownerName;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(int accountNumber, String ownerName,
                       double initialBalance)
    {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        transactions = new ArrayList<>();

        transactions.add("Account created with balance: "
                         + initialBalance);
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if (amount <= 0)
        {
            System.out.println("Amount must be positive.");
            return;
        }

        balance += amount;
        transactions.add("Deposited: " + amount);

        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount)
    {
        if (amount <= 0)
        {
            System.out.println("Amount must be positive.");
        }
        else if (amount > balance)
        {
            System.out.println("Insufficient balance.");
        }
        else
        {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);

            System.out.println("Withdrawal successful!");
        }
    }

    public void showTransactions()
    {
        System.out.println("\nTransaction History:");

        for (String transaction : transactions)
        {
            System.out.println(transaction);
        }
    }

    public void displayAccount()
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Balance: " + balance);
    }
}

public class Main
{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<BankAccount> accounts = new ArrayList<>();

    static BankAccount findAccount(int accountNumber)
    {
        for (BankAccount account : accounts)
        {
            if (account.getAccountNumber() == accountNumber)
            {
                return account;
            }
        }

        return null;
    }

    static void createAccount()
    {
        System.out.print("Enter account number: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (findAccount(number) != null)
        {
            System.out.println("Account number already exists.");
            return;
        }

        System.out.print("Enter owner name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit: ");
        double amount = sc.nextDouble();

        if (amount < 0)
        {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        accounts.add(new BankAccount(number, name, amount));
        System.out.println("Account created successfully!");
    }

    public static void main(String[] args)
    {
        int choice;

        do
        {
            System.out.println("\n===== BANK MANAGEMENT =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    createAccount();
                    break;

                case 2:
                    if (accounts.isEmpty())
                    {
                        System.out.println("No accounts available.");
                    }

                    for (BankAccount account : accounts)
                    {
                        account.displayAccount();
                        System.out.println("----------------");
                    }
                    break;

                case 3:
                case 4:
                case 5:
                    System.out.print("Enter account number: ");
                    int number = sc.nextInt();

                    BankAccount account = findAccount(number);

                    if (account == null)
                    {
                        System.out.println("Account not found.");
                        break;
                    }

                    if (choice == 3)
                    {
                        System.out.print("Enter deposit amount: ");
                        account.deposit(sc.nextDouble());
                    }
                    else if (choice == 4)
                    {
                        System.out.print("Enter withdrawal amount: ");
                        account.withdraw(sc.nextDouble());
                    }
                    else
                    {
                        account.showTransactions();
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}
