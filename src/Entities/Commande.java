/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Mega-PC
 */
public class Commande {
    private int id_commande;
    private int id_user;
    private float prix_total;
    private String code_promo;
    private String date;
    

    public Commande( int id_user,int id_commande, float prix_total, String date) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.prix_total = prix_total;
        this.date = date;
    }

    public Commande() {
        
    }

    
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public String getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(String code_promo) {
        this.code_promo = code_promo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Commande(int id_commande, int id_user, float prix_total, String code_promo, String date) {
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.prix_total = prix_total;
        this.code_promo = code_promo;
        this.date = date;
    }

    public Commande(int id_user, float prix_total, String date) {
        this.id_user = id_user;
        this.prix_total = prix_total;
        this.date = date;
    }
    
    
    
    
}
