
 package codecrewss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("Welcome to Meal Planner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // ✅ Background image
        ImageIcon bgImage = new ImageIcon("src/images/mealplannerbg.jpg"); // <-- put your image path here
        Image scaledImage = bgImage.getImage().getScaledInstance(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                Image.SCALE_SMOOTH
        );
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setLayout(new BorderLayout());
        add(background);

        // ✅ Transparent overlay panel for text and button
        JPanel overlayPanel = new JPanel();
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(new BoxLayout(overlayPanel, BoxLayout.Y_AXIS));
        overlayPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ✅ Welcome text
        JLabel title = new JLabel("Welcome to Meal Planner", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 48));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(200, 0, 20, 0));
        overlayPanel.add(title);

        // ✅ Subtitle
        JLabel subtitle = new JLabel("Your daily healthy diet companion!", JLabel.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 26));
        subtitle.setForeground(Color.WHITE);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        overlayPanel.add(subtitle);

        // ✅ Button to move to Login Page
        JButton startButton = new JButton("Get Started →");
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(46, 204, 113));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        overlayPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        overlayPanel.add(startButton);

        background.add(overlayPanel, BorderLayout.CENTER);

        // ✅ Button ActionListener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // close welcome frame
                new LoginFrame(); // open login page
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomeFrame();
    }
}
