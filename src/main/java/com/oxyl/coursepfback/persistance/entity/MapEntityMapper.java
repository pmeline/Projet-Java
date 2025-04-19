package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.MapModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapEntityMapper {

    public MapEntity mapModelToEntity(MapModel mapModel) {
        MapEntity entity = new MapEntity();
        entity.setId_map(mapModel.getId_map());
        entity.setLigne(mapModel.getLigne());
        entity.setColonne(mapModel.getColonne());
        entity.setChemin_image(mapModel.getChemin_image());
        return entity;
    }
    public MapModel mapEntityToModel(MapEntity mapEntity) {
        MapModel mapModel = new MapModel();
        mapModel.setId_map(mapEntity.getId_map());
        mapModel.setLigne(mapEntity.getLigne());
        mapModel.setColonne(mapEntity.getColonne());
        mapModel.setChemin_image(mapEntity.getChemin_image());
        return mapModel;
    }

    public List<MapModel> mapListEntitiesToModels(List<MapEntity> mapEntities) {
        return mapEntities.stream()
                .map(this::mapEntityToModel)
                .toList();
    }

}