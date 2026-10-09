package object_oriented_programming.assigment_problems;

public class CompanyEmployee {

    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        CompanyEmployee emp1 = new CompanyEmployee("Aman", 45000.0);
        CompanyEmployee emp2 = new CompanyEmployee("Priya", 52000.0);
        CompanyEmployee emp3 = new CompanyEmployee("Rohan", 48000.0);

        CompanyEmployee.printCompanyInfo();
    }
}
