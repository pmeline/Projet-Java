package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.dao.PlanteDAO;
import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
import com.oxyl.coursepfback.persistance.entity.PlanteEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanteRepositoryTest {

    @Mock
    private PlanteDAO planteDAO;

    @Mock
    private PlanteEntityMapper mapper;

    @InjectMocks
    private PlanteRepository planteRepository;

    private PlanteEntity planteEntity;
    private PlanteModel planteModel;

    @BeforeEach
    void setUp() {
        planteEntity = new PlanteEntity();
        planteEntity.setId_plante(1L);
        planteEntity.setNom("Tournesol");
        planteEntity.setPoint_de_vie(100);
        planteEntity.setAttaque_par_seconde(1.0);
        planteEntity.setDegat_attaque(10);
        planteEntity.setCout(50);
        planteEntity.setSoleil_par_seconde(25.0);
        planteEntity.setEffet("Génère du soleil");
        planteEntity.setChemin_image("tournesol.png");

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
    void getAllPlantes_shouldReturnListOfPlantes() {
        List<PlanteEntity> entities = Arrays.asList(planteEntity);
        List<PlanteModel> models = Arrays.asList(planteModel);

        when(planteDAO.getAllPlantes()).thenReturn(entities);
        when(mapper.mapEntitiesToModels(entities)).thenReturn(models);

        List<PlanteModel> result = planteRepository.getAllPlantes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(planteModel, result.get(0));
        verify(planteDAO).getAllPlantes();
        verify(mapper).mapEntitiesToModels(entities);
    }

    @Test
    void getPlante_shouldReturnPlante() {
        when(planteDAO.getPlante(1L)).thenReturn(planteEntity);
        when(mapper.mapEntityToModel(planteEntity)).thenReturn(planteModel);

        PlanteModel result = planteRepository.getPlante(1L);

        assertNotNull(result);
        assertEquals(planteModel, result);
        verify(planteDAO).getPlante(1L);
        verify(mapper).mapEntityToModel(planteEntity);
    }

    @Test
    void getPlante_withNonExistentId_shouldThrowNotFoundException() {
        when(planteDAO.getPlante(1L)).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(NotFoundException.class, () -> planteRepository.getPlante(1L));
        verify(planteDAO).getPlante(1L);
        verify(mapper, never()).mapEntityToModel(any());
    }

    @Test
    void createPlante_shouldSucceed() {
        when(mapper.mapModelToEntity(planteModel)).thenReturn(planteEntity);
        doNothing().when(planteDAO).createPlante(planteEntity);

        assertDoesNotThrow(() -> planteRepository.createPlante(planteModel));
        verify(mapper).mapModelToEntity(planteModel);
        verify(planteDAO).createPlante(planteEntity);
    }

    @Test
    void updatePlante_shouldSucceed() {
        when(planteDAO.getPlante(1L)).thenReturn(planteEntity);
        when(mapper.mapModelToEntity(planteModel)).thenReturn(planteEntity);
        doNothing().when(planteDAO).updatePlante(planteEntity);

        assertDoesNotThrow(() -> planteRepository.updatePlante(planteModel));
        verify(planteDAO).getPlante(1L);
        verify(mapper).mapModelToEntity(planteModel);
        verify(planteDAO).updatePlante(planteEntity);
    }

    @Test
    void updatePlante_withNonExistentId_shouldThrowNotFoundException() {
        when(planteDAO.getPlante(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> planteRepository.updatePlante(planteModel));
        verify(planteDAO).getPlante(1L);
        verify(mapper, never()).mapModelToEntity(any());
        verify(planteDAO, never()).updatePlante(any());
    }

    @Test
    void deletePlante_shouldSucceed() {
        doNothing().when(planteDAO).deletePlante(1L);

        assertDoesNotThrow(() -> planteRepository.deletePlante(1L));
        verify(planteDAO).deletePlante(1L);
    }
} 