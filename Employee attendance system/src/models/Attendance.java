package models;

import java.sql.*;
import java.util.Calendar;

public class Attendance {
    public static void markPresent(int empId) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis());

        try (Connection conn = DBConnection.connect()) {
            String checkSql = "SELECT date FROM attendance WHERE emp_id = ? ORDER BY date DESC LIMIT 1";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, empId);
                ResultSet checkRs = checkStmt.executeQuery();

                if (checkRs.next()) {
                    Date lastPresentDate = checkRs.getDate("date");
                    calendar.setTime(lastPresentDate);
                    calendar.add(Calendar.DATE, 1);
                } else {
                    calendar.setTime(today);
                }
            }

            Date nextDay;
            do {
                nextDay = new Date(calendar.getTimeInMillis());
                String nextCheckSql = "SELECT COUNT(*) AS count FROM attendance WHERE emp_id = ? AND date = ?";
                try (PreparedStatement nextCheckStmt = conn.prepareStatement(nextCheckSql)) {
                    nextCheckStmt.setInt(1, empId);
                    nextCheckStmt.setDate(2, nextDay);
                    ResultSet nextCheckRs = nextCheckStmt.executeQuery();

                    if (nextCheckRs.next() && nextCheckRs.getInt("count") == 0) {
                        String insertSql = "INSERT INTO attendance (emp_id, date, status) VALUES (?, ?, 'Present')";
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                            insertStmt.setInt(1, empId);
                            insertStmt.setDate(2, nextDay);
                            insertStmt.executeUpdate();
                            System.out.println("Marked employee ID " + empId + " as present for " + nextDay);
                        }
                        break;
                    } else {
                        calendar.add(Calendar.DATE, 1);
                    }
                }
            } while (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
