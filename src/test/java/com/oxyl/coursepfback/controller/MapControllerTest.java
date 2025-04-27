package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.MapDTO;
import com.oxyl.coursepfback.dto.MapDTOMapper;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.service.MapService;
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

public class MapControllerTest {

    @Mock
    private MapService mapService;

    @Mock
    private MapDTOMapper mapper;

    private MapController mapController;
    private MapModel mapModel;
    private MapDTO mapDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapController = new MapController(mapService, mapper);

        mapModel = new MapModel();
        mapModel.setId_map(1L);
        mapModel.setLigne(10);
        mapModel.setColonne(5);
        mapModel.setChemin_image("map.png");

        mapDTO = new MapDTO();
        mapDTO.setId_map(1L);
        mapDTO.setLigne(10);
        mapDTO.setColonne(5);
        mapDTO.setChemin_image("map.png");
    }

    @Test
    void getAllMaps_shouldReturnListOfMaps() {
        List<MapModel> maps = Arrays.asList(mapModel);
        List<MapDTO> expectedDTOs = Arrays.asList(mapDTO);

        when(mapService.getAllMaps()).thenReturn(maps);
        when(mapper.mapListModelsToDTO(maps)).thenReturn(expectedDTOs);

        List<MapDTO> result = mapController.getAllMaps();

        assertEquals(expectedDTOs, result);
        verify(mapService).getAllMaps();
        verify(mapper).mapListModelsToDTO(maps);
    }

    @Test
    void getMapById_shouldReturnMap() {
        when(mapService.getMap(1L)).thenReturn(mapModel);
        when(mapper.mapModelToDTO(mapModel)).thenReturn(mapDTO);

        ResponseEntity<MapDTO> response = mapController.getMapById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mapDTO, response.getBody());
        verify(mapService).getMap(1L);
        verify(mapper).mapModelToDTO(mapModel);
    }

    @Test
    void getMapById_withNonExistentId_shouldReturnMap() {
        when(mapService.getMap(999L)).thenReturn(null);
        when(mapper.mapModelToDTO(null)).thenReturn(null);

        ResponseEntity<MapDTO> response = mapController.getMapById(999L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(mapService).getMap(999L);
        verify(mapper).mapModelToDTO(null);
    }

    @Test
    void createMap_shouldCreateAndReturnMap() {
        when(mapper.mapDTOToModel(mapDTO)).thenReturn(mapModel);
        when(mapper.mapModelToDTO(mapModel)).thenReturn(mapDTO);

        ResponseEntity<MapDTO> response = mapController.createMap(mapDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mapDTO, response.getBody());
        verify(mapService).createMap(mapModel);
        verify(mapper).mapDTOToModel(mapDTO);
        verify(mapper).mapModelToDTO(mapModel);
    }

    @Test
    void updateMap_shouldUpdateAndReturnMap() {
        when(mapper.mapDTOToModel(mapDTO)).thenReturn(mapModel);
        when(mapper.mapModelToDTO(mapModel)).thenReturn(mapDTO);

        ResponseEntity<MapDTO> response = mapController.updateMap(1L, mapDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mapDTO, response.getBody());
        verify(mapService).updateMap(mapModel);
        verify(mapper).mapDTOToModel(mapDTO);
        verify(mapper).mapModelToDTO(mapModel);
    }

    @Test
    void deleteMap_shouldDeleteMap() {
        ResponseEntity<Void> response = mapController.deleteMap(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mapService).deleteMap(1L);
    }

    @Test
    void getValidationExample_shouldReturnValidationResult() {
        List<MapModel> maps = Arrays.asList(mapModel);
        when(mapService.getAllMaps()).thenReturn(maps);
        when(mapper.mapListModelsToDTO(maps)).thenReturn(Arrays.asList(mapDTO));

        ResponseEntity<Map<String, Object>> response = mapController.getValidationExample();
        Map<String, Object> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue((Boolean) result.get("valid"));
        assertEquals("Toutes les maps sont valides", result.get("message"));
        assertTrue(((List<?>) result.get("errors")).isEmpty());
        assertEquals(1, result.get("total_maps"));
        verify(mapService).getAllMaps();
        verify(mapper).mapListModelsToDTO(maps);
    }

    @Test
    void getValidationExample_withNoMaps_shouldReturnValidationErrors() {
        when(mapService.getAllMaps()).thenReturn(Arrays.asList());

        ResponseEntity<Map<String, Object>> response = mapController.getValidationExample();
        Map<String, Object> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse((Boolean) result.get("valid"));
        assertEquals("Erreurs de validation détectées", result.get("message"));
        assertEquals(1, ((List<?>) result.get("errors")).size());
        assertEquals("Aucune map trouvée dans la base de données", ((List<?>) result.get("errors")).get(0));
        assertEquals(0, result.get("total_maps"));
        verify(mapService).getAllMaps();
    }

} 