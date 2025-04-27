package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.dao.ZombieDAO;
import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import com.oxyl.coursepfback.persistance.entity.ZombieEntityMapper;
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
class ZombieRepositoryTest {

    @Mock
    private ZombieDAO zombieDAO;

    @Mock
    private ZombieEntityMapper mapper;

    @InjectMocks
    private ZombieRepository zombieRepository;

    private ZombieEntity zombieEntity;
    private ZombieModel zombieModel;

    @BeforeEach
    void setUp() {
        zombieEntity = new ZombieEntity();
        zombieEntity.setId_zombie(1L);
        zombieEntity.setNom("Zombie1");
        zombieEntity.setPoint_de_vie(100);
        zombieEntity.setAttaque_par_seconde(1.0);
        zombieEntity.setDegat_attaque(10);
        zombieEntity.setVitesse_de_deplacement(1.0);
        zombieEntity.setChemin_image("zombie1.png");
        zombieEntity.setId_map(1L);

        zombieModel = new ZombieModel();
        zombieModel.setId_zombie(1L);
        zombieModel.setNom("Zombie1");
        zombieModel.setPoint_de_vie(100);
        zombieModel.setAttaque_par_seconde(1.0);
        zombieModel.setDegat_attaque(10);
        zombieModel.setVitesse_de_deplacement(1.0);
        zombieModel.setChemin_image("zombie1.png");
        zombieModel.setId_map(1L);
    }

    @Test
    void getAllZombies_shouldReturnListOfZombies() {
        List<ZombieEntity> entities = Arrays.asList(zombieEntity);
        List<ZombieModel> models = Arrays.asList(zombieModel);

        when(zombieDAO.getAllZombies()).thenReturn(entities);
        when(mapper.mapListEntityToModel(entities)).thenReturn(models);

        List<ZombieModel> result = zombieRepository.getAllZombies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieModel, result.get(0));
        verify(zombieDAO).getAllZombies();
        verify(mapper).mapListEntityToModel(entities);
    }

    @Test
    void getZombie_shouldReturnZombie() {
        when(zombieDAO.getZombie(1L)).thenReturn(zombieEntity);
        when(mapper.mapEntityToModel(zombieEntity)).thenReturn(zombieModel);

        ZombieModel result = zombieRepository.getZombie(1L);

        assertNotNull(result);
        assertEquals(zombieModel, result);
        verify(zombieDAO).getZombie(1L);
        verify(mapper).mapEntityToModel(zombieEntity);
    }

    @Test
    void getZombie_withNonExistentId_shouldThrowNotFoundException() {
        when(zombieDAO.getZombie(1L)).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(NotFoundException.class, () -> zombieRepository.getZombie(1L));
        verify(zombieDAO).getZombie(1L);
        verify(mapper, never()).mapEntityToModel(any());
    }

    @Test
    void getZombiesByMapId_shouldReturnListOfZombies() {
        List<ZombieEntity> entities = Arrays.asList(zombieEntity);
        List<ZombieModel> models = Arrays.asList(zombieModel);

        when(zombieDAO.getZombiesByMapId(1L)).thenReturn(entities);
        when(mapper.mapListEntityToModel(entities)).thenReturn(models);

        List<ZombieModel> result = zombieRepository.getZombiesByMapId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(zombieModel, result.get(0));
        verify(zombieDAO).getZombiesByMapId(1L);
        verify(mapper).mapListEntityToModel(entities);
    }

    @Test
    void createZombie_shouldSucceed() {
        when(mapper.mapModelToEntity(zombieModel)).thenReturn(zombieEntity);
        doNothing().when(zombieDAO).createZombie(zombieEntity);

        assertDoesNotThrow(() -> zombieRepository.createZombie(zombieModel));
        verify(mapper).mapModelToEntity(zombieModel);
        verify(zombieDAO).createZombie(zombieEntity);
    }

    @Test
    void updateZombie_shouldSucceed() {
        when(zombieDAO.getZombie(1L)).thenReturn(zombieEntity);
        when(mapper.mapModelToEntity(zombieModel)).thenReturn(zombieEntity);
        doNothing().when(zombieDAO).updateZombie(zombieEntity);

        assertDoesNotThrow(() -> zombieRepository.updateZombie(zombieModel));
        verify(zombieDAO).getZombie(1L);
        verify(mapper).mapModelToEntity(zombieModel);
        verify(zombieDAO).updateZombie(zombieEntity);
    }

    @Test
    void updateZombie_withNonExistentId_shouldThrowNotFoundException() {
        when(zombieDAO.getZombie(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> zombieRepository.updateZombie(zombieModel));
        verify(zombieDAO).getZombie(1L);
        verify(mapper, never()).mapModelToEntity(any());
        verify(zombieDAO, never()).updateZombie(any());
    }

    @Test
    void deleteZombie_shouldSucceed() {
        doNothing().when(zombieDAO).deleteZombie(1L);

        assertDoesNotThrow(() -> zombieRepository.deleteZombie(1L));
        verify(zombieDAO).deleteZombie(1L);
    }
} 