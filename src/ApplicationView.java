import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ApplicationView implements ViewInterface {
    public static String licence;
    public static int globalreceiverID;

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
                return getLicenceID();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int appID = resultSet.getInt("ID");
                int senderID = resultSet.getInt("SENDER_ID");
                int receiverID = resultSet.getInt("RECEIVER_ID");
                Date appDate = resultSet.getDate("DATE");
                int senderType = resultSet.getInt("SENDER_TYPE");
                int formID = resultSet.getInt("FORM_ID");

                // Display values
                System.out.print(appID + "\t");
                System.out.print(senderID + "\t");
                System.out.print(receiverID + "\t");
                System.out.print(appDate + "\t");
                System.out.print(senderType + "\t");
                System.out.println(formID);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Your " + modelData.recordCount + " Application has been saved");

        return new ViewData("Pending", "insert.gui");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");
        Integer appID = getInteger("Application ID : ", true);
        Integer senderID = getInteger("Sender ID : ", true);
        Integer receiverID = getInteger("Receiver ID : ", true);
        Integer senderType = getInteger("Sender Type : ", true);
        Integer formID = getInteger("Form ID : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (appID != null) whereParameters.put("ID", appID);
        if (senderID != null) whereParameters.put("SENDER_ID", senderID);
        if (receiverID != null) whereParameters.put("RECEIVER_ID", receiverID);
        if (senderType != null) whereParameters.put("SENDER_TYPE", senderType);
        if (formID != null) whereParameters.put("FORM_ID", formID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Application", "select", parameters);
    }

    int lastInstitutionID;
    public static int lastApplicationID;
    public static int loggedSenderID;
    int lastFormID;

    ViewData insertGUI(ModelData modelData) throws Exception {

        ResultSet resultSet1 = ApplicationModel.selectLastID();
        ResultSet resultSet2 = PersonModel.selectTC();
        ResultSet resultSet3 = FormModel.selectlastFormID();

        //PersonView.PersonTC_PN
        if (resultSet2 != null) {
            while (resultSet2.next()) {
                // Retrieve by column name
                if (resultSet2.getString("TC_PN").equals(PersonView.PersonTC_PN)) {
                    loggedSenderID = resultSet2.getInt("ID");
                }
            }
            resultSet2.close();
        }

        if (resultSet1 != null) {
            while (resultSet1.next()) {
                // Retrieve by column name
                lastApplicationID = resultSet1.getInt("ID");
            }
            resultSet1.close();
        }

        if (resultSet3 != null) {
            while (resultSet3.next()) {
                // Retrieve by column name
                lastFormID = resultSet3.getInt("ID");
            }
            resultSet3.close();
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "SENDER_ID, RECEIVER_ID, SENDER_TYPE, FORM_ID");

        List<Object> rows = new ArrayList<>();

        Integer senderID, receiverID, formID, senderType;

        senderID = loggedSenderID;
        receiverID = globalreceiverID;
        senderType = 1;
        formID = lastFormID;

        System.out.println();
        rows.add(new Application(senderID, receiverID, senderType, formID));

        parameters.put("rows", rows);

        return new ViewData("Application", "insert", parameters);
    }

    private ViewData getLicenceID() throws Exception {

        ResultSet resultSet = InstitutionModel.licenceCheck();
        ResultSet resultSet1 = ApplicationModel.selectLastID();
        Map<String, String> id_licence = new HashMap<>();

        ResultSet resultSet2 = PersonModel.selectTC();

        //PersonView.PersonTC_PN
        if (resultSet2 != null) {
            while (resultSet2.next()) {
                // Retrieve by column name
                if (resultSet2.getString("TC_PN").equals(PersonView.PersonTC_PN)) {
                    loggedSenderID = resultSet2.getInt("ID");
                }
            }
            resultSet2.close();
        }

        if (resultSet1 != null) {
            while (resultSet1.next()) {
                // Retrieve by column name
                lastApplicationID = resultSet1.getInt("ID");
            }
            resultSet1.close();
        }

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                id_licence.put(resultSet.getString("LICENSE_NUMBER"), resultSet.getString("ID"));
                lastInstitutionID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        licence = getString("Please enter the licence number of institution that you want to send: ", true);

        while (true) {
            if (id_licence.containsKey(licence)) {
                globalreceiverID = Integer.parseInt(id_licence.get(licence));
                Map<String, Object> updateParameters = new HashMap<>();
                Map<String, Object> whereParameters = new HashMap<>();
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("updateParameters", updateParameters);
                parameters.put("whereParameters", whereParameters);

                return new ViewData("Form", "insert.gui", parameters);

            } else {
                licence = getString("Please enter the licence number of institution that you want to send: ", true);
            }
        }
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer senderID = getInteger("Sender ID : ", true);
        Integer receiverID = getInteger("Receiver ID : ", true);
        Integer senderType = getInteger("Sender Type : ", true);
        Integer formID = getInteger("Form ID : ", true);

        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (senderID != null) updateParameters.put("SENDER_ID", senderID);
        if (receiverID != null) updateParameters.put("RECEIVER_ID", receiverID);
        if (senderType != null) updateParameters.put("SENDER_TYPE", senderType);
        if (formID != null) updateParameters.put("FORM_ID", formID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Application", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Application", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Application View";
    }
}
