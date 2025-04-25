package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.MapEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface définissant les opérations CRUD pour les cartes
 */
@Repository
public interface MapDAOInterface {
    /**
     * Crée une nouvelle carte
     * @param map l'entité de la carte à créer
     */
    void createMap(MapEntity map);

    /**
     * Récupère une carte par son identifiant
     * @param id l'identifiant de la carte
     * @return l'entité de la carte correspondante
     */
    MapEntity getMap(Long id);

    /**
     * Récupère toutes les cartes
     * @return la liste de toutes les entités de cartes
     */
    List<MapEntity> getAllMaps();

    /**
     * Met à jour une carte existante
     * @param map l'entité de la carte à mettre à jour
     */
    void updateMap(MapEntity map);

    /**
     * Supprime une carte
     * @param id l'identifiant de la carte à supprimer
     */
    void deleteMap(Long id);
}
