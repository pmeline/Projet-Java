package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.PlanteModel;

import java.util.List;

/**
 * Interface définissant les opérations disponibles pour la gestion des plantes
 */
public interface PlanteServiceInterface {
    /**
     * Crée une nouvelle plante
     * @param plante la plante à créer
     */
    void createPlante(PlanteModel plante);

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante à récupérer
     * @return la plante correspondante
     */
    PlanteModel getPlante(Long id_plante);

    /**
     * Récupère toutes les plantes
     * @return la liste de toutes les plantes
     */
    List<PlanteModel> getAllPlantes();

    /**
     * Met à jour une plante existante
     * @param plante la plante à mettre à jour
     */
    void updatePlante(PlanteModel plante);

    /**
     * Supprime une plante
     * @param id_plante l'identifiant de la plante à supprimer
     */
    void deletePlante(Long id_plante);
}
