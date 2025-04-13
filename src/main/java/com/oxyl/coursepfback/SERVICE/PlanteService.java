package com.oxyl.coursepfback.SERVICE;

import com.oxyl.coursepfback.MODEL.PlanteModel;
import com.oxyl.coursepfback.PERSISTANCE.DAO.PlanteDAO;
import com.oxyl.coursepfback.PERSISTANCE.DAO.PlanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanteService implements PlanteServiceInterface {

    private final PlanteDAO planteDAO;

    @Autowired
    public PlanteService(PlanteDAO planteDAO) {
        this.planteDAO = planteDAO;
    }

    public void createPlante(PlanteModel planteModel) {
        PlanteEntity planteEntity = convertToEntity(planteModel);
        planteDAO.createPlante(planteEntity);
    }

    public PlanteModel getPlante(Long id) {
        PlanteEntity entity = planteDAO.getById(id);
        return entity != null ? convertToModel(entity) : null;
    }

    public List<PlanteModel> getAllPlantes() {
        List<PlanteEntity> entities = planteDAO.getAllPlantes();
        return entities.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    public void updatePlante(PlanteModel planteModel) {
        PlanteEntity entity = convertToEntity(planteModel);
        planteDAO.updatePlante(entity);
    }

    public void deletePlante(Long id) {
        planteDAO.deletePlante(id);
    }

    // Conversion des plantes entity en plante modèle
    private PlanteModel convertToModel(PlanteEntity entity) {
        if (entity == null){
            return null;
        }
        PlanteModel model = new PlanteModel();
        model.setId(entity.getId());
        model.setNom(entity.getNom());
        model.setPoint_de_vie(entity.getPoint_de_vie());
        model.setAttaque_par_seconde(entity.getAttaque_par_seconde());
        model.setDegat_attaque(entity.getDegat_attaque());
        model.setCout(entity.getCout());
        model.setSoleil_par_seconde(entity.getSoleil_par_seconde());
        model.setEffet(entity.getEffet());
        model.setChemin_image(entity.getChemin_image());
        return model;
    }

    // Conversion des plantes modèle en plante entity
    private PlanteEntity convertToEntity(PlanteModel model) {
        if (model == null){
            return null;
        }
        PlanteEntity entity = new PlanteEntity();
        entity.setId(model.getId());
        entity.setNom(model.getNom());
        entity.setPoint_de_vie(model.getPoint_de_vie());
        entity.setAttaque_par_seconde(model.getAttaque_par_seconde());
        entity.setDegat_attaque(model.getDegat_attaque());
        entity.setCout(model.getCout());
        entity.setSoleil_par_seconde(model.getSoleil_par_seconde());
        entity.setEffet(model.getEffet());
        entity.setChemin_image(model.getChemin_image());
        return entity;
    }

}
