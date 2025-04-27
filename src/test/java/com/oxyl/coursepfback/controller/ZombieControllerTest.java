package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.ZombieDTO;
import com.oxyl.coursepfback.dto.ZombieDTOMapper;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.service.ZombieService;
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

public class ZombieControllerTest {

    @Mock
    private ZombieService zombieService;

    @Mock
    private ZombieDTOMapper mapper;

    private ZombieController zombieController;
    private ZombieModel zombieModel;
    private ZombieDTO zombieDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zombieController = new ZombieController(zombieService, mapper);

        zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setNom("Zombie Test");
        zombieModel.setPoint_de_vie(100);
        zombieModel.setVitesse_de_deplacement(5.0);
        zombieModel.setDegat_attaque(20);
        zombieModel.setChemin_image("zombie.png");

        zombieDTO = new ZombieDTO();
        zombieDTO.setId_zombie(1L);
        zombieDTO.setNom("Zombie Test");
        zombieDTO.setPoint_de_vie(100);
        zombieDTO.setVitesse_de_deplacement(5.0);
        zombieDTO.setDegat_attaque(20);
        zombieDTO.setChemin_image("zombie.png");
    }

    @Test
    void getAllZombies_shouldReturnListOfZombies() {
        List<ZombieModel> zombies = Arrays.asList(zombieModel);
        List<ZombieDTO> expectedDTOs = Arrays.asList(zombieDTO);

        when(zombieService.getAllZombies()).thenReturn(zombies);
        when(mapper.mapListModelToDTO(zombies)).thenReturn(expectedDTOs);

        List<ZombieDTO> result = zombieController.getAllZombies();

        assertEquals(expectedDTOs, result);
        verify(zombieService).getAllZombies();
        verify(mapper).mapListModelToDTO(zombies);
    }

    @Test
    void getZombieById_shouldReturnZombie() {
        when(zombieService.getZombie(1L)).thenReturn(zombieModel);
        when(mapper.mapModelToDTO(zombieModel)).thenReturn(zombieDTO);

        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zombieDTO, response.getBody());
        verify(zombieService).getZombie(1L);
        verify(mapper).mapModelToDTO(zombieModel);
    }

    @Test
    void getZombieById_withNonExistentId_shouldReturnNotFound() {
        Long id = 999L;
        when(zombieService.getZombie(id)).thenReturn(null);

        ResponseEntity<ZombieDTO> response = zombieController.getZombieById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(zombieService).getZombie(id);
        verify(mapper, never()).mapModelToDTO(any());
    }

    @Test
    void createZombie_shouldCreateAndReturnZombie() {
        when(mapper.mapDTOToModel(zombieDTO)).thenReturn(zombieModel);
        when(mapper.mapModelToDTO(zombieModel)).thenReturn(zombieDTO);

        ResponseEntity<ZombieDTO> response = zombieController.createZombie(zombieDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(zombieDTO, response.getBody());
        verify(zombieService).createZombie(zombieModel);
        verify(mapper).mapDTOToModel(zombieDTO);
        verify(mapper).mapModelToDTO(zombieModel);
    }

    @Test
    void updateZombie_shouldUpdateAndReturnZombie() {
        when(mapper.mapDTOToModel(zombieDTO)).thenReturn(zombieModel);
        when(mapper.mapModelToDTO(zombieModel)).thenReturn(zombieDTO);

        ResponseEntity<ZombieDTO> response = zombieController.updateZombie(1L, zombieDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zombieDTO, response.getBody());
        verify(zombieService).updateZombie(zombieModel);
        verify(mapper).mapDTOToModel(zombieDTO);
        verify(mapper).mapModelToDTO(zombieModel);
    }

    @Test
    void deleteZombie_shouldDeleteZombie() {
        ResponseEntity<Object> response = zombieController.deleteZombie(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zombieService).deleteZombie(1L);
    }

    @Test
    void getValidationExample_shouldReturnValidationResult() {
        // Configuration des mocks
        List<ZombieModel> zombies = Arrays.asList(zombieModel);
        when(zombieService.getAllZombies()).thenReturn(zombies);
        when(mapper.mapListModelToDTO(zombies)).thenReturn(Arrays.asList(zombieDTO));

        // Appel de la méthode
        ResponseEntity<Map<String, Object>> response = zombieController.getValidationExample();
        Map<String, Object> result = response.getBody();

        // Vérifications
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue((Boolean) result.get("valid"));
        assertEquals("Tous les zombies sont valides", result.get("message"));
        assertTrue(((List<?>) result.get("errors")).isEmpty());
        assertEquals(1, result.get("total_zombies"));
        
        // Vérification des appels
        verify(zombieService).getAllZombies();
        verify(mapper).mapListModelToDTO(zombies);
    }

    @Test
    void getValidationExample_withNoZombies_shouldReturnValidationErrors() {
        when(zombieService.getAllZombies()).thenReturn(Arrays.asList());

        ResponseEntity<Map<String, Object>> response = zombieController.getValidationExample();
        Map<String, Object> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse((Boolean) result.get("valid"));
        assertEquals("Erreurs de validation détectées", result.get("message"));
        assertEquals(1, ((List<?>) result.get("errors")).size());
        assertEquals("Aucun zombie trouvé dans la base de données", ((List<?>) result.get("errors")).get(0));
        assertEquals(0, result.get("total_zombies"));
        verify(zombieService).getAllZombies();
    }
} 