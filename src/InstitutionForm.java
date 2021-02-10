public class InstitutionForm {

    private int instFormID;
    private int instID;
    private String text;
    private String addition;
    private int empID;

    public InstitutionForm(int instFormID, int instID, String text, String addition, int empID) {
        this.instFormID = instFormID;
        this.instID = instID;
        this.text = text;
        this.addition = addition;
        this.empID = empID;
    }

    public InstitutionForm(int instID, String text, String addition, int empID) {
        this.instID = instID;
        this.text = text;
        this.addition = addition;
        this.empID = empID;
    }

    public InstitutionForm(){

    }

    public int getInstFormID() {
        return instFormID;
    }

    public void setInstFormID(int instFormID) {
        this.instFormID = instFormID;
    }

    public int getInstID() {
        return instID;
    }

    public void setInstID(int instID) {
        this.instID = instID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return instFormID;
            case "INS_ID":
                return instID;
            case "TEXT":
                return text;
            case "ADDITION":
                return addition;
            case "EMPLOYEE_ID":
                return empID;
            default:
                return null;
        }
    }
    @Override
    public String toString() {
        return "InstitutionForm{" +
                "instFormID=" + instFormID +
                ", instID=" + instID +
                ", text='" + text + '\'' +
                ", addition='" + addition + '\'' +
                ", empID=" + empID +
                '}';
    }
}
