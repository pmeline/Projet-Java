package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.ZombieDTO;
import com.oxyl.coursepfback.dto.ZombieDTOMapper;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.service.ZombieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
