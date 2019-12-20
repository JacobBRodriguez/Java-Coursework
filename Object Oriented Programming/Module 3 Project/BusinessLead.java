import java.util.ArrayList;
import java.util.List;

public class BusinessLead extends BusinessEmployee {

    private int headCount;
    private double baseSalary;
    List<Employee> reports;

    public BusinessLead(String name) {
        super(name);
        baseSalary = this.getBaseSalary() * 2;
        headCount = 10;
        reports = new ArrayList<Employee>();
    }

    private void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    private int getHeadCount() {return this.headCount;}

    public boolean hasHeadCount() {
        if(reports.size() < headCount)
            return true;
        return false;
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam) {
        if(hasHeadCount()) {
            reports.add(e);

            double currentBonus = this.getBonusBudget();
            currentBonus = currentBonus + (1.1*e.getBaseSalary());
            this.setBonusBudget(currentBonus);

            supportTeam.setAssignedManager(this);
            return true;
        }
        else
            return false;
    }

    public boolean requestBonus(Employee e, double bonus) {
        if(reports.contains(e) && this.getBonusBudget() >= bonus) {
            double newBonusBudget = this.getBonusBudget();
            newBonusBudget -= bonus;
            this.setBonusBudget(newBonusBudget);
            return true;
        }
        else
            return false;
    }

    public boolean approveBonus(Employee e, double bonus) {
        // going to call accountant's approveBonus
        Employee techLead = e.getManager();
        for (int i = 0; i < reports.size();i++) {
            Employee tempAccount = reports.get(i);
            if( ((Accountant)tempAccount).getTeamSupported() == techLead ) {
                if( ((Accountant)tempAccount).approveBonus(bonus) )
                    return true;
                else
                    return false;
            }
        }
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
