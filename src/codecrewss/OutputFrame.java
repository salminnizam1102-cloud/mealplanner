package codecrewss;
import javax.swing.*;
import java.awt.*;

	public class OutputFrame extends JFrame {
	    public OutputFrame(double bmr, double tdee, String name) {
	        setTitle("Results");
	        setSize(600, 500);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(new Color(54, 33, 25));

	       
	        JTextArea outputArea = new JTextArea();
	        outputArea.setEditable(false);
	        outputArea.setFont(new Font("Arial", Font.BOLD, 25));
	        outputArea.setBackground(new Color(54, 33, 25));
	        outputArea.setForeground(Color.WHITE);
	        outputArea.append("\t\t\t\tHello " + name + "!\n\n");
	        outputArea.append("\tBMR: " + String.format("%.2f kcal/day\n", bmr));
	        outputArea.append("\tTDEE: " + String.format("%.2f kcal/day\n\n", tdee));
	        outputArea.append(DatabaseConnection.getMealSuggestions(tdee));
	        outputArea.setCaretPosition(0);
	        JScrollPane scroll = new JScrollPane(outputArea);
	        panel.add(scroll, BorderLayout.CENTER);

	       
	        JPanel buttons = new JPanel();
	        buttons.setBackground(new Color(139, 69, 19));
	        JButton prev = new JButton("Previous ");
	        prev.setBackground(Color.WHITE);
	        prev.setForeground(new Color(139, 69, 19));
	        prev.addActionListener(e -> { dispose(); new UserInputFrame(); });
	        JButton visitAgain = new JButton("Visit Again ");
	        visitAgain.setBackground(Color.WHITE);
	        visitAgain.setForeground(new Color(139, 69, 19));
	        visitAgain.addActionListener(e -> { dispose(); new WelcomeFrame(); });
	        buttons.add(prev);
	        buttons.add(visitAgain);
	        panel.add(buttons, BorderLayout.SOUTH);

	        add(panel);
	        setVisible(true);
	    }
	}