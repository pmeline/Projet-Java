package com.oxyl.coursepfback.dto;

/**
 * DTO (Data Transfer Object) représentant un zombie dans le jeu
 */
public class ZombieDTO {

    private Long id_zombie;
    private String nom;
    private Integer point_de_vie;
    private Double attaque_par_seconde;
    private Integer degat_attaque;
    private Double vitesse_de_deplacement;
    private String chemin_image;
    private Long id_map;

    /**
     * Constructeur par défaut
     */
    public ZombieDTO() {
    }

    /**
     * Constructeur avec tous les paramètres
     * @param id_zombie l'identifiant du zombie
     * @param nom le nom du zombie
     * @param point_de_vie les points de vie du zombie
     * @param attaque_par_seconde la vitesse d'attaque du zombie
     * @param degat_attaque les dégâts infligés par le zombie
     * @param vitesse_de_deplacement la vitesse de déplacement du zombie
     * @param chemin_image le chemin vers l'image du zombie
     * @param id_map l'identifiant de la map associée
     */
    public ZombieDTO(
            Long id_zombie,
            String nom,
            Integer point_de_vie,
            Double attaque_par_seconde,
            Integer degat_attaque,
            Double vitesse_de_deplacement,
            String chemin_image,
            Long id_map) {
        this.id_zombie = id_zombie;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.vitesse_de_deplacement = vitesse_de_deplacement;
        this.chemin_image = chemin_image;
        this.id_map = id_map;
    }

    /**
     * @return l'identifiant du zombie
     */
    public Long getId_zombie() {
        return id_zombie;
    }

    /**
     * @param id_zombie l'identifiant du zombie à définir
     */
    public void setId_zombie(Long id_zombie) {
        this.id_zombie = id_zombie;
    }

    /**
     * @return le nom du zombie
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom le nom du zombie à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return les points de vie du zombie
     */
    public Integer getPoint_de_vie() {
        return point_de_vie;
    }

    /**
     * @param point_de_vie les points de vie du zombie à définir
     */
    public void setPoint_de_vie(Integer point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    /**
     * @return la vitesse d'attaque du zombie
     */
    public Double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    /**
     * @param attaque_par_seconde la vitesse d'attaque du zombie à définir
     */
    public void setAttaque_par_seconde(Double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    /**
     * @return les dégâts infligés par le zombie
     */
    public Integer getDegat_attaque() {
        return degat_attaque;
    }

    /**
     * @param degat_attaque les dégâts infligés par le zombie à définir
     */
    public void setDegat_attaque(Integer degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    /**
     * @return la vitesse de déplacement du zombie
     */
    public Double getVitesse_de_deplacement() {
        return vitesse_de_deplacement;
    }

    /**
     * @param vitesse_de_deplacement la vitesse de déplacement du zombie à définir
     */
    public void setVitesse_de_deplacement(Double vitesse_de_deplacement) {
        this.vitesse_de_deplacement = vitesse_de_deplacement;
    }

    /**
     * @return le chemin vers l'image du zombie
     */
    public String getChemin_image() {
        return chemin_image;
    }

    /**
     * @param chemin_image le chemin vers l'image du zombie à définir
     */
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

    /**
     * @return l'identifiant de la map associée
     */
    public Long getId_map() {
        return id_map;
    }

    /**
     * @param id_map l'identifiant de la map associée à définir
     */
    public void setId_map(Long id_map) {
        this.id_map = id_map;
    }

}
