public class TechnicalEmployee extends Employee {
    private int checkIns;
    private String name;

    public TechnicalEmployee(String name) {
        super(name, 75000.00);
    }

    public String employeeStatus() {
        return super.toString() + " has "+checkIns+ " successful check ins";
    }
}
