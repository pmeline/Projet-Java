package com.oxyl.coursepfback.persistance.entity;

/**
 * Entité représentant une carte dans la base de données
 */
public class MapEntity {
    private Long id_map;
    private Integer ligne;
    private Integer colonne;
    private String chemin_image;

    /**
     * Constructeur par défaut
     */
    public MapEntity() {}

    /**
     * Constructeur avec tous les paramètres
     * @param id_map l'identifiant unique de la carte
     * @param ligne le nombre de lignes de la carte
     * @param colonne le nombre de colonnes de la carte
     * @param chemin_image le chemin de l'image de la carte
     */
    public MapEntity(
            Long id_map,
            Integer ligne,
            Integer colonne,
            String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }

    /**
     * @return l'identifiant unique de la carte
     */
    public Long getId_map() {
        return id_map;
    }

    /**
     * @param id_map l'identifiant unique de la carte à définir
     */
    public void setId_map(Long id_map) {
        this.id_map = id_map;
    }

    /**
     * @return le nombre de lignes de la carte
     */
    public Integer getLigne() {
        return ligne;
    }

    /**
     * @param ligne le nombre de lignes de la carte à définir
     */
    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    /**
     * @return le nombre de colonnes de la carte
     */
    public Integer getColonne() {
        return colonne;
    }

    /**
     * @param colonne le nombre de colonnes de la carte à définir
     */
    public void setColonne(Integer colonne) {
        this.colonne = colonne;
    }

    /**
     * @return le chemin de l'image de la carte
     */
    public String getChemin_image() {
        return chemin_image;
    }

    /**
     * @param chemin_image le chemin de l'image de la carte à définir
     */
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
}
