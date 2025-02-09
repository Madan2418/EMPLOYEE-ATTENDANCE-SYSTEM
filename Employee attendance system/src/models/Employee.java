package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Employee {
    private int empId;
    private String name;
    private String position;
    private double baseSalary;
    private String hireDate;

    // Constructors
    public Employee() {
    }

    public Employee(int empId, String name, String position, double baseSalary, String hireDate) {
        this.empId = empId;
        this.name = name;
        this.position = position;
        this.baseSalary = baseSalary;
        this.hireDate = hireDate;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public static void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, position, base_salary, hire_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getPosition());
            stmt.setDouble(3, emp.getBaseSalary());
            stmt.setString(4, emp.getHireDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setPosition(rs.getString("position"));
                emp.setBaseSalary(rs.getDouble("base_salary"));
                emp.setHireDate(rs.getString("hire_date"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
