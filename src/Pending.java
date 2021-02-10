import java.util.Date;

public class Pending {

    private int pendingID;
    private int appID;
    private String status;
    private String result;
    private String addition;
    private String fee;
    private String feeRequestDate;
    private boolean is_paid;


    public Pending(int pendingID, int appID, String status, String result, String addition, String fee, String feeRequestDate, boolean is_paid) {
        this.pendingID = pendingID;
        this.appID = appID;
        this.status = status;
        this.result = result;
        this.addition = addition;
        this.fee = fee;
        this.feeRequestDate = feeRequestDate;
        this.is_paid = is_paid;
    }

    public Pending( int appID, String status, String result, String addition, String fee,String feeRequestDate, boolean is_paid) {
        this.appID = appID;
        this.status = status;
        this.result = result;
        this.addition = addition;
        this.fee = fee;
        this.feeRequestDate = feeRequestDate;
        this.is_paid = is_paid;
    }


    public Pending(){

    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "PendindID": return pendingID;
            case "AppID": return appID;
            case "Status": return status;
            case "Result": return result;
            case "Addition": return addition;
            case "Fee": return fee;
            case "FeeRequestDate": return feeRequestDate;
            case "IsPaid": return is_paid;

            default: return null;
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

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
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


}
