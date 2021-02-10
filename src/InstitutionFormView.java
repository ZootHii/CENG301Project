import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstitutionFormView implements ViewInterface {
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

        return null;
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.print("Your institution form is created successfully ");

        return new ViewData("Application", "insert.gui");
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", " INS_ID, TEXT, ADDITION, EMPLOYEE_ID ");

        List<Object> rows = new ArrayList<>();
        String where = "P.TC_PN = " + PersonView.personTC_PN + " AND P.PASSWORD = " + PersonView.personPassword;
        ResultSet resultSet = EmployeeModel.employeeLoginSelect(where);
        int employeeID = 0;
        String employeeTitle = "";
        int employeeInstitutionID = 0;
        if (resultSet.next()) {
            employeeID = resultSet.getInt("ID");

            employeeInstitutionID = resultSet.getInt("INS_ID");
            resultSet.close();
        }
        String text, addition;


        System.out.println();
        System.out.println("Please enter information below");
        text = getString("State your reason for your application : ", false);
        addition = getString("If you want to add something, please write here : ", true);
        if (addition == null) {
            addition = "";
        }

        System.out.println();

        rows.add(new InstitutionForm(employeeInstitutionID, text, addition, employeeID));

        parameters.put("rows", rows);

        return new ViewData("InstitutionForm", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Institution View";
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

    ViewData updateGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        return null;
    }
}
