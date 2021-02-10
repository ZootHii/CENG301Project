public class Employee {

    private int empID;
    private int personID;
    private int insID;
    private String depName;
    private String title;
    private int authority;
    private int authID;

    public Employee() {

    }

    public Employee(int empID, int personID, int insID, String depName, String title, int authority, int authID) {
        this.empID = empID;
        this.personID = personID;
        this.insID = insID;
        this.depName = depName;
        this.title = title;
        this.authority = authority;
        this.authID = authID;
    }

    public Employee(int personID, int insID, String depName, String title, int authority, int authID) {
        this.personID = personID;
        this.insID = insID;
        this.depName = depName;
        this.title = title;
        this.authority = authority;
        this.authID = authID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getInsID() {
        return insID;
    }

    public void setInsID(int insID) {
        this.insID = insID;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getAuthID() {
        return authID;
    }

    public void setAuthID(int authID) {
        this.authID = authID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return empID;
            case "PERSON_ID":
                return personID;
            case "INS_ID":
                return insID;
            case "DEPT_NAME":
                return depName;
            case "TITLE":
                return title;
            case "AUTHORITY":
                return authority;
            case "AUTH_ID":
                return authID;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", personID=" + personID +
                ", insID=" + insID +
                ", depName='" + depName + '\'' +
                ", title='" + title + '\'' +
                ", authority=" + authority +
                ", authID=" + authID +
                '}';
    }
}
