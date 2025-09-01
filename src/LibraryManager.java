import java.sql.*;
import java.util.Scanner;

public class LibraryManager {
    private Connection conn;
    private Scanner sc;

    public LibraryManager() {
        conn = Database.connect();
        sc = new Scanner(System.in);
    }

    public void addBook() {
        try {
            System.out.print("Enter title: ");
            String title = sc.nextLine();
            System.out.print("Enter author: ");
            String author = sc.nextLine();
            System.out.print("Enter year: ");
            int year = sc.nextInt();
            sc.nextLine();

            String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("year") + " | " +
                    (rs.getBoolean("available") ? "Available" : "Issued") + " | " +
                    rs.getString("issue_date") + " | " +
                    rs.getString("return_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBook() {
        try {
            System.out.print("Enter keyword to search (title/author): ");
            String keyword = sc.nextLine();

            String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("year") + " | " +
                    (rs.getBoolean("available") ? "Available" : "Issued") + " | " +
                    rs.getString("issue_date") + " | " +
                    rs.getString("return_date")
                );
                found = true;
            }
            if (!found) {
                System.out.println("No matching books found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook() {
        try {
            System.out.print("Enter book ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("No book found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook() {
        try {
            System.out.print("Enter book ID to issue: ");
            int id = sc.nextInt();
            sc.nextLine();

            String checkSql = "SELECT available FROM books WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("available")) {
                    String sql = "UPDATE books SET available = FALSE, issue_date = CURDATE(), return_date = NULL WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued.");
                }
            } else {
                System.out.println("No book found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter book ID to return: ");
            int id = sc.nextInt();
            sc.nextLine();

            String checkSql = "SELECT available FROM books WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                if (!rs.getBoolean("available")) {
                    String sql = "UPDATE books SET available = TRUE, return_date = CURDATE() WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is already marked as available.");
                }
            } else {
                System.out.println("No book found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewIssuedBooks() {
    try {
        String sql = "SELECT * FROM books WHERE available = FALSE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        boolean found = false;
        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") + " | " +
                rs.getInt("year") + " | Issued | " +
                rs.getString("issue_date") + " | " +
                rs.getString("return_date")
            );
            found = true;
        }

        if (!found) {
            System.out.println("No books are currently issued.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void viewOverdueBooks() {
    try {
        String sql = "SELECT * FROM books WHERE available = FALSE AND issue_date <= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        boolean found = false;
        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") + " | " +
                rs.getInt("year") + " | Issued | " +
                rs.getString("issue_date") + " | " +
                rs.getString("return_date")
            );
            found = true;
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
