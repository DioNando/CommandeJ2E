package com.commande.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.commande.model.ClientModel;

public class ClientDAO extends DAOContext {

    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Client ORDER BY numClient DESC";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE numClient = ?";
    private static final String INSERT_CLIENT = "INSERT INTO Client (idClient, nomClient) VALUES (?,?)";
    private static final String UPDATE_CLIENT = "UPDATE Client set idClient = ?, nomClient = ? WHERE numClient = ?";
    private static final String DELETE_CLIENT = "DELETE FROM Client WHERE numClient = ?";

    private int noOfRecords;

    public static List< ClientModel> selectAllClients(int offset, int noOfRecords) {
        List< ClientModel> clients = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLIENTS + " LIMIT " + offset + "," + noOfRecords)) {

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    int num = rs.getInt("numClient");
                    String id = rs.getString("idClient");
                    String nom = rs.getString("nomClient");
                    clients.add(new ClientModel(num, id, nom));
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return clients;
    }

    public static void addClient(ClientModel client) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CLIENT)) {

                statement.setString(1, client.getId());
                statement.setString(2, client.getNom());
                statement.executeUpdate();

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static ClientModel selectClient(int numClient) {
        ClientModel client = null;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {

                statement.setInt(1, numClient);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int num = rs.getInt("numClient");
                    String id = rs.getString("idClient");
                    String nom = rs.getString("nomClient");
                    client = new ClientModel(num, id, nom);
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return client;
    }

    public static boolean updateClient(ClientModel client) {
        boolean rowUpdated;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT)) {

                statement.setString(1, client.getId());
                statement.setString(2, client.getNom());
                statement.setInt(3, client.getNum());
                rowUpdated = statement.executeUpdate() > 0;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdated;
    }

    public static boolean deleteClient(int numClient) {
        boolean rowDeleted;
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT)) {

                statement.setInt(1, numClient);
                rowDeleted = statement.executeUpdate() > 0;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowDeleted;
    }

    public int getNoOfRecords() {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Client")) {
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
