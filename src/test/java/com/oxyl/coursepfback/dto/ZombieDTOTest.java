package com.oxyl.coursepfback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZombieDTOTest {

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
        ZombieDTO zombieDTO = new ZombieDTO(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, vitesseDeDeplacement,
            cheminImage, idMap
        );

        // Then
        assertEquals(id, zombieDTO.getId_zombie());
        assertEquals(nom, zombieDTO.getNom());
        assertEquals(pointDeVie, zombieDTO.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombieDTO.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombieDTO.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombieDTO.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombieDTO.getChemin_image());
        assertEquals(idMap, zombieDTO.getId_map());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        ZombieDTO zombieDTO = new ZombieDTO();

        // Then
        assertNull(zombieDTO.getId_zombie());
        assertNull(zombieDTO.getNom());
        assertNull(zombieDTO.getPoint_de_vie());
        assertNull(zombieDTO.getAttaque_par_seconde());
        assertNull(zombieDTO.getDegat_attaque());
        assertNull(zombieDTO.getVitesse_de_deplacement());
        assertNull(zombieDTO.getChemin_image());
        assertNull(zombieDTO.getId_map());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        ZombieDTO zombieDTO = new ZombieDTO();
        Long id = 1L;
        String nom = "Zombie de base";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Double vitesseDeDeplacement = 1.0;
        String cheminImage = "zombie.png";
        Long idMap = 1L;

        // When
        zombieDTO.setId_zombie(id);
        zombieDTO.setNom(nom);
        zombieDTO.setPoint_de_vie(pointDeVie);
        zombieDTO.setAttaque_par_seconde(attaqueParSeconde);
        zombieDTO.setDegat_attaque(degatAttaque);
        zombieDTO.setVitesse_de_deplacement(vitesseDeDeplacement);
        zombieDTO.setChemin_image(cheminImage);
        zombieDTO.setId_map(idMap);

        // Then
        assertEquals(id, zombieDTO.getId_zombie());
        assertEquals(nom, zombieDTO.getNom());
        assertEquals(pointDeVie, zombieDTO.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombieDTO.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombieDTO.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombieDTO.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombieDTO.getChemin_image());
        assertEquals(idMap, zombieDTO.getId_map());
    }
} 