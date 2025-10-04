import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
    public static void main(String[] args) {
        // Frame
        JFrame frame = new JFrame("Login - Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);

        // ✅ Background Image
        ImageIcon bgImage = new ImageIcon("library_bg.jpg"); // <-- put your background image here
        JLabel bgLabel = new JLabel(bgImage);
        bgLabel.setLayout(new GridBagLayout()); // To place login panel in center
        frame.setContentPane(bgLabel);

        // ✅ Login Panel with rounded border
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(350, 200));
        loginPanel.setBackground(new Color(255, 255, 255, 200)); // White with transparency

        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true), // blue border
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        // Add components
        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(userText, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passText, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        loginPanel.add(loginBtn, gbc);

        // ✅ Add login panel to background
        bgLabel.add(loginPanel, new GridBagConstraints());

        frame.setVisible(true);

        // ✅ Login button action
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose();
                    Main.main(null);
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid Username or Password",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
