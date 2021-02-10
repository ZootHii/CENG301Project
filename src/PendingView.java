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

        return null;
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        return new ViewData("InterMenu", InterMenuView.personStatus);
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Your answer has been saved and sent!");

        return new ViewData("InterMenu", "auth");
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
        if (resultSet1 != null) {
            while (resultSet1.next()) {
                appIDofInstitutionList.add(resultSet1.getInt("APP_ID"));
            }
            resultSet1.close();
        }

        Integer is_paid, appID;
        String status, result, addition, fee;

        appID = getInteger("Enter the application's ID you want to answer : ", false);
        while (!appIDofInstitutionList.contains(appID)) {
            appID = getInteger("The ID you entered is not in the list!\nEnter the application's ID you want to answer : ", false);
        }
        status = "Answered";
        result = getString("Give your answer : ", false);
        addition = getString("Add extra information if it is required : ", true);
        if (addition == null) {
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

        return new ViewData("InterMenu", InterMenuView.personStatus);
    }

    @Override
    public String toString() {
        return "Pending View";
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

    ViewData selectOperation(ModelData modelData) throws Exception {
        return null;
    }
}
