package codecrewss;
import javax.swing.*;
import java.awt.*;

public class UserInputFrame extends JFrame {
    private JTextField nameField, ageField, weightField, heightField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> activityCombo;
    private boolean isMale;

    public UserInputFrame() {
        setTitle("User  Input");
        setSize(500, 450); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10)); 
        panel.setBackground(new Color(54, 33, 25));

       
        addLabel(panel, "Name:");
        nameField = addTextField(panel);

       
        addLabel(panel, "Age (years):"); ageField = addTextField(panel);
        addLabel(panel, "Weight (kg):"); weightField = addTextField(panel);
        addLabel(panel, "Height (cm):"); heightField = addTextField(panel);

        addLabel(panel, "Sex:");
        ButtonGroup sexGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male"); femaleRadio = new JRadioButton("Female");
        maleRadio.addActionListener(e -> isMale = true);
        femaleRadio.addActionListener(e -> isMale = false);
        sexGroup.add(maleRadio); sexGroup.add(femaleRadio);
        maleRadio.setSelected(true); 
        JPanel sexPanel = new JPanel(); sexPanel.add(maleRadio); sexPanel.add(femaleRadio);
        panel.add(sexPanel);

        addLabel(panel, "Activity Level:");
        activityCombo = new JComboBox<>(new String[]{"Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Super Active"});
        panel.add(activityCombo);

        JButton submit = new JButton("Submit");
        submit.setBackground(Color.WHITE);
        submit.setForeground(new Color(54, 33, 25));
        submit.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter your name!");
                    return;
                }
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double bmr = BMRCalculator.calculateBMR(age, weight, height, isMale);
                double tdee = BMRCalculator.calculateTDEE(bmr, (String) activityCombo.getSelectedItem());
                dispose();
                new OutputFrame(bmr, tdee, name); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numbers for age, weight, height!");
            }
        });

        JButton prev = new JButton("Previous");
        prev.setBackground(Color.WHITE);
        prev.setForeground(new Color(54, 33, 25));
        prev.addActionListener(e -> { dispose(); new DescriptionFrame(); });

        panel.add(new JLabel()); panel.add(submit);
        panel.add(new JLabel()); panel.add(prev);

        add(panel);
        setVisible(true);
    }

    private JLabel addLabel(JPanel p, String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        p.add(l);
        return l;
    }

    private JTextField addTextField(JPanel p) {
        JTextField tf = new JTextField(10);
        p.add(tf);
        return tf;
    }
}