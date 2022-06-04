/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.commande.ihm;

import com.commande.dao.DAOContext;
import com.commande.dao.CommandeDAO;
import com.commande.model.CommandeModel;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Commande", urlPatterns = {"/commande"})
public class Commande extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAOContext.init(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add":
                    addCommande(request, response);
                    break;
                default:
                    listCommande(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // doGet(request, response);
        String action = request.getServletPath();

        try {
            switch (action) {
                default:
                    addCommande(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< CommandeModel> listCommande = CommandeDAO.selectAllCommandes();
        request.setAttribute("listCommande", listCommande);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commande.jsp");
        dispatcher.forward(request, response);
    }

    private void addCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int numClient = parseInt(request.getParameter("numClient"));
        int numProduit = parseInt(request.getParameter("numProduit"));
        int quantite = parseInt(request.getParameter("quantite"));
        String date = request.getParameter("dateCommande");
        CommandeModel newCommande = new CommandeModel(numClient, numProduit, quantite, date);
        CommandeDAO.addCommande(newCommande);
        response.sendRedirect("produit");
    }

}
