/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dataaccesslayer.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author User
 */
public class LoginFormServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Receiving POST request");
        UserDAOImpl userDAO = new UserDAOImpl();
        User userFound = userDAO.getUserByEmail(request.getParameter("email"));
        if(userFound!= null && userFound.getPassword().equals(request.getParameter("password"))){
            System.out.println("valid login");
            // Store user information in session
            request.getSession().setAttribute("user", userFound);
            response.sendRedirect("http://localhost:8080/FWRP/user/dashboard.jsp");
            
        }else{
            System.out.println("invalid login");
            // Redirect to login page with an alert
            response.sendRedirect("http://localhost:8080/FWRP/user/login.jsp?error=invalid");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
