package models;

import java.sql.*;

public class Payroll {
    public static void calculatePayroll(int empId, String month) {
        try (Connection conn = DBConnection.connect()) {
            // Fetch the employee's base salary
            String salarySql = "SELECT base_salary FROM employees WHERE emp_id = ?";
            try (PreparedStatement salaryStmt = conn.prepareStatement(salarySql)) {
                salaryStmt.setInt(1, empId);
                ResultSet salaryRs = salaryStmt.executeQuery();
                if (salaryRs.next()) {
                    double baseSalary = salaryRs.getDouble("base_salary");
                    
                    String[] monthParts = month.split("-"); 
                    int monthNum = Integer.parseInt(monthParts[1]);
                    int yearNum = Integer.parseInt(monthParts[0]);
        
                    String attendanceSql = "SELECT COUNT(*) AS present_days " +
                                            "FROM attendance " +
                                            "WHERE emp_id = ? " +
                                            "AND status = 'Present' " +
                                            "AND MONTH(date) = ? " +
                                            "AND YEAR(date) = ?";
                    try (PreparedStatement attendanceStmt = conn.prepareStatement(attendanceSql)) {
                        attendanceStmt.setInt(1, empId);
                        attendanceStmt.setInt(2, monthNum);
                        attendanceStmt.setInt(3, yearNum);
                        ResultSet attendanceRs = attendanceStmt.executeQuery();
                        if (attendanceRs.next()) {
                            int presentDays = attendanceRs.getInt("present_days");
                            double totalSalary = (baseSalary / 30) * presentDays;  // Calculate total salary
    
                            int formattedMonth = Integer.parseInt(monthParts[0] + monthParts[1]);
                            String payrollSql = "INSERT INTO payroll (emp_id, month, base_salary, total_salary) VALUES (?, ?, ?, ?) " +
                                                "ON DUPLICATE KEY UPDATE base_salary = ?, total_salary = ?";
                            try (PreparedStatement payrollStmt = conn.prepareStatement(payrollSql)) {
                                payrollStmt.setInt(1, empId);
                                payrollStmt.setInt(2, formattedMonth);
                                payrollStmt.setDouble(3, baseSalary);
                                payrollStmt.setDouble(4, totalSalary); // Insert total_salary
                                payrollStmt.setDouble(5, baseSalary); 
                                payrollStmt.setDouble(6, totalSalary); // Update total_salary if exists
                                payrollStmt.executeUpdate();
                                System.out.println("Payroll record created or updated for Employee ID: " + empId + " for month: " + formattedMonth);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void viewPayroll(int empId, String month) {
        String[] monthParts = month.split("-");
        int formattedMonth = Integer.parseInt(monthParts[0] + monthParts[1]); 
    
        String sql = "SELECT * FROM payroll WHERE emp_id = ? AND month = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, formattedMonth); 
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt("emp_id"));
                System.out.println("Month: " + rs.getInt("month")); 
                System.out.println("Base Salary: " + rs.getDouble("base_salary"));
                System.out.println("Total Salary: " + rs.getDouble("total_salary"));
            } else {
                System.out.println("No payroll found for this employee and month.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}