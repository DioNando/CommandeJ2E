/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.commande.ihm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import org.json.*;

import com.commande.dao.DAOContext;
import com.commande.dao.ClientDAO;
import com.commande.model.ClientModel;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Client", urlPatterns = {"/client"})
public class Client extends HttpServlet {

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
                        selectClient(request, response);
                        break;
                    default:
                        listAllClient(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        } else {
            try {
                listAllClient(request, response);
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
                    addClient(request, response);
                    break;
                case "Modifier":
                    updateClient(request, response);
                    break;
                case "Supprimer":
                    deleteClient(request, response);
                    break;
                case "Chercher":
                    // searchClient(request, response);
                    break;
                default:
                    listAllClient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAllClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        ClientDAO dao = new ClientDAO();

        List< ClientModel> listClient = ClientDAO.selectAllClients((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("listClient", listClient);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfRecords", noOfRecords);
        request.setAttribute("pageActive", "client");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/client.jsp");
        dispatcher.forward(request, response);
    }

    private void selectClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int num = Integer.parseInt(request.getParameter("numClient"));
        JSONObject selectedClient = new JSONObject(ClientDAO.selectClient(num));
        response.setContentType("application/json");
        response.getWriter().write(selectedClient.toString());
    }

    private void addClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("idClient");
        String nom = request.getParameter("nomClient");
        ClientModel newClient = new ClientModel(id, nom);
        ClientDAO.addClient(newClient);
        response.sendRedirect("client");
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int num = Integer.parseInt(request.getParameter("numClient"));
        String id = request.getParameter("idClient");
        String nom = request.getParameter("nomClient");
        ClientModel newClient = new ClientModel(num, id, nom);
        ClientDAO.updateClient(newClient);
        response.sendRedirect("client");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int num = Integer.parseInt(request.getParameter("numClient"));
        ClientDAO.deleteClient(num);
        response.sendRedirect("client");
    }
}
