import java.sql.ResultSet;
import java.util.*;

public class AddressView implements ViewInterface {

    public static int addressID;

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
            case "selectLastAddressIDOperation":
                return selectLastAddressIDOperation(); // add new operation we could not handle with modelData at first so made it public static to get ResultSet
        }
        return null;
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        return new ViewData("Address", "selectLastAddressIDOperation", new HashMap<>());
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "TYPE, COUNTRY, CITY, TOWN, DISTRICT, POSTAL_CODE, TEXT ");

        List<Object> rows = new ArrayList<>();
        String addressType, country, city, town, district, postalCode, text;

        System.out.println("Enter your address information:");
        addressType = getString("Enter your address type(Ex:Home / Office) : ", false);
        country = getString("Enter your country? : ", false);
        city = getString("Enter your city? : ", false);
        town = getString("Enter your town? : ", false);
        district = getString("Enter your district? : ", true);
        if (district == null) {
            district = "";
        }
        postalCode = getString("Enter your postal code? : ", false);
        text = getString("Enter explanation for your address(Optional) : ", true);
        if (text == null) {
            text = "";
        }
        System.out.println();

        rows.add(new Address(addressType, country, city, town, district, postalCode, text));

        parameters.put("rows", rows);

        return new ViewData("Address", "insert", parameters);
    }

    // SELECT LAST ID FROM ADDRESS USING BY selectLastID METHOD IN AddressModel
    ViewData selectLastAddressIDOperation() throws Exception {
        ResultSet resultSet = AddressModel.selectLastAddressID();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                addressID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        return new ViewData("Person", "selectLastPersonIDOperation", new HashMap<>());
    }

    @Override
    public String toString() {
        return "Address View";
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
