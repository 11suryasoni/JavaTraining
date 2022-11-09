package MysqlJava;

import java.sql.*;

import static RestApi.Restapi.logger;

public class EmployeeApiSqlQueries {
    static Connection con = null;

    private void executeQuery(String Query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(Query);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            logger.debug("Columns - " + rsMetaData);

            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                System.out.print(rsMetaData.getColumnName(i) + "                ");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    System.out.print(resultSet.getString(i) + "                ");
                }
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


    public static void selectQuery(int queryNumber) {
        String selectAll = "SELECT * FROM current_dept_emp LIMIT 10;";

        // Left join employees and salaries table using emp_no.
        String leftJoin = "select employees.emp_no,employees.first_name,employees.last_name,salaries.salary from employees " +
                "left join salaries using(emp_no) limit 10;";

        // Right join employees and salaries table using emp_no.
        String rightJoin = "select employees.emp_no,employees.first_name,employees.last_name,salaries.salary from employees " +
                "right join salaries using(emp_no) limit 10;";

        // Right join employees and salaries table and inner join titles table.
        String innerJoin = "select employees.emp_no,employees.first_name,salaries.salary, titles.title from employees right " +
                "join salaries using(emp_no) inner join titles using(emp_no) limit 10;";

        // Inner Join with employees and departments table.
        String innerJoin2 = "select e.emp_no,e.dept_no,d.dept_name from dept_emp e inner join departments d using(dept_no)" +
                " limit 5;";

        // Right Join with employees and departments table with condition.
        String joinWithCondition = "select e.emp_no,e.dept_no,d.dept_name from dept_emp e right join" +
                " departments d using(dept_no) where e.emp_no = 10011 limit 5;";

        // Natural join of Current Department Employee and Departments.
        String naturalJoin = "select * from current_dept_emp natural join departments limit 10;";

        String selfJoin = "select  t1.emp_no , t1.salary from salaries t1,salaries t2 where  t1.emp_no = t2.emp_no " +
                "AND t1.salary <> t2.salary limit 10;";

        EmployeeApiSqlQueries obj = new EmployeeApiSqlQueries();
        switch (queryNumber) {
            case 0:
                obj.executeQuery(selectAll);
                break;
            case 1:
                obj.executeQuery(leftJoin);
                break;
            case 2:
                obj.executeQuery(rightJoin);
                break;
            case 3:
                obj.executeQuery(innerJoin);
                break;
            case 4:
                obj.executeQuery(innerJoin2);
                break;
            case 5:
                obj.executeQuery(joinWithCondition);
                break;
            case 6:
                obj.executeQuery(naturalJoin);
                break;
            case 7:
                obj.executeQuery(selfJoin);
                break;
//            case 8:
//                obj.executeQuery(count);
//                break;
//            case 9:
//                obj.executeQuery(notNull);
//                break;
//            case 10:
//                obj.executeQuery(groupBy);
//                break;
        }
    }


    public static void main(String[] args) {
        try {
            // Establishing MySql Database Connection.
            logger.info("Establishing MySql Database Connection.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "9876");
            logger.debug("Connection Request : " + con);
            if (con != null) {
                logger.info("DATABASE CONNECTED");
                System.out.println("Connected to the database employee");
            }


            selectQuery(3);
            System.out.println("\n\n");
            //   selectQuery(4);


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