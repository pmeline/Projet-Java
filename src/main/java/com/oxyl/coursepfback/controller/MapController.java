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

/**
 * Contrôleur gérant les requêtes HTTP liées aux maps
 */
@RestController
@RequestMapping("/maps")
public class MapController {
    
    private final MapService mapService;
    private final MapDTOMapper mapper;
    
    public MapController(MapService mapService, MapDTOMapper mapper) {
        this.mapService = mapService;
        this.mapper = mapper;
    }

    /**
     * Récupère toutes les maps
     * @return la liste de toutes les maps au format DTO
     */
    @GetMapping
    public List<MapDTO> getAllMaps() {
        List<MapModel> maps = mapService.getAllMaps();
        return mapper.mapListModelsToDTO(maps);
    }

    /**
     * Récupère une map par son identifiant
     * @param id_map l'identifiant de la map
     * @return la map correspondante au format DTO
     */
    @GetMapping("/{id_map}")
    public ResponseEntity<MapDTO> getMapById(@PathVariable("id_map") Long id_map) {
        MapModel map = mapService.getMap(id_map);
        if (map == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapModelToDTO(map), HttpStatus.OK);
    }

    /**
     * Crée une nouvelle map
     * @param mapDTO les données de la map à créer
     * @return la map créée au format DTO
     */
    @PostMapping
    public ResponseEntity<MapDTO> createMap(@RequestBody MapDTO mapDTO) {
        MapModel mapModel = mapper.mapDTOToModel(mapDTO);
        mapService.createMap(mapModel);
        return new ResponseEntity<>(mapper.mapModelToDTO(mapModel), HttpStatus.CREATED);
    }

    /**
     * Met à jour une map existante
     * @param id_map l'identifiant de la map à mettre à jour
     * @param mapDTO les nouvelles données de la map
     * @return la map mise à jour au format DTO
     */
    @PutMapping("/{id_map}")
    public ResponseEntity<MapDTO> updateMap(
            @PathVariable("id_map") Long id_map,
            @RequestBody MapDTO mapDTO) {
        MapModel mapModel = mapper.mapDTOToModel(mapDTO);
        mapModel.setId_map(id_map);
        mapService.updateMap(mapModel);
        return new ResponseEntity<>(mapper.mapModelToDTO(mapModel), HttpStatus.OK);
    }

    /**
     * Supprime une map
     * @param id_map l'identifiant de la map à supprimer
     * @return une réponse vide en cas de succès, ou un message d'erreur en cas d'échec
     */
    @DeleteMapping("/{id_map}")
    public ResponseEntity<Object> deleteMap(@PathVariable("id_map") Long id_map) {
        try {
            mapService.deleteMap(id_map);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
