package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.PlanteDTO;
import com.oxyl.coursepfback.dto.PlanteDTOMapper;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.service.PlanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Contrôleur gérant les requêtes HTTP liées aux plantes
 */
@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private static final Logger logger = LoggerFactory.getLogger(PlanteController.class);
    private final PlanteService planteService;
    private final PlanteDTOMapper mapper;

    public PlanteController(PlanteService planteService, PlanteDTOMapper mapper) {
        logger.info("Initialisation du PlanteController");
        this.planteService = planteService;
        this.mapper = mapper;
    }

    /**
     * Récupère toutes les plantes
     * @return la liste de toutes les plantes au format DTO
     */
    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        List<PlanteModel> plantes = planteService.getAllPlantes();
        return mapper.mapListModelsToDTO(plantes);
    }

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante
     * @return la plante correspondante au format DTO
     */
    @GetMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable("id_plante") Long id_plante) {
        PlanteModel plante = planteService.getPlante(id_plante);
        if (plante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    /**
     * Crée une nouvelle plante
     * @param planteDTO les données de la plante à créer
     * @return la plante créée au format DTO
     */
    @PostMapping
    public ResponseEntity<PlanteDTO> createPlante(@RequestBody PlanteDTO planteDTO) {
        PlanteModel plante = mapper.mapDTOToModel(planteDTO);
        planteService.createPlante(plante);
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.CREATED);
    }

    /**
     * Met à jour une plante existante
     * @param id_plante l'identifiant de la plante à mettre à jour
     * @param planteDTO les nouvelles données de la plante
     * @return la plante mise à jour au format DTO
     */
    @PutMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> updatePlante(
            @PathVariable("id_plante") Long id_plante,
            @RequestBody PlanteDTO planteDTO) {
        PlanteModel plante = mapper.mapDTOToModel(planteDTO);
        plante.setId_plante(id_plante);
        planteService.updatePlante(plante);
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    /**
     * Supprime une plante
     * @param id_plante l'identifiant de la plante à supprimer
     * @return une réponse vide en cas de succès, ou un message d'erreur en cas d'échec
     */
    @DeleteMapping("/{id_plante}")
    public ResponseEntity<Object> deletePlante(@PathVariable("id_plante") Long id_plante) {
        try {
            planteService.deletePlante(id_plante);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/validation")
    public ResponseEntity<Map<String, Object>> getValidationExample() {
        logger.info("Appel de l'endpoint /plantes/validation");
        List<PlanteDTO> plantes = getAllPlantes();
        logger.info("Nombre de plantes trouvées : {}", plantes.size());
        
        Map<String, Object> validationResult = new HashMap<>();
        List<String> errors = new ArrayList<>();
        
        if (plantes.isEmpty()) {
            logger.warn("Aucune plante trouvée");
            errors.add("Aucune plante trouvée dans la base de données");
        } else {
            // Validation de chaque plante
            for (PlanteDTO plante : plantes) {
                // Validation des champs obligatoires
                if (plante.getId_plante() == null) {
                    errors.add("ID de plante manquant pour la plante " + plante.getNom());
                }
                if (plante.getNom() == null || plante.getNom().isEmpty()) {
                    errors.add("Nom de plante manquant pour la plante ID " + plante.getId_plante());
                }
                if (plante.getPoint_de_vie() == null) {
                    errors.add("Points de vie manquants pour la plante " + plante.getNom());
                }
                if (plante.getAttaque_par_seconde() == null) {
                    errors.add("Attaque par seconde manquante pour la plante " + plante.getNom());
                }
                if (plante.getDegat_attaque() == null) {
                    errors.add("Dégâts d'attaque manquants pour la plante " + plante.getNom());
                }
                if (plante.getCout() == null) {
                    errors.add("Coût manquant pour la plante " + plante.getNom());
                }
                if (plante.getSoleil_par_seconde() == null) {
                    errors.add("Soleil par seconde manquant pour la plante " + plante.getNom());
                }
                if (plante.getEffet() == null || plante.getEffet().isEmpty()) {
                    errors.add("Effet manquant pour la plante " + plante.getNom());
                }
                if (plante.getChemin_image() == null || plante.getChemin_image().isEmpty()) {
                    errors.add("Chemin de l'image manquant pour la plante " + plante.getNom());
                }

                // Validation des contraintes métier
                if (plante.getPoint_de_vie() != null && plante.getPoint_de_vie() <= 0) {
                    errors.add("Points de vie doivent être positifs pour la plante " + plante.getNom());
                }
                if (plante.getDegat_attaque() != null && plante.getDegat_attaque() < 0) {
                    errors.add("Dégâts d'attaque ne peuvent pas être négatifs pour la plante " + plante.getNom());
                }
                if (plante.getCout() != null && plante.getCout() <= 0) {
                    errors.add("Coût doit être positif pour la plante " + plante.getNom());
                }
            }
        }

        validationResult.put("valid", errors.isEmpty());
        validationResult.put("message", errors.isEmpty() ? "Toutes les plantes sont valides" : "Erreurs de validation détectées");
        validationResult.put("errors", errors);
        validationResult.put("total_plantes", plantes.size());
        
        logger.info("Résultat de la validation : {}", validationResult);
        return ResponseEntity.ok(validationResult);
    }
}
