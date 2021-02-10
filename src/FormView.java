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
            //case "getLicenceID": return deleteGUI(modelData);
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int formID = resultSet.getInt("ID");
                String text = resultSet.getString("TEXT");
                String addition = resultSet.getString("ADDITION");
                int type = resultSet.getInt("TYPE");
                int returnType = resultSet.getInt("RETURN_TYPE");


                // Display values
                System.out.print(formID + "\t");
                System.out.print(text + "\t");
                System.out.print(addition + "\t");
                System.out.print(type + "\t");
                System.out.println(returnType);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    /*public static int selectLastFormIDOperation(ModelData modelData) throws Exception {
        int lastFormID = 0;
        ResultSet resultSet = FormModel.selectlastFormID();

        if (resultSet != null) {

            lastFormID = resultSet.getInt("ID");

            resultSet.close();
        }
        return lastFormID;

    }*/


    // BURADA YANİ APP OLUŞTUR O FORMU VE DİĞER BİLGİLERİ ATARAK
    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

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
        Integer formID = getInteger("FormID : ", true);
        String text = getString("Text : ", true);
        String addition = getString(" Addition : ", true);
        Integer type = getInteger(" Type : ", true);
        Integer returnType = getInteger(" ReturnType : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (formID != null) whereParameters.put("FormID", formID);
        if (text != null) whereParameters.put("Text", text);
        if (addition != null) whereParameters.put("Addition", addition);
        if (type != null) whereParameters.put("Type", type);
        if (returnType != null) whereParameters.put("Return Type", returnType);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Form", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "TEXT, ADDITION, TYPE, RETURN_TYPE");

        List<Object> rows = new ArrayList<>();

        String text, addition;
        Integer type, return_type;

        System.out.println("Fields to insert:");
        text = getString("State your reason for your application : ", true);
        addition = getString("If you want to add something, please write here : ", true);
        type = 1;
        return_type = getInteger("Return Type (If you want to read your response online press 1 otherwise 0): ", true);
        System.out.println();

        if (text != null && addition != null && type != null && return_type != null) {
            rows.add(new Form(text, addition, type, return_type));
        }

        parameters.put("rows", rows);

        return new ViewData("Form", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String text = getString("Text : ", true);
        String addition = getString(" Addition : ", true);
        Integer type = getInteger(" Type : ", true);
        Integer returnType = getInteger(" ReturnType : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (text != null) updateParameters.put("Text", text);
        if (addition != null) updateParameters.put("Addition", addition);
        if (type != null) updateParameters.put("Type", type);
        if (returnType != null) updateParameters.put("Return Type", returnType);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Form", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Form", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Form View";
    }
}
