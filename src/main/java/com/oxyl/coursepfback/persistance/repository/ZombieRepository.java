package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.dao.ZombieDAO;
import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import com.oxyl.coursepfback.persistance.entity.ZombieEntityMapper;
import com.oxyl.coursepfback.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * Repository pour gérer les opérations CRUD sur les zombies
 */
@Repository
public class ZombieRepository {

    private final ZombieDAO zombieDAO;
    private final ZombieEntityMapper mapper;

    /**
     * Constructeur avec injection de dépendances
     * @param zombieDAO le DAO pour accéder aux données des zombies
     * @param mapper le mapper pour convertir entre entités et modèles
     */
    @Autowired
    public ZombieRepository(ZombieDAO zombieDAO, ZombieEntityMapper mapper) {
        this.zombieDAO = zombieDAO;
        this.mapper = mapper;
    }

    /**
     * Récupère tous les zombies
     * @return la liste de tous les zombies
     */
    public List<ZombieModel> getAllZombies() {
        return mapper.mapListEntityToModel(zombieDAO.getAllZombies());
    }

    /**
     * Récupère un zombie par son identifiant
     * @param id_zombie l'identifiant du zombie
     * @return le zombie correspondant
     */
    public ZombieModel getZombie(Long id_zombie) {
        try {
            return mapper.mapEntityToModel(zombieDAO.getZombie(id_zombie));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Le zombie avec l'id " + id_zombie + " n'existe pas");
        }
    }

    /**
     * Récupère tous les zombies d'une carte
     * @param id_map l'identifiant de la carte
     * @return la liste des zombies de la carte
     */
    public List<ZombieModel> getZombiesByMapId(Long id_map) {
        return mapper.mapListEntityToModel(zombieDAO.getZombiesByMapId(id_map));
    }

    /**
     * Crée un nouveau zombie
     * @param zombieModel le modèle du zombie à créer
     */
    public void createZombie(ZombieModel zombieModel) {
        ZombieEntity zombieEntity = mapper.mapModelToEntity(zombieModel);
        zombieDAO.createZombie(zombieEntity);
    }

    /**
     * Met à jour un zombie existant
     * @param zombieModel le modèle du zombie à mettre à jour
     */
    public void updateZombie(ZombieModel zombieModel) {
        ZombieEntity existingZombie = zombieDAO.getZombie(zombieModel.getId_zombie());
        if (existingZombie == null) {
            throw new NotFoundException("Le zombie avec l'id " + zombieModel.getId_zombie() + " n'existe pas");
        }
        ZombieEntity zombieEntity = mapper.mapModelToEntity(zombieModel);
        zombieDAO.updateZombie(zombieEntity);
    }

    /**
     * Supprime un zombie
     * @param id_zombie l'identifiant du zombie à supprimer
     */
    public void deleteZombie(Long id_zombie) {
        zombieDAO.deleteZombie(id_zombie);
    }

}
