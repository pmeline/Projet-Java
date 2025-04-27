package com.oxyl.coursepfback.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapModelTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        MapModel mapModel = new MapModel(id, ligne, colonne, cheminImage);

        // Then
        assertEquals(id, mapModel.getId_map());
        assertEquals(ligne, mapModel.getLigne());
        assertEquals(colonne, mapModel.getColonne());
        assertEquals(cheminImage, mapModel.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        MapModel mapModel = new MapModel();

        // Then
        assertNull(mapModel.getId_map());
        assertNull(mapModel.getLigne());
        assertNull(mapModel.getColonne());
        assertNull(mapModel.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        MapModel mapModel = new MapModel();
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        mapModel.setId_map(id);
        mapModel.setLigne(ligne);
        mapModel.setColonne(colonne);
        mapModel.setChemin_image(cheminImage);

        // Then
        assertEquals(id, mapModel.getId_map());
        assertEquals(ligne, mapModel.getLigne());
        assertEquals(colonne, mapModel.getColonne());
        assertEquals(cheminImage, mapModel.getChemin_image());
    }
} 