package basicProject;

import java.util.Scanner;

/*
Employee Management System [ --Version 1 ]->
Features:
    1. Add Employee
    2. Show All Employee
    3. Search Employee By ID
    4. Update Employee Details
    5. Delete Employee
    6. Count Employees Using Recursion
    7. Employee Statistics
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
            System.out.printf(" %-28s %-28s %-28s \n", "4. Update employee", "5. Delete Employee", "6. Count Employee");
            System.out.printf(" %-28s %-28s \n", "7. Statistics Of Employee", "8. Quit");
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

                case 4:
                    System.out.println("Updating Employee Details");
                    updateEmployee(employeeDetail, totalEmployees,totalFields);
                    break;

                case 5:
                    System.out.println("Deleting Operation Running");
                    deleteEmployee(employeeDetail, totalEmployees, totalFields);
                    break;

                case 6:
                    System.out.println("Counting Operation Running");
                    int count = countEmployeeRecursively(employeeDetail, 1);
                    System.out.println("Total Employee Is -> " + count);
                    break;

                case 7:
                    System.out.println("Statistical Operation Running");
                    employeeStatistics(employeeDetail);
                    break;

                case 8:
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

        int maxEmployees = employeeDetail.length; // for Obtain no. of ROWS
        int totalFields = employeeDetail[0].length; // For Obtain no. of COLUMN

//        Display Employee Detail Header
        System.out.println("+-----+-----------------+------+---------------+");
//        for (int j = 0; j < n; j++) {
//            System.out.print("|  " + employeeDetail[0][j] + "  ");
//        }
        System.out.printf("| %-3s | %-15s | %-4s | %-13s |\n",
                "ID", "NAME", "AGE", "MOBILE NO.");
        System.out.println("+-----+-----------------+------+---------------+");

//        Display employee Detail Values
        for (int i = 1; i < maxEmployees; i++) { // Responsible for Iterating the Rows
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
            System.out.printf("| %-3d | %-15s | %-4s | %-13s |\n", i,
                    employeeDetail[i][1],
                    employeeDetail[i][2],
                    employeeDetail[i][3]
            );
        }System.out.println("+-----+-----------------+------+---------------+");

/**
        -> Still Remain
        * Skip Empty Rows -> IMPLEMENTED SUCCESSFULLY
        * Table Formating -> IMPLEMENTED SUCCESSFULLY
*/
    }

    public static void addEmployee(String[][] employeeDetail) {

        int maxEmployees = employeeDetail.length;
        int totalFields = employeeDetail[0].length;
        System.out.print("How Many Employees Do You Want To Add? : ");
        int numberOfEmployee = scanInput.nextInt();
        int insertedEmployees = 0; /// Count successfully inserted employee records

        for (int i = 1; i < maxEmployees; i++) {

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
        int maxEmployees = employeeDetail.length; // for Obtain no. of ROWS
        System.out.print("Enter Employee ID Wants to Search : ");
        int employeeId = scanInput.nextInt(); // Employee ID directly maps to array row index

///        Prevent invalid array access
        if (employeeId <= 0 || employeeId >= maxEmployees) {
            System.out.println("Employee Not Found....");
            return;
        }
        if (employeeDetail[employeeId][1] == null) {
            System.out.println("Employee Not Found...");
            return;
        }
//        Display Employee Detail Header
        for (int i = 0; i < 1; i++) {
            System.out.println("+-----+-----------------+------+---------------+");
            /*
            for (int j = 0; j < totalFields; j++) {
                System.out.print("|  " + employeeDetail[i][j] + "  ");
            }
            System.out.println("|");
            */
            System.out.printf("| %-3s | %-15s | %-4s | %-13s |\n",
                    "ID", "NAME", "AGE", "MOBILE NO.");
        }
        System.out.println("+-----+-----------------+------+---------------+");
//        Display employee Detail Record
        for (int i = employeeId; i == employeeId; i++) {
/*
            System.out.print("|  " + i + "  ");
            for (int j = 1; j < totalFields; j++) { // Responsible for Printing Attribute of That Rows
                System.out.print("|  " + employeeDetail[i][j] + "  ");
            }
            System.out.println("|");
*/
            System.out.printf("| %-3d | %-15s | %-4s | %-13s |\n", i,
                    employeeDetail[i][1],
                    employeeDetail[i][2],
                    employeeDetail[i][3]
            );
        }System.out.println("+-----+-----------------+------+---------------+");

/**
        -> Still Remain
        □ Check if employee actually exists -> IMPLEMENTED SUCCESSFULLY
        □ Search by Name
*/
    }

    public static void updateEmployee(String[][] employeeDetail, int totalEmployees, int totalFields) {

        System.out.print("Enter Employee ID wants to Update : ");
        int employeeIdToUpdate = scanInput.nextInt();

        if (employeeIdToUpdate <= 0 || employeeIdToUpdate >= totalEmployees) {
            System.out.println("No Such ID Available...");
        } else if (employeeDetail[employeeIdToUpdate][1] == null) {
            System.out.println("Employee Not available...");
        } else {
            System.out.println("Employee Found...");
            System.out.print("Which Detail Wants to Update : ");
            String wantsToUpdate = scanInput.next();
            if (wantsToUpdate.equalsIgnoreCase("NAME")) {
                System.out.print("Plzz Enter New NAME : ");
                String newName = scanInput.next();
                employeeDetail[employeeIdToUpdate][1] = newName;
                System.out.println("Employee's Name Updated");

            } else if (wantsToUpdate.equalsIgnoreCase("AGE")) {
                System.out.print("Plzz Enter New AGE : ");
                String newAge = String.valueOf(scanInput.nextByte());
                employeeDetail[employeeIdToUpdate][2] = newAge;
                System.out.println("Employee's Age Updated");

            }else if (wantsToUpdate.equalsIgnoreCase("MOBILE NO")) {
                System.out.print("Plzz Enter New MOBILE NO. : ");
                String newMobile = String.valueOf(scanInput.nextLong());
                employeeDetail[employeeIdToUpdate][3] = newMobile;
                System.out.println("Employee's Mobile No. Updated");

            }else System.out.println("Invalid Detail");
        }
    }

    public static void deleteEmployee(String[][] employeeDetail, int totalEmployees, int totalFields) {

        System.out.print("Enter Employee ID which wants to Delete : ");
        int employeeIdToDelete = scanInput.nextInt() ;

        if (employeeIdToDelete <= 0 || employeeIdToDelete >= totalEmployees) {
            System.out.println("No Such ID Available...");
        }
        else if (employeeDetail[employeeIdToDelete][1] == null) {
            System.out.println("Employee Not available...");
        }
        else{
            for (int j = 1; j < totalFields; j++) {
                employeeDetail[employeeIdToDelete][j] = null;
            }
            System.out.println("Deleted Data Of Employee ID : " + employeeIdToDelete);
        }
    }

    public static int countEmployeeRecursively(String[][] employeeDetail, int rows) {

//        Base case if counting pointer pointing to last Rows
        if (rows == employeeDetail.length) {
            return 0;
        }

//        Updating case If counting Pointer to NOT NULL Rows
        if (employeeDetail[rows][1] != null) {
            return 1 + countEmployeeRecursively(employeeDetail, rows + 1);
        }

//        Non-updating case If counting Pointer to NULL Rows
        return countEmployeeRecursively(employeeDetail, rows + 1);
    }

    public static int countEmployee(String[][] employeeDetail) {
        int totalEmployees = 0;

        for (int i = 1; i < employeeDetail.length; i++) {
            if (employeeDetail[i][1] != null) {
                totalEmployees = 1 + totalEmployees;
            }
        }
        return totalEmployees;
    }
    public static void employeeStatistics(String[][] employeeDetail) {
        int sumOfEmployeesAge = 0;
        int highestAge = 0;
        int lowestAge = 0;

        System.out.println("Counting Start...");
        int total = countEmployee(employeeDetail);
        System.out.println("Total Employees : " + total + "\nCounting Complete");

        for (int i = 1; i < employeeDetail.length; i++) {
            if (employeeDetail[i][1] != null) {
                sumOfEmployeesAge = sumOfEmployeesAge + Integer.parseInt(employeeDetail[i][2]);
            }
        }
        if (total == 0) {
            System.out.println("No Data Available");
            return;
        }else{
            int average = Math.round((sumOfEmployeesAge)/total);
            System.out.println("Average AGE of Employees is : " + average);
        }
/**
        -> Still Remain
        □ Total Employees -> IMPLEMENTED SUCCESSFULLY
        □ Average Age -> IMPLEMENTED SUCCESSFULLY
        □ Oldest Employee
        □ Youngest Employee
*/
    }
}
