package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
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
        mapRepository.createMap(mapModel);
    }
    @Override
    public MapModel getMap(Long id_map) {
        return mapRepository.getMap(id_map);
    }
    @Override
    public List<MapModel> getAllMaps() {
        return mapRepository.getAllMaps();
    }
    @Override
    public void updateMap(MapModel mapModel) {
        MapModel oldMap = mapRepository.getMap(mapModel.getId_map());
        if (oldMap != null && !oldMap.getId_map().equals(mapModel.getId_map())) {
            // Si l'ID de la map a changé, mettre à jour les références des zombies
            List<ZombieModel> zombieMap = zombieService.getZombiesByMapId(oldMap.getId_map());
            for (ZombieModel zombie : zombieMap) {
                zombie.setId_map(mapModel.getId_map());
                zombieService.updateZombie(zombie);
            }
        }
        mapRepository.updateMap(mapModel);
    }
    @Override
    public void deleteMap(Long id_map) {
        List<ZombieModel> zombieMap = zombieService.getZombiesByMapId(id_map);
        for (ZombieModel zombie : zombieMap) {
            zombieService.deleteZombie(zombie.getId());
        }
        mapRepository.deleteMap(id_map);
    }
}
