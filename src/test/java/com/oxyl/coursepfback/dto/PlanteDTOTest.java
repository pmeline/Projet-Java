package com.oxyl.coursepfback.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanteDTOTest {

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
        PlanteDTO planteDTO = new PlanteDTO(
            id, nom, pointDeVie, attaqueParSeconde,
            degatAttaque, cout, soleilParSeconde,
            effet, cheminImage
        );

        // Then
        assertEquals(id, planteDTO.getId_plante());
        assertEquals(nom, planteDTO.getNom());
        assertEquals(pointDeVie, planteDTO.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteDTO.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteDTO.getDegat_attaque());
        assertEquals(cout, planteDTO.getCout());
        assertEquals(soleilParSeconde, planteDTO.getSoleil_par_seconde());
        assertEquals(effet, planteDTO.getEffet());
        assertEquals(cheminImage, planteDTO.getChemin_image());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyObject() {
        // When
        PlanteDTO planteDTO = new PlanteDTO();

        // Then
        assertNull(planteDTO.getId_plante());
        assertNull(planteDTO.getNom());
        assertNull(planteDTO.getPoint_de_vie());
        assertNull(planteDTO.getAttaque_par_seconde());
        assertNull(planteDTO.getDegat_attaque());
        assertNull(planteDTO.getCout());
        assertNull(planteDTO.getSoleil_par_seconde());
        assertNull(planteDTO.getEffet());
        assertNull(planteDTO.getChemin_image());
    }

    @Test
    void setters_shouldSetFields() {
        // Given
        PlanteDTO planteDTO = new PlanteDTO();
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
        planteDTO.setId_plante(id);
        planteDTO.setNom(nom);
        planteDTO.setPoint_de_vie(pointDeVie);
        planteDTO.setAttaque_par_seconde(attaqueParSeconde);
        planteDTO.setDegat_attaque(degatAttaque);
        planteDTO.setCout(cout);
        planteDTO.setSoleil_par_seconde(soleilParSeconde);
        planteDTO.setEffet(effet);
        planteDTO.setChemin_image(cheminImage);

        // Then
        assertEquals(id, planteDTO.getId_plante());
        assertEquals(nom, planteDTO.getNom());
        assertEquals(pointDeVie, planteDTO.getPoint_de_vie());
        assertEquals(attaqueParSeconde, planteDTO.getAttaque_par_seconde());
        assertEquals(degatAttaque, planteDTO.getDegat_attaque());
        assertEquals(cout, planteDTO.getCout());
        assertEquals(soleilParSeconde, planteDTO.getSoleil_par_seconde());
        assertEquals(effet, planteDTO.getEffet());
        assertEquals(cheminImage, planteDTO.getChemin_image());
    }
} 