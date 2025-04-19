package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.persistance.dao.MapDAO;
import com.oxyl.coursepfback.persistance.entity.MapEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MapRepository {

    private final MapDAO mapDAO;
    private final MapEntityMapper mapper;

    @Autowired
    public MapRepository(MapDAO mapDAO, MapEntityMapper mapper) {
        this.mapDAO = mapDAO;
        this.mapper = mapper;
    }

    public List<MapModel> getAllMaps() {
        return mapper.mapListEntitiesToModels(mapDAO.getAllMaps());
    }

    public MapModel getMap(Long id_map) {
        return mapper.mapEntityToModel(mapDAO.getMap(id_map));
    }
    public void createMap(MapModel mapModel) {
        mapDAO.createMap(mapper.mapModelToEntity(mapModel));
    }
    public void updateMap(MapModel mapModel) {
        mapDAO.updateMap(mapper.mapModelToEntity(mapModel));
    }
    public void deleteMap(Long id_map) {
        mapDAO.deleteMap(id_map);
    }

}
