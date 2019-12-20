public abstract class Employee {

    private String name;
    private double baseSalary;
    private int employeeID;
    private static int employeeCount; //Global count of total number of employees made
    private Employee assignedManager;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        employeeCount++;
        employeeID = employeeCount;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean equals(Employee other) {
        return this.employeeID == other.employeeID;
    }

    public String toString() {
        return "" + employeeID +" "+name;
    }

    abstract String employeeStatus();

    public Employee getManager() {
        return assignedManager;
    }

    public void setAssignedManager(Employee manager) {
        this.assignedManager = manager;
    }

} // end class
