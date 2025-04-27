package com.oxyl.coursepfback.persistance.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZombieEntityTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        Long id = 1L;
        String nom = "Zombie de base";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Double vitesseDeDeplacement = 1.0;
        String cheminImage = "zombie.png";
        Long idMap = 1L;

        // When
        ZombieEntity zombie = new ZombieEntity(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, vitesseDeDeplacement, cheminImage, idMap
        );

        // Then
        assertNotNull(zombie);
        assertEquals(id, zombie.getId_zombie());
        assertEquals(nom, zombie.getNom());
        assertEquals(pointDeVie, zombie.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombie.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombie.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombie.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombie.getChemin_image());
        assertEquals(idMap, zombie.getId_map());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        ZombieEntity zombie = new ZombieEntity();

        // Then
        assertNotNull(zombie);
        assertNull(zombie.getId_zombie());
        assertNull(zombie.getNom());
        assertNull(zombie.getPoint_de_vie());
        assertNull(zombie.getAttaque_par_seconde());
        assertNull(zombie.getDegat_attaque());
        assertNull(zombie.getVitesse_de_deplacement());
        assertNull(zombie.getChemin_image());
        assertNull(zombie.getId_map());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        ZombieEntity zombie = new ZombieEntity();
        Long id = 1L;
        String nom = "Zombie de base";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Double vitesseDeDeplacement = 1.0;
        String cheminImage = "zombie.png";
        Long idMap = 1L;

        // When
        zombie.setId_zombie(id);
        zombie.setNom(nom);
        zombie.setPoint_de_vie(pointDeVie);
        zombie.setAttaque_par_seconde(attaqueParSeconde);
        zombie.setDegat_attaque(degatAttaque);
        zombie.setVitesse_de_deplacement(vitesseDeDeplacement);
        zombie.setChemin_image(cheminImage);
        zombie.setId_map(idMap);

        // Then
        assertEquals(id, zombie.getId_zombie());
        assertEquals(nom, zombie.getNom());
        assertEquals(pointDeVie, zombie.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombie.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombie.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombie.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombie.getChemin_image());
        assertEquals(idMap, zombie.getId_map());
    }
} 