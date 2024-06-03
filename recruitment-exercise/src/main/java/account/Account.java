package account;

import java.util.*;
public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<String> transactions;

    public Account(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount, String label) {
        balance += amount;
        transactions.add(label + ": +" + amount);  // Record the transaction with a label
    }

    public void withdraw(double amount, String label) {
        balance -= amount;
        transactions.add(label + ": -" + "Balance: "+balance);  // Record the transaction with a label
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String generateStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Name: ").append(ownerName).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        statement.append("Balance: $").append(String.format("%.2f", balance)).append("\n");
        transactions.forEach(transaction -> statement.append(transaction).append("\n"));
        return statement.toString();
    }
}
