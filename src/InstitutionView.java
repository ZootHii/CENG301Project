import java.sql.ResultSet;
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
        }

        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {

        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int instID = resultSet.getInt("ID");
                String licenseNumber = resultSet.getString("LICENSE_NUMBER");
                String name = resultSet.getString("NAME");
                String eMail = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                String fax = resultSet.getString("FAX");
                int addressID = resultSet.getInt("ADDRESS_ID");
                int boundToID = resultSet.getInt("BOUND_TO_ID");
                int type = resultSet.getInt("TYPE");


                // Display values
                //System.out.print(instID + "\t");
                System.out.print(licenseNumber + "\t");
                System.out.print(name + "\t");
                System.out.println(eMail);
                /*System.out.print(phone + "\t");
                System.out.print(fax + "\t");
                System.out.print(addressID + "\t");
                System.out.print(boundToID + "\t");*/
                ;
                //System.out.println(type);
            }
            resultSet.close();
        }
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("whereParameters", getWhereParameters());
        return new ViewData("Application", "getLicenceID", parameters);
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
        Integer instID = getInteger("Institution ID : ", true);
        String licenseNumber = getString("License Number : ", true);
        String name = getString("Name : ", true);
        String eMail = getString("eMail : ", true);
        String phone = getString("Phone : ", true);
        String fax = getString("Fax : ", true);
        Integer addressID = getInteger("Address ID : ", true);
        Integer boundToID = getInteger("Bound To ID : ", true);
        Integer type = getInteger("Type : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (instID != null) whereParameters.put("ID", instID);
        if (licenseNumber != null) whereParameters.put("LICENSE_NUMBER", licenseNumber);
        if (name != null) whereParameters.put("NAME", name);
        if (eMail != null) whereParameters.put("EMAIL", eMail);
        if (phone != null) whereParameters.put("PHONE", phone);
        if (fax != null) whereParameters.put("FAX", fax);
        if (addressID != null) whereParameters.put("ADDRESS_ID", addressID);
        if (boundToID != null) whereParameters.put("BOUND_TO_ID", boundToID);
        if (type != null) whereParameters.put("TYPE", type);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Institution", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "LICENSE_NUMBER, NAME, EMAIL,PHONE,FAX,ADDRESS_ID,BOUND_TO_ID,TYPE");

        List<Object> rows = new ArrayList<>();

        String licenseNumber, name, eMail, phone, fax;
        Integer addressID, boundToID, type;
        do {
            System.out.println("Fields to insert:");
            licenseNumber = getString("License Number : ", true);
            name = getString("Name : ", true);
            eMail = getString("eMail : ", true);
            phone = getString("Phone : ", true);
            fax = getString("Fax : ", true);
            addressID = getInteger("Address ID : ", true);
            boundToID = getInteger("Bound To ID : ", true);
            type = getInteger("Type : ", true);
            System.out.println();

            if (licenseNumber != null && name != null && eMail != null && phone != null && fax != null && addressID != null && boundToID != null && type != null) {
                rows.add(new Institution(licenseNumber, name, eMail, phone, fax, addressID, boundToID, type));
            }
        }
        while (licenseNumber != null && name != null && eMail != null && phone != null && fax != null && addressID != null && boundToID != null && type != null);

        parameters.put("rows", rows);

        return new ViewData("Institution", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String licenseNumber = getString("License Number : ", true);
        String name = getString("Name : ", true);
        String eMail = getString("eMail : ", true);
        String phone = getString("Phone : ", true);
        String fax = getString("Fax : ", true);
        Integer addressID = getInteger("Address ID : ", true);
        Integer boundToID = getInteger("Bound To ID : ", true);
        Integer type = getInteger("Type : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (licenseNumber != null) updateParameters.put("LICENSE_NUMBER", licenseNumber);
        if (name != null) updateParameters.put("NAME", name);
        if (eMail != null) updateParameters.put("EMAIL", eMail);
        if (phone != null) updateParameters.put("PHONE", phone);
        if (fax != null) updateParameters.put("FAX", fax);
        if (addressID != null) updateParameters.put("ADDRESS_ID", addressID);
        if (boundToID != null) updateParameters.put("BOUND_TO_ID", boundToID);
        if (type != null) updateParameters.put("TYPE", type);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Institution", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Institution", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Institution View";
    }

}
