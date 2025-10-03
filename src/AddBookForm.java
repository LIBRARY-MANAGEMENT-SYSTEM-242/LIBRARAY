import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddBookForm extends JFrame {
    private JTextField idField, titleField, authorField, yearField;
    private JButton saveBtn, cancelBtn;

    public AddBookForm() {
        setTitle("Add Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("Year:"));
        yearField = new JTextField();
        panel.add(yearField);

        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");

        panel.add(saveBtn);
        panel.add(cancelBtn);

        add(panel);

        // Save button action
        saveBtn.addActionListener(e -> saveBook());

        // Cancel button action
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void saveBook() {
        try (Connection conn = Database.connect()) {
            String sql = "INSERT INTO books (id, title, author, year, available) VALUES (?, ?, ?, ?, TRUE)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idField.getText()));
            stmt.setString(2, titleField.getText());
            stmt.setString(3, authorField.getText());
            stmt.setInt(4, Integer.parseInt(yearField.getText()));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
