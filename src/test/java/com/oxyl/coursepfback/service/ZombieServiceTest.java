package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
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
class ZombieServiceTest {

    @Mock
    private ZombieRepository zombieRepository;

    @Mock
    private MapService mapService;

    @InjectMocks
    private ZombieService zombieService;

    private ZombieModel zombieModel;
    private MapModel mapModel;

    @BeforeEach
    void setUp() {
        mapModel = new MapModel();
        mapModel.setId_map(1L);
        mapModel.setLigne(5);
        mapModel.setColonne(9);
        mapModel.setChemin_image("map1.png");

        zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setNom("Zombie");
        zombieModel.setPoint_de_vie(100);
        zombieModel.setAttaque_par_seconde(1.0);
        zombieModel.setDegat_attaque(10);
        zombieModel.setVitesse_de_deplacement(1.0);
        zombieModel.setId_map(1L);
        zombieModel.setChemin_image("zombie.png");
    }

    @Test
    void createZombie_shouldSucceed() {
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).createZombie(any(ZombieModel.class));
        assertDoesNotThrow(() -> zombieService.createZombie(zombieModel));
        verify(zombieRepository).createZombie(zombieModel);
    }

    @Test
    void createZombie_withNullZombie_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> zombieService.createZombie(null));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withInvalidPointsDeVie_shouldThrowValidationException() {
        zombieModel.setPoint_de_vie(-1);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withNonExistentMap_shouldThrowValidationException() {
        when(mapService.getMap(1L)).thenThrow(new NotFoundException("Map non trouvée"));
        
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withInvalidAttaqueParSeconde_shouldThrowValidationException() {
        zombieModel.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withInvalidDegatAttaque_shouldThrowValidationException() {
        zombieModel.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withInvalidVitesseDeplacement_shouldThrowValidationException() {
        zombieModel.setVitesse_de_deplacement(-1.0);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withNullNom_shouldThrowValidationException() {
        zombieModel.setNom(null);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withEmptyNom_shouldThrowValidationException() {
        zombieModel.setNom("");
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withNullCheminImage_shouldThrowValidationException() {
        zombieModel.setChemin_image(null);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withEmptyCheminImage_shouldThrowValidationException() {
        zombieModel.setChemin_image("");
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void createZombie_withNullIdMap_shouldThrowValidationException() {
        zombieModel.setId_map(null);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void getZombie_shouldReturnZombie() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        
        ZombieModel result = zombieService.getZombie(1L);
        assertNotNull(result);
        assertEquals(zombieModel, result);
        verify(zombieRepository).getZombie(1L);
    }

    @Test
    void getZombie_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> zombieService.getZombie(null));
        verify(zombieRepository, never()).getZombie(any());
    }

    @Test
    void getZombie_withNonExistentId_shouldThrowNotFoundException() {
        when(zombieRepository.getZombie(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> zombieService.getZombie(1L));
        verify(zombieRepository).getZombie(1L);
    }

    @Test
    void getAllZombies_shouldReturnList() {
        List<ZombieModel> zombies = Arrays.asList(zombieModel);
        when(zombieRepository.getAllZombies()).thenReturn(zombies);
        
        List<ZombieModel> result = zombieService.getAllZombies();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieModel, result.get(0));
        verify(zombieRepository).getAllZombies();
    }

    @Test
    void getZombiesByMapId_shouldReturnList() {
        List<ZombieModel> zombies = Arrays.asList(zombieModel);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        when(zombieRepository.getZombiesByMapId(1L)).thenReturn(zombies);
        
        List<ZombieModel> result = zombieService.getZombiesByMapId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieModel, result.get(0));
        verify(zombieRepository).getZombiesByMapId(1L);
    }

    @Test
    void getZombiesByMapId_withNonExistentMap_shouldThrowValidationException() {
        when(mapService.getMap(1L)).thenThrow(new NotFoundException("Map non trouvée"));
        
        assertThrows(ValidationException.class, () -> zombieService.getZombiesByMapId(1L));
        verify(zombieRepository, never()).getZombiesByMapId(any());
    }

    @Test
    void updateZombie_shouldSucceed() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        assertDoesNotThrow(() -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository).updateZombie(zombieModel);
    }

    @Test
    void updateZombie_withNullZombie_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> zombieService.updateZombie(null));
        verify(zombieRepository, never()).updateZombie(any());
    }

    @Test
    void updateZombie_withNonExistentId_shouldThrowNotFoundException() {
        when(zombieRepository.getZombie(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository, never()).updateZombie(any());
    }

    @Test
    void updateZombie_withInvalidAttaqueParSeconde_shouldThrowValidationException() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        zombieModel.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository, never()).updateZombie(any());
    }

    @Test
    void updateZombie_withInvalidDegatAttaque_shouldThrowValidationException() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        zombieModel.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository, never()).updateZombie(any());
    }

    @Test
    void updateZombie_withInvalidVitesseDeplacement_shouldThrowValidationException() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        zombieModel.setVitesse_de_deplacement(-1.0);
        assertThrows(ValidationException.class, () -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository, never()).updateZombie(any());
    }

    @Test
    void updateZombie_withNullCheminImage_shouldSucceed() {
        ZombieModel zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setChemin_image(null);
        
        ZombieModel existingZombie = new ZombieModel();
        existingZombie.setId_zombie(1L);
        existingZombie.setNom("Old Name");
        existingZombie.setPoint_de_vie(100);
        existingZombie.setAttaque_par_seconde(1.0);
        existingZombie.setDegat_attaque(10);
        existingZombie.setVitesse_de_deplacement(1.0);
        existingZombie.setId_map(1L);
        existingZombie.setChemin_image("old_image.png");
        
        when(zombieRepository.getZombie(1L)).thenReturn(existingZombie);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.updateZombie(zombieModel));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals("Old Name", updatedZombie.getNom());
        assertEquals(100, updatedZombie.getPoint_de_vie());
        assertEquals(1.0, updatedZombie.getAttaque_par_seconde());
        assertEquals(10, updatedZombie.getDegat_attaque());
        assertEquals(1.0, updatedZombie.getVitesse_de_deplacement());
        assertEquals("old_image.png", updatedZombie.getChemin_image());
        assertEquals(1L, updatedZombie.getId_map());
    }

    @Test
    void updateZombie_withNullNom_shouldSucceed() {
        ZombieModel zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setNom(null);
        
        ZombieModel existingZombie = new ZombieModel();
        existingZombie.setId_zombie(1L);
        existingZombie.setNom("Old Name");
        existingZombie.setPoint_de_vie(100);
        existingZombie.setAttaque_par_seconde(1.0);
        existingZombie.setDegat_attaque(10);
        existingZombie.setVitesse_de_deplacement(1.0);
        existingZombie.setId_map(1L);
        existingZombie.setChemin_image("old_image.png");
        
        when(zombieRepository.getZombie(1L)).thenReturn(existingZombie);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.updateZombie(zombieModel));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals("Old Name", updatedZombie.getNom());
        assertEquals(100, updatedZombie.getPoint_de_vie());
        assertEquals(1.0, updatedZombie.getAttaque_par_seconde());
        assertEquals(10, updatedZombie.getDegat_attaque());
        assertEquals(1.0, updatedZombie.getVitesse_de_deplacement());
        assertEquals("old_image.png", updatedZombie.getChemin_image());
        assertEquals(1L, updatedZombie.getId_map());
    }

    @Test
    void updateZombie_withNullIdMap_shouldSucceed() {
        ZombieModel zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setId_map(null);
        
        ZombieModel existingZombie = new ZombieModel();
        existingZombie.setId_zombie(1L);
        existingZombie.setNom("Old Name");
        existingZombie.setPoint_de_vie(100);
        existingZombie.setAttaque_par_seconde(1.0);
        existingZombie.setDegat_attaque(10);
        existingZombie.setVitesse_de_deplacement(1.0);
        existingZombie.setId_map(1L);
        existingZombie.setChemin_image("old_image.png");
        
        when(zombieRepository.getZombie(1L)).thenReturn(existingZombie);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.updateZombie(zombieModel));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals("Old Name", updatedZombie.getNom());
        assertEquals(100, updatedZombie.getPoint_de_vie());
        assertEquals(1.0, updatedZombie.getAttaque_par_seconde());
        assertEquals(10, updatedZombie.getDegat_attaque());
        assertEquals(1.0, updatedZombie.getVitesse_de_deplacement());
        assertEquals("old_image.png", updatedZombie.getChemin_image());
        assertEquals(1L, updatedZombie.getId_map());
    }

    @Test
    void deleteZombie_shouldSucceed() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        
        assertDoesNotThrow(() -> zombieService.deleteZombie(1L));
        verify(zombieRepository).deleteZombie(1L);
    }

    @Test
    void deleteZombie_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> zombieService.deleteZombie(null));
        verify(zombieRepository, never()).deleteZombie(any());
    }

    @Test
    void deleteZombie_withNonExistentId_shouldThrowNotFoundException() {
        when(zombieRepository.getZombie(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> zombieService.deleteZombie(1L));
        verify(zombieRepository, never()).deleteZombie(any());
    }

    @Test
    void deleteZombie_withZombieInMap_shouldDeleteZombie() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        doNothing().when(zombieRepository).deleteZombie(1L);
        
        assertDoesNotThrow(() -> zombieService.deleteZombie(1L));
        verify(zombieRepository).deleteZombie(1L);
    }

    @Test
    void getZombie_withValidId_shouldReturnZombie() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        
        ZombieModel result = zombieService.getZombie(1L);
        assertNotNull(result);
        assertEquals(zombieModel, result);
        verify(zombieRepository).getZombie(1L);
    }

    @Test
    void getAllZombies_withEmptyList_shouldReturnEmptyList() {
        when(zombieRepository.getAllZombies()).thenReturn(Arrays.asList());
        
        List<ZombieModel> result = zombieService.getAllZombies();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(zombieRepository).getAllZombies();
    }

    @Test
    void getZombiesByMapId_withValidMap_shouldReturnZombies() {
        when(mapService.getMap(1L)).thenReturn(mapModel);
        when(zombieRepository.getZombiesByMapId(1L)).thenReturn(Arrays.asList(zombieModel));
        
        List<ZombieModel> result = zombieService.getZombiesByMapId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieModel, result.get(0));
        verify(zombieRepository).getZombiesByMapId(1L);
    }

    @Test
    void getZombiesByMapId_withEmptyList_shouldReturnEmptyList() {
        when(mapService.getMap(1L)).thenReturn(mapModel);
        when(zombieRepository.getZombiesByMapId(1L)).thenReturn(Arrays.asList());
        
        List<ZombieModel> result = zombieService.getZombiesByMapId(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(zombieRepository).getZombiesByMapId(1L);
    }

    @Test
    void updateZombie_withNullValues_shouldKeepExistingValues() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        ZombieModel update = new ZombieModel();
        update.setId_zombie(1L);
        
        assertDoesNotThrow(() -> zombieService.updateZombie(update));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals(zombieModel.getNom(), updatedZombie.getNom());
        assertEquals(zombieModel.getPoint_de_vie(), updatedZombie.getPoint_de_vie());
        assertEquals(zombieModel.getAttaque_par_seconde(), updatedZombie.getAttaque_par_seconde());
        assertEquals(zombieModel.getDegat_attaque(), updatedZombie.getDegat_attaque());
        assertEquals(zombieModel.getVitesse_de_deplacement(), updatedZombie.getVitesse_de_deplacement());
        assertEquals(zombieModel.getChemin_image(), updatedZombie.getChemin_image());
        assertEquals(zombieModel.getId_map(), updatedZombie.getId_map());
    }

    @Test
    void updateZombie_withPartialUpdate_shouldSucceed() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        ZombieModel update = new ZombieModel();
        update.setId_zombie(1L);
        update.setNom("Nouveau nom");
        update.setPoint_de_vie(200);
        
        assertDoesNotThrow(() -> zombieService.updateZombie(update));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals("Nouveau nom", updatedZombie.getNom());
        assertEquals(200, updatedZombie.getPoint_de_vie());
        assertEquals(zombieModel.getAttaque_par_seconde(), updatedZombie.getAttaque_par_seconde());
        assertEquals(zombieModel.getDegat_attaque(), updatedZombie.getDegat_attaque());
        assertEquals(zombieModel.getVitesse_de_deplacement(), updatedZombie.getVitesse_de_deplacement());
        assertEquals(zombieModel.getChemin_image(), updatedZombie.getChemin_image());
        assertEquals(zombieModel.getId_map(), updatedZombie.getId_map());
    }

    @Test
    void createZombie_withMaxPointsDeVie_shouldSucceed() {
        zombieModel.setPoint_de_vie(1000);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).createZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.createZombie(zombieModel));
        verify(zombieRepository).createZombie(zombieModel);
    }

    @Test
    void createZombie_withMaxAttaqueParSeconde_shouldSucceed() {
        zombieModel.setAttaque_par_seconde(10.0);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).createZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.createZombie(zombieModel));
        verify(zombieRepository).createZombie(zombieModel);
    }

    @Test
    void createZombie_withMaxDegatAttaque_shouldSucceed() {
        zombieModel.setDegat_attaque(100);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).createZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.createZombie(zombieModel));
        verify(zombieRepository).createZombie(zombieModel);
    }

    @Test
    void createZombie_withMaxVitesseDeplacement_shouldSucceed() {
        zombieModel.setVitesse_de_deplacement(5.0);
        when(mapService.getMap(1L)).thenReturn(mapModel);
        doNothing().when(zombieRepository).createZombie(any(ZombieModel.class));
        
        assertDoesNotThrow(() -> zombieService.createZombie(zombieModel));
        verify(zombieRepository).createZombie(zombieModel);
    }

    @Test
    void updateZombie_withPartialUpdate_shouldUpdateOnlySpecifiedFields() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        doNothing().when(zombieRepository).updateZombie(any(ZombieModel.class));
        
        ZombieModel update = new ZombieModel();
        update.setId_zombie(1L);
        update.setNom("Nouveau nom");
        update.setPoint_de_vie(200);
        
        assertDoesNotThrow(() -> zombieService.updateZombie(update));
        
        ArgumentCaptor<ZombieModel> captor = ArgumentCaptor.forClass(ZombieModel.class);
        verify(zombieRepository).updateZombie(captor.capture());
        
        ZombieModel updatedZombie = captor.getValue();
        assertEquals("Nouveau nom", updatedZombie.getNom());
        assertEquals(200, updatedZombie.getPoint_de_vie());
        assertEquals(zombieModel.getAttaque_par_seconde(), updatedZombie.getAttaque_par_seconde());
        assertEquals(zombieModel.getDegat_attaque(), updatedZombie.getDegat_attaque());
        assertEquals(zombieModel.getVitesse_de_deplacement(), updatedZombie.getVitesse_de_deplacement());
        assertEquals(zombieModel.getChemin_image(), updatedZombie.getChemin_image());
        assertEquals(zombieModel.getId_map(), updatedZombie.getId_map());
    }

    @Test
    void createZombie_withInvalidMapId_shouldThrowValidationException() {
        zombieModel.setId_map(null);
        assertThrows(ValidationException.class, () -> zombieService.createZombie(zombieModel));
        verify(zombieRepository, never()).createZombie(any());
    }

    @Test
    void updateZombie_withNonExistentMap_shouldThrowValidationException() {
        when(zombieRepository.getZombie(1L)).thenReturn(zombieModel);
        when(mapService.getMap(1L)).thenThrow(new NotFoundException("Map non trouvée"));
        
        assertThrows(ValidationException.class, () -> zombieService.updateZombie(zombieModel));
        verify(zombieRepository, never()).updateZombie(any());
    }
} 