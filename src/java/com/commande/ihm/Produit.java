/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.commande.ihm;

import com.commande.dao.DAOContext;
import com.commande.dao.ProduitDAO;
import com.commande.model.ProduitModel;
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

@WebServlet(name = "Produit", urlPatterns = {"/produit"})
public class Produit extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DAOContext.init(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            listProduit(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("submitProduit");
        try {
            switch (action) {
                case "ajouter":
                    addProduit(request, response);
                    break;
                case "modifier":
                    updateProduit(request, response);
                    break;
                case "supprimer":
                    deleteProduit(request, response);
                    break;
                case "chercher":
                    // chercheProduit(request, response);
                    break;
                default:
                    listProduit(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ProduitDAO dao = new ProduitDAO();

        List< ProduitModel> listProduit = ProduitDAO.selectAllProduits((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listProduit", listProduit);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfRecords", noOfRecords);
        request.setAttribute("pageActive", "produit");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/produit.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("idProduit");
        String designation = request.getParameter("designProduit");
        int prixU = parseInt(request.getParameter("puProduit"));
        int stock = parseInt(request.getParameter("stockProduit"));
        ProduitModel newProduit = new ProduitModel(id, designation, prixU, stock);
        ProduitDAO.addProduit(newProduit);
        response.sendRedirect("produit");
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int num = Integer.parseInt(request.getParameter("numProduit"));
        String id = request.getParameter("idProduit");
        String des = request.getParameter("designProduit");
        int pu = Integer.parseInt(request.getParameter("puProduit"));
        int stock = Integer.parseInt(request.getParameter("stockProduit"));
        ProduitModel newProduit = new ProduitModel(num, id, des, pu, stock);
        ProduitDAO.updateProduit(newProduit);
        response.sendRedirect("produit");
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int num = Integer.parseInt(request.getParameter("numProduit"));
        ProduitDAO.deleteProduit(num);
        response.sendRedirect("produit");
    }

}
