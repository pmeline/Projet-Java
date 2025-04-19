package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.MapModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapDTOMapper {

    public MapDTO mapModelToDTO(MapModel mapModel) {
        if (mapModel == null) {
            return null;
        }

        MapDTO mapDTO = new MapDTO();
        mapDTO.setId_map(mapModel.getId_map());
        mapDTO.setLigne(mapModel.getLigne());
        mapDTO.setColonne(mapModel.getColonne());
        mapDTO.setChemin_image(mapModel.getChemin_image());
        return mapDTO;
    }

    public MapModel mapDTOToModel(MapDTO mapDTO) {
        if (mapDTO == null) {
            return null;
        }

        MapModel mapModel = new MapModel();
        mapModel.setId_map(mapDTO.getId_map());
        mapModel.setLigne(mapDTO.getLigne());
        mapModel.setColonne(mapDTO.getColonne());
        mapModel.setChemin_image(mapDTO.getChemin_image());
        return mapModel;
    }

    public List<MapDTO> mapListModelsToDTO(List<MapModel> mapModels) {
        if (mapModels == null) {
            return null;
        }

        return mapModels.stream()
                .map(this::mapModelToDTO)
                .toList();
    }

}
