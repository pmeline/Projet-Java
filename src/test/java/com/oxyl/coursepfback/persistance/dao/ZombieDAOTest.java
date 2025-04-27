package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
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
class ZombieDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ZombieDAO zombieDAO;

    private ZombieEntity zombieEntity;

    @BeforeEach
    void setUp() {
        zombieEntity = new ZombieEntity();
        zombieEntity.setId_zombie(1L);
        zombieEntity.setNom("Zombie de base");
        zombieEntity.setPoint_de_vie(100);
        zombieEntity.setVitesse_de_deplacement(1.0);
        zombieEntity.setDegat_attaque(10);
        zombieEntity.setId_map(1L);
        zombieEntity.setChemin_image("zombie.png");
    }

    @Test
    void getAllZombies_shouldReturnListOfZombies() {
        List<ZombieEntity> expectedZombies = Arrays.asList(zombieEntity);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedZombies);

        List<ZombieEntity> result = zombieDAO.getAllZombies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieEntity, result.get(0));
        verify(jdbcTemplate).query(anyString(), any(RowMapper.class));
    }

    @Test
    void getZombie_shouldReturnZombie() {
        when(jdbcTemplate.query(
            eq("SELECT * FROM zombie WHERE id_zombie = ?"),
            any(RowMapper.class),
            eq(1L)
        )).thenReturn(Arrays.asList(zombieEntity));

        ZombieEntity result = zombieDAO.getZombie(1L);

        assertNotNull(result);
        assertEquals(zombieEntity, result);
        verify(jdbcTemplate).query(
            eq("SELECT * FROM zombie WHERE id_zombie = ?"),
            any(RowMapper.class),
            eq(1L)
        );
    }

    @Test
    void getZombie_withNonExistentId_shouldReturnNull() {
        when(jdbcTemplate.query(
            eq("SELECT * FROM zombie WHERE id_zombie = ?"),
            any(RowMapper.class),
            eq(999L)
        )).thenReturn(Arrays.asList());

        ZombieEntity result = zombieDAO.getZombie(999L);

        assertNull(result);
        verify(jdbcTemplate).query(
            eq("SELECT * FROM zombie WHERE id_zombie = ?"),
            any(RowMapper.class),
            eq(999L)
        );
    }

    @Test
    void getZombiesByMapId_shouldReturnListOfZombies() {
        List<ZombieEntity> expectedZombies = Arrays.asList(zombieEntity);
        when(jdbcTemplate.query(
            eq("SELECT * FROM zombie WHERE id_map = ?"),
            any(RowMapper.class),
            eq(1L)
        )).thenReturn(expectedZombies);

        List<ZombieEntity> result = zombieDAO.getZombiesByMapId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieEntity, result.get(0));
        verify(jdbcTemplate).query(
            eq("SELECT * FROM zombie WHERE id_map = ?"),
            any(RowMapper.class),
            eq(1L)
        );
    }

    @Test
    void createZombie_shouldSucceed() {
        doReturn(1).when(jdbcTemplate).update(
            eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L)
        );

        assertDoesNotThrow(() -> zombieDAO.createZombie(zombieEntity));
        verify(jdbcTemplate).update(
            eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L)
        );
    }

    @Test
    void updateZombie_shouldSucceed() {
        doReturn(1).when(jdbcTemplate).update(
            eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L),
            eq(1L)
        );

        assertDoesNotThrow(() -> zombieDAO.updateZombie(zombieEntity));
        verify(jdbcTemplate).update(
            eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L),
            eq(1L)
        );
    }

    @Test
    void updateZombie_withNonExistentId_shouldThrowIllegalArgumentException() {
        doReturn(0).when(jdbcTemplate).update(
            eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L),
            eq(999L)
        );

        ZombieEntity nonExistentZombie = new ZombieEntity();
        nonExistentZombie.setId_zombie(999L);
        nonExistentZombie.setNom("Zombie de base");
        nonExistentZombie.setPoint_de_vie(100);
        nonExistentZombie.setVitesse_de_deplacement(1.0);
        nonExistentZombie.setDegat_attaque(10);
        nonExistentZombie.setId_map(1L);
        nonExistentZombie.setChemin_image("zombie.png");

        assertThrows(IllegalArgumentException.class, () -> zombieDAO.updateZombie(nonExistentZombie));
        verify(jdbcTemplate).update(
            eq("UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?"),
            eq("Zombie de base"),
            eq(100),
            eq(null),
            eq(10),
            eq(1.0),
            eq("zombie.png"),
            eq(1L),
            eq(999L)
        );
    }

    @Test
    void deleteZombie_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("DELETE FROM zombie WHERE id_zombie = ?"),
            eq(1L)
        )).thenReturn(1);

        assertDoesNotThrow(() -> zombieDAO.deleteZombie(1L));
        verify(jdbcTemplate).update(
            eq("DELETE FROM zombie WHERE id_zombie = ?"),
            eq(1L)
        );
    }

    @Test
    void deleteZombie_withNonExistentId_shouldSucceed() {
        when(jdbcTemplate.update(
            eq("DELETE FROM zombie WHERE id_zombie = ?"),
            eq(999L)
        )).thenReturn(0);

        assertDoesNotThrow(() -> zombieDAO.deleteZombie(999L));
        verify(jdbcTemplate).update(
            eq("DELETE FROM zombie WHERE id_zombie = ?"),
            eq(999L)
        );
    }
} 