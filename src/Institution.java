import java.util.Date;

public class Institution {

    private int instID;
    private String licenseNumber;
    private String name;
    private String eMail;
    private String phone;
    private String fax;
    private int addressID;
    private int boundToID;
    private int type;

    Institution() {

    }

    public Institution(int instID, String licenseNumber, String name, String eMail, String phone, String fax, int addressID, int boundToID, int type) {
        this.instID = instID;
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
        this.fax = fax;
        this.addressID = addressID;
        this.boundToID = boundToID;
        this.type = type;
    }

    public Institution(String licenseNumber, String name, String eMail, String phone, String fax, int addressID, int boundToID, int type) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
        this.fax = fax;
        this.addressID = addressID;
        this.boundToID = boundToID;
        this.type = type;
    }

    public int getInstID() {
        return instID;
    }

    public void setInstID(int instID) {
        this.instID = instID;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getBoundToID() {
        return boundToID;
    }

    public void setBoundToID(int boundToID) {
        this.boundToID = boundToID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return instID;
            case "LICENSE_NUMBER":
                return licenseNumber;
            case "NAME":
                return name;
            case "EMAIL":
                return eMail;
            case "PHONE":
                return phone;
            case "FAX":
                return fax;
            case "ADDRESS_ID":
                return addressID;
            case "BOUND_TO_ID":
                return boundToID;
            case "TYPE":
                return type;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Institution{" +
                "instID=" + instID +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", addressID=" + addressID +
                ", boundToID=" + boundToID +
                ", type=" + type +
                '}';
    }
}
