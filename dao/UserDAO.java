package dao;

import model.*;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class UserDAO {

    public void register(User user) {
        String sql = "INSERT INTO users(username,password,account_type,balance) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, (user.getAccount() instanceof SavingsAccount) ? "SAVINGS" : "CURRENT");
            ps.setDouble(4, user.getAccount().getBalance());

            ps.executeUpdate();
            System.out.println("✅ Registered successfully!");

        } catch (Exception e) {
            System.out.println("❌ Registration failed!");
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String type = rs.getString("account_type");
                double balance = rs.getDouble("balance");

                Account acc = type.equals("SAVINGS")
                        ? new SavingsAccount(username, balance)
                        : new CurrentAccount(username, balance);

                return new User(username, password, acc);
            }

        } catch (Exception e) {
            System.out.println("Login error!");
        }

        return null;
    }

    public void updateBalance(String username, double balance) {
        String sql = "UPDATE users SET balance=? WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, balance);
            ps.setString(2, username);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Update failed!");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String type = rs.getString("account_type");
                double balance = rs.getDouble("balance");

                Account acc = type.equals("SAVINGS")
                        ? new SavingsAccount(username, balance)
                        : new CurrentAccount(username, balance);

                list.add(new User(username, password, acc));
            }

        } catch (Exception e) {
            System.out.println("Fetch error!");
        }

        return list;
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
            System.out.println("✅ User deleted!");

        } catch (Exception e) {
            System.out.println("Delete failed!");
        }
    }
}