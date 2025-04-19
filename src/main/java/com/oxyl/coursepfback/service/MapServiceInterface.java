package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;

import java.util.List;

public interface MapServiceInterface {
    void createMap(MapModel mapModel);
    MapModel getMap(Long id_map);
    List<MapModel> getAllMaps();
    void updateMap(MapModel mapModel);
    void deleteMap(Long id_map);
}
