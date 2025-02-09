package ui; 

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Employee Attendance and Payroll System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Employee Management", new EmployeePanel());
        tabs.add("Attendance Tracking", new AttendancePanel());
        tabs.add("Payroll Management", new PayrollPanel());

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
