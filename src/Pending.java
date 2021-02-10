import java.util.Date;

public class Pending {

    private int pendingID;
    private int appID;
    private String status;
    private String result;
    private String addition;
    private String fee;
    private String feeRequestDate;
    private int is_paid;

    public Pending(int pendingID, int appID, String status, String result, String addition, String fee, String feeRequestDate, int is_paid) {
        this.pendingID = pendingID;
        this.appID = appID;
        this.status = status;
        this.result = result;
        this.addition = addition;
        this.fee = fee;
        this.feeRequestDate = feeRequestDate;
        this.is_paid = is_paid;
    }

    public Pending(int appID, String status, String result, String addition, String fee, int is_paid) {
        this.appID = appID;
        this.status = status;
        this.result = result;
        this.addition = addition;
        this.fee = fee;
        this.is_paid = is_paid;
    }

    public Pending() {

    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return pendingID;
            case "APP_ID":
                return appID;
            case "STATUS":
                return status;
            case "RESULT":
                return result;
            case "ADDITION":
                return addition;
            case "FEE":
                return fee;
            case "FEE_REQUEST_DATE":
                return feeRequestDate;
            case "IS_PAID":
                return is_paid;

            default:
                return null;
        }
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeeRequestDate() {
        return feeRequestDate;
    }

    public void setFeeRequestDate(String feeRequestDate) {
        this.feeRequestDate = feeRequestDate;
    }

    public int getPendingID() {
        return pendingID;
    }

    public void setPendingID(int pendingID) {
        this.pendingID = pendingID;
    }

    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    @Override
    public String toString() {
        return "Pending{" +
                "pendingID=" + pendingID +
                ", appID=" + appID +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                ", addition='" + addition + '\'' +
                ", fee='" + fee + '\'' +
                ", feeRequestDate='" + feeRequestDate + '\'' +
                ", is_paid=" + is_paid +
                '}';
    }
}
