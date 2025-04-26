package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.ZombieDTO;
import com.oxyl.coursepfback.dto.ZombieDTOMapper;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.service.ZombieService;
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
 * Contrôleur gérant les requêtes HTTP liées aux zombies
 */
@RestController
@RequestMapping("/zombies")
public class ZombieController {

    private final ZombieService zombieService;
    private final ZombieDTOMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(ZombieController.class);

    public ZombieController(ZombieService zombieService, ZombieDTOMapper mapper) {
        this.zombieService = zombieService;
        this.mapper = mapper;
    }

    /**
     * Récupère tous les zombies
     * @return la liste de tous les zombies au format DTO
     */
    @GetMapping
    public List<ZombieDTO> getAllZombies() {
        List<ZombieModel> zombies = zombieService.getAllZombies();
        return mapper.mapListModelToDTO(zombies);
    }

    /**
     * Récupère un zombie par son identifiant
     * @param id_zombie l'identifiant du zombie
     * @return le zombie correspondant au format DTO
     */
    @GetMapping("/{id_zombie}")
    public ResponseEntity<ZombieDTO> getZombieById(@PathVariable("id_zombie") Long id_zombie) {
        ZombieModel zombie = zombieService.getZombie(id_zombie);
        if (zombie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapModelToDTO(zombie), HttpStatus.OK);
    }

    /**
     * Crée un nouveau zombie
     * @param zombieDTO les données du zombie à créer
     * @return le zombie créé au format DTO
     */
    @PostMapping
    public ResponseEntity<ZombieDTO> createZombie(@RequestBody ZombieDTO zombieDTO) {
        ZombieModel zombie = mapper.mapDTOToModel(zombieDTO);
        zombieService.createZombie(zombie);
        return new ResponseEntity<>(mapper.mapModelToDTO(zombie), HttpStatus.CREATED);
    }

    /**
     * Met à jour un zombie existant
     * @param id_zombie l'identifiant du zombie à mettre à jour
     * @param zombieDTO les nouvelles données du zombie
     * @return le zombie mis à jour au format DTO
     */
    @PutMapping("/{id_zombie}")
    public ResponseEntity<ZombieDTO> updateZombie(
            @PathVariable("id_zombie") Long id_zombie,
            @RequestBody ZombieDTO zombieDTO) {
        ZombieModel zombie = mapper.mapDTOToModel(zombieDTO);
        zombie.setId_zombie(id_zombie);
        zombieService.updateZombie(zombie);
        return new ResponseEntity<>(mapper.mapModelToDTO(zombie), HttpStatus.OK);
    }

    /**
     * Supprime un zombie
     * @param id_zombie l'identifiant du zombie à supprimer
     * @return une réponse vide en cas de succès, ou un message d'erreur en cas d'échec
     */
    @DeleteMapping("/{id_zombie}")
    public ResponseEntity<Object> deleteZombie(@PathVariable("id_zombie") Long id_zombie) {
        try {
            zombieService.deleteZombie(id_zombie);
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
        logger.info("Appel de l'endpoint /zombies/validation");
        List<ZombieDTO> zombies = getAllZombies();
        logger.info("Nombre de zombies trouvés : {}", zombies.size());
        
        Map<String, Object> validationResult = new HashMap<>();
        List<String> errors = new ArrayList<>();
        
        if (zombies.isEmpty()) {
            logger.warn("Aucun zombie trouvé");
            errors.add("Aucun zombie trouvé dans la base de données");
        } else {
            // Validation de chaque zombie
            for (ZombieDTO zombie : zombies) {
                // Validation des champs obligatoires
                if (zombie.getId_zombie() == null) {
                    errors.add("ID de zombie manquant pour le zombie " + zombie.getNom());
                }
                if (zombie.getNom() == null || zombie.getNom().isEmpty()) {
                    errors.add("Nom de zombie manquant pour le zombie ID " + zombie.getId_zombie());
                }
                if (zombie.getPoint_de_vie() == null) {
                    errors.add("Points de vie manquants pour le zombie " + zombie.getNom());
                }
                if (zombie.getVitesse_de_deplacement() == null) {
                    errors.add("Vitesse de déplacement manquante pour le zombie " + zombie.getNom());
                }
                if (zombie.getDegat_attaque() == null) {
                    errors.add("Dégâts d'attaque manquants pour le zombie " + zombie.getNom());
                }
                if (zombie.getChemin_image() == null || zombie.getChemin_image().isEmpty()) {
                    errors.add("Chemin de l'image manquant pour le zombie " + zombie.getNom());
                }

                // Validation des contraintes métier
                if (zombie.getPoint_de_vie() != null && zombie.getPoint_de_vie() <= 0) {
                    errors.add("Points de vie doivent être positifs pour le zombie " + zombie.getNom());
                }
                if (zombie.getVitesse_de_deplacement() != null && zombie.getVitesse_de_deplacement() <= 0) {
                    errors.add("Vitesse de déplacement doit être positive pour le zombie " + zombie.getNom());
                }
                if (zombie.getDegat_attaque() != null && zombie.getDegat_attaque() <= 0) {
                    errors.add("Dégâts d'attaque doivent être positifs pour le zombie " + zombie.getNom());
                }
            }
        }

        validationResult.put("valid", errors.isEmpty());
        validationResult.put("message", errors.isEmpty() ? "Tous les zombies sont valides" : "Erreurs de validation détectées");
        validationResult.put("errors", errors);
        validationResult.put("total_zombies", zombies.size());
        
        logger.info("Résultat de la validation : {}", validationResult);
        return ResponseEntity.ok(validationResult);
    }
}
