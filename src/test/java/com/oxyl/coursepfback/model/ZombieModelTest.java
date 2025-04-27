package com.oxyl.coursepfback.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ZombieModelTest {

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
        ZombieModel zombieModel = new ZombieModel(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, vitesseDeDeplacement,
            cheminImage, idMap
        );

        // Then
        assertEquals(id, zombieModel.getId_zombie());
        assertEquals(nom, zombieModel.getNom());
        assertEquals(pointDeVie, zombieModel.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombieModel.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombieModel.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombieModel.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombieModel.getChemin_image());
        assertEquals(idMap, zombieModel.getId_map());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        ZombieModel zombieModel = new ZombieModel();

        // Then
        assertNull(zombieModel.getId_zombie());
        assertNull(zombieModel.getNom());
        assertNull(zombieModel.getPoint_de_vie());
        assertNull(zombieModel.getAttaque_par_seconde());
        assertNull(zombieModel.getDegat_attaque());
        assertNull(zombieModel.getVitesse_de_deplacement());
        assertNull(zombieModel.getChemin_image());
        assertNull(zombieModel.getId_map());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        ZombieModel zombieModel = new ZombieModel();
        Long id = 1L;
        String nom = "Zombie de base";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Double vitesseDeDeplacement = 1.0;
        String cheminImage = "zombie.png";
        Long idMap = 1L;

        // When
        zombieModel.setId_zombie(id);
        zombieModel.setNom(nom);
        zombieModel.setPoint_de_vie(pointDeVie);
        zombieModel.setAttaque_par_seconde(attaqueParSeconde);
        zombieModel.setDegat_attaque(degatAttaque);
        zombieModel.setVitesse_de_deplacement(vitesseDeDeplacement);
        zombieModel.setChemin_image(cheminImage);
        zombieModel.setId_map(idMap);

        // Then
        assertEquals(id, zombieModel.getId_zombie());
        assertEquals(nom, zombieModel.getNom());
        assertEquals(pointDeVie, zombieModel.getPoint_de_vie());
        assertEquals(attaqueParSeconde, zombieModel.getAttaque_par_seconde());
        assertEquals(degatAttaque, zombieModel.getDegat_attaque());
        assertEquals(vitesseDeDeplacement, zombieModel.getVitesse_de_deplacement());
        assertEquals(cheminImage, zombieModel.getChemin_image());
        assertEquals(idMap, zombieModel.getId_map());
    }
} 