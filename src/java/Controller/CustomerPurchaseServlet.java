/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Qina&Kaiwen
 */
import dataaccesslayer.DBConnection;
import dataaccesslayer.ItemDAO;
import dataaccesslayer.ItemDAOImpl;
import dataaccesslayer.TransactionDAOImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;
import model.Transaction;
import model.User;

public class CustomerPurchaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CustomerPurchaseServlet doPost called");
        ItemDAO itemDAO = new ItemDAOImpl();
        Map<Integer, Integer> quantities = new HashMap<>();

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // Handle the case where the user is not logged in (e.g., redirect to login page)
            response.sendRedirect("login.jsp"); // or wherever your login page is
            return;
        }
        int userId = user.getId();
        System.out.println("Using userId: " + user.getName());
        System.out.println("Using userId: " + userId);
        // Iterate over the request parameters to find those related to item quantities
        request.getParameterMap().forEach((key, values) -> {
            if (key.startsWith("quantity_")) {
                try {
                    int itemId = Integer.parseInt(key.substring(9)); // Extract item ID
                    int quantity = Integer.parseInt(values[0]); // Extract quantity
                    if (quantity > 0) { // Add only if quantity is positive
                        quantities.put(itemId, quantity);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.err.println("Error parsing item ID or quantity");
                }
            }
        });

        // Check if no quantities were selected
        if (quantities.isEmpty()) {
            request.getSession().setAttribute("error", "No items selected.");
            response.sendRedirect("orderReview.jsp"); // Redirect back to review page
            return;
        }

        // Process the purchase
        boolean purchaseSuccessful = processPurchase(itemDAO, quantities);

        if (purchaseSuccessful) {
            // If purchase is successful, prepare success response
            int confirmationNumber = (int) (Math.random() * 1000000); // Generate confirmation number
            request.getSession().setAttribute("customerconfirmationNumber", Integer.toString(confirmationNumber));
            BigDecimal totalPrice = new BigDecimal("0.00");
            int totalQuantity = 0;
            Map<String, BigDecimal> lineTotals = new HashMap<>();
            Map<String, Integer> purchasedItemsInfo = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                Item item = itemDAO.getItemById(entry.getKey());
                if (item != null) {
                    purchasedItemsInfo.put(item.getName(), entry.getValue());
                
                      // Create a new transaction object for each item
                        Transaction transaction = new Transaction();
                        transaction.setOrderId(confirmationNumber);
                        transaction.setQuantity(entry.getValue());
                        transaction.setPurchaserId(userId);
                        transaction.setItemInventoryId(entry.getKey());
                        transaction.setTransactionTime(new java.sql.Timestamp(new java.util.Date().getTime()));
                        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
                        transactionDAO.createTransaction(transaction);
                }
                BigDecimal lineTotal = item.getPrice().multiply(new BigDecimal(entry.getValue()));
                lineTotals.put(item.getName(), lineTotal);
                totalQuantity += entry.getValue();
                totalPrice = totalPrice.add(lineTotal);
                request.getSession().setAttribute("lineTotals", lineTotals);
                request.getSession().setAttribute("totalQuantity", totalQuantity);
                request.getSession().setAttribute("totalPrice", totalPrice);

            }
             request.getSession().setAttribute("customerordertime", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            request.getSession().setAttribute("customerpurchasedItems", purchasedItemsInfo);
            response.sendRedirect("purchaseSuccessful.jsp"); // Redirect to success page
        } else {
            // Handle purchase failure
            request.getSession().setAttribute("error", "Purchase failed. Please try again.");
            response.sendRedirect("orderReview.jsp"); // Redirect back to review page
        }
    }

    private boolean processPurchase(ItemDAO itemDAO, Map<Integer, Integer> quantities) {
        // Attempt to update inventory based on quantities map
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            int itemId = entry.getKey();
            int quantityToPurchase = entry.getValue();

            Item item = itemDAO.getItemById(itemId);
            if (item != null && item.getQuantity() >= quantityToPurchase) {
                // If enough stock is available, update the inventory
                itemDAO.updateItemQuantity(itemId, item.getQuantity() - quantityToPurchase);
            } else {
                // If not enough stock is available, return false
                return false;
            }
        }
        return true;
    }
}
