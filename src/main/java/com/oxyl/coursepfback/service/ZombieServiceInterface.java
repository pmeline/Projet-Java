package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.ZombieModel;

import java.util.List;

/**
 * Interface définissant les opérations disponibles pour la gestion des zombies
 */
public interface ZombieServiceInterface {
    /**
     * Crée un nouveau zombie
     * @param zombie le zombie à créer
     */
    void createZombie(ZombieModel zombie);

    /**
     * Récupère un zombie par son identifiant
     * @param id_zombie l'identifiant du zombie à récupérer
     * @return le zombie correspondant
     */
    ZombieModel getZombie(Long id_zombie);

    /**
     * Récupère tous les zombies
     * @return la liste de tous les zombies
     */
    List<ZombieModel> getAllZombies();

    /**
     * Récupère tous les zombies associés à une map
     * @param id_map l'identifiant de la map
     * @return la liste des zombies de la map
     */
    List<ZombieModel> getZombiesByMapId(Long id_map);

    /**
     * Met à jour un zombie existant
     * @param zombie le zombie à mettre à jour
     */
    void updateZombie(ZombieModel zombie);

    /**
     * Supprime un zombie
     * @param id_zombie l'identifiant du zombie à supprimer
     */
    void deleteZombie(Long id_zombie);
}
