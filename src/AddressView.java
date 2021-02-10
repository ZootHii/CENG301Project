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
            case "selectlastaddressid":
                return selectLastAddressIDOperation(modelData);
        }
        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int addressID = resultSet.getInt("ID");
                String addressType = resultSet.getString("TYPE");
                String country = resultSet.getString("COUNTRY");
                String city = resultSet.getString("CITY");
                String town = resultSet.getString("TOWN");
                String district = resultSet.getString("DISTRICT");
                String postalCode = resultSet.getString("POSTAL_CODE");
                String text = resultSet.getString("TEXT");

                // Display values
                System.out.print(addressID + "\t");
                System.out.print(addressType + "\t");
                System.out.print(country + "\t");
                System.out.print(city + "\t");
                System.out.print(town + "\t");
                System.out.print(district + "\t");
                System.out.print(postalCode + "\t");
                System.out.println(text);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }


    ViewData selectLastAddressIDOperation(ModelData modelData) throws Exception {

        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                addressID = resultSet.getInt("ID");
            }
            resultSet.close();
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getAll());

        return new ViewData("Person", "selectlastaddressid", parameters);
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getAll());

        return new ViewData("Address", "selectlastaddressid", parameters);
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
        Integer addressID = getInteger("Address ID : ", true);
        String addressType = getString("Home or Office? : ", true);
        String country = getString("Country? : ", true);
        String city = getString("City? : ", true);
        String town = getString("Town? : ", true);
        String district = getString("District? : ", true);
        String postalCode = getString("Postal Code? : ", true);
        String text = getString("Text explanation : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (addressID != null) whereParameters.put("ID", addressID);
        if (addressType != null) whereParameters.put("IS_TURKISH", addressType);
        if (country != null) whereParameters.put("TC_PN", country);
        if (city != null) whereParameters.put("NAME", city);
        if (town != null) whereParameters.put("SURNAME", town);
        if (district != null) whereParameters.put("EMAIL", district);
        if (postalCode != null) whereParameters.put("PHONE", postalCode);
        if (text != null) whereParameters.put("PHONE2", text);

        return whereParameters;
    }

    Map<String, Object> getAll() throws Exception {

        Map<String, Object> whereParameters = new HashMap<>();
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Address", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "TYPE, COUNTRY, CITY,TOWN,DISTRICT,POSTAL_CODE,TEXT ");

        List<Object> rows = new ArrayList<>();
        String addressType, country, city, town, district, postalCode, text;

        System.out.println("Enter your address information:");
        addressType = getString("Enter your address type(Ex:Home / Office) : ", true);
        country = getString("Enter your country? : ", true);
        city = getString("Enter your city? : ", true);
        town = getString("Enter your town? : ", true);
        district = getString("Enter your district? : ", true);
        postalCode = getString("Enter your postal code? : ", true);
        text = getString("Enter explanation for your address(Optional) : ", true);
        System.out.println();

        if (addressType != null && country != null && city != null && town != null && district != null
                && postalCode != null) {
            rows.add(new Address(addressType, country, city, town, district, postalCode, text));
        }

        parameters.put("rows", rows);

        return new ViewData("Address", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer addressID = getInteger("Address ID : ", true);
        String addressType = getString("Home or Office? : ", true);
        String country = getString("Country? : ", true);
        String city = getString("City? : ", true);
        String town = getString("Town? : ", true);
        String district = getString("District? : ", true);
        String postalCode = getString("Postal Code? : ", true);
        String text = getString("Text explanation : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (addressID != null) updateParameters.put("ID", addressID);
        if (addressType != null) updateParameters.put("TYPE", addressType);
        if (country != null) updateParameters.put("COUNTRY", country);
        if (city != null) updateParameters.put("CITY", city);
        if (town != null) updateParameters.put("TOWN", town);
        if (district != null) updateParameters.put("DISTRICT", district);
        if (postalCode != null) updateParameters.put("POSTAL_CODE", postalCode);
        if (text != null) updateParameters.put("TEXT", text);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Address", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Address", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Address View";
    }

}
