package model;

public class CurrentAccount extends Account {

    private double overdraftLimit = 1000;

    public CurrentAccount(String name, double balance) {
        super(name, balance);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }
}
