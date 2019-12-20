import java.util.ArrayList;
import java.util.List;

public class TechnicalLead extends TechnicalEmployee {
    private int headCount;
    private double baseSalary;
    private List<Employee> reports;

    public TechnicalLead(String name) {
        super(name);
        baseSalary = this.getBaseSalary() * 1.3;
        headCount = 4;
        reports = new ArrayList<Employee>();
    }

    public boolean hadHeadCount() {
        if(reports.size() < headCount)
            return true;
        return false;
    }

    public boolean addReport(SoftwareEngineer e) {
        if(hadHeadCount()) {
            reports.add(e);
            e.setAssignedManager(this);
            return true;
        }
        else
            return false;
    }

    public List<Employee> getReports() {
        return this.reports;
    }

    public boolean approveCheckIn(SoftwareEngineer e) {
        // if employee reports to manager AND employee has access code
        if(reports.contains(e) && e.getCodeAccess()) {
            return true;
        }
        else
            return false;
    }

    public boolean requestBonus(Employee e, double bonus) {
        Employee bisLead = this.getManager();

        if( ((BusinessLead) bisLead).approveBonus(e, bonus) ) {
            return true;
        }
        else
            return false;
    }

    public String getTeamStatus() {
        String teamStatus;
        if(reports.size() == 0) {
            teamStatus = this.toString() + " and no direct reports yet";
        }
        else {
            teamStatus = this.toString() +" and is managing: \n";
            for(int i=0;i<reports.size();i++)
                teamStatus = teamStatus + reports.get(i).toString() +"\n";
        }
        return teamStatus;
    }
}
