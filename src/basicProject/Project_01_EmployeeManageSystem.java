package basicProject;

import java.util.Scanner;

/*
Employee Management System [ --Version 1 ]->
Features:
    1. Add Employee
    2. Show All Employee
    3. Search Employee By ID
    4. Update Employee Details (Upcoming)
    5. Delete Employee (Upcoming)
    6. Count Employees Using Recursion (Upcoming)
    7. Employee Statistics (Upcoming)
Concepts Used:
    - Scanner Class
    - 2D Arrays
    - Methods
    - Loops
    - Switch Case
    - Recursion
    - Conditional Statements
    - Table Formatting using printf()
Future Improvements:
    - Search By Name
    - Data Validation
    - Employee Statistics
    - File Handling
    - Database Integration (JDBC)
*/
public class Project_01_EmployeeManageSystem {

    // Shared Scanner object used throughout the application
    private static final Scanner scanInput = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("+++++++ Welcome to Employee Management System +++++++++ \n");
        System.out.print("Please Enter Total Space For Employee -> ");
        int totalEmployees = scanInput.nextInt(); /// Row => Employee Record

//        System.out.print("Please Enter no. of Detail you wants -> ");
//        int n = scanInput.nextInt();
        int totalFields = 4; /// Column = Employee Attributes

        String[][] employeeDetail = new String[totalEmployees][totalFields];

///        Initialize column names for employee table
        getHeader(employeeDetail);

///        Main menu loop keeps running until user chooses Exit
        while (true) {
            System.out.printf(" %-28s %-28s %-28s \n", "1. Show All Employee Detail", "2. Add Employee", "3. Search employee");
            System.out.printf(" %-28s %-28s \n", "4. Delete Employee (Upcoming)", "5. Quit");
            System.out.print("\nChoose an Option : ");
            int option = scanInput.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Detail of All Employee : ");
                    showDetail(employeeDetail);
                    break;

                case 2:
                    System.out.println("Adding Employee Detail");
                    addEmployee(employeeDetail);
                    break;

                case 3:
                    System.out.println("Searching Employee by ID");
                    searchEmployeeById(employeeDetail);
                    break;

                case 5:
                      System.out.println("Thank you");
                      return;

                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public static void getHeader(String[][] employeeDetail) {
/*
        System.out.println("Added Detail no. 0 : ID");
        System.out.println("Added Detail no. 1 : NAME");
        for (int j = 2; j < employeeDetail[0].length; j++) {
            System.out.print("Add Detail No. " + j + " : ");
            employeeDetail[0][j] = scanInput.next();
        }
*/
//        System.out.println("Added Detail no. 0 : ID");
        employeeDetail[0][0] = "ID";
//        System.out.println("Added Detail no. 1 : NAME");
        employeeDetail[0][1] = "NAME";
//        System.out.print("Add Detail no. 2 :");
//        employeeDetail[0][2] = scanInput.next();
        employeeDetail[0][2] = "AGE";
//        System.out.print("Add Detail no. 3 :");
//        employeeDetail[0][3] = scanInput.next();
        employeeDetail[0][3] = "MOBILE NO.";
    }

    public static void showDetail(String[][] employeeDetail) {

        int m = employeeDetail.length; // for Obtain no. of ROWS
        int n = employeeDetail[0].length; // For Obtain no. of COLUMN

//        Display Employee Detail Header
        System.out.println("+-----+-----------------+------+---------------+");
//        for (int j = 0; j < n; j++) {
//            System.out.print("|  " + employeeDetail[0][j] + "  ");
//        }
        System.out.printf("| %-3s | %-15s | %-4s | %-13s |\n",
                "ID", "NAME", "AGE", "MOBILE NO.");
        System.out.println("+------+-----------------+------+---------------+");

//        Display employee Detail Values
        for (int i = 1; i < m; i++) { // Responsible for Iterating the Rows
            if (employeeDetail[i][1] == null) {
                continue;
            }
/*
            System.out.print("|  " + i + "  ");
            for (int j = 1; j < n; j++) { // Responsible for Printing Attribute of That Rows
                System.out.print("|  " + employeeDetail[i][j] + "  ");
            }
            System.out.println("  |");
*/
            System.out.printf("| %-4d | %-15s | %-4s | %-13s |\n", i,
                    employeeDetail[i][1],
                    employeeDetail[i][2],
                    employeeDetail[i][3]
            );
        }System.out.println("+------+-----------------+------+---------------+");

/**
        -> Still Remain
        * Skip Empty Rows -> IMPLEMENTED SUCCESSFULLY
        * Table Formating -> IMPLEMENTED SUCCESSFULLY
*/
    }

    public static void addEmployee(String[][] employeeDetail) {

        int totalEmployees = employeeDetail.length;
        int totalFields = employeeDetail[0].length;
        System.out.print("How Many Employee U Wants to Add: ");
        int numberOfEmployee = scanInput.nextInt();
        int insertedEmployees = 0; /// Count successfully inserted employee records

        for (int i = 1; i < totalEmployees; i++) {

//            Base case for How many You want To ADD Employee
            if (insertedEmployees == numberOfEmployee) {
                break;
            }
///            What If ith Index already Filled -> Skip already occupied employee slots
            if (employeeDetail[i][1] != null) {
///                i++; // for loop already increments [ i ] -> So rows can be skipped.
                continue;
            }
            else{
///                Collect all attributes of a single employee
                for (int j = 1; j < totalFields; j++) {
                    System.out.print("Please Enter " + employeeDetail[0][j] + " of " + (insertedEmployees+1) +" Employee : ");
                    if (j == 1) {
                        employeeDetail[i][j] = scanInput.next();
                    } else if (j == 2) {
                        employeeDetail[i][j] = String.valueOf(scanInput.nextByte());
                    } else {
                        employeeDetail[i][j] = String.valueOf(scanInput.nextLong());
                    }
                }insertedEmployees++; // After Filling all Column of ith ROWS
            }
        }
        System.out.println("Added " + insertedEmployees + " Employee SuccessFully.");
/**
        -> Still Remain
        * Check IF storage is Full
        * Validate Age
        * Validate Mobile Number
*/
    }

    public static void searchEmployeeById(String[][] employeeDetail) {
        int totalEmployees = employeeDetail.length; // for Obtain no. of ROWS
        int totalFields = employeeDetail[0].length; // For Obtain no. of COLUMN
        System.out.print("Enter Employee ID Wants to Search : ");
        int employeeId = scanInput.nextInt(); // Employee ID directly maps to array row index

///        Prevent invalid array access
        if (employeeId <= 0 || employeeId >= totalEmployees) {
            System.out.println("Employee Not Found....");
            return;
        }
        if (employeeDetail[employeeId][1] == null) {
            System.out.println("Employee Not Found...");
            return;
        }
//        Display Employee Detail Header
        for (int i = 0; i < 1; i++) {
            System.out.println("+------+--------+-------+--------------+");
            for (int j = 0; j < totalFields; j++) {
                System.out.print("|  " + employeeDetail[i][j] + "  ");
            }
            System.out.println("|");
        }
        System.out.println("+------+--------+-------+--------------+");
//        Display employee Detail Record
        for (int i = employeeId; i == employeeId; i++) {
            System.out.print("|  " + i + "  ");
            for (int j = 1; j < totalFields; j++) { // Responsible for Printing Attribute of That Rows
                System.out.print("|  " + employeeDetail[i][j] + "  ");
            }
            System.out.println("|");
        }System.out.println("+------+--------+-------+--------------+");

/**
 -> Still Remain
 □ Check if employee actually exists -> IMPLEMENTED SUCCESSFULLY
 □ Search by Name
 */
    }
}
