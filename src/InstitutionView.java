import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class InstitutionView implements ViewInterface {

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
            case "selectInstitutionOperation":
                return selectInstitutionOperation(modelData);
        }

        return null;
    }

    ViewData selectOperation(ModelData modelData) throws Exception {

        printInstitutions(modelData);
        return new ViewData("Application", "getLicenceID", new HashMap<>());
    }

    ViewData selectInstitutionOperation(ModelData modelData) throws Exception {

        printInstitutions(modelData);
        return new ViewData("Application", "getInstitutionLicenceID", new HashMap<>());
    }

    private void printInstitutions(ModelData modelData) throws SQLException {
        ResultSet resultSet = modelData.resultSet;
        if (resultSet != null) {

            System.out.println("------------------------------------");
            System.out.println("License     Name     Email");
            System.out.println("------------------------------------");
            while (resultSet.next()) {
                // Retrieve by column name
                String licenseNumber = resultSet.getString("LICENSE_NUMBER");
                String name = resultSet.getString("NAME");
                String eMail = resultSet.getString("EMAIL");

                // Display value
                System.out.print(licenseNumber);
                System.out.print("\t\t\t" + name);
                System.out.print("    " + eMail);
                System.out.println();

            }
            System.out.println("------------------------------------");
            System.out.println();
            resultSet.close();
        }
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Institution View";
    }


    ViewData updateGUI(ModelData modelData) throws Exception {
        return null;
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
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

    ViewData selectGUI(ModelData modelData) throws Exception {
        return null;
    }
}
