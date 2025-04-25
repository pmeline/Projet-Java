package com.oxyl.coursepfback.persistance.entity;

import com.oxyl.coursepfback.model.PlanteModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper pour convertir entre PlanteEntity et PlanteModel
 */
@Component
public class PlanteEntityMapper {

    /**
     * Convertit un PlanteModel en PlanteEntity
     * @param planteModel le modèle à convertir
     * @return l'entité convertie
     */
    public PlanteEntity mapModelToEntity(PlanteModel planteModel) {
        if (planteModel == null) {
            return null;
        }
        PlanteEntity entity = new PlanteEntity();
        entity.setId_plante(planteModel.getId_plante());
        entity.setNom(planteModel.getNom());
        entity.setPoint_de_vie(planteModel.getPoint_de_vie());
        entity.setAttaque_par_seconde(planteModel.getAttaque_par_seconde());
        entity.setDegat_attaque(planteModel.getDegat_attaque());
        entity.setCout(planteModel.getCout());
        entity.setSoleil_par_seconde(planteModel.getSoleil_par_seconde());
        entity.setEffet(planteModel.getEffet());
        entity.setChemin_image(planteModel.getChemin_image());
        return entity;
    }

    /**
     * Convertit une PlanteEntity en PlanteModel
     * @param planteEntity l'entité à convertir
     * @return le modèle converti
     */
    public PlanteModel mapEntityToModel(PlanteEntity planteEntity) {
        if (planteEntity == null) {
            return null;
        }
        return new PlanteModel(
                planteEntity.getId_plante(),
                planteEntity.getNom(),
                planteEntity.getPoint_de_vie(),
                planteEntity.getAttaque_par_seconde(),
                planteEntity.getDegat_attaque(),
                planteEntity.getCout(),
                planteEntity.getSoleil_par_seconde(),
                planteEntity.getEffet(),
                planteEntity.getChemin_image()
        );
    }

    /**
     * Convertit une liste de PlanteEntity en une liste de PlanteModel
     * @param planteEntities la liste d'entités à convertir
     * @return la liste de modèles convertie
     */
    public List<PlanteModel> mapEntitiesToModels(List<PlanteEntity> planteEntities) {
        return planteEntities.stream()
                .map(this::mapEntityToModel)
                .toList();
    }
}
