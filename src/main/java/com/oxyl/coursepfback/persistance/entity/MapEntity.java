package com.oxyl.coursepfback.persistance.entity;

public class MapEntity {
    private Long id_map;
    private Integer ligne;
    private Integer colonne;
    private String chemin_image;

    public MapEntity(){}

    public MapEntity(Long id_map, Integer ligne, Integer colonne, String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }

    // Getters et Setters
    public Long getId_map() {
        return id_map;
    }
    public void setId_map(Long id_map) {
        this.id_map = id_map;
    }
    public Integer getLigne() {
        return ligne;
    }
    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }
    public Integer getColonne() {
        return colonne;
    }
    public void setColonne(Integer colonne) {
        this.colonne = colonne;
    }
    public String getChemin_image() {
        return chemin_image;
    }
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

}
