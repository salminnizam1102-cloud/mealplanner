package codecrewss;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - Meal Planner");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main background panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(54, 33, 25)); // Dark brown

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Container for the login box
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(245, 245, 245)); // Light cream
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 2),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        GridBagConstraints lg = new GridBagConstraints();
        lg.insets = new Insets(10, 10, 10, 10);
        lg.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Welcome to Meal Planner", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(54, 33, 25));
        lg.gridx = 0; lg.gridy = 0; lg.gridwidth = 2;
        loginPanel.add(titleLabel, lg);

        // Username label & field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(new Color(54, 33, 25));
        lg.gridx = 0; lg.gridy = 1; lg.gridwidth = 1;
        loginPanel.add(userLabel, lg);

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        lg.gridx = 1; lg.gridy = 1;
        loginPanel.add(usernameField, lg);

        // Password label & field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setForeground(new Color(54, 33, 25));
        lg.gridx = 0; lg.gridy = 2;
        loginPanel.add(passLabel, lg);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        lg.gridx = 1; lg.gridy = 2;
        loginPanel.add(passwordField, lg);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        JButton loginButton = createStyledButton("Login");
        JButton registerButton = createStyledButton("Sign up");
        JButton backButton = createStyledButton("Back");

        // ✅ LOGIN
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!");
                return;
            }

            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username + ".");
                dispose();
                new DescriptionFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });

        // ✅ REGISTER
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now login.");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists or error occurred!");
            }
        });

        // ⬅ BACK
        backButton.addActionListener(e -> {
            dispose();
            new WelcomeFrame();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        lg.gridx = 0; lg.gridy = 3; lg.gridwidth = 2;
        loginPanel.add(buttonPanel, lg);

        // Add the login panel to the center
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(loginPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    // Helper method to create professional buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(139, 69, 19));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(160, 82, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(139, 69, 19));
            }
        });
        return button;
    }

    // ✅ AUTHENTICATION
    private boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ REGISTRATION
    private boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // --- For quick testing ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
