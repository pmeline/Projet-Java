package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.MapModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper pour convertir entre MapEntity et MapModel
 */
@Component
public class MapEntityMapper {

    /**
     * Convertit un MapModel en MapEntity
     * @param mapModel le modèle à convertir
     * @return l'entité convertie
     */
    public MapEntity mapModelToEntity(MapModel mapModel) {
        MapEntity entity = new MapEntity();
        entity.setId_map(mapModel.getId_map());
        entity.setLigne(mapModel.getLigne());
        entity.setColonne(mapModel.getColonne());
        entity.setChemin_image(mapModel.getChemin_image());
        return entity;
    }

    /**
     * Convertit une MapEntity en MapModel
     * @param mapEntity l'entité à convertir
     * @return le modèle converti
     */
    public MapModel mapEntityToModel(MapEntity mapEntity) {
        MapModel mapModel = new MapModel();
        mapModel.setId_map(mapEntity.getId_map());
        mapModel.setLigne(mapEntity.getLigne());
        mapModel.setColonne(mapEntity.getColonne());
        mapModel.setChemin_image(mapEntity.getChemin_image());
        return mapModel;
    }

    /**
     * Convertit une liste de MapEntity en une liste de MapModel
     * @param mapEntities la liste d'entités à convertir
     * @return la liste de modèles convertie
     */
    public List<MapModel> mapListEntitiesToModels(List<MapEntity> mapEntities) {
        return mapEntities.stream()
                .map(this::mapEntityToModel)
                .toList();
    }

}