package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.persistance.dao.MapDAO;
import com.oxyl.coursepfback.persistance.entity.MapEntity;
import com.oxyl.coursepfback.persistance.entity.MapEntityMapper;
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
class MapRepositoryTest {

    @Mock
    private MapDAO mapDAO;

    @Mock
    private MapEntityMapper mapper;

    @InjectMocks
    private MapRepository mapRepository;

    private MapEntity mapEntity;
    private MapModel mapModel;

    @BeforeEach
    void setUp() {
        mapEntity = new MapEntity();
        mapEntity.setId_map(1L);
        mapEntity.setLigne(5);
        mapEntity.setColonne(9);
        mapEntity.setChemin_image("map1.png");

        mapModel = new MapModel();
        mapModel.setId_map(1L);
        mapModel.setLigne(5);
        mapModel.setColonne(9);
        mapModel.setChemin_image("map1.png");
    }

    @Test
    void getAllMaps_shouldReturnListOfMaps() {
        List<MapEntity> entities = Arrays.asList(mapEntity);
        List<MapModel> models = Arrays.asList(mapModel);

        when(mapDAO.getAllMaps()).thenReturn(entities);
        when(mapper.mapListEntitiesToModels(entities)).thenReturn(models);

        List<MapModel> result = mapRepository.getAllMaps();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mapModel, result.get(0));
        verify(mapDAO).getAllMaps();
        verify(mapper).mapListEntitiesToModels(entities);
    }

    @Test
    void getMap_shouldReturnMap() {
        when(mapDAO.getMap(1L)).thenReturn(mapEntity);
        when(mapper.mapEntityToModel(mapEntity)).thenReturn(mapModel);

        MapModel result = mapRepository.getMap(1L);

        assertNotNull(result);
        assertEquals(mapModel, result);
        verify(mapDAO).getMap(1L);
        verify(mapper).mapEntityToModel(mapEntity);
    }

    @Test
    void getMap_withNonExistentId_shouldThrowNotFoundException() {
        when(mapDAO.getMap(1L)).thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(NotFoundException.class, () -> mapRepository.getMap(1L));
        verify(mapDAO).getMap(1L);
        verify(mapper, never()).mapEntityToModel(any());
    }

    @Test
    void createMap_shouldSucceed() {
        when(mapper.mapModelToEntity(mapModel)).thenReturn(mapEntity);
        doNothing().when(mapDAO).createMap(mapEntity);

        assertDoesNotThrow(() -> mapRepository.createMap(mapModel));
        verify(mapper).mapModelToEntity(mapModel);
        verify(mapDAO).createMap(mapEntity);
    }

    @Test
    void updateMap_shouldSucceed() {
        when(mapDAO.getMap(1L)).thenReturn(mapEntity);
        when(mapper.mapModelToEntity(mapModel)).thenReturn(mapEntity);
        doNothing().when(mapDAO).updateMap(mapEntity);

        assertDoesNotThrow(() -> mapRepository.updateMap(mapModel));
        verify(mapDAO).getMap(1L);
        verify(mapper).mapModelToEntity(mapModel);
        verify(mapDAO).updateMap(mapEntity);
    }

    @Test
    void deleteMap_shouldSucceed() {
        doNothing().when(mapDAO).deleteMap(1L);

        assertDoesNotThrow(() -> mapRepository.deleteMap(1L));
        verify(mapDAO).deleteMap(1L);
    }
} 