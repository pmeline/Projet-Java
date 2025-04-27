package com.oxyl.coursepfback.persistance.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanteEntityTest {

    @Test
    void constructor_shouldSetAllFields() {
        // Given
        Long id = 1L;
        String nom = "Tournesol";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Integer cout = 50;
        Double soleilParSeconde = 1.0;
        String effet = "Production de soleil";
        String cheminImage = "tournesol.png";

        // When
        PlanteEntity planteEntity = new PlanteEntity(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, cout, soleilParSeconde,
            effet, cheminImage
        );

        // Then
        assertEquals(id, planteEntity.getId_plante());
        assertEquals(nom, planteEntity.getNom());
        assertEquals(pointDeVie, planteEntity.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteEntity.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteEntity.getDegat_attaque());
        assertEquals(cout, planteEntity.getCout());
        assertEquals(soleilParSeconde, planteEntity.getSoleil_par_seconde());
        assertEquals(effet, planteEntity.getEffet());
        assertEquals(cheminImage, planteEntity.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        PlanteEntity planteEntity = new PlanteEntity();

        // Then
        assertNull(planteEntity.getId_plante());
        assertNull(planteEntity.getNom());
        assertNull(planteEntity.getPoint_de_vie());
        assertNull(planteEntity.getAttaque_par_seconde());
        assertNull(planteEntity.getDegat_attaque());
        assertNull(planteEntity.getCout());
        assertNull(planteEntity.getSoleil_par_seconde());
        assertNull(planteEntity.getEffet());
        assertNull(planteEntity.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        PlanteEntity planteEntity = new PlanteEntity();
        Long id = 1L;
        String nom = "Tournesol";
        Integer pointDeVie = 100;
        Double attaqueParSeconde = 1.0;
        Integer degatAttaque = 20;
        Integer cout = 50;
        Double soleilParSeconde = 1.0;
        String effet = "Production de soleil";
        String cheminImage = "tournesol.png";

        // When
        planteEntity.setId_plante(id);
        planteEntity.setNom(nom);
        planteEntity.setPoint_de_vie(pointDeVie);
        planteEntity.setAttaque_par_seconde(attaqueParSeconde);
        planteEntity.setDegat_attaque(degatAttaque);
        planteEntity.setCout(cout);
        planteEntity.setSoleil_par_seconde(soleilParSeconde);
        planteEntity.setEffet(effet);
        planteEntity.setChemin_image(cheminImage);

        // Then
        assertEquals(id, planteEntity.getId_plante());
        assertEquals(nom, planteEntity.getNom());
        assertEquals(pointDeVie, planteEntity.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteEntity.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteEntity.getDegat_attaque());
        assertEquals(cout, planteEntity.getCout());
        assertEquals(soleilParSeconde, planteEntity.getSoleil_par_seconde());
        assertEquals(effet, planteEntity.getEffet());
        assertEquals(cheminImage, planteEntity.getChemin_image());
    }
} 