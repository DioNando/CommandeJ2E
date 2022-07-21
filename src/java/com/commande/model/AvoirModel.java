package com.commande.model;

public class AvoirModel {

    private String numCommande;
    private String numProduit;
    private int quantite;

    public AvoirModel() {
    }

    public AvoirModel(String numCommande, String numProduit, int quantite) {
        this.numCommande = numCommande;
        this.numProduit = numProduit;
        this.quantite = quantite;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(String numProduit) {
        this.numProduit = numProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
