package codecrewss;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class OutputFrame extends JFrame {

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
        
        setTitle("Results");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        BackgroundPanel panel = new BackgroundPanel("image/outputimage.jpg");

        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setContentAreaFilled(false); 
        logoutButton.setBorderPainted(false);     
        logoutButton.setFocusPainted(false); 
        logoutButton.setForeground(new Color(139, 69, 19));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame(); 
            }
        });
        topPanel.add(logoutButton);
        panel.add(topPanel, BorderLayout.NORTH);
        JTextPane outputPane = new JTextPane();
        outputPane.setEditable(false);
        outputPane.setFont(new Font("Arial", Font.BOLD, 25));
        outputPane.setOpaque(false);
        outputPane.setForeground(Color.BLACK);

       
        StyledDocument doc = outputPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(center, Color.BLACK);
        StyleConstants.setFontSize(center, 25);
        StyleConstants.setFontFamily(center, "Arial");
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

       
        try {
            doc.insertString(doc.getLength(), "\nHello " + name + "!\n\n", center);
            doc.insertString(doc.getLength(),
                    "BMR: " + String.format("%.2f kcal/day\n", bmr), center);
            doc.insertString(doc.getLength(),
                    "TDEE: " + String.format("%.2f kcal/day\n\n", tdee), center);
            String mealSuggestions = DatabaseConnection.getMealSuggestions(tdee);
            doc.insertString(doc.getLength(), mealSuggestions, center);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        JScrollPane scroll = new JScrollPane(outputPane);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panel.add(scroll, BorderLayout.CENTER);

        
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(139, 69, 19)); 

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

        buttons.add(prev);
        buttons.add(visitAgain);
        panel.add(buttons, BorderLayout.SOUTH);

      
        add(panel);
        setVisible(true);
    }

   
}