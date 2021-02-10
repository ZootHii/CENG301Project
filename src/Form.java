public class Form {

    private int formID;
    private String text;
    private String addition;
    private int type;
    private int returnType;

    public Form(int formID, String text, String addition, int type, int returnType) {
        this.formID = formID;
        this.text = text;
        this.addition = addition;
        this.type = type;
        this.returnType = returnType;
    }

    public Form(String text, String addition, int type, int returnType) {
        this.text = text;
        this.addition = addition;
        this.type = type;
        this.returnType = returnType;
    }

    public Form(){

    }

    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "Form{" +
                "formID=" + formID +
                ", text='" + text + '\'' +
                ", addition='" + addition + '\'' +
                ", type=" + type +
                ", returnType=" + returnType +
                '}';
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return formID;
            case "TEXT":
                return text;
            case "ADDITION":
                return addition;
            case "TYPE":
                return type;
            case "RETURN_TYPE":
                return returnType;
            default:
                return null;
        }
    }

}
