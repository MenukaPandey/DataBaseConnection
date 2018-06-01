package PS;

import java.sql.*;

public class MultipleResultDemo {
    public static void main(String[] args) {
        Connection con;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded");

            String server = "//localhost\\DESKTOP-VEIF510\\MENUKASQLSERVER";
            String database = "Menuka13";
            int port = 1433;
            int rsCount = 0;

            String jdbcUrl = "jdbc:sqlserver:" + server + ":" + port + ";databaseName=" + database + ";integratedSecurity=true";
            //String jdbcUrl = "jdbc:sqlserver://localhost\\ITDEPT:1433;database=Menuka";

            con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connection Obtained");

            statement = con.createStatement();
            System.out.println("Statement is created");

            String sql="SELECT * FROM student WHERE RollNo=14;" +"SELECT * FROM student WHERE RollNo=17";
            boolean results=statement.execute(sql);
            do{
                if (results){
                    resultSet =statement.getResultSet();
                    rsCount++;
                    System.out.println("ResultSet#" +rsCount);
                    while (resultSet.next()){
                        int rollno=resultSet.getInt("RollNo");
                        String name=resultSet.getString("Name");
                        System.out.println("The name is "+name +" and rollno is "+rollno);

                    }
                    resultSet.close();
                    results=statement.getMoreResults();

                }

            }while (results);
            statement.close();
            con.close();
        }catch (SQLException se){

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
