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
            case "displayLoggedApplication":
                return displayLoggedApplication();
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
        return new ViewData("InterMenu", "");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Your answer has been saved and sent!");

        return new ViewData("InterMenu", "auth");
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
        ResultSet resultSet = ApplicationModel.selectLastID();
        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                ApplicationView.lastApplicationID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        int appID = ApplicationView.lastApplicationID;
        String status = "In process";
        String result = "Not answered";
        String addition = "";
        String fee = "Not announced";
        int is_paid = 0;
        System.out.println();

        rows.add(new Pending(appID, status, result, addition, fee, is_paid));
        parameters.put("rows", rows);
        return new ViewData("Pending", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        String where = "P.TC_PN = " + PersonView.personTC_PN + " AND P.PASSWORD = " + PersonView.personPassword;
        ResultSet resultSet = EmployeeModel.employeeLoginSelect(where);
        int employeeInstitutionID = 0;
        if (resultSet.next()) {
            employeeInstitutionID = resultSet.getInt("INS_ID");
            resultSet.close();
        }

        ArrayList<Integer> appIDofInstitutionList = new ArrayList<>();

        ResultSet resultSet1 = EmployeeModel.employeeInsBinder(String.valueOf(employeeInstitutionID));
        if(resultSet1 != null){
            while (resultSet1.next()){
                appIDofInstitutionList.add(resultSet1.getInt("APP_ID"));
            }
            resultSet1.close();
        }

        Integer is_paid, appID;
        String status, result, addition, fee;


        appID = getInteger("Enter the application's ID you want to answer : ", false);
        while(!appIDofInstitutionList.contains(appID)){
            appID = getInteger("The ID you entered is not in the list!\nEnter the application's ID you want to answer : ", false);
        }
        status = "Answered";
        result = getString("Give your answer : ", false);
        addition = getString("Add extra information if it is required : ", true);
        if(addition == null){
            addition = "";
        }
        fee = getString("Enter the cost of returning if there is any : ", false);
        is_paid = getInteger("Has the person paid the fee(1 for Yes, 0 for No) : ", false);
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
        Map<String, Object> whereParameters = new HashMap<>();
        whereParameters.put("APP_ID", appID);
        parameters.put("whereParameters", whereParameters);


        return new ViewData("Pending", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Pending", "delete", parameters);
    }

    private ViewData displayLoggedApplication() throws Exception {
        ResultSet resultSetLogged = PendingModel.selectLoggedApplication();
        System.out.print("------------------------------------------------------------------------------------");
        System.out.println("\nStatus\t\tResult\t\t\tAddition\tFee\t\t\tDate\t   Request Date\t Payment");
        System.out.println("------------------------------------------------------------------------------------");
        if (resultSetLogged != null) {
            while (resultSetLogged.next()) {
                if (resultSetLogged.getString("TC_PN").equals(PersonView.personTC_PN)) {
                    String status = resultSetLogged.getString("STATUS");
                    String result = resultSetLogged.getString("RESULT");
                    String addition = resultSetLogged.getString("ADDITION");
                    String fee = resultSetLogged.getString("FEE");
                    String date = resultSetLogged.getString("DATE");
                    String feeRequestDate = resultSetLogged.getString("FEE_REQUEST_DATE");
                    String is_paid = resultSetLogged.getString("IS_PAID");

                    // Display values
                    System.out.print(status + "\t");
                    System.out.print(result + "\t");
                    if (addition.equals("")) {
                        System.out.print("Empty" + "\t");
                    } else {
                        System.out.print(addition + "\t");
                    }
                    System.out.print(fee + "\t");
                    System.out.print(date + "\t");
                    System.out.print(feeRequestDate + "\t");
                    if (is_paid.equals("1")) {
                        System.out.println("Paid");
                    } else {
                        System.out.println("Not paid");
                    }
                }
            }
            System.out.println("------------------------------------------------------------------------------------");

            resultSetLogged.close();
        }

        return new ViewData("InterMenu", "");
    }

    @Override
    public String toString() {
        return "Pending View";
    }
}
