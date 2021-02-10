import java.util.Date;

public class Application {

    private int appID;
    private int senderID;
    private int receiverID;
    private Date appDate;
    private int senderType;
    private int formID;

    Application() {

    }

    public Application(int senderID, int receiverID, int senderType, int formID) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.senderType = senderType;
        this.formID = formID;
    }

    public Application(int appID, int senderID, int receiverID, Date appDate, int senderType, int formID) {
        this.appID = appID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.appDate = appDate;
        this.senderType = senderType;
        this.formID = formID;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public int isSenderType() {
        return senderType;
    }

    public void setSenderType(int senderType) {
        this.senderType = senderType;
    }

    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return appID;
            case "SENDER_ID":
                return senderID;
            case "RECEIVER_ID":
                return receiverID;
            case "DATE":
                return appDate;
            case "SENDER_TYPE":
                return senderType;
            case "FORM_ID":
                return formID;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Application{" +
                "appID=" + appID +
                ", senderID=" + senderID +
                ", receiverID=" + receiverID +
                ", appDate=" + appDate +
                ", senderType=" + senderType +
                ", formID=" + formID +
                '}';
    }
}
