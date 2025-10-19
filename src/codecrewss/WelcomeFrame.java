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

     
        ImageIcon bgImage = new ImageIcon("src/image/welcome.jpg"); 
        Image scaledImage = bgImage.getImage().getScaledInstance(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                Image.SCALE_SMOOTH
        );

        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setLayout(new GridBagLayout());
        add(background);

    
        JPanel overlayPanel = new JPanel();
        overlayPanel.setOpaque(true);
        overlayPanel.setBackground(new Color(0, 0, 0, 150));
        overlayPanel.setLayout(new BoxLayout(overlayPanel, BoxLayout.Y_AXIS));
        overlayPanel.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));

     
        JLabel title = new JLabel("Welcome to Meal Planner", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 54));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        overlayPanel.add(title);

     
        JLabel subtitle = new JLabel("Plan smart. Eat healthy. Live better.", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        subtitle.setForeground(new Color(220, 220, 220));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        overlayPanel.add(subtitle);

        overlayPanel.add(Box.createRigidArea(new Dimension(0, 60)));

      
        JButton startButton = new JButton("Get Started →");
        styleButton(startButton);
        overlayPanel.add(startButton);

        
        background.add(overlayPanel);

     
        startButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }

    private void styleButton(JButton button) {
        Color baseColor = new Color(52, 152, 219);   
        Color hoverColor = new Color(41, 128, 185);  

        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(14, 36, 14, 36));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 60));

       
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(baseColor.darker(), 2, true),
                BorderFactory.createEmptyBorder(12, 36, 12, 36)
        ));

        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor);
            }
        });
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeFrame::new);
    }
}