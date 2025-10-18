package codecrewss;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class OutputFrame extends JFrame {

    // Custom panel to draw the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public OutputFrame(double bmr, double tdee, String name) {
        // Frame setup
        setTitle("Results");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background panel setup
        BackgroundPanel panel = new BackgroundPanel("image/bg.jpg");

        // JTextPane (supports alignment & styling)
        JTextPane outputPane = new JTextPane();
        outputPane.setEditable(false);
        outputPane.setFont(new Font("Arial", Font.BOLD, 25));
        outputPane.setOpaque(false);
        outputPane.setForeground(Color.BLACK);

        // Center text horizontally
        StyledDocument doc = outputPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(center, Color.BLACK);
        StyleConstants.setFontSize(center, 25);
        StyleConstants.setFontFamily(center, "Arial");
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Insert content
        try {
            doc.insertString(doc.getLength(), "\nHello " + name + "!\n\n", center);
            doc.insertString(doc.getLength(),
                    "BMR: " + String.format("%.2f kcal/day\n", bmr), center);
            doc.insertString(doc.getLength(),
                    "TDEE: " + String.format("%.2f kcal/day\n\n", tdee), center);

            // Fetch meal suggestions from your DatabaseConnection class
            String mealSuggestions = DatabaseConnection.getMealSuggestions(tdee);
            doc.insertString(doc.getLength(), mealSuggestions, center);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // Scroll pane for text
        JScrollPane scroll = new JScrollPane(outputPane);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panel.add(scroll, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(139, 69, 19)); // Brown background

        JButton prev = new JButton("Previous");
        prev.setBackground(Color.WHITE);
        prev.setForeground(new Color(139, 69, 19));
        prev.setFont(new Font("Arial", Font.BOLD, 18));
        prev.addActionListener(e -> {
            dispose();
            new UserInputFrame();
        });

        JButton visitAgain = new JButton("Visit Again");
        visitAgain.setBackground(Color.WHITE);
        visitAgain.setForeground(new Color(139, 69, 19));
        visitAgain.setFont(new Font("Arial", Font.BOLD, 18));
        visitAgain.addActionListener(e -> {
            dispose();
            new WelcomeFrame();
        });

        JButton logout = new JButton("Logout");
        logout.setBackground(Color.WHITE);
        logout.setForeground(new Color(128, 0, 0));
        logout.setFont(new Font("Arial", Font.BOLD, 18));
        logout.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        buttons.add(prev);
        buttons.add(visitAgain);
        buttons.add(logout);
        panel.add(buttons, BorderLayout.SOUTH);

        // Add everything to frame
        add(panel);
        setVisible(true);
    }
}

