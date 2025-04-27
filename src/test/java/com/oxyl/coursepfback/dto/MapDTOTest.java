package com.oxyl.coursepfback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDTOTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        MapDTO mapDTO = new MapDTO(id, ligne, colonne, cheminImage);

        // Then
        assertEquals(id, mapDTO.getId_map());
        assertEquals(ligne, mapDTO.getLigne());
        assertEquals(colonne, mapDTO.getColonne());
        assertEquals(cheminImage, mapDTO.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        MapDTO mapDTO = new MapDTO();

        // Then
        assertNull(mapDTO.getId_map());
        assertNull(mapDTO.getLigne());
        assertNull(mapDTO.getColonne());
        assertNull(mapDTO.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        MapDTO mapDTO = new MapDTO();
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        mapDTO.setId_map(id);
        mapDTO.setLigne(ligne);
        mapDTO.setColonne(colonne);
        mapDTO.setChemin_image(cheminImage);

        // Then
        assertEquals(id, mapDTO.getId_map());
        assertEquals(ligne, mapDTO.getLigne());
        assertEquals(colonne, mapDTO.getColonne());
        assertEquals(cheminImage, mapDTO.getChemin_image());
    }
} 