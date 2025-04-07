import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ReturnBook extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/Your_DataBase";
    private static final String USER = "Your_Username";
    private static final String PASSWORD = "Your_Password";
            // GUI components
        private JTextField bookcallnoField;
        private JTextField studentidField;
        private JButton returnButton;
        private JButton backButton;

        public ReturnBook() {
            // Frame settings
            setTitle("Return Book");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(4, 2, 10, 10));

            // Components
            JLabel bookcallnoLabel = new JLabel("Book Callno:");
            bookcallnoField = new JTextField();

            JLabel studentidLabel = new JLabel("Student ID:");
            studentidField = new JTextField();

            returnButton = new JButton("Return Book");
            backButton = new JButton("Back");

            add(bookcallnoLabel);
            add(bookcallnoField);
            add(studentidLabel);
            add(studentidField);
            add(returnButton);
            add(backButton);

            // Button actions
            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String bookcallno = bookcallnoField.getText();
                    int studentid;
                    try {
                        studentid = Integer.parseInt(studentidField.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ReturnBook.this, "Invalid Student ID format!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int status = returnBook(bookcallno, studentid);
                    if (status > 0) {
                        JOptionPane.showMessageDialog(ReturnBook.this, "Book returned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(ReturnBook.this, "Unable to return book. Check details.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    LibrarianSuccess.main(new String[]{}); // Navigate back
                    dispose(); // Close current window
                }
            });

            setVisible(true);
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public static int returnBook(String bookcallno, int studentid) {
            int status = 0;

            String checkIssueQuery = "SELECT * FROM issuebooks WHERE bookcallno = ? AND studentid = ?";
            String deleteIssueQuery = "DELETE FROM issuebooks WHERE bookcallno = ? AND studentid = ?";
            String updateBookQuery = "UPDATE books SET issued = issued - 1 WHERE callno = ?";

            try (Connection con = getConnection();
                 PreparedStatement checkIssueStmt = con.prepareStatement(checkIssueQuery)) {

                checkIssueStmt.setString(1, bookcallno);
                checkIssueStmt.setInt(2, studentid);

                try (ResultSet rs = checkIssueStmt.executeQuery()) {
                    if (rs.next()) {
                        try (PreparedStatement deleteIssueStmt = con.prepareStatement(deleteIssueQuery)) {
                            deleteIssueStmt.setString(1, bookcallno);
                            deleteIssueStmt.setInt(2, studentid);

                            status = deleteIssueStmt.executeUpdate();

                            if (status > 0) {
                                try (PreparedStatement updateBookStmt = con.prepareStatement(updateBookQuery)) {
                                    updateBookStmt.setString(1, bookcallno);
                                    int bookUpdateStatus = updateBookStmt.executeUpdate();

                                    if (bookUpdateStatus > 0) {
                                        System.out.println("Book return processed and book count updated successfully.");
                                    } else {
                                        System.out.println("Book return processed but failed to update book count.");
                                    }
                                }
                            } else {
                                System.out.println("Failed to delete issue record.");
                            }
                        }
                    } else {
                        System.out.println("No such issued book found.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("Database error during book return: " + e.getMessage());
                e.printStackTrace();
            }

            return status;
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new ReturnBook());
        }
    }

