import java.sql.ResultSet;
import java.util.*;

public class FormView implements ViewInterface {

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
        System.out.print("Your form is created successfully ");

        return new ViewData("Application", "insert.gui");
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "TEXT, ADDITION, TYPE, RETURN_TYPE");

        List<Object> rows = new ArrayList<>();

        String text, addition;
        int type, return_type;

        System.out.println();
        System.out.println("Please enter information below");
        text = getString("State your reason for your application : ", false);
        addition = getString("If you want to add something, please write here : ", true);
        if (addition == null) {
            addition = "";
        }
        type = 1; // always person because we did not make for institution
        return_type = getInteger("Return Type (If you want to read your response online press 1 otherwise 0): ", false);
        System.out.println();

        rows.add(new Form(text, addition, type, return_type));

        parameters.put("rows", rows);

        return new ViewData("Form", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Form View";
    }

    // not used but implements
    ViewData deleteGUI(ModelData modelData) throws Exception {
        return null;
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

    ViewData selectOperation(ModelData modelData) throws Exception {
        return null;
    }
}
