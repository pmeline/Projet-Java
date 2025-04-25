package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;

import java.util.List;

/**
 * Interface définissant les opérations disponibles pour la gestion des maps
 */
public interface MapServiceInterface {
    /**
     * Crée une nouvelle map
     * @param mapModel la map à créer
     */
    void createMap(MapModel mapModel);

    /**
     * Récupère une map par son identifiant
     * @param id_map l'identifiant de la map à récupérer
     * @return la map correspondante
     */
    MapModel getMap(Long id_map);

    /**
     * Récupère toutes les maps
     * @return la liste de toutes les maps
     */
    List<MapModel> getAllMaps();

    /**
     * Met à jour une map existante
     * @param mapModel la map à mettre à jour
     */
    void updateMap(MapModel mapModel);

    /**
     * Supprime une map
     * @param id_map l'identifiant de la map à supprimer
     */
    void deleteMap(Long id_map);
}
