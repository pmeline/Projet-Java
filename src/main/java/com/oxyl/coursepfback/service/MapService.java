package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant les opérations sur les maps
 */
@Service
public class MapService implements MapServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(MapService.class);
    private final MapRepository mapRepository;
    private final ZombieService zombieService;

    public MapService(MapRepository mapRepository, ZombieService zombieService) {
        this.mapRepository = mapRepository;
        this.zombieService = zombieService;
    }

    /**
     * Crée une nouvelle map
     * @param mapModel la map à créer
     * @throws ValidationException si les données de la map sont invalides
     */
    @Override
    public void createMap(MapModel mapModel) {
        logger.debug("Création d'une nouvelle map: {}", mapModel);
        if (mapModel == null) {
            logger.error("Tentative de création d'une map null");
            throw new ValidationException("La map ne peut pas être null");
        }
        if (mapModel.getLigne() <= 0) {
            logger.error("Tentative de création d'une map avec un nombre de lignes négatif: {}", mapModel.getLigne());
            throw new ValidationException("Le nombre de lignes doit être positif");
        }
        if (mapModel.getColonne() <= 0) {
            logger.error("Tentative de création d'une map avec un nombre de colonnes négatif: {}", mapModel.getColonne());
            throw new ValidationException("Le nombre de colonnes doit être positif");
        }
        if (mapModel.getChemin_image() == null || mapModel.getChemin_image().trim().isEmpty()) {
            logger.error("Tentative de création d'une map avec un chemin d'image vide");
            throw new ValidationException("Le chemin de l'image ne peut pas être vide");
        }
        
        mapRepository.createMap(mapModel);
        logger.info("Map créée avec succès: {}", mapModel);
    }

    /**
     * Récupère une map par son identifiant
     * @param id_map l'identifiant de la map
     * @return la map correspondante
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si la map n'existe pas
     */
    @Override
    public MapModel getMap(Long id_map) {
        logger.debug("Récupération de la map avec l'id: {}", id_map);
        if (id_map == null) {
            logger.error("Tentative de récupération d'une map avec un id null");
            throw new ValidationException("L'id de la map ne peut pas être null");
        }
        MapModel map = mapRepository.getMap(id_map);
        if (map == null) {
            logger.error("Map non trouvée avec l'id: {}", id_map);
            throw new NotFoundException("La map avec l'id " + id_map + " n'existe pas");
        }
        logger.debug("Map trouvée: {}", map);
        return map;
    }

    /**
     * Récupère toutes les maps
     * @return la liste de toutes les maps
     */
    @Override
    public List<MapModel> getAllMaps() {
        logger.debug("Récupération de toutes les maps");
        List<MapModel> maps = mapRepository.getAllMaps();
        logger.debug("{} maps trouvées", maps.size());
        return maps;
    }

    /**
     * Met à jour une map existante
     * @param mapModel la map avec les nouvelles données
     * @throws ValidationException si les données sont invalides
     * @throws NotFoundException si la map n'existe pas
     */
    @Override
    public void updateMap(MapModel mapModel) {
        logger.debug("Mise à jour de la map: {}", mapModel);
        if (mapModel == null) {
            logger.error("Tentative de mise à jour d'une map null");
            throw new ValidationException("La map ne peut pas être null");
        }
        
        // Vérifier d'abord si la map existe
        MapModel existingMap = getMap(mapModel.getId_map());
        if (existingMap == null) {
            logger.error("Tentative de mise à jour d'une map inexistante avec l'id: {}", mapModel.getId_map());
            throw new NotFoundException("La map avec l'id " + mapModel.getId_map() + " n'existe pas");
        }
        
        if (mapModel.getLigne() != null) {
            if (mapModel.getLigne() <= 0) {
                logger.error("Tentative de mise à jour du nombre de lignes avec une valeur négative: {}", mapModel.getLigne());
                throw new ValidationException("Le nombre de lignes doit être positif");
            }
            existingMap.setLigne(mapModel.getLigne());
        }
        if (mapModel.getColonne() != null) {
            if (mapModel.getColonne() <= 0) {
                logger.error("Tentative de mise à jour du nombre de colonnes avec une valeur négative: {}", mapModel.getColonne());
                throw new ValidationException("Le nombre de colonnes doit être positif");
            }
            existingMap.setColonne(mapModel.getColonne());
        }
        if (mapModel.getChemin_image() != null && !mapModel.getChemin_image().trim().isEmpty()) {
            existingMap.setChemin_image(mapModel.getChemin_image());
        }
        
        mapRepository.updateMap(existingMap);
        logger.info("Map mise à jour avec succès: {}", existingMap);
    }

    /**
     * Supprime une map et tous les zombies associés
     * @param id_map l'identifiant de la map à supprimer
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si la map n'existe pas
     */
    @Override
    public void deleteMap(Long id_map) {
        logger.debug("Suppression de la map avec l'id: {}", id_map);
        if (id_map == null) {
            logger.error("Tentative de suppression d'une map avec un id null");
            throw new ValidationException("L'id de la map ne peut pas être null");
        }
        
        MapModel existingMap = mapRepository.getMap(id_map);
        if (existingMap == null) {
            logger.error("Tentative de suppression d'une map inexistante avec l'id: {}", id_map);
            throw new NotFoundException("La map avec l'id " + id_map + " n'existe pas");
        }
        
        List<ZombieModel> zombies = zombieService.getZombiesByMapId(id_map);
        for (ZombieModel zombie : zombies) {
            zombieService.deleteZombie(zombie.getId_zombie());
        }
        
        mapRepository.deleteMap(id_map);
        logger.info("Map supprimée avec succès: {}", existingMap);
    }
}
