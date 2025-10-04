import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setLocationRelativeTo(null); // Center the frame

            LibraryManager lm = new LibraryManager();

            // ✅ Background Image
            ImageIcon bgImage = new ImageIcon("backgroundlogin.jpg"); // Ensure path is correct
            JLabel bgLabel = new JLabel(bgImage);
            bgLabel.setLayout(new GridBagLayout()); // To center content
            frame.setContentPane(bgLabel);

            // ✅ Button Panel (with GridLayout and padding)
            JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10)); // 1 column, vertical buttons
            panel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Padding
            panel.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white

            // ✅ Create buttons
            JButton studentBtn = new JButton("Student Registration");
            JButton addBtn = new JButton("Add Book");
            JButton viewBtn = new JButton("View Books");
            JButton searchBtn = new JButton("Search Book");
            JButton deleteBtn = new JButton("Delete Book");
            JButton issueBtn = new JButton("Issue Book");
            JButton returnBtn = new JButton("Return Book");
            JButton issuedBtn = new JButton("View Issued Books");
            JButton overdueBtn = new JButton("View Overdue Books");
            JButton logoutBtn = new JButton("Logout");

            // ✅ Style buttons consistently
            JButton[] buttons = { studentBtn, addBtn, viewBtn, searchBtn, deleteBtn,
                                  issueBtn, returnBtn, issuedBtn, overdueBtn, logoutBtn };

            for (JButton button : buttons) {
                button.setFocusPainted(false);
                button.setBackground(new Color(70, 130, 180)); // Steel Blue
                button.setForeground(Color.WHITE);
                button.setPreferredSize(new Dimension(200, 35));
                panel.add(button); // Add to panel
            }

            // ✅ Add actions
            studentBtn.addActionListener(e -> lm.addStudentGUI());
            addBtn.addActionListener(e -> lm.addBookGUI());
            viewBtn.addActionListener(e -> lm.viewBooksGUI());
            searchBtn.addActionListener(e -> lm.searchBookGUI());
            deleteBtn.addActionListener(e -> lm.deleteBookGUI());
            issueBtn.addActionListener(e -> lm.issueBookGUI());
            returnBtn.addActionListener(e -> lm.returnBookGUI());
            issuedBtn.addActionListener(e -> lm.viewIssuedBooksGUI());
            overdueBtn.addActionListener(e -> lm.viewOverdueBooksGUI());
            logoutBtn.addActionListener(e -> {
                frame.dispose();
                Login.main(null);
            });

            // ✅ Add button panel to background (centered)
            bgLabel.add(panel, new GridBagConstraints());

            // ✅ Show the frame
            frame.setVisible(true);
        });
    }
}
