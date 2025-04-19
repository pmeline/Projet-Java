package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.dao.PlanteDAO;
import com.oxyl.coursepfback.persistance.entity.PlanteEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanteRepository {

    private final PlanteDAO planteDAO;
    private final PlanteEntityMapper mapper;

    @Autowired
    public PlanteRepository(PlanteDAO planteDAO, PlanteEntityMapper mapper) {
        this.planteDAO = planteDAO;
        this.mapper = mapper;
    }

    public List<PlanteModel> getAllPlantes() {
        return mapper.mapEntitiesToModels(planteDAO.getAllPlantes());
    }

    public PlanteModel getPlante(Long id_plante) {
        return mapper.mapEntityToModel(planteDAO.getPlante(id_plante));
    }

    public void createPlante(PlanteModel plante) {
        PlanteEntity entity = mapper.mapModelToEntity(plante);
        planteDAO.createPlante(entity);
    }

    public void updatePlante(PlanteModel plante) {
        PlanteEntity entity = mapper.mapModelToEntity(plante);
        planteDAO.updatePlante(entity);
    }

    public void deletePlante(Long id_plante) {
        planteDAO.deletePlante(id_plante);
    }

}
