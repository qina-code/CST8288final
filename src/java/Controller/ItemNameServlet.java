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
import model.Item;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/inventory/ItemNameServlet")
public class ItemNameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getItemNameList doGet called");
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        List<Item> items = itemDAO.getItems2(); // Fetch the list of items
        System.out.println("getItemNameList retrieved: " + items);
        request.setAttribute("ItemNameList", items); // Store it as a request attribute

        // Forward to consumers.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/inventory/add_item.jsp");
        dispatcher.forward(request, response);
    }
}
