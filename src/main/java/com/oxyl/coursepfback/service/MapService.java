package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant les opérations liées aux maps
 */
@Service
public class MapService implements MapServiceInterface {

    private final MapRepository mapRepository;
    private final ZombieService zombieService;

    @Autowired
    public MapService(MapRepository mapRepository, ZombieService zombieService) {
        this.mapRepository = mapRepository;
        this.zombieService = zombieService;
    }

    /**
     * Crée une nouvelle map
     * @param mapModel la map à créer
     * @throws IllegalArgumentException si les données de la map sont invalides
     */
    @Override
    public void createMap(MapModel mapModel) {
        if (mapModel == null) {
            throw new IllegalArgumentException("La map ne peut pas être null");
        }
        if (mapModel.getLigne() <= 0 || mapModel.getColonne() <= 0) {
            throw new IllegalArgumentException("Les dimensions de la map doivent être positives");
        }
        mapRepository.createMap(mapModel);
    }

    /**
     * Récupère une map par son identifiant
     * @param id_map l'identifiant de la map
     * @return la map correspondante
     * @throws IllegalArgumentException si l'identifiant est null ou si la map n'existe pas
     */
    @Override
    public MapModel getMap(Long id_map) {
        if (id_map == null) {
            throw new IllegalArgumentException("L'id de la map ne peut pas être null");
        }
        MapModel map = mapRepository.getMap(id_map);
        if (map == null) {
            throw new IllegalArgumentException("La map avec l'id " + id_map + " n'existe pas");
        }
        return map;
    }

    /**
     * Récupère toutes les maps
     * @return la liste de toutes les maps
     */
    @Override
    public List<MapModel> getAllMaps() {
        return mapRepository.getAllMaps();
    }

    /**
     * Met à jour une map existante
     * @param mapModel la map avec les nouvelles données
     * @throws IllegalArgumentException si les données sont invalides
     */
    @Override
    public void updateMap(MapModel mapModel) {
        if (mapModel == null) {
            throw new IllegalArgumentException("La map ne peut pas être null");
        }
        
        if (mapModel.getLigne() <= 0 || mapModel.getColonne() <= 0) {
            throw new IllegalArgumentException("Les dimensions de la map doivent être positives");
        }
        
        mapRepository.updateMap(mapModel);
    }

    /**
     * Supprime une map et tous les zombies associés
     * @param id_map l'identifiant de la map à supprimer
     * @throws IllegalArgumentException si l'identifiant est null ou si la map n'existe pas
     */
    @Override
    public void deleteMap(Long id_map) {
        if (id_map == null) {
            throw new IllegalArgumentException("L'id de la map ne peut pas être null");
        }
        
        getMap(id_map);
        
        List<ZombieModel> zombies = zombieService.getZombiesByMapId(id_map);
        for (ZombieModel zombie : zombies) {
            zombieService.deleteZombie(zombie.getId_zombie());
        }
        
        mapRepository.deleteMap(id_map);
    }
}
