import java.sql.ResultSet;
import java.util.*;

public class EmployeeView implements ViewInterface {

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
            case "employeeInstitutionBind":
                return employeeInstitutionBind();
            case "employeeAnswerPendingApplication":
                return employeeAnswerPendingApplication();
        }

        return null;
    }

    ViewData employeeInstitutionBind() throws Exception {

        showPendingApplications();

        return new ViewData("InterMenu", "auth");
    }

    ViewData employeeAnswerPendingApplication() throws Exception {

        if (showPendingApplications()) {
            return new ViewData("Pending", "update.gui");
        }
        return new ViewData("InterMenu", "auth");
    }

    private boolean showPendingApplications() throws Exception {
        int employeeInstitutionID = 0;

        // logged employee finder by unique personTC_PN and personPassword // Employee is also a Person
        String where = "P.TC_PN = " + PersonView.personTC_PN + " AND P.PASSWORD = " + PersonView.personPassword;
        ResultSet resultSet = EmployeeModel.employeeLoginSelect(where);

        if (resultSet.next()) {
            employeeInstitutionID = resultSet.getInt("INS_ID");
            resultSet.close();
        }

        ResultSet resultSet1 = EmployeeModel.employeeInsBinder(String.valueOf(employeeInstitutionID));

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID      NAME         SURNAME         EMAIL         PHONE         DATE         LAST DATE         STATUS         TEXT         ADDITION         RETURN TYPE     ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (resultSet1 != null) {
            if (!resultSet1.next()) {
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("There is no Application sent to your Institution");
                return false;
            }
            while (resultSet1.next()) {
                // Retrieve by column name
                int pendingID = resultSet1.getInt("ID");
                String personName = resultSet1.getString("NAME");
                String personSurname = resultSet1.getString("SURNAME");
                String personEmail = resultSet1.getString("EMAIL");
                String personPhone = resultSet1.getString("PHONE");
                String appDate = resultSet1.getString("DATE");
                String feeRequestDate = resultSet1.getString("FEE_REQUEST_DATE");
                String pendingStatus = resultSet1.getString("STATUS");
                String personFormText = resultSet1.getString("TEXT");
                String personFormAddition = resultSet1.getString("ADDITION");
                int personFormReturnType = resultSet1.getInt("RETURN_TYPE");

                // Display values
                System.out.print(pendingID + "\t|\t");
                System.out.print(personName + "\t|\t");
                System.out.print(personSurname + "\t|\t");
                System.out.print(personEmail + "\t|\t");
                System.out.print(personPhone + "\t|\t");
                System.out.print(appDate + "\t|\t");
                System.out.print(feeRequestDate + "\t|\t");
                System.out.print(pendingStatus + "\t|\t");
                System.out.print(personFormText + "\t|\t");
                if (personFormAddition.equals("")) {
                    System.out.print("Empty" + "\t|\t");
                } else {
                    System.out.print(personFormAddition + "\t|\t");
                }
                if (personFormReturnType == 1) {
                    System.out.println("Online");
                } else {
                    System.out.println("Written");
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
            resultSet1.close();
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee View";
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        return null;
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
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
}
