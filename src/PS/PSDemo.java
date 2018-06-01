package PS;

import java.sql.*;
import java.util.Scanner;

public class PSDemo {
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String server="//localhost\\DESKTOP-VEIF510\\MENUKASQLSERVER";
    static String database="Menuka13";
    static int port= 1433;

    public static void main(String[] args) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        Scanner input = new Scanner(System.in);
        int b,c;
        String a;
        String conString = "jdbc:sqlserver:" + server + ":" + port + ";databaseName=" + database + ";integratedSecurity=true";

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(conString);

            System.out.println("Press 1 for INSERT\nPress 2 for UPDATE\nPress 3 for DELETE");
            System.out.print(">>");
            c= input.nextInt();
            Scanner inputNew = new Scanner(System.in);
            switch(c) {
                case 1:
                    String insertString = "insert into  STUDENT values(?,?)";
                    statement = connection.prepareStatement(insertString);
                    System.out.print("Enter name: ");
                    a = inputNew.nextLine();
                    statement.setString(1, a);
                    System.out.print("Enter roll no: ");
                    b = inputNew.nextInt();
                    statement.setInt(2, b);
                    statement.executeUpdate();
                    break;

                case 2:
                    String updateString = "UPDATE student SET name=? where rollno=?";
                    statement = connection.prepareStatement(updateString);
                    System.out.print("Enter name: ");
                    a = inputNew.nextLine();
                    statement.setString(1, a);
                    System.out.print("Enter roll no: ");
                    b = inputNew.nextInt();
                    statement.setInt(2, b);
                    statement.executeUpdate();
                    break;

                case 3:
                    String deleteString = "DELETE student where rollno=?";
                    statement = connection.prepareStatement(deleteString);
                    System.out.print("Enter roll no: ");
                    b = inputNew.nextInt();
                    statement.setInt(1, b);
                    statement.executeUpdate();
                    break;
            }

            Statement statement1 = connection.createStatement();
            resultSet = statement1.executeQuery("SELECT * FROM student");

            while(resultSet.next()){
                System.out.println("The name of the person is "+
                        resultSet.getString("name") +
                        " whose roll no. is " +
                        resultSet.getInt("rollno"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
        }
    }
}