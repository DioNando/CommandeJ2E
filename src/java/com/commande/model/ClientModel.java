package com.commande.model;

public class ClientModel {

    private int num;
    private String id;
    private String nom;

    public ClientModel() {
    }

    public ClientModel(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ClientModel(int num, String id, String nom) {
        this.num = num;
        this.id = id;
        this.nom = nom;
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

    public String getNom() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

}
