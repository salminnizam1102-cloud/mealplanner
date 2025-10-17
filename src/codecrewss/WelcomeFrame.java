package codecrewss;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
       
        setTitle("Welcome to Meal Planner");
        setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); 

        
        JLabel heading = new JLabel("Welcome to Meal Planner");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.BLACK);

     
        JButton nextButton = new JButton("explore"); 
        nextButton.setFont(new Font("Arial", Font.PLAIN, 18));

       
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DescriptionFrame().setVisible(true);
                dispose();  
            }
        });

       
        panel.add(heading, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

       
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame().setVisible(true);
        });
    }
}

