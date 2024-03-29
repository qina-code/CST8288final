/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dataaccesslayer.TransactionDAO;
import dataaccesslayer.TransactionDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Transaction;
import model.User;

/**
 *
 * @author User
 */
public class TransactionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TransactionServlet doGet called");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if the user is not logged in
            return;
        }
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        List<Transaction> transactions = transactionDAO.getTransactionsByPurchaserEmail(user.getEmail());
        System.out.println("History retrieved: " + transactions);
        request.setAttribute("transactions", transactions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/transaction.jsp");
        dispatcher.forward(request, response);
    }
}

//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TransactionServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet TransactionServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        System.out.print("search for transactions");
//
//        //search for transaction accroding to user type
//        User user = (User) request.getSession().getAttribute("user");
//        String type = user.getType();
//        List<Transaction> transactions = new ArrayList<>();
//        if (type.equalsIgnoreCase("consumer") || type.equalsIgnoreCase("charitable_organization")) {
////            consumer or organization
//            TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
//            transactions = transactionDAO.getTransactionsByPurchaserEmail(user.getEmail());
//
//        } else {
//            //retailer
//            TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
//            transactions = transactionDAO.getTransactionsByOwnerEmail(user.getEmail());
//
//        }
//        System.out.print("setting transactions attribute");
//
//        System.out.print(transactions);
//        request.getSession().setAttribute("transactions", transactions);
//        //redirect to transaction page
//        response.sendRedirect("http://localhost:8080/FWRP/user/transaction.jsp");
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //put purchase into database
//        String itemName = request.getParameter("itemname");
//        User user = (User) request.getSession().getAttribute("user");
//        String email = user.getEmail();
//
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>


