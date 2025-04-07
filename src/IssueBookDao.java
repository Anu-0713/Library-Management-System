import java.sql.*;

public class IssueBookDao {

    public static boolean checkBook(String bookcallno) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE callno = ?");
            ps.setString(1, bookcallno);
            ResultSet rs = ps.executeQuery();
            status = rs.next(); // if there is a record, book exists
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int save(String bookcallno, int studentid, String studentname, String studentcontact) {
        int status = 0;
        try {
            Connection con = DB.getConnection();

            // Update book quantity
            PreparedStatement ps = con.prepareStatement("UPDATE books SET issued = issued + 1 WHERE callno = ?");
            ps.setString(1, bookcallno);
            int updateStatus = ps.executeUpdate();

            if (updateStatus > 0) {
                // Insert issued book record
                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO issuebooks(bookcallno, studentid, studentname, studentcontact, issueddate) VALUES (?, ?, ?, ?, CURRENT_DATE)"
                );
                ps2.setString(1, bookcallno);
                ps2.setInt(2, studentid);
                ps2.setString(3, studentname);
                ps2.setString(4, studentcontact);

                status = ps2.executeUpdate();
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
