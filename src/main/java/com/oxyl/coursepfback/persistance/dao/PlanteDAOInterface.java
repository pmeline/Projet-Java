package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface définissant les opérations de base de données pour les plantes
 */
@Repository
public interface PlanteDAOInterface {
    /**
     * Crée une nouvelle plante dans la base de données
     * @param plante la plante à créer
     */
    void createPlante(PlanteEntity plante);

    /**
     * Met à jour une plante existante dans la base de données
     * @param plante la plante à mettre à jour
     */
    void updatePlante(PlanteEntity plante);

    /**
     * Supprime une plante de la base de données
     * @param id l'identifiant de la plante à supprimer
     */
    void deletePlante(Long id);

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante à récupérer
     * @return la plante correspondante
     */
    PlanteEntity getPlante(Long id_plante);

    /**
     * Récupère toutes les plantes de la base de données
     * @return la liste de toutes les plantes
     */
    List<PlanteEntity> getAllPlantes();
}
