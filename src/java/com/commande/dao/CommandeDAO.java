package com.commande.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.commande.model.CommandeModel;

public class CommandeDAO extends DAOContext {

    private static final String SELECT_ALL_COMMANDES = "SELECT * FROM Commande";
    private static final String INSERT_COMMANDE = "INSERT INTO Commande (numClient, numProduit, quantite, dateCommande) VALUES (?,?,?,?)";

    public static List< CommandeModel> selectAllCommandes() {
        List< CommandeModel> commandes = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COMMANDES)) {

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int numClient = rs.getInt("numClient");
                    int numProduit = rs.getInt("numProduit");
                    int quantite = rs.getInt("quantite");
                    String date = rs.getString("dateCommande");
                    commandes.add(new CommandeModel(numClient, numProduit, quantite, date));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return commandes;
    }

    public static void addCommande(CommandeModel commande) {

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_COMMANDE)) {

                statement.setInt(1, commande.getNumClient());
                statement.setInt(2, commande.getNumProduit());
                statement.setInt(3, commande.getQuantite());
                statement.setString(4, commande.getDate());
                statement.executeUpdate();

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }
}
