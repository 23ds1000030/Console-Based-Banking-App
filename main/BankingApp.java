package main;

import service.BankService;
import model.User;
import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        int choice;

        do {
            System.out.println("\n1 Register 2 Login 3 Admin 4 Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    service.register(sc);
                    break;

                case 2:
                    User user = service.login(sc);
                    if (user != null) {
                        service.bankingMenu(sc, user);
                    } else {
                        System.out.println("Invalid login!");
                    }
                    break;

                case 3:
                    System.out.print("Admin username & password: ");
                    if (service.adminLogin(sc)) {
                        service.adminMenu(sc);
                    } else {
                        System.out.println("Invalid admin!");
                    }
                    break;
            }

        } while (choice != 4);

        sc.close();
    }
}
