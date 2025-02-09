# EMPLOYEE-ATTENDANCE-SYSTEM
This Java-based Employee Attendance System helps organizations track employee attendance, manage records, and calculate payroll efficiently. It is built using Java Swing for the graphical user interface and MySQL for backend storage.
Here’s a detailed description for your **Employee Attendance System** based on the provided Java and Swing code:  

---

# Employee Attendance System  

This **Java-based Employee Attendance System** helps organizations track employee attendance, manage records, and calculate payroll efficiently. It is built using **Java Swing** for the graphical user interface and **MySQL** for backend storage.  

## Features  

### 📌 **Employee Management**  
- Add new employees with details such as **name, position, base salary, and hire date**.  
- Retrieve a list of all employees from the database.  

### 📌 **Attendance Management**  
- Mark employees as **Present** in the attendance table.  
- Prevents duplicate attendance entries for the same employee on the same day.  
- Uses **JDBC** for database connectivity.  

### 📌 **Payroll Calculation**  
- Calculates salary based on **days present** in a given month.  
- Stores payroll data and allows **updating salary records** if needed.  
- Employees can **view their payroll details** for any month.  

## Technology Stack  
- **Java** – Core business logic and object-oriented structure.  
- **Swing** – GUI framework for building the user interface.  
- **JDBC** – Database connectivity with MySQL.  
- **MySQL** – Stores employee, attendance, and payroll data.  

## Database Structure  
- **employees** – Stores employee details (`emp_id`, `name`, `position`, `base_salary`, `hire_date`).  
- **attendance** – Records daily attendance (`emp_id`, `date`, `status`).  
- **payroll** – Stores salary details (`emp_id`, `month`, `base_salary`, `total_salary`).  

## Installation & Setup  
1. **Clone the Repository:**  
   ```sh
   git clone https://github.com/Madan2418/EMPLOYEE-ATTENDANCE-SYSTEM.git
   ```  
2. **Setup MySQL Database**:  
   - Create a database named `hr_system`.  
   - Import the provided SQL schema file (`database.sql`).  

3. **Configure Database Connection**:  
   - Edit `DBConnection.java` with your MySQL credentials.  

4. **Run the Application:**  
   - Open the project in **IntelliJ, Eclipse, or NetBeans**.  
   - Compile and execute the `main` function in the GUI class.  

## Code Overview  

### 1️⃣ **Database Connection (`DBConnection.java`)**  
Handles MySQL database connectivity using JDBC.  

### 2️⃣ **Employee Management (`Employee.java`)**  
- Allows adding employees to the database.  
- Fetches a list of all employees.  

### 3️⃣ **Attendance System (`Attendance.java`)**  
- **Marks employees as present**.  
- Prevents marking duplicate attendance for the same day.  

### 4️⃣ **Payroll System (`Payroll.java`)**  
- Calculates salary based on present days in a month.  
- Saves payroll details and allows salary updates.  
