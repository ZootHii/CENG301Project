import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.*;

public class PersonView implements ViewInterface {

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
            case "loginCheck":
                return loginCheck();

        }
        return new ViewData("MainMenu", "");
    }

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int personID = resultSet.getInt("ID");
                int isTurkish = resultSet.getInt("IS_TURKISH");
                String tcPn = resultSet.getString("TC_PN");
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                String eMail = resultSet.getString("EMAIL");
                String phoneNumber = resultSet.getString("PHONE");
                String phoneNumber2 = resultSet.getString("PHONE2");
                String fax = resultSet.getString("FAX");
                int gender = resultSet.getInt("GENDER");
                Date birthDate = resultSet.getDate("BIRTHDATE");
                int age = resultSet.getInt("AGE");
                int addressID = resultSet.getInt("ADDRESS_ID");

                // Display values
                System.out.print(personID + "\t");
                System.out.print(isTurkish + "\t");
                System.out.print(tcPn + "\t");
                System.out.print(name + "\t");
                System.out.print(surname + "\t");
                System.out.print(eMail + "\t");
                System.out.print(phoneNumber + "\t");
                System.out.print(phoneNumber2 + "\t");
                System.out.print(fax + "\t");
                System.out.print(gender + "\t");
                System.out.print(birthDate + "\t");
                System.out.print(age + "\t");
                System.out.println(addressID);
            }
            resultSet.close();
        }
        return new ViewData("MainMenu", "");
    }

    public static String PersonTC_PN;

    private ViewData loginCheck() throws Exception {
        ResultSet resultSet = PersonModel.selectTC();
        Map<String, String> tc_pass = new HashMap<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                tc_pass.put(resultSet.getString("TC_PN"), resultSet.getString("PASSWORD"));
            }
            resultSet.close();
        }

        PersonTC_PN = getString("Please enter your TC or PN : ", true);
        String pass = getString("Enter your password : ", true);

        while (true) {
            if (tc_pass.containsKey(PersonTC_PN) && tc_pass.get(PersonTC_PN).equals(pass)) {
                Map<String, Object> parameters = new HashMap<>();
                return new ViewData("InterMenu", "");
            } else {
                System.out.println("You entered wrong");
                PersonTC_PN = getString("Please enter your TC or PN : ", true);
                pass = getString("Enter your password : ", true);
            }
        }
    }

    int personID;
    private ViewData selectLastAddressIDOperation(ModelData modelData) throws Exception {

        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                personID = resultSet.getInt("ID");
            }
            resultSet.close();
        }
        Map<String, Object> updateParameters = new HashMap<>();
        Map<String, Object> whereParameters = new HashMap<>();
        Map<String, Object> parameters = new HashMap<>();

        updateParameters.put("ADDRESS_ID", AddressView.addressID);
        whereParameters.put("ID", personID);

        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", whereParameters);

        return new ViewData("Person", "update", parameters); // UPDATE LAST PERSON'S ADDRESS_ID
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("Address", "insert.gui");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("You have been successfully registered! ");

        return new ViewData("InterMenu", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");
        Integer personID = getInteger("Person ID : ", true);
        Integer isTurkish = getInteger("Is Turkish? : ", true);
        String tcPn = getString("TC // PN : ", true);
        String name = getString("Name : ", true);
        String surname = getString("Surname : ", true);
        String eMail = getString("Email : ", true);
        String phoneNumber = getString("Phone Number : ", true);
        String phoneNumber2 = getString("Phone Number 2 : ", true);
        String fax = getString("Fax number : ", true);
        Integer gender = getInteger("Gender : ", true);
        String birthDate = getString("Birthdate : ", true);
        Integer age = getInteger("Age : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (personID != null) whereParameters.put("ID", personID);
        if (isTurkish != null) whereParameters.put("IS_TURKISH", isTurkish);
        if (tcPn != null) whereParameters.put("TC_PN", tcPn);
        if (name != null) whereParameters.put("NAME", name);
        if (surname != null) whereParameters.put("SURNAME", surname);
        if (eMail != null) whereParameters.put("EMAIL", eMail);
        if (phoneNumber != null) whereParameters.put("PHONE", phoneNumber);
        if (phoneNumber2 != null) whereParameters.put("PHONE2", phoneNumber2);
        if (fax != null) whereParameters.put("FAX", fax);
        if (gender != null) whereParameters.put("GENDER", gender);
        if (birthDate != null) whereParameters.put("BIRTHDATE", birthDate);
        if (age != null) whereParameters.put("AGE", age);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "IS_TURKISH, TC_PN, NAME,SURNAME,EMAIL,PHONE,PHONE2,FAX,GENDER,BIRTHDATE, PASSWORD");

        List<Object> rows = new ArrayList<>();
        Integer isTurkish, gender, addressID;
        String tcPn, name, surname, eMail, phoneNumber, phoneNumber2, fax, birthDate, password;
        ResultSet resultSet = PersonModel.selectTC();
        ArrayList<String> tcList = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                tcList.add(resultSet.getString("TC_PN"));

            }
            resultSet.close();
        }
        System.out.println("Fields to insert:");
        isTurkish = getInteger("Is Turkish? : ", true);
        tcPn = getString("TC // PN : ", true);
        while (tcList.contains(tcPn)) {
            System.out.println("This TC or PN is already registered");
            tcPn = getString("TC // PN : ", true);
        }
        PersonTC_PN = tcPn;
        password = getString("Password :", true);
        name = getString("Name : ", true);
        surname = getString("Surname : ", true);
        eMail = getString("Email : ", true);
        phoneNumber = getString("Phone Number : ", true);
        phoneNumber2 = getString("Phone Number 2 : ", true);
        fax = getString("Fax number : ", true);
        gender = getInteger("Gender : ", true);
        birthDate = getString("Birthdate : ", true);
        System.out.println();

        if (isTurkish != null && tcPn != null && name != null && surname != null && eMail != null && phoneNumber != null && phoneNumber2 != null && fax != null && gender != null && birthDate != null && password != null) {
            rows.add(new Person(isTurkish, tcPn, name, surname, eMail, phoneNumber, phoneNumber2, fax, gender, birthDate, password));
        }

        parameters.put("rows", rows);

        return new ViewData("Person", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer personID = getInteger("Person ID : ", true);
        Integer isTurkish = getInteger("Is Turkish? : ", true);
        String tcPn = getString("TC / PN : ", true);
        String password = getString("password : ", true);
        String name = getString("Name : ", true);
        String surname = getString("Surname : ", true);
        String eMail = getString("Email : ", true);
        String phoneNumber = getString("Phone Number : ", true);
        String phoneNumber2 = getString("Phone Number 2 : ", true);
        String fax = getString("Fax number : ", true);
        Integer gender = getInteger("Gender : ", true);
        String birthDate = getString("Birthdate : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (personID != null) updateParameters.put("ID", personID);
        if (isTurkish != null) updateParameters.put("IS_TURKISH", isTurkish);
        if (tcPn != null) updateParameters.put("TC_PN", tcPn);
        if (password != null) updateParameters.put("PASSWORD", password);
        if (name != null) updateParameters.put("NAME", name);
        if (surname != null) updateParameters.put("SURNAME", surname);
        if (eMail != null) updateParameters.put("EMAIL", eMail);
        if (phoneNumber != null) updateParameters.put("PHONE", phoneNumber);
        if (phoneNumber2 != null) updateParameters.put("PHONE2", phoneNumber2);
        if (fax != null) updateParameters.put("FAX", fax);
        if (gender != null) updateParameters.put("GENDER", gender);
        if (birthDate != null) updateParameters.put("BIRTHDATE", birthDate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Person View";
    }
}
