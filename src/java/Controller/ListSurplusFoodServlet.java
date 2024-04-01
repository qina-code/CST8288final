/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Tianying Le
 */

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import dataaccesslayer.ItemDAOImpl;
import model.Item;
import model.User;
import dataaccesslayer.UserDAOImpl;
import java.io.PrintWriter;
import static java.lang.System.out;

@WebServlet("/inventory/ListSurplusFoodServlet")
public class ListSurplusFoodServlet extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Set response content type
    response.setContentType("text/html");

    // Get PrintWriter object
    PrintWriter out = response.getWriter();

    // Write JavaScript to show a pop-up message
    out.println("<script type='text/javascript'>");
    out.println("alert('Item added successfully!');");
    out.println("window.location.href = 'http://localhost:8080/FWRP/inventory/ListSurplusFoodServlet';");
    out.println("</script>");
}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update the list of surplus items
        updateSurplusItemsList(request);

        // Forward the request to the JSP page to display the surplus items
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventory/list_surplus_food.jsp");
        dispatcher.forward(request, response);
    }

    // Method to update the surplus items list
    private void updateSurplusItemsList(HttpServletRequest request) {
        // Logic to update the surplus items list
        // You may use DAO methods to retrieve the surplus items from the database
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        List<Item> surplusItems = itemDAO.getSurplusItems();

        // Set the updated surplus items list as a request attribute
        request.setAttribute("items", surplusItems);

        // Send notifications to subscribed users
        sendNotificationsToSubscribedUsers(surplusItems);
    }

    // Method to send notifications to subscribed users
    private void sendNotificationsToSubscribedUsers(List<Item> surplusItems) {
        // Retrieve subscribed users from the database
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> subscribedUsers = userDAO.getSubscribedUsers();

        // Iterate through subscribed users and send notifications
        for (User user : subscribedUsers) {
            sendNotification(user, "New surplus food items available");
        }
    }

    // Method to send notification to a specific user
    private void sendNotification(User user, String message) {
        // Implement logic to send notification via email or phone
        // For example, you can use JavaMail API for sending emails
        // Or integrate with a third-party SMS gateway for sending text messages
    }
}

