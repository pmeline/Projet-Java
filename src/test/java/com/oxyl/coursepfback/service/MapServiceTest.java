package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.MapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MapServiceTest {

    @Mock
    private MapRepository mapRepository;

    @Mock
    private ZombieService zombieService;

    @InjectMocks
    private MapService mapService;

    private MapModel mapModel;

    @BeforeEach
    void setUp() {
        mapModel = new MapModel();
        mapModel.setId_map(1L);
        mapModel.setLigne(5);
        mapModel.setColonne(9);
        mapModel.setChemin_image("map1.png");
    }

    @Test
    void createMap_shouldSucceed() {
        doNothing().when(mapRepository).createMap(any(MapModel.class));
        assertDoesNotThrow(() -> mapService.createMap(mapModel));
        verify(mapRepository).createMap(mapModel);
    }

    @Test
    void createMap_withNullMap_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> mapService.createMap(null));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void createMap_withInvalidLigne_shouldThrowValidationException() {
        mapModel.setLigne(-1);
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void createMap_withInvalidColonne_shouldThrowValidationException() {
        mapModel.setColonne(-1);
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void createMap_withNullCheminImage_shouldThrowValidationException() {
        mapModel.setChemin_image(null);
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void createMap_withEmptyCheminImage_shouldThrowValidationException() {
        mapModel.setChemin_image("");
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void createMap_withInvalidDimensions_shouldThrowValidationException() {
        mapModel.setLigne(0);
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());

        mapModel.setLigne(5);
        mapModel.setColonne(0);
        assertThrows(ValidationException.class, () -> mapService.createMap(mapModel));
        verify(mapRepository, never()).createMap(any());
    }

    @Test
    void getMap_shouldReturnMap() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        
        MapModel result = mapService.getMap(1L);
        assertNotNull(result);
        assertEquals(mapModel, result);
        verify(mapRepository).getMap(1L);
    }

    @Test
    void getMap_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> mapService.getMap(null));
        verify(mapRepository, never()).getMap(any());
    }

    @Test
    void getMap_withNonExistentId_shouldThrowNotFoundException() {
        when(mapRepository.getMap(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> mapService.getMap(1L));
        verify(mapRepository).getMap(1L);
    }

    @Test
    void getAllMaps_shouldReturnList() {
        List<MapModel> maps = Arrays.asList(mapModel);
        when(mapRepository.getAllMaps()).thenReturn(maps);
        
        List<MapModel> result = mapService.getAllMaps();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mapModel, result.get(0));
        verify(mapRepository).getAllMaps();
    }

    @Test
    void getAllMaps_withEmptyList_shouldReturnEmptyList() {
        when(mapRepository.getAllMaps()).thenReturn(Arrays.asList());
        List<MapModel> result = mapService.getAllMaps();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mapRepository).getAllMaps();
    }

    @Test
    void updateMap_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        doNothing().when(mapRepository).updateMap(any(MapModel.class));
        assertDoesNotThrow(() -> mapService.updateMap(mapModel));
        verify(mapRepository).updateMap(mapModel);
    }

    @Test
    void updateMap_withNullMap_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> mapService.updateMap(null));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withInvalidLigne_shouldThrowValidationException() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        mapModel.setLigne(-1);
        assertThrows(ValidationException.class, () -> mapService.updateMap(mapModel));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withInvalidColonne_shouldThrowValidationException() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        mapModel.setColonne(-1);
        assertThrows(ValidationException.class, () -> mapService.updateMap(mapModel));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withNullCheminImage_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        doNothing().when(mapRepository).updateMap(any(MapModel.class));
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(10);
        update.setColonne(10);
        
        assertDoesNotThrow(() -> mapService.updateMap(update));
        verify(mapRepository).updateMap(any(MapModel.class));
    }

    @Test
    void updateMap_withEmptyCheminImage_shouldThrowValidationException() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(10);
        update.setColonne(10);
        update.setChemin_image("");
        
        assertThrows(ValidationException.class, () -> mapService.updateMap(update));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withNonExistentId_shouldThrowNotFoundException() {
        when(mapRepository.getMap(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> mapService.updateMap(mapModel));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withNullLigne_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        doNothing().when(mapRepository).updateMap(any(MapModel.class));
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setColonne(10);
        update.setChemin_image("new_map.png");
        
        assertDoesNotThrow(() -> mapService.updateMap(update));
        verify(mapRepository).updateMap(any(MapModel.class));
    }

    @Test
    void updateMap_withNullColonne_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        doNothing().when(mapRepository).updateMap(any(MapModel.class));
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(10);
        update.setChemin_image("new_map.png");
        
        assertDoesNotThrow(() -> mapService.updateMap(update));
        verify(mapRepository).updateMap(any(MapModel.class));
    }

    @Test
    void updateMap_withInvalidDimensions_shouldThrowValidationException() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(0);
        update.setColonne(9);
        update.setChemin_image("new_map.png");
        
        assertThrows(ValidationException.class, () -> mapService.updateMap(update));
        verify(mapRepository, never()).updateMap(any());

        update.setLigne(5);
        update.setColonne(0);
        assertThrows(ValidationException.class, () -> mapService.updateMap(update));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withInvalidCheminImage_shouldThrowValidationException() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(5);
        update.setColonne(9);
        update.setChemin_image("");
        
        assertThrows(ValidationException.class, () -> mapService.updateMap(update));
        verify(mapRepository, never()).updateMap(any());
    }

    @Test
    void updateMap_withAllNullValues_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        doNothing().when(mapRepository).updateMap(any(MapModel.class));
        
        MapModel update = new MapModel();
        update.setId_map(1L);
        update.setLigne(null);
        update.setColonne(null);
        update.setChemin_image(null);
        
        assertDoesNotThrow(() -> mapService.updateMap(update));
        
        ArgumentCaptor<MapModel> captor = ArgumentCaptor.forClass(MapModel.class);
        verify(mapRepository).updateMap(captor.capture());
        
        MapModel updatedMap = captor.getValue();
        assertEquals(mapModel.getLigne(), updatedMap.getLigne());
        assertEquals(mapModel.getColonne(), updatedMap.getColonne());
        assertEquals(mapModel.getChemin_image(), updatedMap.getChemin_image());
    }

    @Test
    void deleteMap_shouldSucceed() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        when(zombieService.getZombiesByMapId(1L)).thenReturn(Arrays.asList());
        
        assertDoesNotThrow(() -> mapService.deleteMap(1L));
        verify(mapRepository).deleteMap(1L);
    }

    @Test
    void deleteMap_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> mapService.deleteMap(null));
        verify(mapRepository, never()).deleteMap(any());
    }

    @Test
    void deleteMap_withNonExistentId_shouldThrowNotFoundException() {
        when(mapRepository.getMap(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> mapService.deleteMap(1L));
        verify(mapRepository, never()).deleteMap(any());
    }

    @Test
    void deleteMap_withZombies_shouldDeleteZombies() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        ZombieModel zombie = new ZombieModel();
        zombie.setId_zombie(1L);
        when(zombieService.getZombiesByMapId(1L)).thenReturn(Arrays.asList(zombie));
        doNothing().when(zombieService).deleteZombie(1L);
        doNothing().when(mapRepository).deleteMap(1L);
        
        assertDoesNotThrow(() -> mapService.deleteMap(1L));
        verify(zombieService).deleteZombie(1L);
        verify(mapRepository).deleteMap(1L);
    }

    @Test
    void deleteMap_withMultipleZombies_shouldDeleteAllZombies() {
        when(mapRepository.getMap(1L)).thenReturn(mapModel);
        ZombieModel zombie1 = new ZombieModel();
        zombie1.setId_zombie(1L);
        ZombieModel zombie2 = new ZombieModel();
        zombie2.setId_zombie(2L);
        when(zombieService.getZombiesByMapId(1L)).thenReturn(Arrays.asList(zombie1, zombie2));
        doNothing().when(zombieService).deleteZombie(anyLong());
        doNothing().when(mapRepository).deleteMap(1L);
        
        assertDoesNotThrow(() -> mapService.deleteMap(1L));
        verify(zombieService).deleteZombie(1L);
        verify(zombieService).deleteZombie(2L);
        verify(mapRepository).deleteMap(1L);
    }
} 