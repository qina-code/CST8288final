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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataaccesslayer.ItemDAOImpl;
import model.Item;

@WebServlet("/inventory/IdentifySurplusServlet")
public class IdentifySurplusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Extract surplus item details from the request parameters
    int itemId = Integer.parseInt(request.getParameter("surplusItem"));
    String surplusReason = request.getParameter("surplusReason");

    // Update the list of surplus items
    updateSurplusItemsList(request);

    // Create a JavaScript code to display a pop-up message
    String script = "<script>";
    script += "alert('Surplus reason added successfully!');";
    script += "window.location.href = '/FWRP/inventory/IdentifySurplusServlet';";
    script += "</script>";

    // Write the JavaScript code to the response
    response.getWriter().println(script);
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update the list of surplus items
        updateSurplusItemsList(request);

        // Forward the request to the JSP page to display the surplus items
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventory/identify_surplus.jsp");
        dispatcher.forward(request, response);
    }

    private void updateSurplusItemsList(HttpServletRequest request) {
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        List<Item> surplusItems = itemDAO.getSurplusItems();
        request.setAttribute("items", surplusItems);
    }
}
