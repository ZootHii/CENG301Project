import java.sql.ResultSet;
import java.util.*;

public class ApplicationView implements ViewInterface {

    int lastInstitutionID;
    int lastFormID;
    public static String globalLicenceNumber;
    public static int globalReceiverID;
    public static int lastApplicationID;
    public static int globalLoggedSenderID;
    public static int lastInstitutionFormID;

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        switch (operationName) {
            case "select":
                return selectOperation(modelData);
            case "insert":
                return insertOperation(modelData);
            case "update":
                return updateOperation(modelData);
            case "delete":
                return deleteOperation(modelData);
            case "select.gui":
                return selectGUI(modelData);
            case "insert.gui":
                return insertGUI(modelData);
            case "update.gui":
                return updateGUI(modelData);
            case "delete.gui":
                return deleteGUI(modelData);
            case "getLicenceID":
                return getLicenceID(); // add new operation we could not handle with modelData at first
            case "getInstitutionLicenceID":
                return getInstitutionLicenceID(); // add new operation we could not handle with modelData at first
            case "insertInstitutionGUI":
                return insertInstitutionGUI(); // add new operation we could not handle with modelData at first
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        return null;
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        return new ViewData("Pending", "insert.gui");
    }

    // insert gui for PersonForm to Application // Form class is actually PersonClass but we defined Form at first
    ViewData insertGUI(ModelData modelData) throws Exception {

        getInformation();

        ResultSet resultSet = FormModel.selectLastFormID();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                lastFormID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", " SENDER_ID, RECEIVER_ID, SENDER_TYPE, FORM_ID ");

        List<Object> rows = new ArrayList<>();

        // get necessary values from global values
        int senderID = globalLoggedSenderID;
        int receiverID = globalReceiverID;
        int senderType = 1; // sender type is always 1 refer to Online
        int formID = lastFormID;

        System.out.println();
        rows.add(new Application(senderID, receiverID, senderType, formID));

        parameters.put("rows", rows);

        return new ViewData("Application", "insert", parameters);
    }

    // insert gui for InstitutionForm to Application
    ViewData insertInstitutionGUI() throws Exception {

        getInformation();

        ResultSet resultSet = InstitutionFormModel.selectLastInstitutionFormID();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", " SENDER_ID, RECEIVER_ID, SENDER_TYPE, FORM_ID ");

        List<Object> rows = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                lastInstitutionFormID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        // get necessary values from global values
        int senderID = globalLoggedSenderID;
        int receiverID = globalReceiverID;
        int senderType = 1; // sender type is always 1 refer to Online
        int formID = lastInstitutionFormID;

        System.out.println();
        rows.add(new Application(senderID, receiverID, senderType, formID));

        parameters.put("rows", rows);

        return new ViewData("Application", "insert", parameters);
    }

    // Get necessary information
    private void getInformation() throws Exception {
        ResultSet resultSet1 = ApplicationModel.selectLastID();
        ResultSet resultSet2 = PersonModel.selectTC();

        if (resultSet1.next()) {
            // Retrieve by column name
            lastApplicationID = resultSet1.getInt("ID");

            resultSet1.close();
        }

        if (resultSet2 != null) {
            while (resultSet2.next()) {
                // Retrieve by column name
                if (resultSet2.getString("TC_PN").equals(PersonView.personTC_PN)) { // personTC_PN equals logged person TC or Passport Number
                    globalLoggedSenderID = resultSet2.getInt("ID"); // so logged person has unique TC_PN and we get ID by using this TC_PN
                }
            }
            resultSet2.close();
        }
    }

    private ViewData getLicenceID() throws Exception {

        getInformation();

        ResultSet resultSet = InstitutionModel.licenceCheck();

        Map<String, String> id_licence = new HashMap<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                id_licence.put(resultSet.getString("LICENSE_NUMBER"), resultSet.getString("ID"));
                lastInstitutionID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        globalLicenceNumber = getString("Please enter the licence number of institution that you want to send: ", true);

        while (true) {
            if (id_licence.containsKey(globalLicenceNumber)) {
                globalReceiverID = Integer.parseInt(id_licence.get(globalLicenceNumber));
                Map<String, Object> updateParameters = new HashMap<>();
                Map<String, Object> whereParameters = new HashMap<>();
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("updateParameters", updateParameters);
                parameters.put("whereParameters", whereParameters);

                return new ViewData("Form", "insert.gui", parameters);

            } else {
                globalLicenceNumber = getString("Please enter the licence number of institution that you want to send: ", true);
            }
        }
    }

    private ViewData getInstitutionLicenceID() throws Exception {

        getInformation();

        ResultSet resultSet = InstitutionModel.licenceCheck();

        Map<String, String> id_licence = new HashMap<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                id_licence.put(resultSet.getString("LICENSE_NUMBER"), resultSet.getString("ID"));
                lastInstitutionID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        globalLicenceNumber = getString("Please enter the licence number of institution that you want to send: ", true);

        while (true) {
            if (id_licence.containsKey(globalLicenceNumber)) {
                globalReceiverID = Integer.parseInt(id_licence.get(globalLicenceNumber));
                Map<String, Object> updateParameters = new HashMap<>();
                Map<String, Object> whereParameters = new HashMap<>();
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("updateParameters", updateParameters);
                parameters.put("whereParameters", whereParameters);

                return new ViewData("InstitutionForm", "insert.gui", parameters);

            } else {
                globalLicenceNumber = getString("Please enter the licence number of institution that you want to send: ", true);
            }
        }
    }

    @Override
    public String toString() {
        return "Application View";
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        return null;
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        return null;
    }

    Map<String, Object> getWhereParameters() throws Exception {
        return null;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        return null;
    }
}
