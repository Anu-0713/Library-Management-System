import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibrarianDao {

    public static int save(String name, String password, String email, String address, String city, String contact) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO librarian (name, password, email, address, city, contact) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, city);
            ps.setString(6, contact);

            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM librarian WHERE id = ?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM librarian WHERE name = ? AND password = ?");
            ps.setString(1, name);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
