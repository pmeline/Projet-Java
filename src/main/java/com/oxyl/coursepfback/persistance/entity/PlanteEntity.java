package com.oxyl.coursepfback.persistance.entity;

public class PlanteEntity {
    private Long id_plante;
    private String nom;
    private Integer point_de_vie;
    private Double attaque_par_seconde;
    private Integer degat_attaque;
    private Integer cout;
    private Double soleil_par_seconde;
    private String effet;
    private String chemin_image;

    public PlanteEntity() {

    }

    public PlanteEntity(Long id_plante, String nom, Integer point_de_vie, Double attaque_par_seconde, Integer degat_attaque, Integer cout, Double soleil_par_seconde, String effet, String chemin_image) {
        this.id_plante = id_plante;
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


    public Long getId_plante() {
        return id_plante;
    }

    public String getNom() {
        return nom;
    }

    public Integer getPoint_de_vie() {
        return point_de_vie;
    }

    public Double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    public Integer getDegat_attaque() {
        return degat_attaque;
    }

    public Integer getCout() {
        return cout;
    }

    public Double getSoleil_par_seconde() {
        return soleil_par_seconde;
    }

    public String getEffet() {
        return effet;
    }

    public String getChemin_image() {
        return chemin_image;
    }

    public void setId_plante(Long id_plante) {
        this.id_plante = id_plante;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoint_de_vie(Integer point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public void setAttaque_par_seconde(Double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    public void setDegat_attaque(Integer degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public void setSoleil_par_seconde(Double soleil_par_seconde) {
        this.soleil_par_seconde = soleil_par_seconde;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }


}
