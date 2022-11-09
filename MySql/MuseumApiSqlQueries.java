package MysqlJava;

import java.sql.*;
import static RestApi.Restapi.logger;

public class MuseumApiSqlQueries {

    // Static Connection Variable.
    static Connection con = null;

    // This method is to Execute Particular Query.
    private void executeQuery(String Query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(Query);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            logger.debug("Columns - " + rsMetaData);

            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                logger.debug(rsMetaData.getColumnName(i));
                System.out.print(rsMetaData.getColumnName(i) + "       ");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    logger.debug(resultSet.getString(i));
                    System.out.print(resultSet.getString(i) + "                ");
                }
                logger.debug('\n');
                System.out.println();
            }
        } catch (SQLException e) {
            logger.error("SqlException - " + e);
            System.out.println("SQL Exception : " + e);
        } catch (Exception e) {
            logger.error("Exception - " + e);
            System.out.println("EXCEPTION IN THREAD : " + e);
        }
        System.out.println("Query Executed");
    }

    // This method is to Select particular Query by Number.
    public static void selectQuery(int queryNumber) {

        // Strings Containing Queries.
        String selectAll = "SELECT * FROM ApiInfoBase;";
        String limit = "SELECT * FROM ApiInfoBase WHERE accessionYear=1967 ORDER BY objectId DESC LIMIT 2;";
        String between = "SELECT * FROM ApiInfoBase WHERE accessionYear BETWEEN 1960 AND 2000 " +
                " AND objectId NOT IN (1,2,3);";
        String count = "SELECT COUNT(*) FROM ApiInfoBase WHERE objectId > 5";
        String nested = "SELECT * FROM ApiInfoBase WHERE objectId IN (SELECT objectId FROM ApiInfoBase" +
                " WHERE accessionYear > 1970) ORDER BY accessionYear ;";
        String nestedCount = "SELECT count(accessionYear) FROM ApiInfoBase WHERE objectEndDate IN " +
                "(SELECT objectEndDate FROM ApiInfoBase WHERE objectBeginDate BETWEEN 1900 AND 2000);";
        String nestedGroupBy = "select objectId From ApiInfoBase where objectId IN" +
                "(select objectID from ApiInfoBase where accessionYear=1967 group by objectId);";
        String nestedMultirow = "select objectId,accessionYear from ApiInfoBase where objectId IN " +
                "(select objectID from ApiInfoBase where accessionYear=1967);";
        String nestedAvg = "select AVG(objectId) From ApiInfoBase where objectId IN" +
                "(select objectID from ApiInfoBase where accessionYear=1967 group by objectId);";
        String notNull = "SELECT objectId,Constituents from ApiInfoBase WHERE Constituents IS NOT NULL order by objectId;";
        String groupBy = "SELECT Constituents from ApiInfoBase  group by Constituents;";

        MuseumApiSqlQueries obj = new MuseumApiSqlQueries();
        // Switch is to Select Particular queries.
        switch (queryNumber) {
            case 0:
                logger.debug("Query : " + selectAll);
                obj.executeQuery(selectAll);
                break;
            case 1:
                logger.debug("Query : " + limit);
                obj.executeQuery(limit);
                break;
            case 2:
                logger.debug("Query : " + between);
                obj.executeQuery(between);
                break;
            case 3:
                logger.debug("Query : " + nested);
                obj.executeQuery(nested);
                break;
            case 4:
                logger.debug("Query : " + nestedCount);
                obj.executeQuery(nestedCount);
                break;
            case 5:
                logger.debug("Query : "+nestedGroupBy);
                obj.executeQuery(nestedGroupBy);
                break;
            case 6:
                logger.debug("Query : " + nestedMultirow);
                obj.executeQuery(nestedMultirow);
                break;
            case 7:
                logger.debug("Query : "+nestedAvg);
                obj.executeQuery(nestedAvg);
                break;
            case 8:
                logger.debug("Query : " + count);
                obj.executeQuery(count);
                break;
            case 9:
                logger.debug("Query : " + notNull);
                obj.executeQuery(notNull);
                break;
            case 10:
                logger.debug("Query : " + groupBy);
                obj.executeQuery(groupBy);
                break;
        }
    }


    public static void main(String[] args) {
        try {
            // Establishing MySql Database Connection.
            logger.info("Establishing MySql Database Connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
            logger.debug("Connection Request : " + con);
            if (con != null) {
                logger.info("DATABASE CONNECTED");
                System.out.println("Connected to the database MuseumApi");
            }
            // Selecting Particular Query.

            selectQuery(0);
            System.out.println("\n\n");
            selectQuery(4);


        } catch (SQLException e) {
            logger.error("SqlException - " + e);
            System.out.println("SQL Exception : " + e);
        } catch (Exception e) {
            logger.error("Exception - " + e);
            System.out.println("EXCEPTION IN THREAD : " + e);
        } finally {
            // Closing Database connection.
            logger.info("Closing Sql Database connection");
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    logger.error("SqlException While Closing Database Connection -" + ex);
                    ex.printStackTrace();
                }
            }
        }
    }
}
