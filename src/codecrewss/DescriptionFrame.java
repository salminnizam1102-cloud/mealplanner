package codecrewss;
import javax.swing.*;
import java.awt.*;

public class DescriptionFrame extends JFrame {
    public DescriptionFrame() {
        setTitle("Meal Planner Description");
        setSize(600, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(
            "<html><center><font color='blue' size='+3'><b>ABOUT US</b></font><br><br>" +
            "This calculates your daily calorie needs (BMR & TDEE) and suggests meals.it Helps maintain healthy diet in busy life.<br>" +
            "<b>This meal planner provides a customizable, balanced daily meal plan designed for healthy adults.<br><br>"
            + "</b><font color='blue' size='+1'>MEAL DISTRIBUTION.</b></font><br><br>"
            + "</b>•Breakfast: Energizing start with fiber-rich carbs and nuts(35% of calories)<br>"
            + "</b>•Lunch: Lean protein salad for balanced midday fuel(40% of calories)<br>"
            + "</b>•Dinner:Light, low-calorie option to end the day(25% of calories)<br><br>"
            +"</b><font color='blue' size='+1'>FEATURES.</b></font><br><br>"
            +"-This plan is specially designed to keep your energy levels steady<br>"+
            "- Personalize nutrition based on age, weight, height, sex, activity.<br>" +
            "- Suggest meals from 1400-3800 kcal ranges.<br>" +
            "- Promote fitness through balanced eating.</html>", JLabel.CENTER);
        label.setFont(new Font("times new roman", Font.ITALIC, 16));
        label.setForeground(new Color(54,33,25));
        panel.add(label, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(54, 33, 25));
       
        JButton next = new JButton("continue");
        next.setBackground(Color.WHITE);
        next.setForeground(new Color(54,33, 25));
        next.addActionListener(e -> { dispose(); new UserInputFrame(); }); 
        buttons.add(next);
        panel.add(buttons, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}