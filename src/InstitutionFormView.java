import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstitutionFormView implements ViewInterface{
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
                int instFormID = resultSet.getInt("ID");
                int instID = resultSet.getInt("INST_ID");
                String text = resultSet.getString("TEXT");
                String addition = resultSet.getString("ADDITION");
                int empID = resultSet.getInt("EMPLOYEE_ID");


                // Display values
                System.out.print(instFormID + "\t");
                System.out.print(instID + "\t");
                System.out.print(text + "\t");
                System.out.print(addition + "\t");
                System.out.println(empID);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.print("Your institution form is created successfully ");

        return new ViewData("Application", "insert.gui");
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
        Integer insFormID = getInteger("Institution Form ID : ", true);
        Integer insID = getInteger("Institution ID : ", true);
        String text = getString("Text : ", true);
        String addition = getString("Addition : ", true);
        Integer empID = getInteger("Employee ID : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (insFormID != null) whereParameters.put("ID", empID);
        if (insID != null) whereParameters.put("INST_ID", insID);
        if (text != null) whereParameters.put("TEXT", text);
        if (addition != null) whereParameters.put("ADDITION", addition);
        if (empID != null) whereParameters.put("EMPLOYEE_ID", empID);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Department", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", " InstitutionID,Text,Addition,EmployeeID");

        List<Object> rows = new ArrayList<>();
        String where = "P.TC_PN = " + PersonView.personTC_PN + " AND P.PASSWORD = " + PersonView.personPassword;
        ResultSet resultSet = EmployeeModel.employeeLoginSelect(where);
        int employeeID = 0;
        String employeeTitle = "";
        int employeeInstitutionID=0;
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
        if (addition == null){
            addition = "";
        }

        System.out.println();

        rows.add(new InstitutionForm(employeeInstitutionID,text, addition,employeeID));

        parameters.put("rows", rows);

        return new ViewData("InstitutionForm", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer insFormID = getInteger("Institution Form ID : ", true);
        Integer insID = getInteger("Institution ID : ", true);
        String text = getString("Text : ", true);
        String addition = getString("Addition : ", true);
        Integer empID = getInteger("Employee ID : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (insID != null) updateParameters.put("INST_ID", insID);
        if (text != null) updateParameters.put("TEXT", text);
        if (addition != null) updateParameters.put("ADDITION", addition);
        if (empID != null) updateParameters.put("EMPLOYEE_ID", empID);

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
