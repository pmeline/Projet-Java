package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.MapModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper pour convertir entre MapModel et MapDTO
 */
@Component
public class MapDTOMapper {

    /**
     * Convertit un MapModel en MapDTO
     * @param mapModel le modèle à convertir
     * @return le DTO converti
     */
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

    /**
     * Convertit un MapDTO en MapModel
     * @param mapDTO le DTO à convertir
     * @return le modèle converti
     */
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

    /**
     * Convertit une liste de MapModel en une liste de MapDTO
     * @param mapModels la liste de modèles à convertir
     * @return la liste de DTOs convertie
     */
    public List<MapDTO> mapListModelsToDTO(List<MapModel> mapModels) {
        if (mapModels == null) {
            return null;
        }

        return mapModels.stream()
                .map(this::mapModelToDTO)
                .toList();
    }

}
