package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.MapDTO;
import com.oxyl.coursepfback.dto.MapDTOMapper;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    
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
        logger.info("Récupération de toutes les maps");
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
        logger.info("Récupération de la map avec l'id: {}", id_map);
        MapModel map = mapService.getMap(id_map);
        return ResponseEntity.ok(mapper.mapModelToDTO(map));
    }

    /**
     * Crée une nouvelle map
     * @param mapDTO les données de la map à créer
     * @return la map créée au format DTO
     */
    @PostMapping
    public ResponseEntity<MapDTO> createMap(@RequestBody MapDTO mapDTO) {
        logger.info("Création d'une nouvelle map: {}", mapDTO);
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
        logger.info("Mise à jour de la map avec l'id: {}", id_map);
        MapModel mapModel = mapper.mapDTOToModel(mapDTO);
        mapModel.setId_map(id_map);
        mapService.updateMap(mapModel);
        return ResponseEntity.ok(mapper.mapModelToDTO(mapModel));
    }

    /**
     * Supprime une map
     * @param id_map l'identifiant de la map à supprimer
     * @return une réponse vide en cas de succès
     */
    @DeleteMapping("/{id_map}")
    public ResponseEntity<Void> deleteMap(@PathVariable("id_map") Long id_map) {
        logger.info("Suppression de la map avec l'id: {}", id_map);
        mapService.deleteMap(id_map);
        return ResponseEntity.noContent().build();
    }

    /**
     * Valide toutes les maps
     * @return le résultat de la validation
     */
    @GetMapping("/validation")
    public ResponseEntity<Map<String, Object>> getValidationExample() {
        logger.info("Appel de l'endpoint /maps/validation");
        List<MapDTO> maps = getAllMaps();
        logger.info("Nombre de maps trouvées : {}", maps.size());
        
        Map<String, Object> validationResult = new HashMap<>();
        List<String> errors = new ArrayList<>();
        
        if (maps.isEmpty()) {
            logger.warn("Aucune map trouvée");
            errors.add("Aucune map trouvée dans la base de données");
        } else {
            for (MapDTO map : maps) {
                if (map.getLigne() <= 0) {
                    errors.add("Le nombre de lignes doit être positif pour la map ID " + map.getId_map());
                }
                if (map.getColonne() <= 0) {
                    errors.add("Le nombre de colonnes doit être positif pour la map ID " + map.getId_map());
                }
                if (map.getChemin_image() == null || map.getChemin_image().trim().isEmpty()) {
                    errors.add("Chemin de l'image manquant pour la map ID " + map.getId_map());
                }
            }
        }

        validationResult.put("valid", errors.isEmpty());
        validationResult.put("message", errors.isEmpty() ? "Toutes les maps sont valides" : "Erreurs de validation détectées");
        validationResult.put("errors", errors);
        validationResult.put("total_maps", maps.size());
        
        return ResponseEntity.ok(validationResult);
    }
}
