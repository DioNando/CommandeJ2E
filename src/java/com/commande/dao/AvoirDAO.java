package com.commande.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.commande.model.CommandeModel;
import com.commande.model.AvoirModel;
import com.commande.model.ProduitModel;

public class AvoirDAO extends DAOContext {

    private static final String SELECT_ALL_AVOIR = "SELECT * FROM Avoir";
    private static final String INSERT_AVOIR = "INSERT INTO Avoir (numCommande, numProduit, quantite) VALUES (?,?,?)";
    private static final String UPDATE_STOCK_PRODUIT = "UPDATE Produit SET stockProduit = ? WHERE numProduit = ?";
    private static final String SELECT_ID_PRODUIT = "SELECT * FROM Produit WHERE numProduit = ?";

    public static List< AvoirModel> selectAllAvoir() {
        List< AvoirModel> avoir = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AVOIR)) {

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    String numCommande = rs.getString("numCommande");
                    String numProduit = rs.getString("numProduit");
                    int quantite = rs.getInt("quantite");
                    avoir.add(new AvoirModel(numCommande, numProduit, quantite));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return avoir;
    }

    public static void addAvoir(AvoirModel avoir) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_AVOIR)) {

                statement.setString(1, avoir.getNumCommande());
                statement.setString(2, avoir.getNumProduit());
                statement.setInt(3, avoir.getQuantite());
                statement.executeUpdate();

                updateProduit(avoir.getNumProduit(), avoir.getQuantite());

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void updateProduit(String numProduit, int quantite) {
        int newStock = 0;

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {

            try (PreparedStatement statement = connection.prepareStatement(SELECT_ID_PRODUIT)) {

                statement.setString(1, numProduit);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    newStock = rs.getInt("stockProduit") - quantite;
                }
            }

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK_PRODUIT)) {

                statement.setInt(1, newStock);
                statement.setString(2, numProduit);
                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
