package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.MapEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapDAOInterface {
    void createMap(MapEntity map);
    MapEntity getMap(Long id);
    List<MapEntity> getAllMaps();
    void updateMap(MapEntity map);
    void deleteMap(Long id);
}
