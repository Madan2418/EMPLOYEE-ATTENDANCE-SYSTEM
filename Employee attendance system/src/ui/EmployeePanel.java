package ui; 
import models.Employee; 

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {
    private JTextField nameField, positionField, salaryField;
    private JButton addButton;

    public EmployeePanel() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Base Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> addEmployee());
        add(addButton);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String position = positionField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        Employee emp = new Employee();
        emp.setName(name);
        emp.setPosition(position);
        emp.setBaseSalary(salary);
        Employee.addEmployee(emp);
        
        JOptionPane.showMessageDialog(this, "Employee added successfully!");
    }
}
