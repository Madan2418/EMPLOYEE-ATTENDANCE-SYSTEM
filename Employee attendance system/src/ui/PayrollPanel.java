package ui;

import javax.swing.*;
import java.awt.*;
import models.Payroll; 

public class PayrollPanel extends JPanel {
    private JTextField empIdField;
    private JTextField monthField; 
    private JButton calculatePayrollButton;
    private JButton viewPayrollButton;

    public PayrollPanel() {
        setLayout(new FlowLayout());

        empIdField = new JTextField(10);
        monthField = new JTextField(10); 
        add(new JLabel("Employee ID:"));
        add(empIdField);
        
        add(new JLabel("Month (YYYY-MM):")); 
        add(monthField); 

        calculatePayrollButton = new JButton("Calculate Payroll");
        calculatePayrollButton.addActionListener(e -> calculatePayroll());
        add(calculatePayrollButton); 

        viewPayrollButton = new JButton("View Payroll");
        viewPayrollButton.addActionListener(e -> viewPayroll());
        add(viewPayrollButton); 
    }

    private void calculatePayroll() {
        try {
            int empId = Integer.parseInt(empIdField.getText());
            String month = monthField.getText(); 
            Payroll.calculatePayroll(empId, month); 
            JOptionPane.showMessageDialog(this, "Payroll calculated for Employee ID: " + empId + " for month: " + month);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while calculating payroll: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewPayroll() {
        try {
            int empId = Integer.parseInt(empIdField.getText());
            String month = monthField.getText();
        
            Payroll.viewPayroll(empId, month); 
            JOptionPane.showMessageDialog(this, "Viewing payroll for Employee ID: " + empId + " for month: " + month);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while viewing payroll: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
