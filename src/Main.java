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

            LibraryManager lm = new LibraryManager();
             // ✅ Background Image
        ImageIcon bgImage = new ImageIcon("backgroundlogin.jpg"); // <-- put your background image here
        JLabel bgLabel = new JLabel(bgImage);
        bgLabel.setLayout(new GridBagLayout()); // To place login panel in center
        frame.setContentPane(bgLabel);

            // ✅ Create a panel with GridLayout and padding
            JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
            panel.setBorder(new EmptyBorder(20, 40, 20, 40)); 
            // top, left, bottom, right spacing (you can adjust numbers)

            // ✅ Buttons
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

            // ✅ Button Actions
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

            // ✅ Add buttons to panel (not directly to frame)
            panel.add(studentBtn);
            panel.add(addBtn);
            panel.add(viewBtn);
            panel.add(searchBtn);
            panel.add(deleteBtn);
            panel.add(issueBtn);
            panel.add(returnBtn);
            panel.add(issuedBtn);
            panel.add(overdueBtn);
            panel.add(logoutBtn);

            // ✅ Add panel to frame
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}

}
