import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class InstitutionModel implements ModelInterface {

    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	ID, LICENSE_NUMBER, NAME, EMAIL,PHONE,FAX,ADDRESS_ID,BOUND_TO_ID,TYPE ");
        sql.append(" FROM Institution ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY ID");

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    public static ResultSet licenceCheck() throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	ID, LICENSE_NUMBER ");
        sql.append(" FROM Institution ");
        sql.append(" ORDER BY ID");

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Institution (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof Institution) {
                rowCount++;

                Institution institution = (Institution) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(institution.getByName(fieldName)));
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

        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        return rowCount;
    }

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE Institution SET ");
        int appendCount = 0;
        for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
            sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
            if (++appendCount < updateParameters.size()) {
                sql.append(", ");
            }
        }

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

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
        sql.append(" DELETE FROM Institution ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

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

    @Override
    public String toString() {
        return "Institution Model";
    }
}
