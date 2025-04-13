package com.oxyl.coursepfback.PERSISTANCE.DAO;
import java.sql.Date;

public class PlanteEntity {
    private Long id;
    private String nom;
    private int point_de_vie;
    private float attaque_par_seconde;
    private int degat_attaque;
    private int cout;
    private float soleil_par_seconde;
    private String effet;
    private String chemin_image;

    public PlanteEntity() {

    }

    public PlanteEntity(Long id, String nom, int point_de_vie, float attaque_par_seconde, int degat_attaque, int cout, float soleil_par_seconde, String effet, String chemin_image) {
        this.id = id;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.soleil_par_seconde = soleil_par_seconde;
        this.cout = cout;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }

    //Getter et setter


    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public float getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    public int getDegat_attaque() {
        return degat_attaque;
    }

    public int getCout() {
        return cout;
    }

    public float getSoleil_par_seconde() {
        return soleil_par_seconde;
    }

    public String getEffet() {
        return effet;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setId(Long id) {this.id = id;}
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public void setAttaque_par_seconde(float attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    public void setDegat_attaque(int degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public void setSoleil_par_seconde(float soleil_par_seconde) {
        this.soleil_par_seconde = soleil_par_seconde;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }


}
