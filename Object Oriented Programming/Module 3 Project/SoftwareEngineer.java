public class SoftwareEngineer extends TechnicalEmployee {

    private int checkIns; // ********* Note will calling super's employeeStatus get this
                            // checkIns?
    private boolean accessCode = false;

    public SoftwareEngineer(String name) {
        super(name);
    }

    public boolean getCodeAccess() {
        return accessCode;
    }

    public void setCodeAccess(boolean access) {
        accessCode = access;
    }

    public int getSuccessfulCheckIns() {return checkIns;}

    public boolean checkInCode() {
        Employee manager = this.getManager();
        if( ((TechnicalLead) manager).approveCheckIn(this) ) {
            checkIns++;
            return true;
        }
        else {
            this.setCodeAccess(false);
            return false;
        }
    }

}
