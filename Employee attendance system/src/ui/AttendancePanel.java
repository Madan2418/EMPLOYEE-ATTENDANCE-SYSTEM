package ui; 
import models.Attendance;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendancePanel extends JPanel {
    private JTextField empIdField;
    private JButton markPresentButton;

    public AttendancePanel() {
        empIdField = new JTextField(10);
        markPresentButton = new JButton("Mark Present");

        add(new JLabel("Employee ID:"));
        add(empIdField);
        add(markPresentButton);

        markPresentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markPresent();
            }
        });
    }

    private void markPresent() {
        try {
            int empId = Integer.parseInt(empIdField.getText());
            Attendance.markPresent(empId); 
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
