/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.commande.ihm;

import com.commande.dao.DAOContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commande.dao.DAOContext;
import com.commande.dao.UserDAO;

import com.commande.model.UserModel;

/**
 *
 * @author Fernando David
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        DAOContext.init(this.getServletContext());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("userName", "");
        request.setAttribute("userPassword", "");
        request.setAttribute("errorMessage", "");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("isLogging", (Boolean) false);
            session.removeAttribute("connectedUser");
            // session.invalidate();
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = request.getParameter("idUser");
        String password = request.getParameter("mdpUser");
        
        UserModel connectedUser = UserDAO.isValidLogin(login, password);
        if (connectedUser != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isLogging", (Boolean) true);
            session.setAttribute("connectedUser", connectedUser);
            request.getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
        } else {
            request.setAttribute("userName", request.getParameter("idUser"));
            request.setAttribute("errorMessage", "Utilisateur ou mot de passe incorrecte");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
}
