/*
Copyright (c) 2022. -> All Rights Reserved
Unauthorized Copying or Redistribution of this file in Source or Class file
format is Strictly Prohibited.

This RestApi code class is used to fetch data from API and save into CSV.

@Author -> suryaPs (Surya prakash sonI)
 */
package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class CRUD {

    static final Logger logger = LogManager.getLogger(CRUD.class);

    // select("select * from ApiInfoBase");
    public String select(String tableName, String Column, String Condition) {
        Connection con = null;
        JSONArray result = null;
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

            logger.info("Validation TableName, Column & Condition:");
            logger.debug("Table Name - " + tableName + " Column " + Column + " Condition " + Condition);
            if (tableName.equals("")) {
                logger.error("Table Name Missed");
                return "-1";
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

            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt
                    .executeQuery(Command.toString());
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            logger.debug("Columns - " + rsMetaData);
//            int nColumns = rsMetaData.getColumnCount();
//            for (int i = 1; i <= nColumns; ++i) {
//                buf.append(rsMetaData.getColumnName(i));
//                buf.append(" = ");
//                buf.append(resultSet);
//                if (i < nColumns)
//                    buf.append(" , ");
//                System.out.println(buf);
//            }
            result = new JSONArray();
            int count = rsMetaData.getColumnCount();
            while (resultSet.next()) {

                JSONObject row = new JSONObject();
                for (int i = 1; i <= count; i++) {
                    row.put(rsMetaData.getColumnName(i), resultSet.getString(i));
                }
                result.put(row);
            }

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
        return result.toString();
    }

    // delete("DELETE FROM ApiInfoBase where objectID=5");
    public void delete(String tableName, String id) throws ClassNotFoundException, SQLException, IOException {

        Connection con = null;
        try {
            // Establishing MySql Database Connection.
            logger.info("Establishing MySql Database Connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
            logger.debug("Connection Request : " + con);
            if (con != null) {
                logger.error("DATABASE CONNECTED");
                System.out.println("Connected to the database MuseumApi");
            }

            logger.info("Validation TableName");
            logger.debug("Table Name - " + tableName);
            if (tableName.equals("")) {
                logger.error("Table Name Missed");
                throw new IOException("2");
            }
            StringBuilder Command = new StringBuilder();
            Command.append("Delete From ").append(tableName);
            logger.info("Validation Condition :");
            if (!id.equals("")) {
                logger.debug("Condition - " + id);
                Command.append(" where objectId = ").append(id);
            }
            try {
                logger.info("Preparing Statement And Executing Sql Command");
                PreparedStatement statement = con.prepareStatement(Command.toString());
                statement.executeUpdate(Command.toString());
                logger.debug("Sql Command Passed - " + Command);
                logger.info("Delete Execution SuccessFull");
            } catch (SQLException e) {
                logger.error("TimeOut Error -" + e);
                throw new SQLException(String.valueOf(e.getErrorCode()));
            }
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException - " + e);
            throw new ClassNotFoundException("1");
        } catch (SQLException e) {
            logger.error("SqlException - " + e);
            System.out.println("SQL Exception : " + e);
            throw new SQLException(String.valueOf(e.getErrorCode()));
        } catch (IOException e) {
            logger.error("Exception - " + e);
            System.out.println("EXCEPTION IN MAIN THREAD : " + e);
            throw new IOException("2");
        } catch (RuntimeException e) {
            logger.error("Exception - " + e);
            System.out.println("EXCEPTION IN MAIN THREAD : " + e);
            throw new RuntimeException("3");
        } finally {
            // Closing Database connection.
            logger.info("Closing Sql Database connection");
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    logger.error("SqlException While Closing Database Connection -" + ex);
                    ex.printStackTrace();
                    throw new SQLException(String.valueOf(ex.getErrorCode()));
                }
            }
        }
    }

    // delete("Update ApiInfoBase set objectID=5");
    public String update(String json) throws SQLException, ClassNotFoundException, IOException {
        String result = "Old Updated Data";
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Connection to database");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
            Statement stmt = connect.createStatement();
            StringBuilder sb = new StringBuilder("update ApiInfoBase set ");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            String condition = "";

            logger.info("Creating query");
            for (Map.Entry<String, Object> m : map.entrySet()) {
                if (m.getKey().equals("objectId"))
                    condition = "where " + m.getKey() + " = " + m.getValue();
                else
                    sb.append(m.getKey()).append(" = ").append("'").append(m.getValue()).append("'").append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(condition);

            String query = sb.toString();
            if (json != null) {
                logger.info("creating json");
                stmt.executeUpdate(query);
                result = "updated";
                logger.info("json executed");
            } else {
                logger.error("json is invalid");
                throw new IOException("2");
            }
            logger.info("createMuseum table updated");
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException - " + e);
            throw new IOException("2");
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException - " + e);
            throw new ClassNotFoundException("1");
        } catch (SQLException e) {
            logger.error("SQLException - " + e);
            throw new SQLException(String.valueOf(e.getErrorCode()));
        } catch (RuntimeException e) {
            logger.error("RuntimeException - " + e);
            throw new RuntimeException("3");
        } finally {
            try {
                if (connect != null) {
                    logger.info("Connection closed");
                    connect.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException - " + e);
                System.err.println(e.getMessage());
                throw new SQLException(String.valueOf(e.getErrorCode()));
            }
        }
        return result;
    }

    // insert("insert into ApiInfoBase values()");
    public String insert(String tableName, StringBuilder json) throws ClassNotFoundException, SQLException, IOException {
        String output = null;
        System.out.println(tableName + " " + json);
        Connection con;
        try {
            logger.info("connecting to the database");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
        }catch (ClassNotFoundException e){
            logger.error("{} Class Not Found", e.getMessage());
            throw new  ClassNotFoundException("1");
        } catch (SQLException e) {
            logger.error("{} occurred while connecting to the database", e.getMessage());
            throw new  SQLException(String.valueOf(e.getErrorCode()));
        }
        if (json != null) {
            if (json.charAt(0) == '[') {
                json.replace(0, 1, "");
                json.replace(json.length() - 1, json.length(), "");
            }
            System.out.println(json);
            StringBuilder insertQuery = new StringBuilder("insert into " + tableName + "(");
            org.json.JSONObject obj = new org.json.JSONObject(json.toString());
            obj.keys().forEachRemaining(s -> insertQuery.append(s).append(","));
            insertQuery.deleteCharAt(insertQuery.length() - 1);
            insertQuery.append(") values(");
            obj.keys().forEachRemaining(s -> {
                insertQuery.append("'").append(String.valueOf(obj.get(s)).replaceAll("'", "")).append("'").append(",");
            });
            insertQuery.deleteCharAt(insertQuery.length() - 1);
            insertQuery.append(")");
            logger.debug(insertQuery);
            PreparedStatement pstmt1;
            try {
                if (con != null && !con.isClosed()) {
                    pstmt1 = con.prepareStatement(insertQuery.toString());
                    System.out.println((pstmt1.executeUpdate() + "Row's got effected while inserting data"));
                    output = "Inserted successfully";
                    pstmt1.close();
                } else {
                    System.out.println("connection to the database is lost while inserting data");
                    logger.error("connection to the database is lost while inserting data");
                }
            } catch (SQLException e) {
                System.out.println("Error code "+e.getErrorCode());
                logger.error("{} occurred while inserting data into table", e.getMessage());
                throw new  SQLException(String.valueOf(e.getErrorCode()));
            }
        } else{
            logger.error("Wrong Json data");
            throw new IOException("2");
        }
        return output;
    }

}