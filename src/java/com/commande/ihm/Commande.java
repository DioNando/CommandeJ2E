/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.commande.ihm;

import com.commande.dao.DAOContext;
import com.commande.dao.CommandeDAO;
import com.commande.dao.ProduitDAO;
import com.commande.dao.AvoirDAO;
import com.commande.model.CommandeModel;
import com.commande.model.ProduitModel;
import com.commande.model.AvoirModel;
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
import org.json.*;

@WebServlet(name = "Commande", urlPatterns = {"/commande"})
public class Commande extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAOContext.init(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            try {
                switch (action) {
                    case "Selectionner":
                        selectCommande(request, response);
                        break;
                    default:
                        listAllCommande(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        } else {
            try {
                listAllCommande(request, response);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("submit");
        try {
            switch (action) {
                case "Ajouter":
                    addCommande(request, response);
                    break;
                case "Modifier":
                    updateCommande(request, response);
                    break;
                case "Supprimer":
                    deleteCommande(request, response);
                    break;
                case "Chercher":
                    // searchCommande(request, response);
                    break;
                default:
                    listAllCommande(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAllCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        /* List< CommandeModel> listCommande = CommandeDAO.selectAllCommandes();
        request.setAttribute("listCommande", listCommande); */

        List< ProduitModel> listProduit = ProduitDAO.selectAllProduitsNoPagination();
        request.setAttribute("listProduit", listProduit);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/commande.jsp");
        dispatcher.forward(request, response);
    }

    private void selectCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

    }

    private void addCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int numClient = parseInt(request.getParameter("numClient"));
        String newIdCommande = null;
        String date = request.getParameter("dateCommande");

        // GONNA NEED A LOOP
        String numProduit = request.getParameter("numProduit");
        int quantite = parseInt(request.getParameter("quantiteProduit"));

        CommandeModel newCommande = new CommandeModel(numClient, date);
        newIdCommande = CommandeDAO.addCommande(newCommande);
        AvoirModel newAvoir = new AvoirModel(newIdCommande, numProduit, quantite);

        AvoirDAO.addAvoir(newAvoir);

        System.out.println(newIdCommande);
        response.sendRedirect("commande");
    }

    private void updateCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

    }

    private void deleteCommande(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

    }

}
