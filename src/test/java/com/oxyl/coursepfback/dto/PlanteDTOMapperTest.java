package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.PlanteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlanteDTOMapperTest {

    private final PlanteDTOMapper mapper = new PlanteDTOMapper();

    @Test
    void mapModelToDTO_shouldConvertCorrectly() {
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
        PlanteDTO dto = mapper.mapModelToDTO(model);

        // Then
        assertNotNull(dto);
        assertEquals(model.getId_plante(), dto.getId_plante());
        assertEquals(model.getNom(), dto.getNom());
        assertEquals(model.getPoint_de_vie(), dto.getPoint_de_vie());
        assertEquals(model.getAttaque_par_seconde(), dto.getAttaque_par_seconde());
        assertEquals(model.getDegat_attaque(), dto.getDegat_attaque());
        assertEquals(model.getCout(), dto.getCout());
        assertEquals(model.getSoleil_par_seconde(), dto.getSoleil_par_seconde());
        assertEquals(model.getEffet(), dto.getEffet());
        assertEquals(model.getChemin_image(), dto.getChemin_image());
    }

    @Test
    void mapModelToDTO_withNullModel_shouldReturnNull() {
        // When
        PlanteDTO dto = mapper.mapModelToDTO(null);

        // Then
        assertNull(dto);
    }

    @Test
    void mapDTOToModel_shouldConvertCorrectly() {
        // Given
        PlanteDTO dto = new PlanteDTO();
        dto.setId_plante(1L);
        dto.setNom("Tournesol");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(1.0);
        dto.setDegat_attaque(20);
        dto.setCout(50);
        dto.setSoleil_par_seconde(1.0);
        dto.setEffet("Production de soleil");
        dto.setChemin_image("tournesol.png");

        // When
        PlanteModel model = mapper.mapDTOToModel(dto);

        // Then
        assertNotNull(model);
        assertEquals(dto.getId_plante(), model.getId_plante());
        assertEquals(dto.getNom(), model.getNom());
        assertEquals(dto.getPoint_de_vie(), model.getPoint_de_vie());
        assertEquals(dto.getAttaque_par_seconde(), model.getAttaque_par_seconde());
        assertEquals(dto.getDegat_attaque(), model.getDegat_attaque());
        assertEquals(dto.getCout(), model.getCout());
        assertEquals(dto.getSoleil_par_seconde(), model.getSoleil_par_seconde());
        assertEquals(dto.getEffet(), model.getEffet());
        assertEquals(dto.getChemin_image(), model.getChemin_image());
    }

    @Test
    void mapDTOToModel_withNullDTO_shouldReturnNull() {
        // When
        PlanteModel model = mapper.mapDTOToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapListModelsToDTO_shouldConvertAllModels() {
        // Given
        PlanteModel model1 = new PlanteModel();
        model1.setId_plante(1L);
        model1.setNom("Tournesol");
        model1.setPoint_de_vie(100);
        model1.setAttaque_par_seconde(1.0);
        model1.setDegat_attaque(20);
        model1.setCout(50);
        model1.setSoleil_par_seconde(1.0);
        model1.setEffet("Production de soleil");
        model1.setChemin_image("tournesol.png");

        PlanteModel model2 = new PlanteModel();
        model2.setId_plante(2L);
        model2.setNom("Pistachier");
        model2.setPoint_de_vie(200);
        model2.setAttaque_par_seconde(2.0);
        model2.setDegat_attaque(40);
        model2.setCout(100);
        model2.setSoleil_par_seconde(0.0);
        model2.setEffet("Attaque");
        model2.setChemin_image("pistachier.png");

        List<PlanteModel> models = Arrays.asList(model1, model2);

        // When
        List<PlanteDTO> dtos = mapper.mapListModelsToDTO(models);

        // Then
        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        PlanteDTO dto1 = dtos.get(0);
        assertEquals(model1.getId_plante(), dto1.getId_plante());
        assertEquals(model1.getNom(), dto1.getNom());
        assertEquals(model1.getPoint_de_vie(), dto1.getPoint_de_vie());
        assertEquals(model1.getAttaque_par_seconde(), dto1.getAttaque_par_seconde());
        assertEquals(model1.getDegat_attaque(), dto1.getDegat_attaque());
        assertEquals(model1.getCout(), dto1.getCout());
        assertEquals(model1.getSoleil_par_seconde(), dto1.getSoleil_par_seconde());
        assertEquals(model1.getEffet(), dto1.getEffet());
        assertEquals(model1.getChemin_image(), dto1.getChemin_image());

        PlanteDTO dto2 = dtos.get(1);
        assertEquals(model2.getId_plante(), dto2.getId_plante());
        assertEquals(model2.getNom(), dto2.getNom());
        assertEquals(model2.getPoint_de_vie(), dto2.getPoint_de_vie());
        assertEquals(model2.getAttaque_par_seconde(), dto2.getAttaque_par_seconde());
        assertEquals(model2.getDegat_attaque(), dto2.getDegat_attaque());
        assertEquals(model2.getCout(), dto2.getCout());
        assertEquals(model2.getSoleil_par_seconde(), dto2.getSoleil_par_seconde());
        assertEquals(model2.getEffet(), dto2.getEffet());
        assertEquals(model2.getChemin_image(), dto2.getChemin_image());
    }

    @Test
    void mapListModelsToDTO_withNullList_shouldReturnNull() {
        // When
        List<PlanteDTO> dtos = mapper.mapListModelsToDTO(null);

        // Then
        assertNull(dtos);
    }
} 