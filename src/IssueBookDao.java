/*package com.library.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueBookDao {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Emily_03";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static int save(String callno, String name, String author, String publisher, int quantity) {
        int status = 0;
        String sql = "INSERT INTO books(callno, name, author, publisher, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, callno);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static List<String> getAllBooks() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
*/
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
