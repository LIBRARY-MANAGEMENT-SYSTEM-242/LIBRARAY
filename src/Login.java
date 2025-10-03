import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
    public static void main(String[] args) {
        // Create Frame
        JFrame frame = new JFrame("Login - Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Center window

        // Panel with padding
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form panel (labels + fields)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        // Add components with layout
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(userText, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passText, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(loginBtn, gbc);

        // Add formPanel to main panel
        panel.add(formPanel, BorderLayout.CENTER);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Login Button Action
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passText.getPassword());

                if(username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose(); // close login window
                    Main.main(null); // open your Main.java menu
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password", 
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}