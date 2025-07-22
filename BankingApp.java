import java.util.*;

class Account {
    private String accNo;
    private String name;
    private double balance;

    public Account(String accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    public String getAccNo() { return accNo; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account No: " + accNo + ", Name: " + name + ", Balance: ₹" + balance;
    }
}

class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private int nextAccountNumber = 10001;

    public void createAccount(String name, double initialBalance) {
        String accNo = "ACC" + nextAccountNumber++;
        Account acc = new Account(accNo, name, initialBalance);
        accounts.put(accNo, acc);
        System.out.println("✅ Account created! Your Account Number is: " + accNo);
    }

    public void deposit(String accNo, double amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            acc.deposit(amount);
            System.out.println("✅ Deposit successful.");
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    public void withdraw(String accNo, double amount) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            if (acc.withdraw(amount)) {
                System.out.println("✅ Withdrawal successful.");
            } else {
                System.out.println("❌ Insufficient balance.");
            }
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    public void viewAccount(String accNo) {
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("❌ Account not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n===== Banking System Menu =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double bal = scanner.nextDouble();
                    scanner.nextLine(); // clear newline
                    bank.createAccount(name, bal);
                }
                case 2 -> {
                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.deposit(accNo, amount);
                }
                case 3 -> {
                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    bank.withdraw(accNo, amount);
                }
                case 4 -> {
                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();
                    bank.viewAccount(accNo);
                }
                case 5 -> System.out.println("👋 Exiting. Thank you!");
                default -> System.out.println("❗ Invalid choice.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
