package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.PlanteDTO;
import com.oxyl.coursepfback.dto.PlanteDTOMapper;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.service.PlanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @Mock
    private PlanteDTOMapper mapper;

    private PlanteController planteController;
    private PlanteModel planteModel;
    private PlanteDTO planteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        planteController = new PlanteController(planteService, mapper);

        planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("Tournesol");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(10);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(25.0);
        planteModel.setEffet("Génère du soleil");
        planteModel.setChemin_image("tournesol.png");

        planteDTO = new PlanteDTO();
        planteDTO.setId_plante(1L);
        planteDTO.setNom("Tournesol");
        planteDTO.setPoint_de_vie(100);
        planteDTO.setAttaque_par_seconde(1.0);
        planteDTO.setDegat_attaque(10);
        planteDTO.setCout(50);
        planteDTO.setSoleil_par_seconde(25.0);
        planteDTO.setEffet("Génère du soleil");
        planteDTO.setChemin_image("tournesol.png");
    }

    @Test
    void getAllPlantes_shouldReturnListOfPlantes() {
        List<PlanteModel> plantes = Arrays.asList(planteModel);
        List<PlanteDTO> expectedDTOs = Arrays.asList(planteDTO);

        when(planteService.getAllPlantes()).thenReturn(plantes);
        when(mapper.mapListModelsToDTO(plantes)).thenReturn(expectedDTOs);

        List<PlanteDTO> result = planteController.getAllPlantes();

        assertEquals(expectedDTOs, result);
        verify(planteService).getAllPlantes();
        verify(mapper).mapListModelsToDTO(plantes);
    }

    @Test
    void getPlanteById_shouldReturnPlante() {
        when(planteService.getPlante(1L)).thenReturn(planteModel);
        when(mapper.mapModelToDTO(planteModel)).thenReturn(planteDTO);

        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planteDTO, response.getBody());
        verify(planteService).getPlante(1L);
        verify(mapper).mapModelToDTO(planteModel);
    }

    @Test
    void getPlanteById_withNonExistentId_shouldReturnNotFound() {
        Long id = 999L;
        when(planteService.getPlante(id)).thenReturn(null);

        ResponseEntity<PlanteDTO> response = planteController.getPlanteById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(planteService).getPlante(id);
        verify(mapper, never()).mapModelToDTO(any());
    }

    @Test
    void createPlante_shouldCreateAndReturnPlante() {
        when(mapper.mapDTOToModel(planteDTO)).thenReturn(planteModel);
        when(mapper.mapModelToDTO(planteModel)).thenReturn(planteDTO);

        ResponseEntity<PlanteDTO> response = planteController.createPlante(planteDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(planteDTO, response.getBody());
        verify(planteService).createPlante(planteModel);
        verify(mapper).mapDTOToModel(planteDTO);
        verify(mapper).mapModelToDTO(planteModel);
    }

    @Test
    void updatePlante_shouldUpdateAndReturnPlante() {
        when(mapper.mapDTOToModel(planteDTO)).thenReturn(planteModel);
        when(mapper.mapModelToDTO(planteModel)).thenReturn(planteDTO);

        ResponseEntity<PlanteDTO> response = planteController.updatePlante(1L, planteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planteDTO, response.getBody());
        verify(planteService).updatePlante(planteModel);
        verify(mapper).mapDTOToModel(planteDTO);
        verify(mapper).mapModelToDTO(planteModel);
    }

    @Test
    void deletePlante_shouldDeletePlante() {
        ResponseEntity<Object> response = planteController.deletePlante(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(planteService).deletePlante(1L);
    }

    @Test
    void getValidationExample_shouldReturnValidationResult() {
        List<PlanteModel> plantes = Arrays.asList(planteModel);
        when(planteService.getAllPlantes()).thenReturn(plantes);
        when(mapper.mapListModelsToDTO(plantes)).thenReturn(Arrays.asList(planteDTO));

        ResponseEntity<Map<String, Object>> response = planteController.getValidationExample();
        Map<String, Object> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue((Boolean) result.get("valid"));
        assertEquals("Toutes les plantes sont valides", result.get("message"));
        assertTrue(((List<?>) result.get("errors")).isEmpty());
        assertEquals(1, result.get("total_plantes"));
        verify(planteService).getAllPlantes();
        verify(mapper).mapListModelsToDTO(plantes);
    }

    @Test
    void getValidationExample_withNoPlantes_shouldReturnValidationErrors() {
        when(planteService.getAllPlantes()).thenReturn(Arrays.asList());

        ResponseEntity<Map<String, Object>> response = planteController.getValidationExample();
        Map<String, Object> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse((Boolean) result.get("valid"));
        assertEquals("Erreurs de validation détectées", result.get("message"));
        assertEquals(1, ((List<?>) result.get("errors")).size());
        assertEquals("Aucune plante trouvée dans la base de données", ((List<?>) result.get("errors")).get(0));
        assertEquals(0, result.get("total_plantes"));
        verify(planteService).getAllPlantes();
    }
} 