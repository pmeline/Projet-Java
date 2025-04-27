package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.ZombieModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZombieDTOMapperTest {

    private final ZombieDTOMapper mapper = new ZombieDTOMapper();

    @Test
    void mapModelToDTO_shouldConvertCorrectly() {
        // Given
        ZombieModel model = new ZombieModel();
        model.setId_zombie(1L);
        model.setNom("Zombie de base");
        model.setPoint_de_vie(100);
        model.setAttaque_par_seconde(1.0);
        model.setDegat_attaque(20);
        model.setVitesse_de_deplacement(1.0);
        model.setChemin_image("zombie.png");
        model.setId_map(1L);

        // When
        ZombieDTO dto = mapper.mapModelToDTO(model);

        // Then
        assertNotNull(dto);
        assertEquals(model.getId_zombie(), dto.getId_zombie());
        assertEquals(model.getNom(), dto.getNom());
        assertEquals(model.getPoint_de_vie(), dto.getPoint_de_vie());
        assertEquals(model.getAttaque_par_seconde(), dto.getAttaque_par_seconde());
        assertEquals(model.getDegat_attaque(), dto.getDegat_attaque());
        assertEquals(model.getVitesse_de_deplacement(), dto.getVitesse_de_deplacement());
        assertEquals(model.getChemin_image(), dto.getChemin_image());
        assertEquals(model.getId_map(), dto.getId_map());
    }

    @Test
    void mapModelToDTO_withNullModel_shouldReturnNull() {
        // When
        ZombieDTO dto = mapper.mapModelToDTO(null);

        // Then
        assertNull(dto);
    }

    @Test
    void mapDTOToModel_shouldConvertCorrectly() {
        // Given
        ZombieDTO dto = new ZombieDTO();
        dto.setId_zombie(1L);
        dto.setNom("Zombie de base");
        dto.setPoint_de_vie(100);
        dto.setAttaque_par_seconde(1.0);
        dto.setDegat_attaque(20);
        dto.setVitesse_de_deplacement(1.0);
        dto.setChemin_image("zombie.png");
        dto.setId_map(1L);

        // When
        ZombieModel model = mapper.mapDTOToModel(dto);

        // Then
        assertNotNull(model);
        assertEquals(dto.getId_zombie(), model.getId_zombie());
        assertEquals(dto.getNom(), model.getNom());
        assertEquals(dto.getPoint_de_vie(), model.getPoint_de_vie());
        assertEquals(dto.getAttaque_par_seconde(), model.getAttaque_par_seconde());
        assertEquals(dto.getDegat_attaque(), model.getDegat_attaque());
        assertEquals(dto.getVitesse_de_deplacement(), model.getVitesse_de_deplacement());
        assertEquals(dto.getChemin_image(), model.getChemin_image());
        assertEquals(dto.getId_map(), model.getId_map());
    }

    @Test
    void mapDTOToModel_withNullDTO_shouldReturnNull() {
        // When
        ZombieModel model = mapper.mapDTOToModel(null);

        // Then
        assertNull(model);
    }

    @Test
    void mapListModelToDTO_shouldConvertAllModels() {
        // Given
        ZombieModel model1 = new ZombieModel();
        model1.setId_zombie(1L);
        model1.setNom("Zombie de base");
        model1.setPoint_de_vie(100);
        model1.setAttaque_par_seconde(1.0);
        model1.setDegat_attaque(20);
        model1.setVitesse_de_deplacement(1.0);
        model1.setChemin_image("zombie.png");
        model1.setId_map(1L);

        ZombieModel model2 = new ZombieModel();
        model2.setId_zombie(2L);
        model2.setNom("Zombie rapide");
        model2.setPoint_de_vie(50);
        model2.setAttaque_par_seconde(2.0);
        model2.setDegat_attaque(10);
        model2.setVitesse_de_deplacement(2.0);
        model2.setChemin_image("zombie_rapide.png");
        model2.setId_map(1L);

        List<ZombieModel> models = Arrays.asList(model1, model2);

        // When
        List<ZombieDTO> dtos = mapper.mapListModelToDTO(models);

        // Then
        assertNotNull(dtos);
        assertEquals(2, dtos.size());

        ZombieDTO dto1 = dtos.get(0);
        assertEquals(model1.getId_zombie(), dto1.getId_zombie());
        assertEquals(model1.getNom(), dto1.getNom());
        assertEquals(model1.getPoint_de_vie(), dto1.getPoint_de_vie());
        assertEquals(model1.getAttaque_par_seconde(), dto1.getAttaque_par_seconde());
        assertEquals(model1.getDegat_attaque(), dto1.getDegat_attaque());
        assertEquals(model1.getVitesse_de_deplacement(), dto1.getVitesse_de_deplacement());
        assertEquals(model1.getChemin_image(), dto1.getChemin_image());
        assertEquals(model1.getId_map(), dto1.getId_map());

        ZombieDTO dto2 = dtos.get(1);
        assertEquals(model2.getId_zombie(), dto2.getId_zombie());
        assertEquals(model2.getNom(), dto2.getNom());
        assertEquals(model2.getPoint_de_vie(), dto2.getPoint_de_vie());
        assertEquals(model2.getAttaque_par_seconde(), dto2.getAttaque_par_seconde());
        assertEquals(model2.getDegat_attaque(), dto2.getDegat_attaque());
        assertEquals(model2.getVitesse_de_deplacement(), dto2.getVitesse_de_deplacement());
        assertEquals(model2.getChemin_image(), dto2.getChemin_image());
        assertEquals(model2.getId_map(), dto2.getId_map());
    }

    @Test
    void mapListModelToDTO_withNullList_shouldReturnNull() {
        // When
        List<ZombieDTO> dtos = mapper.mapListModelToDTO(null);

        // Then
        assertNull(dtos);
    }
} 