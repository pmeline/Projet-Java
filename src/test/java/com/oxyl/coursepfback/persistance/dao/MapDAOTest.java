package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.MapEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MapDAO mapDAO;

    private MapEntity mapEntity;

    @BeforeEach
    void setUp() {
        mapEntity = new MapEntity();
        mapEntity.setId_map(1L);
        mapEntity.setLigne(5);
        mapEntity.setColonne(9);
        mapEntity.setChemin_image("map1.png");
    }

    @Test
    void getAllMaps_shouldReturnListOfMaps() {
        List<MapEntity> expectedMaps = Arrays.asList(mapEntity);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedMaps);

        List<MapEntity> result = mapDAO.getAllMaps();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mapEntity, result.get(0));
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
    }

    @Test
    void getMap_shouldReturnMap() {
        when(jdbcTemplate.query(
            eq("SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?"),
            any(RowMapper.class),
            eq(1L)
        )).thenReturn(Arrays.asList(mapEntity));

        MapEntity result = mapDAO.getMap(1L);

        assertNotNull(result);
        assertEquals(mapEntity, result);
        verify(jdbcTemplate).query(
            eq("SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?"),
            any(RowMapper.class),
            eq(1L)
        );
    }

    @Test
    void getMap_withNonExistentId_shouldReturnNull() {
        when(jdbcTemplate.query(
            eq("SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?"),
            any(RowMapper.class),
            eq(999L)
        )).thenReturn(Arrays.asList());

        MapEntity result = mapDAO.getMap(999L);

        assertNull(result);
        verify(jdbcTemplate).query(
            eq("SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?"),
            any(RowMapper.class),
            eq(999L)
        );
    }

    @Test
    void createMap_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)"),
            eq(5),
            eq(9),
            eq("map1.png")
        )).thenReturn(1);

        assertDoesNotThrow(() -> mapDAO.createMap(mapEntity));
        verify(jdbcTemplate).update(
            eq("INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)"),
            eq(5),
            eq(9),
            eq("map1.png")
        );
    }

    @Test
    void updateMap_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?"),
            eq(5),
            eq(9),
            eq("map1.png"),
            eq(1L)
        )).thenReturn(1);

        assertDoesNotThrow(() -> mapDAO.updateMap(mapEntity));
        verify(jdbcTemplate).update(
            eq("UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?"),
            eq(5),
            eq(9),
            eq("map1.png"),
            eq(1L)
        );
    }

    @Test
    void deleteMap_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("DELETE FROM map WHERE id_map = ?"),
            eq(1L)
        )).thenReturn(1);

        assertDoesNotThrow(() -> mapDAO.deleteMap(1L));
        verify(jdbcTemplate).update(
            eq("DELETE FROM map WHERE id_map = ?"),
            eq(1L)
        );
    }
} 