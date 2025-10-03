import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class LibraryManager {
    private Connection conn;

    public LibraryManager() {
        conn = Database.connect();
    }

public void addStudentGUI() {
    JFrame frame = new JFrame("Student Registration");
    frame.setSize(400, 400);

    // ✅ Panel with padding (10px on all sides)
    JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

    JLabel idLabel = new JLabel("Student ID:");
    JTextField idField = new JTextField();

    JLabel nameLabel = new JLabel("Student Name:");
    JTextField nameField = new JTextField();

    JLabel courseLabel = new JLabel("Course:");
    JTextField courseField = new JTextField();

    JLabel branchLabel = new JLabel("Branch:");
    JTextField branchField = new JTextField();

    JLabel semLabel = new JLabel("Semester:");
    JTextField semField = new JTextField();

    JButton saveBtn = new JButton("Save");

    // ✅ Add components to panel
    panel.add(idLabel); panel.add(idField);
    panel.add(nameLabel); panel.add(nameField);
    panel.add(courseLabel); panel.add(courseField);
    panel.add(branchLabel); panel.add(branchField);
    panel.add(semLabel); panel.add(semField);
    panel.add(new JLabel()); panel.add(saveBtn);

    // Add panel to frame
    frame.add(panel);

    saveBtn.addActionListener(e -> {
        try {
            String sql = "INSERT INTO students (student_id, name, course, branch, semester) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setString(3, courseField.getText());
            stmt.setString(4, branchField.getText());
            stmt.setString(5, semField.getText());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Student Registered Successfully!");
            frame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    });

    frame.setVisible(true);
}

    // ✅ Add Book (GUI)
    public void addBookGUI() {
        JFrame frame = new JFrame("Add Book");
    frame.setSize(400, 300);
    frame.setLayout(new GridLayout(6, 2)); // Increase rows to 6

    JTextField idField = new JTextField();      // Added for Book ID
    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();
    JTextField yearField = new JTextField();

    JButton saveBtn = new JButton("Save");

    frame.add(new JLabel("Book ID:"));          // Label for ID
    frame.add(idField);
    frame.add(new JLabel("Title:"));
    frame.add(titleField);
    frame.add(new JLabel("Author:"));
    frame.add(authorField);
    frame.add(new JLabel("Year:"));
    frame.add(yearField);
    frame.add(new JLabel(""));
    frame.add(saveBtn);

    saveBtn.addActionListener(e -> {
        try {
            String sql = "INSERT INTO books (id, title, author, year) VALUES (?, ?, ?, ?)"; // Include ID
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));      // Set ID
            stmt.setString(2, titleField.getText());
            stmt.setString(3, authorField.getText());
            stmt.setInt(4, Integer.parseInt(yearField.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book added successfully!");
            frame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    });

    frame.setVisible(true);
}

    // ✅ View All Books (GUI)
    public void viewBooksGUI() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Title");
            model.addColumn("Author");
            model.addColumn("Year");
            model.addColumn("Available");
            model.addColumn("Issue Date");
            model.addColumn("Return Date");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available") ? "Yes" : "No",
                        rs.getString("issue_date"),
                        rs.getString("return_date")
                });
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(null, scrollPane, "All Books", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // ✅ Search Book (GUI)
    public void searchBookGUI() {
          JFrame frame = new JFrame("Search Book");
    frame.setSize(600, 400);
    frame.setLayout(new BorderLayout());

    // Top panel for input
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    JTextField searchField = new JTextField(20);
    JButton searchBtn = new JButton("Search");

    topPanel.add(new JLabel("Enter Book ID or Title:"));
    topPanel.add(searchField);
    topPanel.add(searchBtn);

    // Table for displaying results
    DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Title", "Author", "Year", "Available"}, 0);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(scrollPane, BorderLayout.CENTER);

    searchBtn.addActionListener(e -> {
        try {
            model.setRowCount(0); // Clear previous results

            String input = searchField.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter Book ID or Title.");
                return;
            }

            String sql;
            PreparedStatement stmt;

            if (input.matches("\\d+")) { // Search by ID
                sql = "SELECT * FROM books WHERE id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(input));
            } else { // Search by Title
                sql = "SELECT * FROM books WHERE title LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + input + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available") ? "Yes" : "No"
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    });

    frame.setVisible(true);
}


    // ✅ Delete Book (GUI)
    public void deleteBookGUI() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter Book ID to delete:");
            int id = Integer.parseInt(idStr);

            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Book Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No book found with that ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // ✅ Issue Book (GUI)
    public void issueBookGUI() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter Book ID to issue:");
            int id = Integer.parseInt(idStr);

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
                    JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Book is already issued.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No book found with that ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // ✅ Return Book (GUI)
    public void returnBookGUI() {
        try {
            String idStr = JOptionPane.showInputDialog("Enter Book ID to return:");
            int id = Integer.parseInt(idStr);

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
                    JOptionPane.showMessageDialog(null, "Book Returned Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Book is already available.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No book found with that ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // ✅ View Issued Books (GUI)
    public void viewIssuedBooksGUI() {
        try {
            String sql = "SELECT * FROM books WHERE available = FALSE";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Title", "Author", "Year", "Issue Date"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("issue_date")
                });
            }

            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Issued Books", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // ✅ View Overdue Books (GUI) - 7 days
    public void viewOverdueBooksGUI() {
        try {
            String sql = "SELECT * FROM books WHERE available = FALSE AND issue_date <= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID", "Title", "Author", "Year", "Issue Date"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("issue_date")
                });
            }

            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Overdue Books", JOptionPane.WARNING_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
