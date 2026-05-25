package service;

import dao.UserDAO;
import model.*;
import java.util.*;

public class BankService {

    private UserDAO dao = new UserDAO();

    public void register(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        System.out.println("1 Savings  2 Current");
        int type = sc.nextInt();

        Account acc = (type == 1)
                ? new SavingsAccount(username, 5000)
                : new CurrentAccount(username, 5000);

        dao.register(new User(username, password, acc));
    }

    public User login(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.next();

        System.out.print("Password: ");
        String p = sc.next();

        return dao.login(u, p);
    }

    public void bankingMenu(Scanner sc, User user) {
        Account acc = user.getAccount();
        int choice;

        do {
            System.out.println("\n1 Deposit 2 Withdraw 3 Display 4 Logout");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    acc.deposit(sc.nextDouble());
                    dao.updateBalance(user.getUsername(), acc.getBalance());
                    break;

                case 2:
                    acc.withdraw(sc.nextDouble());
                    dao.updateBalance(user.getUsername(), acc.getBalance());
                    break;

                case 3:
                    acc.display();
                    break;
            }
        } while (choice != 4);
    }

    // Admin
    public boolean adminLogin(Scanner sc) {
        return sc.next().equals("admin") && sc.next().equals("admin123");
    }

    public void adminMenu(Scanner sc) {
        int choice;

        do {
            System.out.println("\n1 View Users 2 Delete User 3 Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (User u : dao.getAllUsers()) {
                        System.out.println(u.getUsername());
                        u.getAccount().display();
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    dao.deleteUser(sc.next());
                    break;
            }
        } while (choice != 3);
    }
}
