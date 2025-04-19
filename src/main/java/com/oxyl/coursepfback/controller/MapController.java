package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.MapDTO;
import com.oxyl.coursepfback.dto.MapDTOMapper;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.service.MapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maps")
public class MapController {
    
    private final MapService mapService;
    private final MapDTOMapper mapper;
    
    public MapController(MapService mapService, MapDTOMapper mapper) {
        this.mapService = mapService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MapDTO> getAllMaps() {
        List<MapModel> maps = mapService.getAllMaps(); // <== ici, MapModel
        return mapper.mapListModelsToDTO(maps);
    }


    @GetMapping("/{id_map}")
    public ResponseEntity<MapDTO> getMapById(@PathVariable ("id_map") Long id_map) {
        MapModel map = mapService.getMap(id_map);
        if (map == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapModelToDTO(map), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMap( @RequestBody MapDTO mapDTO) {
        MapModel mapModel = mapper.mapDTOToModel(mapDTO);
        mapService.createMap(mapModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id_map}")
    public ResponseEntity<Void> updateMap(@PathVariable ("id_map") Long id_map, @RequestBody MapDTO mapDTO) {
        MapModel mapModel = mapper.mapDTOToModel(mapDTO);
        mapService.updateMap(mapModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_map}")
    public ResponseEntity<Object> deleteMap(@PathVariable ("id_map") Long id_map) {
        try {
            mapService.deleteMap(id_map);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
