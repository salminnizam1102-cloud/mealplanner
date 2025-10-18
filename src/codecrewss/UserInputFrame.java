package codecrewss;

import javax.swing.*;
import java.awt.*;

public class UserInputFrame extends JFrame {
    private JTextField nameField, ageField, weightField, heightField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> activityCombo;
    private boolean isMale = true;

    public UserInputFrame() {
        setTitle("User Input - Meal Planner");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Background panel =====
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(54, 33, 25)); // dark brown

        // ===== Form container =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245)); // light background
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 2),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Title =====
        JLabel title = new JLabel("Enter Your Details", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(54, 33, 25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1; // reset for normal fields

        // ===== Name =====
        addLabel(formPanel, gbc, "Name:", 1);
        nameField = addTextField(formPanel, gbc, 1);

        // ===== Age =====
        addLabel(formPanel, gbc, "Age (years):", 2);
        ageField = addTextField(formPanel, gbc, 2);

        // ===== Weight =====
        addLabel(formPanel, gbc, "Weight (kg):", 3);
        weightField = addTextField(formPanel, gbc, 3);

        // ===== Height =====
        addLabel(formPanel, gbc, "Height (cm):", 4);
        heightField = addTextField(formPanel, gbc, 4);

        // ===== Gender =====
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sexLabel.setForeground(new Color(54, 33, 25));
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(sexLabel, gbc);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        maleRadio.setBackground(new Color(245, 245, 245));
        femaleRadio.setBackground(new Color(245, 245, 245));
        maleRadio.setFont(new Font("Arial", Font.PLAIN, 15));
        femaleRadio.setFont(new Font("Arial", Font.PLAIN, 15));
        maleRadio.setSelected(true);
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleRadio);
        sexGroup.add(femaleRadio);

        maleRadio.addActionListener(e -> isMale = true);
        femaleRadio.addActionListener(e -> isMale = false);

        JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sexPanel.setOpaque(false);
        sexPanel.add(maleRadio);
        sexPanel.add(femaleRadio);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(sexPanel, gbc);

        // ===== Activity Level =====
        JLabel activityLabel = new JLabel("Activity Level:");
        activityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        activityLabel.setForeground(new Color(54, 33, 25));
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(activityLabel, gbc);

        activityCombo = new JComboBox<>(new String[]{
                "Sedentary",
                "Lightly Active",
                "Moderately Active",
                "Very Active",
                "Super Active"
        });
        activityCombo.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(activityCombo, gbc);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton submitButton = createStyledButton("Submit");
        JButton prevButton = createStyledButton("Previous");

        buttonPanel.add(submitButton);
        buttonPanel.add(prevButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        // ===== Add form to background =====
        backgroundPanel.add(formPanel);
        add(backgroundPanel);

        // ===== Button Actions =====
        submitButton.addActionListener(e -> handleSubmit());
        prevButton.addActionListener(e -> {
            dispose();
            new DescriptionFrame();
        });

        setVisible(true);
    }

    // Helper to create labels
    private void addLabel(JPanel panel, GridBagConstraints gbc, String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(54, 33, 25));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);
    }

    // Helper to create text fields
    private JTextField addTextField(JPanel panel, GridBagConstraints gbc, int y) {
        JTextField field = new JTextField(12);
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        gbc.gridx = 1;
        gbc.gridy = y;
        panel.add(field, gbc);
        return field;
    }

    // Styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(139, 69, 19));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(160, 82, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(139, 69, 19));
            }
        });
        return button;
    }

    // ===== Handle Submit Logic =====
    private void handleSubmit() {
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
            JOptionPane.showMessageDialog(this, "Enter valid numbers for age, weight, and height!");
        }
    }

    // --- For quick testing only ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInputFrame::new);
    }
}
