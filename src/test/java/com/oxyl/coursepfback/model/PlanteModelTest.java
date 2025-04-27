package com.oxyl.coursepfback.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanteModelTest {

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
        PlanteModel planteModel = new PlanteModel(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, cout, soleilParSeconde,
            effet, cheminImage
        );

        // Then
        assertEquals(id, planteModel.getId_plante());
        assertEquals(nom, planteModel.getNom());
        assertEquals(pointDeVie, planteModel.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteModel.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteModel.getDegat_attaque());
        assertEquals(cout, planteModel.getCout());
        assertEquals(soleilParSeconde, planteModel.getSoleil_par_seconde());
        assertEquals(effet, planteModel.getEffet());
        assertEquals(cheminImage, planteModel.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        PlanteModel planteModel = new PlanteModel();

        // Then
        assertNull(planteModel.getId_plante());
        assertNull(planteModel.getNom());
        assertNull(planteModel.getPoint_de_vie());
        assertNull(planteModel.getAttaque_par_seconde());
        assertNull(planteModel.getDegat_attaque());
        assertNull(planteModel.getCout());
        assertNull(planteModel.getSoleil_par_seconde());
        assertNull(planteModel.getEffet());
        assertNull(planteModel.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        PlanteModel planteModel = new PlanteModel();
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
        planteModel.setId_plante(id);
        planteModel.setNom(nom);
        planteModel.setPoint_de_vie(pointDeVie);
        planteModel.setAttaque_par_seconde(attaqueParSeconde);
        planteModel.setDegat_attaque(degatAttaque);
        planteModel.setCout(cout);
        planteModel.setSoleil_par_seconde(soleilParSeconde);
        planteModel.setEffet(effet);
        planteModel.setChemin_image(cheminImage);

        // Then
        assertEquals(id, planteModel.getId_plante());
        assertEquals(nom, planteModel.getNom());
        assertEquals(pointDeVie, planteModel.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteModel.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteModel.getDegat_attaque());
        assertEquals(cout, planteModel.getCout());
        assertEquals(soleilParSeconde, planteModel.getSoleil_par_seconde());
        assertEquals(effet, planteModel.getEffet());
        assertEquals(cheminImage, planteModel.getChemin_image());
    }
} 