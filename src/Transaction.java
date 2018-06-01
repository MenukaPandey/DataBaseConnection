import java.sql.*;

public class Transaction {
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

            String jdbcUrl = "jdbc:sqlserver:" + server + ":" + port + ";databaseName=" + database + ";integratedSecurity=true";
            //String jdbcUrl = "jdbc:sqlserver://localhost\\ITDEPT:1433;database=Menuka";

            con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connection Obtained");

            statement = con.createStatement();
            System.out.println("Statement is created");
            con.setAutoCommit(true);
            String sql1="UPDATE student SET RollNo=RollNo+1"+ "WHERE Name='Menuka'";
            String sql2="UPDATE student SET RollNo=RollNo+2" +"WHERE Name='Pemba'";

            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            con.commit();
            resultSet=statement.executeQuery("SELECT * FROM student");
            System.out.println("After the transaction is complete");
            while (resultSet.next()){
                System.out.println("The name of the person is "+
                        resultSet.getString("Name")+
                        " whose rollno is "+
                        resultSet.getInt("RollNo"));

            }
            resultSet.close();
            statement.close();

        }catch (SQLException se){

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
