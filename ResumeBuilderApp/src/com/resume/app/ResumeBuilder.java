package com.resume.app;

import javax.swing.*;   // ✅ Needed for Swing components
import java.awt.*;
import java.io.*;

public class ResumeBuilder extends JFrame {

    private JTextField nameField, emailField, phoneField, skillsField, eduField, expField;
    private JTextArea projectArea, previewArea;

    public ResumeBuilder() {
        setTitle("📄 Creative Resume Builder");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Root Panel with padding
        JPanel rootPanel = new JPanel();
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rootPanel.setBackground(new Color(245, 245, 245));
        setContentPane(rootPanel);

        JLabel title = new JLabel("🎯 Resume Builder");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Labels and Fields
        JLabel name = new JLabel("👤 Name:");
        JLabel email = new JLabel("📧 Email:");
        JLabel phone = new JLabel("📱 Phone:");
        JLabel skills = new JLabel("🛠 Skills:");
        JLabel edu = new JLabel("🎓 Education:");
        JLabel exp = new JLabel("🏢 Experience:");
        JLabel project = new JLabel("💡 Projects:");

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        skillsField = new JTextField(20);
        eduField = new JTextField(20);
        expField = new JTextField(20);

        // Multiline Project Area
        projectArea = new JTextArea(4, 20);
        projectArea.setLineWrap(true);
        projectArea.setWrapStyleWord(true);
        JScrollPane projectScroll = new JScrollPane(projectArea);

        // Buttons
        JButton generateBtn = new JButton("📝 Generate Resume");
        JButton saveBtn = new JButton("📥 Save as Text");
        JButton resetBtn = new JButton("🔄 Reset");

        // Style buttons
        Font btnFont = new Font("Segoe UI", Font.PLAIN, 14);
        generateBtn.setFont(btnFont);
        saveBtn.setFont(btnFont);
        resetBtn.setFont(btnFont);

        generateBtn.setBackground(new Color(144, 238, 144));
        saveBtn.setBackground(new Color(173, 216, 230));
        resetBtn.setBackground(new Color(255, 182, 193));

        // Resume preview
        previewArea = new JTextArea(12, 50);
        previewArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        previewArea.setBorder(BorderFactory.createTitledBorder("📄 Resume Preview"));
        previewArea.setLineWrap(true);
        previewArea.setWrapStyleWord(true);
        JScrollPane previewScroll = new JScrollPane(previewArea);

        // Layout
        GroupLayout layout = new GroupLayout(rootPanel);
        rootPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addComponent(title)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(name)
                        .addComponent(email)
                        .addComponent(phone)
                        .addComponent(skills)
                        .addComponent(edu)
                        .addComponent(exp)
                        .addComponent(project))
                    .addGroup(layout.createParallelGroup()
                        .addComponent(nameField)
                        .addComponent(emailField)
                        .addComponent(phoneField)
                        .addComponent(skillsField)
                        .addComponent(eduField)
                        .addComponent(expField)
                        .addComponent(projectScroll))
                    .addGroup(layout.createParallelGroup()
                        .addComponent(generateBtn)
                        .addComponent(saveBtn)
                        .addComponent(resetBtn))
                )
                .addComponent(previewScroll)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(title)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(nameField)
                    .addComponent(generateBtn))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(emailField)
                    .addComponent(saveBtn))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phone)
                    .addComponent(phoneField)
                    .addComponent(resetBtn))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(skills)
                    .addComponent(skillsField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(edu)
                    .addComponent(eduField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(exp)
                    .addComponent(expField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(project)
                    .addComponent(projectScroll))
                .addComponent(previewScroll)
        );

        // Actions
        generateBtn.addActionListener(e -> generateResume());
        saveBtn.addActionListener(e -> saveResume());
        resetBtn.addActionListener(e -> resetFields());

        setVisible(true);
    }

    private void generateResume() {
        StringBuilder resume = new StringBuilder();
        resume.append("========== RESUME ==========\n");
        resume.append("👤 Name: ").append(nameField.getText()).append("\n");
        resume.append("📧 Email: ").append(emailField.getText()).append("\n");
        resume.append("📱 Phone: ").append(phoneField.getText()).append("\n");
        resume.append("🛠 Skills: ").append(skillsField.getText()).append("\n");
        resume.append("🎓 Education: ").append(eduField.getText()).append("\n");
        resume.append("🏢 Experience: ").append(expField.getText()).append("\n");
        resume.append("💡 Projects:\n");

        // Split projects into lines and add bullet points
        String[] projects = projectArea.getText().split("\\n");
        for (String p : projects) {
            if (!p.trim().isEmpty()) {
                resume.append("   • ").append(p.trim()).append("\n");
            }
        }

        resume.append("============================\n");
        previewArea.setText(resume.toString());
    }

    private void saveResume() {
        try {
            FileWriter writer = new FileWriter("MyResume.txt");
            writer.write(previewArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "Resume saved as MyResume.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file!");
        }
    }

    private void resetFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        skillsField.setText("");
        eduField.setText("");
        expField.setText("");
        projectArea.setText("");
        previewArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResumeBuilder::new);
    }
}
