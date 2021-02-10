import java.sql.ResultSet;
import java.util.*;

public class PersonView implements ViewInterface {

    int personID;
    public static String personTC_PN;
    public static String personPassword;

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
            case "selectLastPersonIDOperation":
                return selectLastPersonIDOperation();
            case "loginCheck":
                return loginCheck();

        }
        return null;
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("You have been successfully registered! ");
        System.out.println();

        return new ViewData("InterMenu", "normal");
    }

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

        personTC_PN = getString("Please enter your TC or PN : ", true);
        personPassword = getString("Enter your password : ", true);

        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> whereParameters = new HashMap<>();

        while (true) {
            if (tc_pass.containsKey(personTC_PN) && tc_pass.get(personTC_PN).equals(personPassword)) {
                String where = "P.TC_PN = " + personTC_PN + " AND P.PASSWORD = " + personPassword;
                ResultSet resultSet1 = EmployeeModel.employeeLoginSelect(where);

                if (resultSet1.next()) {
                    System.out.println();
                    System.out.println("You logged in successfully as an authority!");
                    resultSet1.close();
                    return new ViewData("InterMenu", "auth");
                } else {
                    System.out.println();
                    System.out.println("You logged in successfully!");
                    System.out.println();
                    return new ViewData("InterMenu", "normal");
                }


            } else {
                System.out.println();
                System.out.println("You entered wrong, please enter again");
                System.out.println();
                personTC_PN = getString("Please enter your TC or PN : ", true);
                personPassword = getString("Enter your password : ", true);
            }
        }
    }

    private ViewData selectLastPersonIDOperation() throws Exception {
        ResultSet resultSet = PersonModel.selectLastPersonID();

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

        PersonModel.updatePersonWithAddress(updateParameters, whereParameters);
        return new ViewData("InterMenu", "normal"); // UPDATE LAST PERSON'S ADDRESS_ID
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

        System.out.println("Enter your information");
        isTurkish = getInteger("Are you Turkish ?(1 for yes 0 for no) : ", false);
        tcPn = getString("Enter your Turkish Identity number or Passport number : ", false);
        while (tcList.contains(tcPn)) {
            System.out.println("This Turkish Identity number or Passport number is already registered");
            tcPn = getString("Enter your Turkish Identity number or Passport number again : ", false);
        }
        personTC_PN = tcPn;
        password = getString("Create a password : ", false);
        name = getString("Enter your name : ", false);
        surname = getString("Enter your surname : ", false);
        eMail = getString("Enter your email : ", false);
        phoneNumber = getString("Enter your phone number : ", false);
        phoneNumber2 = getString("Enter your home phone number : ", true);
        if (phoneNumber2 == null) {
            phoneNumber2 = "";
        }
        fax = getString("Enter your fax number : ", true);
        if (fax == null) {
            fax = "";
        }
        gender = getInteger("Are you male or female ?(1 for male 0 for female) : ", false);
        birthDate = getString("Enter your birthdate (Ex:yyyy-mm-dd): ", false);
        System.out.println();

        rows.add(new Person(isTurkish, tcPn, name, surname, eMail, phoneNumber, phoneNumber2, fax, gender, birthDate, password));

        parameters.put("rows", rows);

        return new ViewData("Person", "insert", parameters);
    }

    @Override
    public String toString() {
        return "Person View";
    }

    ViewData insertOperation(ModelData modelData) throws Exception {

        return new ViewData("Address", "insert.gui");
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
