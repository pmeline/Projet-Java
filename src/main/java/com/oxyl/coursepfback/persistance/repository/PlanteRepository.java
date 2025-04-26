package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.dao.PlanteDAO;
import com.oxyl.coursepfback.persistance.entity.PlanteEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
import org.springframework.stereotype.Repository;
import com.oxyl.coursepfback.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * Repository pour gérer les opérations CRUD sur les plantes
 */
@Repository
public class PlanteRepository {

    private final PlanteDAO planteDAO;
    private final PlanteEntityMapper mapper;

    /**
     * Constructeur avec injection de dépendances
     * @param planteDAO le DAO pour accéder aux données des plantes
     * @param mapper le mapper pour convertir entre entités et modèles
     */
    @Autowired
    public PlanteRepository(PlanteDAO planteDAO, PlanteEntityMapper mapper) {
        this.planteDAO = planteDAO;
        this.mapper = mapper;
    }

    /**
     * Récupère toutes les plantes
     * @return la liste de toutes les plantes
     */
    public List<PlanteModel> getAllPlantes() {
        return mapper.mapEntitiesToModels(planteDAO.getAllPlantes());
    }

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante
     * @return la plante correspondante
     */
    public PlanteModel getPlante(Long id_plante) {
        try {
            return mapper.mapEntityToModel(planteDAO.getPlante(id_plante));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("La plante avec l'id " + id_plante + " n'existe pas");
        }
    }

    /**
     * Crée une nouvelle plante
     * @param plante le modèle de la plante à créer
     */
    public void createPlante(PlanteModel plante) {
        PlanteEntity entity = mapper.mapModelToEntity(plante);
        planteDAO.createPlante(entity);
    }

    /**
     * Met à jour une plante existante
     * @param plante le modèle de la plante à mettre à jour
     */
    public void updatePlante(PlanteModel plante) {
        PlanteEntity existingPlante = planteDAO.getPlante(plante.getId_plante());
        if (existingPlante == null) {
            throw new NotFoundException("La plante avec l'id " + plante.getId_plante() + " n'existe pas");
        }
        PlanteEntity entity = mapper.mapModelToEntity(plante);
        planteDAO.updatePlante(entity);
    }

    /**
     * Supprime une plante
     * @param id_plante l'identifiant de la plante à supprimer
     */
    public void deletePlante(Long id_plante) {
        planteDAO.deletePlante(id_plante);
    }

}
