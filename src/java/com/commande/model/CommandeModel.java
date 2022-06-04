package com.commande.model;

public class CommandeModel {

    private int numClient;
    private int numProduit;
    private int quantite;
    private String date;

    public CommandeModel() {
    }

    public CommandeModel(int numClient, int numProduit, int quantite, String date) {
        this.numClient = numClient;
        this.numProduit = numProduit;
        this.quantite = quantite;
        this.date = date;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumclient(int numClient) {
        this.numClient = numClient;
    }

    public int getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(int numProduit) {
        this.numProduit = numProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
