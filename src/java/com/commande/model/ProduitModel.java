package com.commande.model;

public class ProduitModel {

    private int num;
    private String id;
    private String designation;
    private int prixU;
    private int stock;

    public ProduitModel() {
    }

    public ProduitModel(String id, String designation, int prixU, int stock) {
        this.id = id;
        this.designation = designation;
        this.prixU = prixU;
        this.stock = stock;
    }

    public ProduitModel(int num, String id, String designation, int prixU, int stock) {
        this.num = num;
        this.id = id;
        this.designation = designation;
        this.prixU = prixU;
        this.stock = stock;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrixU() {
        return prixU;
    }

    public void setPrixU(int prixU) {
        this.prixU = prixU;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
