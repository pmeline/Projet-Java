package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.persistance.dao.MapDAO;
import com.oxyl.coursepfback.persistance.entity.MapEntity;
import com.oxyl.coursepfback.persistance.entity.MapEntityMapper;
import com.oxyl.coursepfback.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * Repository pour gérer les opérations CRUD sur les cartes
 */
@Repository
public class MapRepository {

    private final MapDAO mapDAO;
    private final MapEntityMapper mapper;

    /**
     * Constructeur avec injection de dépendances
     * @param mapDAO le DAO pour accéder aux données des cartes
     * @param mapper le mapper pour convertir entre entités et modèles
     */
    @Autowired
    public MapRepository(MapDAO mapDAO, MapEntityMapper mapper) {
        this.mapDAO = mapDAO;
        this.mapper = mapper;
    }

    /**
     * Récupère toutes les cartes
     * @return la liste de toutes les cartes
     */
    public List<MapModel> getAllMaps() {
        return mapper.mapListEntitiesToModels(mapDAO.getAllMaps());
    }

    /**
     * Récupère une carte par son identifiant
     * @param id_map l'identifiant de la carte
     * @return la carte correspondante
     */
    public MapModel getMap(Long id_map) {
        try {
            return mapper.mapEntityToModel(mapDAO.getMap(id_map));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("La map avec l'id " + id_map + " n'existe pas");
        }
    }

    /**
     * Crée une nouvelle carte
     * @param mapModel le modèle de la carte à créer
     */
    public void createMap(MapModel mapModel) {
        mapDAO.createMap(mapper.mapModelToEntity(mapModel));
    }

    /**
     * Met à jour une carte existante
     * @param mapModel le modèle de la carte à mettre à jour
     */
    public void updateMap(MapModel mapModel) {
        MapEntity existingMap = mapDAO.getMap(mapModel.getId_map());
        if (existingMap == null) {
            throw new NotFoundException("La map avec l'id " + mapModel.getId_map() + " n'existe pas");
        }
        MapEntity mapEntity = mapper.mapModelToEntity(mapModel);
        mapDAO.updateMap(mapEntity);
    }

    /**
     * Supprime une carte
     * @param id_map l'identifiant de la carte à supprimer
     */
    public void deleteMap(Long id_map) {
        mapDAO.deleteMap(id_map);
    }
}
