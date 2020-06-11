/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Nour ghidaoui
 */
public class Article {
    private int idArticle;
    private String Titre;
    private String Description;
    private String date;
    private int id_user;
    private String image;
    private String updated_at;
    private String etat; 

    public Article() {
        
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Article(int idArticle, String Titre, String Description, String date, int id_user, String image, String updated_at, String etat) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
        this.date = date;
        this.id_user = id_user;
        this.image = image;
        this.updated_at = updated_at;
        this.etat = etat;
    }

    
    
    
    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", Titre=" + Titre + ", Description=" + Description + ", date=" + date + ", id_user=" + id_user + ", image=" + image + ", updated_at=" + updated_at + ", etat=" + etat + '}';
    }

   
    
    
}
