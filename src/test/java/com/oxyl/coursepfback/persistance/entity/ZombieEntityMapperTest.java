package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.ZombieModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZombieEntityMapperTest {

    private final ZombieEntityMapper mapper = new ZombieEntityMapper();

    @Test
    void mapEntityToModel_shouldConvertCorrectly() {
        // Given
        ZombieEntity entity = new ZombieEntity(
            1L, "Zombie de base", 100, 1.0,
            20, 1.0, "zombie.png", 1L
        );

        // When
        ZombieModel model = mapper.mapEntityToModel(entity);

        // Then
        assertNotNull(model);
        assertEquals(entity.getId_zombie(), model.getId_zombie());
        assertEquals(entity.getNom(), model.getNom());
        assertEquals(entity.getPoint_de_vie(), model.getPoint_de_vie());
        assertEquals(entity.getAttaque_par_seconde(), model.getAttaque_par_seconde());
        assertEquals(entity.getDegat_attaque(), model.getDegat_attaque());
        assertEquals(entity.getVitesse_de_deplacement(), model.getVitesse_de_deplacement());
        assertEquals(entity.getChemin_image(), model.getChemin_image());
        assertEquals(entity.getId_map(), model.getId_map());
    }

    @Test
    void mapEntityToModel_withNullEntity_shouldReturnNull() {
        // When
        ZombieModel model = mapper.mapEntityToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapModelToEntity_shouldConvertCorrectly() {
        // Given
        ZombieModel model = new ZombieModel(
            1L, "Zombie de base", 100, 1.0,
            20, 1.0, "zombie.png", 1L
        );

        // When
        ZombieEntity entity = mapper.mapModelToEntity(model);

        // Then
        assertNotNull(entity);
        assertEquals(model.getId_zombie(), entity.getId_zombie());
        assertEquals(model.getNom(), entity.getNom());
        assertEquals(model.getPoint_de_vie(), entity.getPoint_de_vie());
        assertEquals(model.getAttaque_par_seconde(), entity.getAttaque_par_seconde());
        assertEquals(model.getDegat_attaque(), entity.getDegat_attaque());
        assertEquals(model.getVitesse_de_deplacement(), entity.getVitesse_de_deplacement());
        assertEquals(model.getChemin_image(), entity.getChemin_image());
        assertEquals(model.getId_map(), entity.getId_map());
    }

    @Test
    void mapModelToEntity_withNullModel_shouldReturnNull() {
        // When
        ZombieEntity entity = mapper.mapModelToEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void mapListEntityToModel_shouldConvertAllEntities() {
        // Given
        ZombieEntity entity1 = new ZombieEntity(
            1L, "Zombie 1", 100, 1.0,
            20, 1.0, "zombie1.png", 1L
        );
        ZombieEntity entity2 = new ZombieEntity(
            2L, "Zombie 2", 150, 1.5,
            25, 1.2, "zombie2.png", 1L
        );
        List<ZombieEntity> entities = Arrays.asList(entity1, entity2);

        // When
        List<ZombieModel> models = mapper.mapListEntityToModel(entities);

        // Then
        assertNotNull(models);
        assertEquals(2, models.size());
        
        ZombieModel model1 = models.get(0);
        assertEquals(entity1.getId_zombie(), model1.getId_zombie());
        assertEquals(entity1.getNom(), model1.getNom());
        assertEquals(entity1.getPoint_de_vie(), model1.getPoint_de_vie());
        assertEquals(entity1.getAttaque_par_seconde(), model1.getAttaque_par_seconde());
        assertEquals(entity1.getDegat_attaque(), model1.getDegat_attaque());
        assertEquals(entity1.getVitesse_de_deplacement(), model1.getVitesse_de_deplacement());
        assertEquals(entity1.getChemin_image(), model1.getChemin_image());
        assertEquals(entity1.getId_map(), model1.getId_map());

        ZombieModel model2 = models.get(1);
        assertEquals(entity2.getId_zombie(), model2.getId_zombie());
        assertEquals(entity2.getNom(), model2.getNom());
        assertEquals(entity2.getPoint_de_vie(), model2.getPoint_de_vie());
        assertEquals(entity2.getAttaque_par_seconde(), model2.getAttaque_par_seconde());
        assertEquals(entity2.getDegat_attaque(), model2.getDegat_attaque());
        assertEquals(entity2.getVitesse_de_deplacement(), model2.getVitesse_de_deplacement());
        assertEquals(entity2.getChemin_image(), model2.getChemin_image());
        assertEquals(entity2.getId_map(), model2.getId_map());
    }

    @Test
    void mapListEntityToModel_withNullList_shouldReturnNull() {
        // When
        List<ZombieModel> models = mapper.mapListEntityToModel(null);

        // Then
        assertNull(models);
    }
} 