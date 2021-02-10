import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class EmployeeModel implements ModelInterface{

    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	ID, PERSON_ID, INS_ID,DEPARTMENT_NAME,TITLE, AUTHORITY,AUTH_ID ");
        sql.append(" FROM Employees");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY ID");
        //System.out.println(sql.toString() + "\n");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Employees (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof Employee) {
                rowCount++;

                Employee employee = (Employee) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(employee.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        return rowCount;
    }

    public static ResultSet employeeLoginSelect(String where) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	* ");
        sql.append(" FROM Person AS P INNER JOIN Employees as E\n" +
                "ON P.ID = E.PERSON_ID\n" +
                "INNER JOIN Address AS A\n" +
                "ON A.ID = P.ADDRESS_ID\n" +
                "INNER JOIN Institution AS I\n" +
                "ON E.INS_ID = I.ID ");

        sql.append("WHERE "+where+" ");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    public static ResultSet employeeInsBinder(String where) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	* ");
        sql.append(" FROM Application AS A INNER JOIN Pending AS P\n" +
                "ON P.APP_ID = A.ID\n" +
                "INNER JOIN Employees AS E\n" +
                "ON E.INS_ID = A.RECEIVER_ID\n" +
                "INNER JOIN Person\n" +
                "ON Person.ID = A.SENDER_ID\n" +
                "INNER JOIN PersonForm AS PF\n" +
                "ON PF.ID = A.FORM_ID ");

        sql.append("WHERE A.RECEIVER_ID = "+where+" ");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }


    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Employees SET ");
        int appendCount = 0;
        for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
            sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
            if (++appendCount < updateParameters.size()) {
                sql.append(", ");
            }
        }
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM Employees ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public ResultSet selectLastID(Map<String, Object> whereParameters) throws Exception {
        return null;
    }


    public ResultSet selectlastaddressid(Map<String, Object> whereParameters) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Employees Model";
    }



}
