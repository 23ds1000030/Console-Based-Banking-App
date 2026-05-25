package model;

public class SavingsAccount extends Account {

    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }
}
