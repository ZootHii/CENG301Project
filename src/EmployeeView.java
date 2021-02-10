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

        return new ViewData("MainMenu", "");
    }

    ViewData employeeInstitutionBind() throws Exception {

        showPendingApplications();

        return new ViewData("InterMenu", "auth");
    }

    ViewData employeeAnswerPendingApplication() throws Exception {

        showPendingApplications();

        return new ViewData("Pending", "update.gui");
    }

    private void showPendingApplications() throws Exception {
        String where = "P.TC_PN = " + PersonView.personTC_PN + " AND P.PASSWORD = " + PersonView.personPassword;
        ResultSet resultSet = EmployeeModel.employeeLoginSelect(where);
        int employeeInstitutionID = 0;
        if (resultSet.next()) {
            employeeInstitutionID = resultSet.getInt("INS_ID");
            resultSet.close();
        }

        ResultSet resultSet1 = EmployeeModel.employeeInsBinder(String.valueOf(employeeInstitutionID));

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID      NAME         SURNAME         EMAIL         PHONE         DATE         LAST DATE         STATUS         TEXT         ADDITION         RETURN TYPE     ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (resultSet1 != null) {
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
                    System.out.print("Empty"+ "\t|\t");
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
    }


    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int empID = resultSet.getInt("ID");
                int personID = resultSet.getInt("PERSON_ID");
                int insID = resultSet.getInt("INS_ID");
                String departmentName = resultSet.getString("DEPARTMENT_NAME");
                String title = resultSet.getString("TITLE");
                int authority = resultSet.getInt("AUTHORITY");
                int authID = resultSet.getInt("AUTH_ID");

                // Display values
                System.out.print(empID + "\t");
                System.out.print(personID + "\t");
                System.out.print(insID + "\t");
                System.out.print(departmentName + "\t");
                System.out.print(title + "\t");
                System.out.print(authority + "\t");
                System.out.println(authID);
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
        Integer empID = getInteger("Employee ID : ", true);
        Integer personID = getInteger("Person ID : ", true);
        Integer insID = getInteger("Institution ID : ", true);
        String deptName = getString("Department Name: ", true);
        String title = getString("Title : ", true);
        Integer authority = getInteger("Authority : ", true);
        Integer authID = getInteger("Authority ID : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (empID != null) whereParameters.put("ID", empID);
        if (personID != null) whereParameters.put("PERSON_ID", personID);
        if (insID != null) whereParameters.put("INS_ID", insID);
        if (deptName != null) whereParameters.put("DEPARTMENT_NAME", deptName);
        if (title != null) whereParameters.put("TITLE", title);
        if (authority != null) whereParameters.put("AUTHORITY", authority);
        if (authID != null) whereParameters.put("AUTH_ID", authID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Department", "select", parameters);
    }


    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "EmployeeID, PersonID,InstitutionID,DepartmentName,Title,Authority,AuthorityID ");

        List<Object> rows = new ArrayList<>();

        Integer personID, insID, authority, authID;
        String deptName, title;
        do {
            System.out.println("Fields to insert:");
            personID = getInteger("Person ID : ", true);
            insID = getInteger("Institution ID : ", true);
            deptName = getString("Department Name: ", true);
            title = getString("Title : ", true);
            authority = getInteger("Authority : ", true);
            authID = getInteger("Authority ID : ", true);
            System.out.println();

            if (personID != null && insID != null && deptName != null && title != null && authority != null && authID != null) {
                rows.add(new Employee(personID, insID, deptName, title, authority, authID));
            }
        }
        while (personID != null && insID != null && deptName != null && title != null && authority != null && authID != null);

        parameters.put("rows", rows);

        return new ViewData("Department", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer empID = getInteger("Employee ID : ", true);
        Integer personID = getInteger("Person ID : ", true);
        Integer insID = getInteger("Institution ID : ", true);
        String deptName = getString("Department Name: ", true);
        String title = getString("Title : ", true);
        Integer authority = getInteger("Authority : ", true);
        Integer authID = getInteger("Authority ID : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (empID != null) updateParameters.put("ID", empID);
        if (personID != null) updateParameters.put("PERSON_ID", personID);
        if (insID != null) updateParameters.put("INS_ID", insID);
        if (deptName != null) updateParameters.put("DEPARTMENT_NAME", deptName);
        if (title != null) updateParameters.put("TITLE", title);
        if (authority != null) updateParameters.put("AUTHORITY", authority);
        if (authID != null) updateParameters.put("AUTH_ID", authID);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Department", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Department", "delete", parameters);
    }


    @Override
    public String toString() {
        return "Department View";
    }
}
