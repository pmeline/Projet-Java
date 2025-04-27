package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.MapModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapDTOMapperTest {

    private final MapDTOMapper mapper = new MapDTOMapper();

    @Test
    void mapModelToDTO_shouldConvertCorrectly() {
        // Given
        MapModel model = new MapModel();
        model.setId_map(1L);
        model.setLigne(5);
        model.setColonne(9);
        model.setChemin_image("map1.png");

        // When
        MapDTO dto = mapper.mapModelToDTO(model);

        // Then
        assertNotNull(dto);
        assertEquals(model.getId_map(), dto.getId_map());
        assertEquals(model.getLigne(), dto.getLigne());
        assertEquals(model.getColonne(), dto.getColonne());
        assertEquals(model.getChemin_image(), dto.getChemin_image());
    }

    @Test
    void mapModelToDTO_withNullModel_shouldReturnNull() {
        // When
        MapDTO dto = mapper.mapModelToDTO(null);

        // Then
        assertNull(dto);
    }

    @Test
    void mapDTOToModel_shouldConvertCorrectly() {
        // Given
        MapDTO dto = new MapDTO();
        dto.setId_map(1L);
        dto.setLigne(5);
        dto.setColonne(9);
        dto.setChemin_image("map1.png");

        // When
        MapModel model = mapper.mapDTOToModel(dto);

        // Then
        assertNotNull(model);
        assertEquals(dto.getId_map(), model.getId_map());
        assertEquals(dto.getLigne(), model.getLigne());
        assertEquals(dto.getColonne(), model.getColonne());
        assertEquals(dto.getChemin_image(), model.getChemin_image());
    }

    @Test
    void mapDTOToModel_withNullDTO_shouldReturnNull() {
        // When
        MapModel model = mapper.mapDTOToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapListModelsToDTO_shouldConvertAllModels() {
        // Given
        MapModel model1 = new MapModel();
        model1.setId_map(1L);
        model1.setLigne(5);
        model1.setColonne(9);
        model1.setChemin_image("map1.png");

        MapModel model2 = new MapModel();
        model2.setId_map(2L);
        model2.setLigne(6);
        model2.setColonne(10);
        model2.setChemin_image("map2.png");

        List<MapModel> models = Arrays.asList(model1, model2);

        // When
        List<MapDTO> dtos = mapper.mapListModelsToDTO(models);

        // Then
        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        MapDTO dto1 = dtos.get(0);
        assertEquals(model1.getId_map(), dto1.getId_map());
        assertEquals(model1.getLigne(), dto1.getLigne());
        assertEquals(model1.getColonne(), dto1.getColonne());
        assertEquals(model1.getChemin_image(), dto1.getChemin_image());

        MapDTO dto2 = dtos.get(1);
        assertEquals(model2.getId_map(), dto2.getId_map());
        assertEquals(model2.getLigne(), dto2.getLigne());
        assertEquals(model2.getColonne(), dto2.getColonne());
        assertEquals(model2.getChemin_image(), dto2.getChemin_image());
    }

    @Test
    void mapListModelsToDTO_withNullList_shouldReturnNull() {
        // When
        List<MapDTO> dtos = mapper.mapListModelsToDTO(null);

        // Then
        assertNull(dtos);
    }
} 