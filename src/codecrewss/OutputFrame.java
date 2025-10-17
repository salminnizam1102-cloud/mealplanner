package codecrewss;

import javax.swing.*;
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
        setSize(600, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        BackgroundPanel panel = new BackgroundPanel("path/to/your/image.jpg");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 25));
        outputArea.setOpaque(false); // Make text area transparent
        outputArea.setForeground(Color.WHITE);
        outputArea.append("\t\t\t\tHello " + name + "!\n\n");
        outputArea.append("\tBMR: " + String.format("%.2f kcal/day\n", bmr));
        outputArea.append("\tTDEE: " + String.format("%.2f kcal/day\n\n", tdee));
        outputArea.append(DatabaseConnection.getMealSuggestions(tdee));
        outputArea.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(139, 69, 19));
        JButton prev = new JButton("Previous ");
        prev.setBackground(Color.WHITE);
        prev.setForeground(new Color(139, 69, 19));
        prev.addActionListener(e -> {
            dispose();
            new UserInputFrame();
        });

        JButton visitAgain = new JButton("Visit Again ");
        visitAgain.setBackground(Color.WHITE);
        visitAgain.setForeground(new Color(139, 69, 19));
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
	}
