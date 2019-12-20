import java.util.List;

public class Accountant extends BusinessEmployee {

    private TechnicalLead teamSupported;

    public Accountant(String name) {
        super(name);
        this.setBonusBudget(0.0);
    }

    public TechnicalLead getTeamSupported() {
        return this.teamSupported;
    }

    public void supportTeam(TechnicalLead lead) {

        double bonusAmount = 0.0;
        List<Employee> techReports = lead.getReports();
        this.teamSupported = lead;

        for(int i=0;i < techReports.size(); i++) {
            bonusAmount += techReports.get(i).getBaseSalary();
        }

        bonusAmount = (bonusAmount*.10) + bonusAmount;
        this.setBonusBudget(bonusAmount);
    }

    public boolean approveBonus(double bonus) {

        if(getTeamSupported() == null) {
            return false;
        }

        else if(bonus > getBonusBudget()) {
            return false;
        }

        else
            return true;
    }

    public String employeeStatus() {
        return super.toString() + " is supporting " + this.getTeamSupported();
    }
}
