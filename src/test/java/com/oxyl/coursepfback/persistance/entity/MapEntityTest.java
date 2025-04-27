package com.oxyl.coursepfback.persistance.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapEntityTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        MapEntity mapEntity = new MapEntity(id, ligne, colonne, cheminImage);

        // Then
        assertEquals(id, mapEntity.getId_map());
        assertEquals(ligne, mapEntity.getLigne());
        assertEquals(colonne, mapEntity.getColonne());
        assertEquals(cheminImage, mapEntity.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        MapEntity mapEntity = new MapEntity();

        // Then
        assertNull(mapEntity.getId_map());
        assertNull(mapEntity.getLigne());
        assertNull(mapEntity.getColonne());
        assertNull(mapEntity.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        MapEntity mapEntity = new MapEntity();
        Long id = 1L;
        Integer ligne = 5;
        Integer colonne = 9;
        String cheminImage = "map1.png";

        // When
        mapEntity.setId_map(id);
        mapEntity.setLigne(ligne);
        mapEntity.setColonne(colonne);
        mapEntity.setChemin_image(cheminImage);

        // Then
        assertEquals(id, mapEntity.getId_map());
        assertEquals(ligne, mapEntity.getLigne());
        assertEquals(colonne, mapEntity.getColonne());
        assertEquals(cheminImage, mapEntity.getChemin_image());
    }
} 