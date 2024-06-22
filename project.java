

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

class Book {

    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String userName = "root";
    private final String passWord = "asmithasql";

    public void addBook(int b_id, String name, String author, int quantity_available) {
        String query = "INSERT INTO book (b_id, name, author, q_avai) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, b_id);
            st.setString(2, name);
            st.setString(3, author);
            st.setInt(4, quantity_available);

            int rows = st.executeUpdate();
            System.out.println("Number of rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    public void display() {
        String query = "SELECT * FROM book";

        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                int b_id = rs.getInt("b_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int q_avai = rs.getInt("q_avai");
                System.out.println("ID: " + b_id + ", Name: " + name + ", Author: " + author + ", Quantity Available: " + q_avai);
            }

        } catch (SQLException e) {
            System.err.println("Error displaying books: " + e.getMessage());
        }
    }

    public void checkout(int b_id, int id, LocalDate checkout, LocalDate checkin) {
        String query = "INSERT INTO transaction (b_id, id, checkout, checkin) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, b_id);
            st.setInt(2, id);
            st.setDate(3, java.sql.Date.valueOf(checkout));
            st.setDate(4, java.sql.Date.valueOf(checkin));

            int rows = st.executeUpdate();
            System.out.println("Number of rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error checking out book: " + e.getMessage());
        }
    }

    public void addUser(int id, String name) {
        String query = "INSERT INTO user (id, name) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url, userName, passWord);
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, id);
            st.setString(2, name);

            int rows = st.executeUpdate();
            System.out.println("Number of rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error adding user: " + e.getMessage());
        }
    }
}

public class project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;
        Book b = new Book();

        while (continueProgram) {
            System.out.println("Welcome");
            System.out.println("Enter your choice:");
            System.out.println("1. Add new book");
            System.out.println("2. Add new user");
            System.out.println("3. Display all books available");
            System.out.println("4. Book check-out");
            System.out.println("5. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the book id:");
                    int b_id = sc.nextInt();
                    System.out.println("Enter the book name:");
                    sc.nextLine(); // Consume newline
                    String name = sc.nextLine();
                    System.out.println("Enter the author name:");
                    String author = sc.nextLine();
                    System.out.println("Enter the quantity available:");
                    int quantity_available = sc.nextInt();
                    b.addBook(b_id, name, author, quantity_available);
                    break;
                case 2:
                    System.out.println("Enter the user id:");
                    int id = sc.nextInt();
                    System.out.println("Enter the user name:");
                    sc.nextLine(); // Consume newline
                    String user_name = sc.nextLine();
                    b.addUser(id, user_name);
                    break;
                case 3:
                    b.display();
                    break;
                case 4:
                    System.out.println("Enter the book id:");
                    int bid = sc.nextInt();
                    System.out.println("Enter the user id:");
                    int uid = sc.nextInt();
                    LocalDate currentdate = LocalDate.now();
                    LocalDate returndate = currentdate.plusDays(15);
                    b.checkout(bid, uid, currentdate, returndate);
                    break;
                case 5:
                    System.out.println("Do you want to exit? (yes/no)");
                    String exitChoice = sc.next().toLowerCase();
                    if (exitChoice.equals("yes")) {
                        continueProgram = false;
                        System.out.println("Exiting program...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        System.out.println("Press Enter to exit...");
        sc.nextLine(); // Consume any leftover newline character
        sc.nextLine(); // Wait for the user to press Enter to exit

        sc.close();
    }
}
