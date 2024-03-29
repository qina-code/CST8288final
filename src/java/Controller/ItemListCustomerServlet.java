/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Qina&Kaiwen
 */
import dataaccesslayer.ItemDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;


public class ItemListCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ItemListCustomerServlet doGet called");
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        List<Item> items = itemDAO.getItems(); 
        System.out.println("Items retrieved: " + items);
        request.setAttribute("items", items); 

        // Forward to consumers.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/customer.jsp");
        dispatcher.forward(request, response);
    }
}
