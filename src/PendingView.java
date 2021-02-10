import java.sql.ResultSet;
import java.util.*;

public class PendingView implements ViewInterface {

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
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int pendingID = resultSet.getInt("ID");
                int appID = resultSet.getInt("APP_ID");
                String status = resultSet.getString("STATUS");
                String result = resultSet.getString("RESULT");
                String addition = resultSet.getString("ADDITION");
                String fee = resultSet.getString("FEE");
                String feeRequestDate = resultSet.getString("FEE_REQUEST_DATE");
                String is_paid = resultSet.getString("IS_PAID");


                // Display values
                System.out.print(pendingID + "\t");
                System.out.print(appID + "\t");
                System.out.print(status + "\t");
                System.out.print(result + "\t");
                System.out.print(addition + "\t");
                System.out.print(fee + "\t");
                System.out.print(feeRequestDate + "\t");
                System.out.println(is_paid);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
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
        Integer pendingID = getInteger("Pending ID : ", true);
        String appID = getString("Application ID : ", true);
        String status = getString("Application Status : ", true);
        String result = getString("Result&Answer to return :  : ", true);
        String addition = getString("Additional text : ", true);
        String fee = getString("Fee : ", true);
        String is_paid = getString("Is it paid? (0 for no, 1 for yes) : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (pendingID != null) whereParameters.put("ID", pendingID);
        if (appID != null) whereParameters.put("APP_ID", appID);
        if (status != null) whereParameters.put("STATUS", status);
        if (result != null) whereParameters.put("RESULT", result);
        if (addition != null) whereParameters.put("ADDITION", addition);
        if (fee != null) whereParameters.put("FEE", fee);
        if (is_paid != null) whereParameters.put("IS_PAID", is_paid);


        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Pending", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "APP_ID,STATUS,RESULT,ADDITION,FEE,IS_PAID");

        List<Object> rows = new ArrayList<>();

        Integer is_paid, appID;
        String status, result, addition, fee;

        System.out.println("Fields to insert:");
        appID = getInteger("App ID : ", true);
        status = getString("STATUS : ", true);
        result = getString("RESULT : ", true);
        addition = getString("ADDITION : ", true);
        fee = getString("FEE : ", true);
        is_paid = getInteger("IS_PAID : ", true);
        System.out.println();

        if (appID != null && status != null && result != null && addition != null && fee != null
                && is_paid != null) {
            rows.add(new Pending(appID, status, result, addition, fee, is_paid));
        }


        parameters.put("rows", rows);

        return new ViewData("Pending", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {

        Integer is_paid, appID;
        String status, result, addition, fee;

        System.out.println("Fields to update:");
        appID = getInteger("App ID : ", true);
        status = getString("STATUS : ", true);
        result = getString("RESULT : ", true);
        addition = getString("ADDITION : ", true);
        fee = getString("FEE : ", true);
        is_paid = getInteger("IS_PAID : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (appID != null) updateParameters.put("APP_ID", appID);
        if (status != null) updateParameters.put("STATUS", status);
        if (result != null) updateParameters.put("RESULT", result);
        if (addition != null) updateParameters.put("ADDITION", addition);
        if (fee != null) updateParameters.put("FEE", fee);
        if (is_paid != null) updateParameters.put("IS_PAID", is_paid);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Pending", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Pending", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Pending View";
    }
}
