package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface définissant les opérations de base de données pour les zombies
 */
@Repository
public interface ZombieDAOInterface {
    /**
     * Crée un nouveau zombie dans la base de données
     * @param zombie le zombie à créer
     */
    void createZombie(ZombieEntity zombie);

    /**
     * Met à jour un zombie existant dans la base de données
     * @param zombie le zombie à mettre à jour
     */
    void updateZombie(ZombieEntity zombie);

    /**
     * Supprime un zombie de la base de données
     * @param id l'identifiant du zombie à supprimer
     */
    void deleteZombie(Long id);

    /**
     * Récupère un zombie par son identifiant
     * @param id l'identifiant du zombie à récupérer
     * @return le zombie correspondant
     */
    ZombieEntity getZombie(Long id);

    /**
     * Récupère tous les zombies associés à une map
     * @param id_map l'identifiant de la map
     * @return la liste des zombies de la map
     */
    List<ZombieEntity> getZombiesByMapId(Long id_map);

    /**
     * Récupère tous les zombies de la base de données
     * @return la liste de tous les zombies
     */
    List<ZombieEntity> getAllZombies();
}
