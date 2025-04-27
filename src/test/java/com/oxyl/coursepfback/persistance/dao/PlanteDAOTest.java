package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
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
class PlanteDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PlanteDAO planteDAO;

    private PlanteEntity planteEntity;

    @BeforeEach
    void setUp() {
        planteEntity = new PlanteEntity();
        planteEntity.setId_plante(1L);
        planteEntity.setNom("Tournesol");
        planteEntity.setPoint_de_vie(100);
        planteEntity.setAttaque_par_seconde(1.0);
        planteEntity.setDegat_attaque(10);
        planteEntity.setCout(50);
        planteEntity.setSoleil_par_seconde(25.0);
        planteEntity.setEffet("Génère du soleil");
        planteEntity.setChemin_image("tournesol.png");
    }

    @Test
    void getAllPlantes_shouldReturnListOfPlantes() {
        List<PlanteEntity> expectedPlantes = Arrays.asList(planteEntity);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedPlantes);

        List<PlanteEntity> result = planteDAO.getAllPlantes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(planteEntity, result.get(0));
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
    }

    @Test
    void getPlante_shouldReturnPlante() {
        when(jdbcTemplate.queryForObject(
            eq("SELECT * FROM plante WHERE id_plante = ?"),
            any(RowMapper.class),
            eq(1L)
        )).thenReturn(planteEntity);

        PlanteEntity result = planteDAO.getPlante(1L);

        assertNotNull(result);
        assertEquals(planteEntity, result);
        verify(jdbcTemplate).queryForObject(
            eq("SELECT * FROM plante WHERE id_plante = ?"),
            any(RowMapper.class),
            eq(1L)
        );
    }

    @Test
    void getPlante_withNonExistentId_shouldThrowEmptyResultDataAccessException() {
        when(jdbcTemplate.queryForObject(
            eq("SELECT * FROM plante WHERE id_plante = ?"),
            any(RowMapper.class),
            eq(999L)
        )).thenThrow(new org.springframework.dao.EmptyResultDataAccessException(1));

        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> planteDAO.getPlante(999L));
        verify(jdbcTemplate).queryForObject(
            eq("SELECT * FROM plante WHERE id_plante = ?"),
            any(RowMapper.class),
            eq(999L)
        );
    }

    @Test
    void createPlante_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
            eq("Tournesol"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png")
        )).thenReturn(1);

        assertDoesNotThrow(() -> planteDAO.createPlante(planteEntity));
        verify(jdbcTemplate).update(
            eq("INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
            eq("Tournesol"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png")
        );
    }

    @Test
    void updatePlante_shouldSucceed() {
        doReturn(1).when(jdbcTemplate).update(
            eq("UPDATE plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, nom = ? WHERE id_plante = ?"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png"),
            eq("Tournesol"),
            eq(1L)
        );

        assertDoesNotThrow(() -> planteDAO.updatePlante(planteEntity));
        verify(jdbcTemplate).update(
            eq("UPDATE plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, nom = ? WHERE id_plante = ?"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png"),
            eq("Tournesol"),
            eq(1L)
        );
    }

    @Test
    void updatePlante_withNonExistentId_shouldThrowIllegalArgumentException() {
        doReturn(0).when(jdbcTemplate).update(
            eq("UPDATE plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, nom = ? WHERE id_plante = ?"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png"),
            eq("Tournesol"),
            eq(999L)
        );

        PlanteEntity nonExistentPlante = new PlanteEntity();
        nonExistentPlante.setId_plante(999L);
        nonExistentPlante.setNom("Tournesol");
        nonExistentPlante.setPoint_de_vie(100);
        nonExistentPlante.setAttaque_par_seconde(1.0);
        nonExistentPlante.setDegat_attaque(10);
        nonExistentPlante.setCout(50);
        nonExistentPlante.setSoleil_par_seconde(25.0);
        nonExistentPlante.setEffet("Génère du soleil");
        nonExistentPlante.setChemin_image("tournesol.png");

        assertThrows(IllegalArgumentException.class, () -> planteDAO.updatePlante(nonExistentPlante));
        verify(jdbcTemplate).update(
            eq("UPDATE plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, nom = ? WHERE id_plante = ?"),
            eq(100),
            eq(1.0),
            eq(10),
            eq(50),
            eq(25.0),
            eq("Génère du soleil"),
            eq("tournesol.png"),
            eq("Tournesol"),
            eq(999L)
        );
    }

    @Test
    void deletePlante_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("DELETE FROM plante WHERE id_plante = ?"),
            eq(1L)
        )).thenReturn(1);

        assertDoesNotThrow(() -> planteDAO.deletePlante(1L));
        verify(jdbcTemplate).update(
            eq("DELETE FROM plante WHERE id_plante = ?"),
            eq(1L)
        );
    }

    @Test
    void deletePlante_withNonExistentId_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("DELETE FROM plante WHERE id_plante = ?"),
            eq(999L)
        )).thenReturn(0);

        assertDoesNotThrow(() -> planteDAO.deletePlante(999L));
        verify(jdbcTemplate).update(
            eq("DELETE FROM plante WHERE id_plante = ?"),
            eq(999L)
        );
    }
} 