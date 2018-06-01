
import java.sql.*;

public class JdbcConnection {
    public static void main(String[] args) {
        Connection con ;
        Statement statement;
        ResultSet resultSet;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Loaded");

            String server = "//localhost\\DESKTOP-VEIF510\\MENUKASQLSERVER";
            String database = "Menuka13";
            int port = 1433;

            String jdbcUrl = "jdbc:sqlserver:" + server + ":" + port + ";databaseName=" + database + ";integratedSecurity=true";
            //String jdbcUrl = "jdbc:sqlserver://localhost\\ITDEPT:1433;database=Menuka";

            con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connection Obtained");

            statement = con.createStatement();
            System.out.println("Statement is created");
            resultSet = statement.executeQuery("select * from student");

            while(resultSet.next()){
                System.out.println("The name of the person is " + resultSet.getString("name")
                        + " whose roll number is " + String.valueOf(resultSet.getInt("rollno")));
            }

            resultSet.close();
            statement.close();
            con.close();
            System.out.println("Resource Released");

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
