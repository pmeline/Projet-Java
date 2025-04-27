package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.PlanteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlanteEntityMapperTest {

    private final PlanteEntityMapper mapper = new PlanteEntityMapper();

    @Test
    void mapModelToEntity_shouldConvertCorrectly() {
        // Given
        PlanteModel model = new PlanteModel();
        model.setId_plante(1L);
        model.setNom("Tournesol");
        model.setPoint_de_vie(100);
        model.setAttaque_par_seconde(1.0);
        model.setDegat_attaque(20);
        model.setCout(50);
        model.setSoleil_par_seconde(1.0);
        model.setEffet("Production de soleil");
        model.setChemin_image("tournesol.png");

        // When
        PlanteEntity entity = mapper.mapModelToEntity(model);

        // Then
        assertNotNull(entity);
        assertEquals(model.getId_plante(), entity.getId_plante());
        assertEquals(model.getNom(), entity.getNom());
        assertEquals(model.getPoint_de_vie(), entity.getPoint_de_vie());
        assertEquals(model.getAttaque_par_seconde(), entity.getAttaque_par_seconde());
        assertEquals(model.getDegat_attaque(), entity.getDegat_attaque());
        assertEquals(model.getCout(), entity.getCout());
        assertEquals(model.getSoleil_par_seconde(), entity.getSoleil_par_seconde());
        assertEquals(model.getEffet(), entity.getEffet());
        assertEquals(model.getChemin_image(), entity.getChemin_image());
    }

    @Test
    void mapModelToEntity_withNullModel_shouldReturnNull() {
        // When
        PlanteEntity entity = mapper.mapModelToEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void mapEntityToModel_shouldConvertCorrectly() {
        // Given
        PlanteEntity entity = new PlanteEntity();
        entity.setId_plante(1L);
        entity.setNom("Tournesol");
        entity.setPoint_de_vie(100);
        entity.setAttaque_par_seconde(1.0);
        entity.setDegat_attaque(20);
        entity.setCout(50);
        entity.setSoleil_par_seconde(1.0);
        entity.setEffet("Production de soleil");
        entity.setChemin_image("tournesol.png");

        // When
        PlanteModel model = mapper.mapEntityToModel(entity);

        // Then
        assertNotNull(model);
        assertEquals(entity.getId_plante(), model.getId_plante());
        assertEquals(entity.getNom(), model.getNom());
        assertEquals(entity.getPoint_de_vie(), model.getPoint_de_vie());
        assertEquals(entity.getAttaque_par_seconde(), model.getAttaque_par_seconde());
        assertEquals(entity.getDegat_attaque(), model.getDegat_attaque());
        assertEquals(entity.getCout(), model.getCout());
        assertEquals(entity.getSoleil_par_seconde(), model.getSoleil_par_seconde());
        assertEquals(entity.getEffet(), model.getEffet());
        assertEquals(entity.getChemin_image(), model.getChemin_image());
    }

    @Test
    void mapEntityToModel_withNullEntity_shouldReturnNull() {
        // When
        PlanteModel model = mapper.mapEntityToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapEntitiesToModels_shouldConvertAllEntities() {
        // Given
        PlanteEntity entity1 = new PlanteEntity();
        entity1.setId_plante(1L);
        entity1.setNom("Tournesol");
        entity1.setPoint_de_vie(100);
        entity1.setAttaque_par_seconde(1.0);
        entity1.setDegat_attaque(20);
        entity1.setCout(50);
        entity1.setSoleil_par_seconde(1.0);
        entity1.setEffet("Production de soleil");
        entity1.setChemin_image("tournesol.png");

        PlanteEntity entity2 = new PlanteEntity();
        entity2.setId_plante(2L);
        entity2.setNom("Pistachier");
        entity2.setPoint_de_vie(200);
        entity2.setAttaque_par_seconde(2.0);
        entity2.setDegat_attaque(40);
        entity2.setCout(100);
        entity2.setSoleil_par_seconde(0.0);
        entity2.setEffet("Attaque");
        entity2.setChemin_image("pistachier.png");

        List<PlanteEntity> entities = Arrays.asList(entity1, entity2);

        // When
        List<PlanteModel> models = mapper.mapEntitiesToModels(entities);

        // Then
        assertNotNull(models);
        assertEquals(2, models.size());

        PlanteModel model1 = models.get(0);
        assertEquals(entity1.getId_plante(), model1.getId_plante());
        assertEquals(entity1.getNom(), model1.getNom());
        assertEquals(entity1.getPoint_de_vie(), model1.getPoint_de_vie());
        assertEquals(entity1.getAttaque_par_seconde(), model1.getAttaque_par_seconde());
        assertEquals(entity1.getDegat_attaque(), model1.getDegat_attaque());
        assertEquals(entity1.getCout(), model1.getCout());
        assertEquals(entity1.getSoleil_par_seconde(), model1.getSoleil_par_seconde());
        assertEquals(entity1.getEffet(), model1.getEffet());
        assertEquals(entity1.getChemin_image(), model1.getChemin_image());

        PlanteModel model2 = models.get(1);
        assertEquals(entity2.getId_plante(), model2.getId_plante());
        assertEquals(entity2.getNom(), model2.getNom());
        assertEquals(entity2.getPoint_de_vie(), model2.getPoint_de_vie());
        assertEquals(entity2.getAttaque_par_seconde(), model2.getAttaque_par_seconde());
        assertEquals(entity2.getDegat_attaque(), model2.getDegat_attaque());
        assertEquals(entity2.getCout(), model2.getCout());
        assertEquals(entity2.getSoleil_par_seconde(), model2.getSoleil_par_seconde());
        assertEquals(entity2.getEffet(), model2.getEffet());
        assertEquals(entity2.getChemin_image(), model2.getChemin_image());
    }
} 