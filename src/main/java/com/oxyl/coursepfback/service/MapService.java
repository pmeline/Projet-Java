package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService implements MapServiceInterface {

    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
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
        mapRepository.updateMap(mapModel);
    }
    @Override
    public void deleteMap(Long id_map) {
        mapRepository.deleteMap(id_map);
    }
}
