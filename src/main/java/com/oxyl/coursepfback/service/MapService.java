package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService implements MapServiceInterface {

    private final MapRepository mapRepository;
    private final ZombieService zombieService;

    @Autowired
    public MapService(MapRepository mapRepository, ZombieService zombieService) {
        this.mapRepository = mapRepository;
        this.zombieService = zombieService;
    }

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

    @Override
    public List<MapModel> getAllMaps() {
        return mapRepository.getAllMaps();
    }

    @Override
    public void updateMap(MapModel mapModel) {
        if (mapModel == null) {
            throw new IllegalArgumentException("La map ne peut pas être null");
        }
        
        // Vérifie les champs obligatoires
        if (mapModel.getLigne() <= 0 || mapModel.getColonne() <= 0) {
            throw new IllegalArgumentException("Les dimensions de la map doivent être positives");
        }
        
        mapRepository.updateMap(mapModel);
    }

    @Override
    public void deleteMap(Long id_map) {
        if (id_map == null) {
            throw new IllegalArgumentException("L'id de la map ne peut pas être null");
        }
        
        // Vérifie si la map existe
        getMap(id_map);
        
        // Supprime tous les zombies associés à cette map
        List<ZombieModel> zombies = zombieService.getZombiesByMapId(id_map);
        for (ZombieModel zombie : zombies) {
            zombieService.deleteZombie(zombie.getId_zombie());
        }
        
        // Supprime la map
        mapRepository.deleteMap(id_map);
    }
}
