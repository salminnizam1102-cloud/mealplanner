package codecrewss;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("Welcome to Meal Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("<html><center><h1 style='color:#2C3E50;'>Welcome to Meal Planner</h1>"
                + "<h3 style='color:#16A085;'>Your daily healthy diet assistant</h3></center></html>", JLabel.CENTER);
        panel.add(title, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Go to Login Page →");
        nextBtn.setFont(new Font("Arial", Font.BOLD, 18));
        nextBtn.setBackground(new Color(46, 204, 113));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // close welcome frame
                new LoginFrame(); // open login frame
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(nextBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeFrame());
    }
}