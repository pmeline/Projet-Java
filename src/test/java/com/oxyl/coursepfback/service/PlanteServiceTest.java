package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.repository.PlanteRepository;
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
class PlanteServiceTest {

    @Mock
    private PlanteRepository planteRepository;

    @InjectMocks
    private PlanteService planteService;

    private PlanteModel planteModel;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    void createPlante_shouldSucceed() {
        doNothing().when(planteRepository).createPlante(any(PlanteModel.class));
        assertDoesNotThrow(() -> planteService.createPlante(planteModel));
        verify(planteRepository).createPlante(planteModel);
    }

    @Test
    void createPlante_withNullPlante_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> planteService.createPlante(null));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withInvalidPointsDeVie_shouldThrowValidationException() {
        planteModel.setPoint_de_vie(-1);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withInvalidAttaqueParSeconde_shouldThrowValidationException() {
        planteModel.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withZeroDegatAttaque_shouldSucceed() {
        planteModel.setDegat_attaque(0);
        doNothing().when(planteRepository).createPlante(any(PlanteModel.class));
        assertDoesNotThrow(() -> planteService.createPlante(planteModel));
        verify(planteRepository).createPlante(planteModel);
    }

    @Test
    void createPlante_withInvalidDegatAttaque_shouldThrowValidationException() {
        planteModel.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withInvalidSoleilParSeconde_shouldThrowValidationException() {
        planteModel.setSoleil_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withInvalidCout_shouldThrowValidationException() {
        planteModel.setCout(-50);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withNullNom_shouldThrowValidationException() {
        planteModel.setNom(null);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withEmptyNom_shouldThrowValidationException() {
        planteModel.setNom("");
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withNullEffet_shouldThrowValidationException() {
        planteModel.setEffet(null);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withEmptyEffet_shouldThrowValidationException() {
        planteModel.setEffet("");
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withNullCheminImage_shouldThrowValidationException() {
        planteModel.setChemin_image(null);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withEmptyCheminImage_shouldThrowValidationException() {
        planteModel.setChemin_image("");
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void createPlante_withNegativeValues_shouldThrowValidationException() {
        planteModel.setPoint_de_vie(-1);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());

        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());

        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());

        planteModel.setDegat_attaque(10);
        planteModel.setCout(-50);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());

        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.createPlante(planteModel));
        verify(planteRepository, never()).createPlante(any());
    }

    @Test
    void getPlante_shouldReturnPlante() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        
        PlanteModel result = planteService.getPlante(1L);
        assertNotNull(result);
        assertEquals(planteModel, result);
        verify(planteRepository).getPlante(1L);
    }

    @Test
    void getPlante_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> planteService.getPlante(null));
        verify(planteRepository, never()).getPlante(any());
    }

    @Test
    void getPlante_withNonExistentId_shouldThrowNotFoundException() {
        when(planteRepository.getPlante(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> planteService.getPlante(1L));
        verify(planteRepository).getPlante(1L);
    }

    @Test
    void getAllPlantes_shouldReturnList() {
        List<PlanteModel> plantes = Arrays.asList(planteModel);
        when(planteRepository.getAllPlantes()).thenReturn(plantes);
        
        List<PlanteModel> result = planteService.getAllPlantes();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(planteModel, result.get(0));
        verify(planteRepository).getAllPlantes();
    }

    @Test
    void getAllPlantes_withEmptyList_shouldReturnEmptyList() {
        when(planteRepository.getAllPlantes()).thenReturn(Arrays.asList());
        List<PlanteModel> result = planteService.getAllPlantes();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(planteRepository).getAllPlantes();
    }

    @Test
    void updatePlante_shouldSucceed() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        assertDoesNotThrow(() -> planteService.updatePlante(planteModel));
        verify(planteRepository).updatePlante(planteModel);
    }

    @Test
    void updatePlante_withNullPlante_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> planteService.updatePlante(null));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withNonExistentId_shouldThrowNotFoundException() {
        when(planteRepository.getPlante(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withInvalidAttaqueParSeconde_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        planteModel.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withZeroDegatAttaque_shouldSucceed() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        PlanteModel update = new PlanteModel();
        update.setId_plante(1L);
        update.setDegat_attaque(0);
        
        assertDoesNotThrow(() -> planteService.updatePlante(update));
        verify(planteRepository).updatePlante(any(PlanteModel.class));
    }

    @Test
    void updatePlante_withInvalidDegatAttaque_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        planteModel.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withInvalidSoleilParSeconde_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        planteModel.setSoleil_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withInvalidCout_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        planteModel.setCout(-50);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withNullNom_shouldSucceed() {
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom(null);
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet("Test");
        planteModel.setChemin_image("test.jpg");
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Test");
        existingPlante.setChemin_image("test.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        assertDoesNotThrow(() -> planteService.updatePlante(planteModel));
        
        ArgumentCaptor<PlanteModel> captor = ArgumentCaptor.forClass(PlanteModel.class);
        verify(planteRepository).updatePlante(captor.capture());
        
        PlanteModel updatedPlante = captor.getValue();
        assertEquals("Existing", updatedPlante.getNom());
        assertEquals(100, updatedPlante.getPoint_de_vie());
        assertEquals(1.0, updatedPlante.getAttaque_par_seconde());
        assertEquals(20, updatedPlante.getDegat_attaque());
        assertEquals(50, updatedPlante.getCout());
        assertEquals(0.0, updatedPlante.getSoleil_par_seconde());
        assertEquals("Test", updatedPlante.getEffet());
        assertEquals("test.jpg", updatedPlante.getChemin_image());
    }

    @Test
    void updatePlante_withEmptyNom_shouldThrowValidationException() {
        // Given
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet("Test");
        planteModel.setChemin_image("test.jpg");
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Test");
        existingPlante.setChemin_image("test.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        
        // When/Then
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any(PlanteModel.class));
    }

    @Test
    void updatePlante_withNullEffet_shouldSucceed() {
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("Test");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet(null);
        planteModel.setChemin_image("test.jpg");
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Existing Effet");
        existingPlante.setChemin_image("test.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        assertDoesNotThrow(() -> planteService.updatePlante(planteModel));
        
        ArgumentCaptor<PlanteModel> captor = ArgumentCaptor.forClass(PlanteModel.class);
        verify(planteRepository).updatePlante(captor.capture());
        
        PlanteModel updatedPlante = captor.getValue();
        assertEquals("Test", updatedPlante.getNom());
        assertEquals(100, updatedPlante.getPoint_de_vie());
        assertEquals(1.0, updatedPlante.getAttaque_par_seconde());
        assertEquals(20, updatedPlante.getDegat_attaque());
        assertEquals(50, updatedPlante.getCout());
        assertEquals(0.0, updatedPlante.getSoleil_par_seconde());
        assertEquals("Existing Effet", updatedPlante.getEffet());
        assertEquals("test.jpg", updatedPlante.getChemin_image());
    }

    @Test
    void updatePlante_withEmptyEffet_shouldThrowValidationException() {
        // Given
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("Test");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet("");
        planteModel.setChemin_image("test.jpg");
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Test");
        existingPlante.setChemin_image("test.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        
        // When/Then
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any(PlanteModel.class));
    }

    @Test
    void updatePlante_withNullCheminImage_shouldSucceed() {
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("Test");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet("Test");
        planteModel.setChemin_image(null);
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Test");
        existingPlante.setChemin_image("existing.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        assertDoesNotThrow(() -> planteService.updatePlante(planteModel));
        
        ArgumentCaptor<PlanteModel> captor = ArgumentCaptor.forClass(PlanteModel.class);
        verify(planteRepository).updatePlante(captor.capture());
        
        PlanteModel updatedPlante = captor.getValue();
        assertEquals("Test", updatedPlante.getNom());
        assertEquals(100, updatedPlante.getPoint_de_vie());
        assertEquals(1.0, updatedPlante.getAttaque_par_seconde());
        assertEquals(20, updatedPlante.getDegat_attaque());
        assertEquals(50, updatedPlante.getCout());
        assertEquals(0.0, updatedPlante.getSoleil_par_seconde());
        assertEquals("Test", updatedPlante.getEffet());
        assertEquals("existing.jpg", updatedPlante.getChemin_image());
    }

    @Test
    void updatePlante_withEmptyCheminImage_shouldThrowValidationException() {
        // Given
        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(1L);
        planteModel.setNom("Test");
        planteModel.setPoint_de_vie(100);
        planteModel.setAttaque_par_seconde(1.0);
        planteModel.setDegat_attaque(20);
        planteModel.setCout(50);
        planteModel.setSoleil_par_seconde(0.0);
        planteModel.setEffet("Test");
        planteModel.setChemin_image("");
        
        PlanteModel existingPlante = new PlanteModel();
        existingPlante.setId_plante(1L);
        existingPlante.setNom("Existing");
        existingPlante.setPoint_de_vie(100);
        existingPlante.setAttaque_par_seconde(1.0);
        existingPlante.setDegat_attaque(20);
        existingPlante.setCout(50);
        existingPlante.setSoleil_par_seconde(0.0);
        existingPlante.setEffet("Test");
        existingPlante.setChemin_image("test.jpg");
        
        when(planteRepository.getPlante(1L)).thenReturn(existingPlante);
        
        // When/Then
        assertThrows(ValidationException.class, () -> planteService.updatePlante(planteModel));
        verify(planteRepository, never()).updatePlante(any(PlanteModel.class));
    }

    @Test
    void updatePlante_withNullValues_shouldSucceed() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        PlanteModel update = new PlanteModel();
        update.setId_plante(1L);
        update.setNom(null);
        update.setPoint_de_vie(null);
        update.setAttaque_par_seconde(null);
        update.setDegat_attaque(null);
        update.setCout(null);
        update.setSoleil_par_seconde(null);
        update.setEffet(null);
        update.setChemin_image(null);
        
        assertDoesNotThrow(() -> planteService.updatePlante(update));
        
        ArgumentCaptor<PlanteModel> captor = ArgumentCaptor.forClass(PlanteModel.class);
        verify(planteRepository).updatePlante(captor.capture());
        
        PlanteModel updatedPlante = captor.getValue();
        assertEquals(planteModel.getNom(), updatedPlante.getNom());
        assertEquals(planteModel.getPoint_de_vie(), updatedPlante.getPoint_de_vie());
        assertEquals(planteModel.getAttaque_par_seconde(), updatedPlante.getAttaque_par_seconde());
        assertEquals(planteModel.getDegat_attaque(), updatedPlante.getDegat_attaque());
        assertEquals(planteModel.getCout(), updatedPlante.getCout());
        assertEquals(planteModel.getSoleil_par_seconde(), updatedPlante.getSoleil_par_seconde());
        assertEquals(planteModel.getEffet(), updatedPlante.getEffet());
        assertEquals(planteModel.getChemin_image(), updatedPlante.getChemin_image());
    }

    @Test
    void updatePlante_withPartialUpdate_shouldSucceed() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        doNothing().when(planteRepository).updatePlante(any(PlanteModel.class));
        
        PlanteModel update = new PlanteModel();
        update.setId_plante(1L);
        update.setNom("Nouveau nom");
        
        assertDoesNotThrow(() -> planteService.updatePlante(update));
        
        ArgumentCaptor<PlanteModel> captor = ArgumentCaptor.forClass(PlanteModel.class);
        verify(planteRepository).updatePlante(captor.capture());
        
        PlanteModel updatedPlante = captor.getValue();
        assertEquals("Nouveau nom", updatedPlante.getNom());
        assertEquals(planteModel.getPoint_de_vie(), updatedPlante.getPoint_de_vie());
        assertEquals(planteModel.getAttaque_par_seconde(), updatedPlante.getAttaque_par_seconde());
        assertEquals(planteModel.getDegat_attaque(), updatedPlante.getDegat_attaque());
        assertEquals(planteModel.getCout(), updatedPlante.getCout());
        assertEquals(planteModel.getSoleil_par_seconde(), updatedPlante.getSoleil_par_seconde());
        assertEquals(planteModel.getEffet(), updatedPlante.getEffet());
        assertEquals(planteModel.getChemin_image(), updatedPlante.getChemin_image());
    }

    @Test
    void updatePlante_withNegativeValues_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        
        PlanteModel update = new PlanteModel();
        update.setId_plante(1L);
        update.setPoint_de_vie(-1);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setPoint_de_vie(100);
        update.setAttaque_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setAttaque_par_seconde(1.0);
        update.setDegat_attaque(-10);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setDegat_attaque(10);
        update.setCout(-50);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setCout(50);
        update.setSoleil_par_seconde(-1.0);
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void updatePlante_withEmptyValues_shouldThrowValidationException() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        
        PlanteModel update = new PlanteModel();
        update.setId_plante(1L);
        update.setNom("");
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setNom("Test Plante");
        update.setEffet("");
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());

        update.setEffet("Test Effet");
        update.setChemin_image("");
        assertThrows(ValidationException.class, () -> planteService.updatePlante(update));
        verify(planteRepository, never()).updatePlante(any());
    }

    @Test
    void deletePlante_shouldSucceed() {
        when(planteRepository.getPlante(1L)).thenReturn(planteModel);
        
        assertDoesNotThrow(() -> planteService.deletePlante(1L));
        verify(planteRepository).deletePlante(1L);
    }

    @Test
    void deletePlante_withNullId_shouldThrowValidationException() {
        assertThrows(ValidationException.class, () -> planteService.deletePlante(null));
        verify(planteRepository, never()).deletePlante(any());
    }

    @Test
    void deletePlante_withNonExistentId_shouldThrowNotFoundException() {
        when(planteRepository.getPlante(1L)).thenReturn(null);
        
        assertThrows(NotFoundException.class, () -> planteService.deletePlante(1L));
        verify(planteRepository, never()).deletePlante(any());
    }
} 