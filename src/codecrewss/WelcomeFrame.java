package codecrewss;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        // Frame settings
        setTitle("Welcome to Meal Planner");
        setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel with coffee brown background
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(54, 33, 25)); // Coffee brown color

        // Heading
        JLabel heading = new JLabel("Welcome to Meal Planner");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.WHITE); // Text color for better contrast

        // Next button
        JButton nextButton = new JButton("explore"); 
        nextButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add action listener to button to call DescriptionFrame
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DescriptionFrame().setVisible(true);
                dispose();  // Close WelcomeFrame
            }
        });

        // Add components
        panel.add(heading, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

        // Add panel to frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame().setVisible(true);
        });
    }
}
