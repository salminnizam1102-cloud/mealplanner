
package codecrewss;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - Meal Planner");
        setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(54, 33, 25));
        panel.setBorder(BorderFactory.createEmptyBorder(150, 500, 150, 500));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(54, 33, 25));

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(new Color(54, 33, 25));

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(54, 33, 25));

        // ✅ LOGIN
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username);
                dispose();
                new DescriptionFrame(); // go to next page after login
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

        // ⬅ BACK BUTTON
        backButton.addActionListener(e -> {
            dispose();
            new WelcomeFrame();
        });

        panel.add(userLabel); panel.add(usernameField);
        panel.add(passLabel); panel.add(passwordField);
        panel.add(loginButton); panel.add(registerButton);
        panel.add(new JLabel()); panel.add(backButton);

        add(panel);
        setVisible(true);
    }

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
}
