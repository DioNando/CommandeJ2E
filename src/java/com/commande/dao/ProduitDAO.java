package com.commande.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.commande.model.ProduitModel;

public class ProduitDAO extends DAOContext {

    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM Produit ORDER BY numProduit ASC";
    private static final String INSERT_PRODUIT = "INSERT INTO Produit (idProduit, designProduit, puProduit, stockProduit) VALUES (?,?,?,?)";
    private static final String UPDATE_PRODUIT = "UPDATE Produit set idProduit = ?, designProduit = ?, puProduit = ?, stockProduit = ? WHERE numClient = ?";
    private static final String DELETE_PRODUIT = "DELETE FROM Produit WHERE numProduit = ?";

    private int noOfRecords;

    public static List< ProduitModel> selectAllProduits(int offset, int noOfRecords) {
        List< ProduitModel> produits = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUITS + " LIMIT " + offset + "," + noOfRecords)) {

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int num = rs.getInt("numProduit");
                    String id = rs.getString("idProduit");
                    String designation = rs.getString("designProduit");
                    int prixU = rs.getInt("puProduit");
                    int stock = rs.getInt("stockProduit");
                    produits.add(new ProduitModel(num, id, designation, prixU, stock));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return produits;
    }

    public static void addProduit(ProduitModel produit) {

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_PRODUIT)) {

                statement.setString(1, produit.getId());
                statement.setString(2, produit.getDesignation());
                statement.setInt(3, produit.getPrixU());
                statement.setInt(4, produit.getStock());
                statement.executeUpdate();

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    public static boolean updateProduit(ProduitModel produit) {
        boolean rowUpdated;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUIT)) {

                statement.setString(1, produit.getId());
                statement.setString(2, produit.getDesignation());
                statement.setInt(3, produit.getStock());
                statement.setInt(4, produit.getPrixU());
                statement.setInt(5, produit.getNum());
                rowUpdated = statement.executeUpdate() > 0;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdated;
    }

    public static boolean deleteProduit(int numProduit) {
        boolean rowDeleted;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUIT)) {

                statement.setInt(1, numProduit);
                rowDeleted = statement.executeUpdate() > 0;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowDeleted;
    }

    public int getNoOfRecords() {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Produit")) {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    this.noOfRecords = rs.getInt(1);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return noOfRecords;
    }
}
