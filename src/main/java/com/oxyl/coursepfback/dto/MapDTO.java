package com.oxyl.coursepfback.dto;

/**
 * DTO (Data Transfer Object) représentant une map dans le jeu
 */
public class MapDTO {
    private Long id_map;
    private Integer ligne;
    private Integer colonne;
    private String chemin_image;

    /**
     * Constructeur par défaut
     */
    public MapDTO() {}

    /**
     * Constructeur avec tous les paramètres
     * @param id_map l'identifiant de la map
     * @param ligne le nombre de lignes de la map
     * @param colonne le nombre de colonnes de la map
     * @param chemin_image le chemin vers l'image de la map
     */
    public MapDTO(
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
     * @return l'identifiant de la map
     */
    public Long getId_map() {
        return id_map;
    }

    /**
     * @param id_map l'identifiant de la map à définir
     */
    public void setId_map(Long id_map) {
        this.id_map = id_map;
    }

    /**
     * @return le nombre de lignes de la map
     */
    public Integer getLigne() {
        return ligne;
    }

    /**
     * @param ligne le nombre de lignes de la map à définir
     */
    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    /**
     * @return le nombre de colonnes de la map
     */
    public Integer getColonne() {
        return colonne;
    }

    /**
     * @param colonne le nombre de colonnes de la map à définir
     */
    public void setColonne(Integer colonne) {
        this.colonne = colonne;
    }

    /**
     * @return le chemin vers l'image de la map
     */
    public String getChemin_image() {
        return chemin_image;
    }

    /**
     * @param chemin_image le chemin vers l'image de la map à définir
     */
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
}
