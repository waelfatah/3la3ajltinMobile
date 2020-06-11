/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.util.Iterator;

/**
 *
 * @author Mega-PC
 */
public class Produit {

    private Integer id_prod;
    private String nom;
    private String marque;
    private Float prix_prod;
    private Integer quantite;
    private String type_prod;
    private String urlImage;
    private int qteProduitPanier;
    private int qteApresVente;
    

    public int getQteProduitPanier() {
        return qteProduitPanier;
    }

    public void setQteProduitPanier(int qteProduitPanier) {
        this.qteProduitPanier = qteProduitPanier;
    }

    public Produit() {

    }

    public Produit(String nom, String marque, float prix_prod, int quantite, String type_prod) {
        this.nom = nom;
        this.marque = marque;
        this.prix_prod = prix_prod;
        this.quantite = quantite;
        this.type_prod = type_prod;
    }

    public Produit(Integer id_prod, String nom, String marque, Float prix_prod, Integer quantite, String type_prod, String urlImage) {
        this.id_prod = id_prod;
        this.nom = nom;
        this.marque = marque;
        this.prix_prod = prix_prod;
        this.quantite = quantite;
        this.type_prod = type_prod;
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", nom=" + nom + ", marque=" + marque + ", prix_prod=" + prix_prod + ", quantite=" + quantite + ", type_prod=" + type_prod + ", urlImage=" + urlImage + ", qteProduitPanier=" + qteProduitPanier + '}';
    }

    public float getPrix_prod() {
        return prix_prod;
    }

    public void setPrix_prod(float prix_prod) {
        this.prix_prod = prix_prod;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getType_prod() {
        return type_prod;
    }

    public void setType_prod(String type_prod) {
        this.type_prod = type_prod;
    }

    public Integer getId_prod() {
        return id_prod;
    }

    public void setId_prod(Integer id_prod) {
        this.id_prod = id_prod;
    }

    public void ajouterQuantite(int qte) {
        this.qteProduitPanier += qte;
    }


    public float getPrixTotal(){
        float prixTot = 0;
        
        prixTot=(float)this.getPrix_prod()*this.getQteProduitPanier();
        
        return prixTot;
        
    }
 
    public int getQteApresVente() {
        return qteApresVente;
    }

    public void setQteApresVente(int qteApresVente) {
        this.qteApresVente = qteApresVente;
    }
    

    

}
