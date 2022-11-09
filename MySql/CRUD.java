/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This RestApi code class is used to fetch data from API and save into CSV.

@Author -> suryaPs (Surya prakash sonI)
 */
package MysqlJava;

import java.sql.*;

import static MysqlJava.MySqlInsertion.con;
import static MysqlJava.MySqlInsertion.logger;

// CRUD OPERATIONS
public class CRUD {

    // select("select * from ApiInfoBase");
    public int select(String tableName,String Column, String Condition) {
        logger.info("Validation TableName, Column & Condition:");
        logger.debug("Table Name - " + tableName + " Column " + Column + " Condition " + Condition);
        if (tableName.equals("")) {
            logger.error("Table Name Missed");
            return -1;
        }
        StringBuilder Command = new StringBuilder();
        if (!Column.equals("") && !Condition.equals("")) {
            Command.append("Select ").append(Column).append(" From ").append(tableName);
            Command.append(" Where ").append(Condition);
        } else if (!Column.equals("")) {
            Command.append("Select ").append(Column).append(" From ").append(tableName);
        } else if (!Condition.equals("")) {
            Command.append("Select * From ").append(tableName);
            Command.append(" Where ").append(Condition);
        } else {
            Command.append("Select * From ").append(tableName);
        }
        logger.debug("Command Passed -" + Command);
        logger.info("Preparing Statement And Executing Sql Command");
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt
                    .executeQuery(Command.toString());
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            logger.debug("Columns - " + rsMetaData);
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                System.out.print(rsMetaData.getColumnName(i) + "     ");
            }
            System.out.println(" ");
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++)
                    System.out.print(resultSet.getString(i) + "             ");
                System.out.println(" ");
            }
            logger.info("Select Execution SuccessFull");
        } catch (SQLException e) {
            logger.error("Sql Exception - " + e);
        }
        return 0;
    }

    // delete("DELETE FROM ApiInfoBase where objectID=5");
    public int delete(String tableName, String Condition) {
        logger.info("Validation TableName");
        logger.debug("Table Name - " + tableName);
        if (tableName.equals("")) {
            logger.error("Table Name Missed");
            return -1;
        }
        StringBuilder Command = new StringBuilder();
        Command.append("Delete From ").append(tableName);
        logger.info("Validation Condition :");
        if (!Condition.equals("")) {
            logger.debug("Condition - " + Condition);
            Command.append(" Where ").append(Condition);
        }
        try {
            logger.info("Preparing Statement And Executing Sql Command");
            PreparedStatement statement = con.prepareStatement(Command.toString());
            statement.executeUpdate(Command.toString());
            logger.debug("Sql Command Passed - " + Command);
            logger.info("Delete Execution SuccessFull");
        } catch (SQLException e) {
            logger.error("TimeOut Error -" + e);
        }
        return 0;
    }

    public int update(String tableName,String column, String values, String Condition) throws SQLException {
        logger.info("Validation TableName, column,values & Condition:");
        logger.debug("Table Name - " + tableName + " column " + column + " Values " + values + " Condition " + Condition);
        if (tableName.equals("")) {
            logger.error("Table Name Missed");
            return -1;
        } else if (column.equals("") || values.equals("")) {
            logger.error("Column - Values Missed");
            return -1;
        }
        StringBuilder Command = new StringBuilder();

        String[] columnsList = column.split(",");
        String[] valueList = values.split(",");

        if (columnsList.length != valueList.length) {
            logger.error("Column - Values Mismatched");
            return -1;
        }

        Command.append("Update ").append(tableName).append(" SET ");

        for (int i = 0; i < columnsList.length; i++) {
            Command.append(columnsList[i]).append(" = ").append(valueList[i]);
            if (i + 1 != columnsList.length)
                Command.append(",");
        }
        if (!Condition.equals(""))
            Command.append(" Where ").append(Condition);

        PreparedStatement statement = con.prepareStatement(Command.toString());
        statement.executeUpdate(Command.toString());
        System.out.println("A user was Updated successfully!");
        return 0;
    }


    public void executeStmt(String command) throws SQLException {

        PreparedStatement statement = con.prepareStatement(command);
        statement.executeUpdate(command);
        System.out.println("A user was deleted successfully!");
    }
}
