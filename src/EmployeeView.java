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
        }

        return new ViewData("MainMenu", "");
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

        Integer personID,insID,authority,authID;
        String deptName,title;
        do {
            System.out.println("Fields to insert:");
            personID = getInteger("Person ID : ", true);
            insID = getInteger("Institution ID : ", true);
            deptName = getString("Department Name: ", true);
            title = getString("Title : ", true);
            authority = getInteger("Authority : ", true);
            authID = getInteger("Authority ID : ", true);
            System.out.println();

            if ( personID != null && insID != null && deptName != null && title != null && authority != null && authID != null) {
                rows.add(new Employee( personID,insID,deptName,title,authority,authID));
            }
        }
        while ( personID != null && insID != null && deptName != null && title != null && authority != null && authID != null);

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
