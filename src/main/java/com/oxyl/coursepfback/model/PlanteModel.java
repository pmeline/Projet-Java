package com.oxyl.coursepfback.model;

/**
 * Modèle représentant une plante dans le jeu Plants vs Zombies
 */
public class PlanteModel {
    private Long id_plante;
    private String nom;
    private Integer point_de_vie;
    private Double attaque_par_seconde;
    private Integer degat_attaque;
    private Integer cout;
    private Double soleil_par_seconde;
    private String effet;
    private String chemin_image;

    /**
     * Constructeur par défaut
     */
    public PlanteModel() {}

    /**
     * Constructeur avec tous les paramètres
     * @param id_plante l'identifiant unique de la plante
     * @param nom le nom de la plante
     * @param point_de_vie les points de vie de la plante
     * @param attaque_par_seconde la fréquence d'attaque de la plante
     * @param degat_attaque les dégâts infligés par la plante
     * @param cout le coût en soleil de la plante
     * @param soleil_par_seconde la production de soleil par seconde
     * @param effet l'effet spécial de la plante
     * @param chemin_image le chemin de l'image de la plante
     */
    public PlanteModel(
            Long id_plante,
            String nom,
            Integer point_de_vie,
            Double attaque_par_seconde,
            Integer degat_attaque,
            Integer cout,
            Double soleil_par_seconde,
            String effet,
            String chemin_image) {
        this.id_plante = id_plante;
        this.nom = nom;
        this.point_de_vie = point_de_vie;
        this.attaque_par_seconde = attaque_par_seconde;
        this.degat_attaque = degat_attaque;
        this.cout = cout;
        this.soleil_par_seconde = soleil_par_seconde;
        this.effet = effet;
        this.chemin_image = chemin_image;
    }

    /**
     * @return l'identifiant unique de la plante
     */
    public Long getId_plante() {
        return id_plante;
    }

    /**
     * @param id_plante l'identifiant unique de la plante
     */
    public void setId_plante(Long id_plante) {
        this.id_plante = id_plante;
    }

    /**
     * @return le nom de la plante
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom le nom de la plante
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return les points de vie de la plante
     */
    public Integer getPoint_de_vie() {
        return point_de_vie;
    }

    /**
     * @param point_de_vie les points de vie de la plante
     */
    public void setPoint_de_vie(Integer point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    /**
     * @return la fréquence d'attaque de la plante (attaque par seconde)
     */
    public Double getAttaque_par_seconde() {
        return attaque_par_seconde;
    }

    /**
     * @param attaque_par_seconde la fréquence d'attaque de la plante
     */
    public void setAttaque_par_seconde(Double attaque_par_seconde) {
        this.attaque_par_seconde = attaque_par_seconde;
    }

    /**
     * @return les dégâts infligés par la plante
     */
    public Integer getDegat_attaque() {
        return degat_attaque;
    }

    /**
     * @param degat_attaque les dégâts infligés par la plante
     */
    public void setDegat_attaque(Integer degat_attaque) {
        this.degat_attaque = degat_attaque;
    }

    /**
     * @return le coût en soleil de la plante
     */
    public Integer getCout() {
        return cout;
    }

    /**
     * @param cout le coût en soleil de la plante
     */
    public void setCout(Integer cout) {
        this.cout = cout;
    }

    /**
     * @return la production de soleil par seconde
     */
    public Double getSoleil_par_seconde() {
        return soleil_par_seconde;
    }

    /**
     * @param soleil_par_seconde la production de soleil par seconde
     */
    public void setSoleil_par_seconde(Double soleil_par_seconde) {
        this.soleil_par_seconde = soleil_par_seconde;
    }

    /**
     * @return l'effet spécial de la plante
     */
    public String getEffet() {
        return effet;
    }

    /**
     * @param effet l'effet spécial de la plante
     */
    public void setEffet(String effet) {
        this.effet = effet;
    }

    /**
     * @return le chemin de l'image de la plante
     */
    public String getChemin_image() {
        return chemin_image;
    }

    /**
     * @param chemin_image le chemin de l'image de la plante
     */
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
}
