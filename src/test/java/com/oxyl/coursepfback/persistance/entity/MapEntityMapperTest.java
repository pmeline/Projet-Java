package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.MapModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapEntityMapperTest {

    private final MapEntityMapper mapper = new MapEntityMapper();

    @Test
    void mapModelToEntity_shouldConvertCorrectly() {
        // Given
        MapModel model = new MapModel();
        model.setId_map(1L);
        model.setLigne(5);
        model.setColonne(9);
        model.setChemin_image("map1.png");

        // When
        MapEntity entity = mapper.mapModelToEntity(model);

        // Then
        assertNotNull(entity);
        assertEquals(model.getId_map(), entity.getId_map());
        assertEquals(model.getLigne(), entity.getLigne());
        assertEquals(model.getColonne(), entity.getColonne());
        assertEquals(model.getChemin_image(), entity.getChemin_image());
    }

    @Test
    void mapEntityToModel_shouldConvertCorrectly() {
        // Given
        MapEntity entity = new MapEntity();
        entity.setId_map(1L);
        entity.setLigne(5);
        entity.setColonne(9);
        entity.setChemin_image("map1.png");

        // When
        MapModel model = mapper.mapEntityToModel(entity);

        // Then
        assertNotNull(model);
        assertEquals(entity.getId_map(), model.getId_map());
        assertEquals(entity.getLigne(), model.getLigne());
        assertEquals(entity.getColonne(), model.getColonne());
        assertEquals(entity.getChemin_image(), model.getChemin_image());
    }

    @Test
    void mapEntityToModel_withNullEntity_shouldReturnNull() {
        // When
        MapModel model = mapper.mapEntityToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapListEntitiesToModels_shouldConvertAllEntities() {
        // Given
        MapEntity entity1 = new MapEntity();
        entity1.setId_map(1L);
        entity1.setLigne(5);
        entity1.setColonne(9);
        entity1.setChemin_image("map1.png");

        MapEntity entity2 = new MapEntity();
        entity2.setId_map(2L);
        entity2.setLigne(6);
        entity2.setColonne(10);
        entity2.setChemin_image("map2.png");

        List<MapEntity> entities = Arrays.asList(entity1, entity2);

        // When
        List<MapModel> models = mapper.mapListEntitiesToModels(entities);

        // Then
        assertNotNull(models);
        assertEquals(2, models.size());

        MapModel model1 = models.get(0);
        assertEquals(entity1.getId_map(), model1.getId_map());
        assertEquals(entity1.getLigne(), model1.getLigne());
        assertEquals(entity1.getColonne(), model1.getColonne());
        assertEquals(entity1.getChemin_image(), model1.getChemin_image());

        MapModel model2 = models.get(1);
        assertEquals(entity2.getId_map(), model2.getId_map());
        assertEquals(entity2.getLigne(), model2.getLigne());
        assertEquals(entity2.getColonne(), model2.getColonne());
        assertEquals(entity2.getChemin_image(), model2.getChemin_image());
    }
} 