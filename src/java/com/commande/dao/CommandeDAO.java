package com.commande.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.commande.model.CommandeModel;
import com.commande.model.AvoirModel;
import com.commande.model.ProduitModel;
import com.commande.model.ClientModel;

public class CommandeDAO extends DAOContext {

    private static final String SELECT_ALL_COMMANDES = "SELECT * FROM Commande";
    private static final String INSERT_COMMANDE = "INSERT INTO Commande (numClient, dateCommande) VALUES (?,?)";
    private static final String INSERT_AVOIR = "INSERT INTO Avoir (numCommande, numProduit, quantite) VALUES (?,?,?)";
    private static final String UPDATE_QUANTITE_PRODUIT = "";
    private static final String SELECT_LAST_ID = "SELECT MAX(numCommande) AS lastID FROM Commande";

    public static List< CommandeModel> selectAllCommandes() {
        List< CommandeModel> commandes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COMMANDES)) {

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int numCommande = rs.getInt("numCommande");
                    int numClient = rs.getInt("numClient");
                    String date = rs.getString("dateCommande");
                    commandes.add(new CommandeModel(numCommande, numClient, date));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return commandes;
    }

    public static String addCommande(CommandeModel commande) {
        String lastID = null;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_COMMANDE)) {

                statement.setInt(1, commande.getNumClient());
                statement.setString(2, commande.getDate());
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement(SELECT_LAST_ID)) {

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    lastID = rs.getString("lastID");
                }
                return (lastID);
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
