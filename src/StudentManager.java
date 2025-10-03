import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentManager {
    private Connection conn;

    public StudentManager() {
        conn = Database.connect(); // ✅ Use your existing Database.java connection
    }

    public void addStudentGUI() {
        JFrame frame = new JFrame("Student Registration");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

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

        frame.add(idLabel); frame.add(idField);
        frame.add(nameLabel); frame.add(nameField);
        frame.add(courseLabel); frame.add(courseField);
        frame.add(branchLabel); frame.add(branchField);
        frame.add(semLabel); frame.add(semField);
        frame.add(new JLabel()); frame.add(saveBtn);

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
}
