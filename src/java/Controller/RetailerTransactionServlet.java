package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataaccesslayer.ItemDAOImpl;
import dataaccesslayer.UserDAOImpl;
import java.math.BigDecimal;
import model.Item;
import model.User;

@WebServlet("/inventory/RetailerTransactionServlet")
public class RetailerTransactionServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract item details from the request parameters
        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String expirationDateStr = request.getParameter("expirationDate");
        BigDecimal price = new BigDecimal(request.getParameter("price")); 
        
        // Parse the expiration date string into a Date object
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = null;
        try {
            expirationDate = dateFormat.parse(expirationDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing error, if needed
        }

        // Create an instance of ItemDAOImpl
        ItemDAOImpl itemDAO = new ItemDAOImpl();

        // Create a new Item object
       // Create a new Item object
        Item newItem = new Item(0, itemName, quantity, price, expirationDate); // Pass 0 as id parameter


        // Add the new item to the database
        boolean itemAdded = itemDAO.addItem(newItem);





        // Set response content type
        response.setContentType("text/html");

        // Get PrintWriter object
        PrintWriter out = response.getWriter();

        // Write HTML response based on success or failure
        if (itemAdded) {
            out.println("<h1>Item added successfully!</h1>");
            out.println("<p>Thank you for your submission.</p>");
            // Add link to go back to inventory management
            out.println("<p><a href='/FWRP/inventory/add_item.jsp'>Go back to Inventory Management</a></p>");

            // Send notifications to subscribed users
            sendNotificationsToSubscribedUsers(newItem);
        } else {
            out.println("<h1>Error adding item</h1>");
            out.println("<p>There was an error while adding the item. Please try again later.</p>");
            out.println("<p><a href='/FWRP/inventory/add_item.jsp'>Go back to Inventory Management</a></p>");
        }
    }

    private void sendNotificationsToSubscribedUsers(Item newItem) {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> subscribedUsers = userDAO.getSubscribedUsers();

        for (User user : subscribedUsers) {
            sendNotification(user, "New surplus food item available: " + newItem.getName());
        }
    }

    private void sendNotification(User user, String message) {
        // Implement logic to send notification via email or phone
        // Example: Use JavaMail API for sending emails
        // Example: Use a third-party SMS gateway for sending text messages
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method is used to handle GET requests for retrieving retailer transactions
        // You can keep your existing code for doGet if it serves your purpose
    }
}
