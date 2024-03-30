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
public class RegistrationFormServlet extends HttpServlet {
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

    // Extract data from FormData
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String userType = request.getParameter("userType");
    boolean subscribed = Boolean.parseBoolean(request.getParameter("subscribed"));

    // Create a new user object
    User newUser = new User(name, email, password, userType, subscribed);

    // Create a UserDAOImpl instance
    UserDAOImpl userDAO = new UserDAOImpl();
    
        // Check if the user email already exists in the database
    if (userDAO.getUserByEmail(email) != null) {
        System.out.println("User email already exists");

        // Redirect to registration page with an error
        response.sendRedirect("http://localhost:8080/FWRP/user/registration.jsp?error=email_exists");
        return; // Exit from the method to prevent further execution
    }
    
    // Attempt to create the user in the database
    if (userDAO.createUser(newUser) > 0) {
        System.out.println("New user created");
        // Store user information in session
        request.getSession().setAttribute("user", newUser);
        response.sendRedirect("http://localhost:8080/FWRP/user/dashboard.jsp");
           
    } else {
        System.out.println("Failed to create new user");

        // Redirect to login page with an alert
        response.sendRedirect("http://localhost:8080/FWRP/user/registration.jsp?error=fail");
        
    }
}


    /**
     * Returns a short description of the servlet.z
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
