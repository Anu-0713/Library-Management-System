import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBConnection {
	public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:Port/Your_database";// ⚠️ Replace with your database name 
        String username = "YourUsername";// ⚠️ Replace with your Username
        String password = "Your_password"; // ⚠️ Replace with your password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
