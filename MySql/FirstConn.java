package MysqlJava;

import java.sql.*;

public class FirstConn {
    public static void main(String[] args) {

         Connection connect = null;
         Statement statement = null;
         PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MuseumApi", "root", "9876");
            if (con != null) {
                System.out.println("Connected to the database MuseumApi");
            }
            Statement stmt = con.createStatement();
            ResultSet resultSet =  stmt
                    .executeQuery("select * from ApiInfoBase");
//            for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
//                System.out.print("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
//            }

            while(resultSet.next())
            {
//                System.out.println(resultSet.getString(1)+ "    " +resultSet.getString(2)+ "     "+
//                        resultSet.getString(3));
                  for(int i=1;i<58;i++)
                      System.out.print(resultSet.getString(i)+"     ");
                System.out.println(" ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("{} occurred while dealing with database" + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}//
