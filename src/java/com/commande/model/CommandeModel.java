package com.commande.model;

public class CommandeModel {

    private int numCommande;
    private int numClient;
    private String date;

    public CommandeModel() {
    }

    public CommandeModel(int numClient, String date) {
        this.numClient = numClient;
        this.date = date;
    }

    public CommandeModel(int numCommande, int numClient, String date) {
        this.numCommande = numCommande;
        this.numClient = numClient;
        this.date = date;
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumclient(int numClient) {
        this.numClient = numClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
