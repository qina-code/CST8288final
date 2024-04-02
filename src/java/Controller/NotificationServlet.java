package Controller;

import dataaccesslayer.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@WebServlet(name = "NotificationServlet", urlPatterns = {"/NotificationServlet"})
public class NotificationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NotificationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Email configuration
        String host = "smtp-mail.outlook.com";
        String port = "587"; // or 465 for SSL
        final String username = "xxx@algonquinlive.com";
        final String password = "xxx";

        //get current item category
        int categoryId = 3;
        //get current item address(owner address)
        String city = "Ottawa";
        String postal = "K1G 4F3";

        //get user with same preference  or location
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT user.email FROM user JOIN location on user.id = location.user_id "
                     + "JOIN preference ON preference.user_id = user.id "
                     + "WHERE city = ? or preference.category_id = ? or location.postal_code like ?")) {
            pstmt.setString(1, city);
            pstmt.setInt(2, categoryId);
            pstmt.setString(3, "K%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String receiverEmail = rs.getString("user.email");
//                       String receiverEmail = "qiu00038@algonquinlive.com";

                    //send email to the address with info: new listing food
//                    sendEmail(host, port, username, password, receiverEmail, "New Listing in Your Area", "There is a new listing in your area. Please check it out!");
                    System.out.println("Email sent successfully to " + receiverEmail);
                    System.out.println("New surplus food available");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle or throw an exception as needed
        }
    }

    public static void sendEmail(String host, String port, final String username, final String password, String toAddress, String subject, String message) throws MessagingException {
        // Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a default MimeMessage object
        MimeMessage mimeMessage = new MimeMessage(session);

        // Set From: header field of the header
        mimeMessage.setFrom(new InternetAddress(username));

        // Set To: header field of the header
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));

        // Set Subject: header field
        mimeMessage.setSubject(subject);

        // Now set the actual message
        mimeMessage.setText(message);

        // Send message
        Transport.send(mimeMessage);
        System.out.println("Email sent successfully to " + toAddress);
    }
}
