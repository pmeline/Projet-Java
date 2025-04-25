package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.PlanteModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper pour convertir entre PlanteModel et PlanteDTO
 */
@Component
public class PlanteDTOMapper {

    /**
     * Convertit un PlanteModel en PlanteDTO
     * @param planteModel le modèle à convertir
     * @return le DTO converti
     */
    public PlanteDTO mapModelToDTO(PlanteModel planteModel){
        if (planteModel == null){
            return null;
        }

        PlanteDTO planteDTO = new PlanteDTO();
        planteDTO.setId_plante(planteModel.getId_plante());
        planteDTO.setNom(planteModel.getNom());
        planteDTO.setAttaque_par_seconde(planteModel.getAttaque_par_seconde());
        planteDTO.setCout(planteModel.getCout());
        planteDTO.setDegat_attaque(planteModel.getDegat_attaque());
        planteDTO.setEffet(planteModel.getEffet());
        planteDTO.setPoint_de_vie(planteModel.getPoint_de_vie());
        planteDTO.setSoleil_par_seconde(planteModel.getSoleil_par_seconde());
        planteDTO.setChemin_image(planteModel.getChemin_image());
        return planteDTO;
    }

    /**
     * Convertit un PlanteDTO en PlanteModel
     * @param planteDTO le DTO à convertir
     * @return le modèle converti
     */
    public PlanteModel mapDTOToModel(PlanteDTO planteDTO) {
        if (planteDTO == null) {
            return null;
        }

        PlanteModel planteModel = new PlanteModel();
        planteModel.setId_plante(planteDTO.getId_plante());
        planteModel.setNom(planteDTO.getNom());
        planteModel.setAttaque_par_seconde(planteDTO.getAttaque_par_seconde());
        planteModel.setCout(planteDTO.getCout());
        planteModel.setDegat_attaque(planteDTO.getDegat_attaque());
        planteModel.setEffet(planteDTO.getEffet());
        planteModel.setPoint_de_vie(planteDTO.getPoint_de_vie());
        planteModel.setSoleil_par_seconde(planteDTO.getSoleil_par_seconde());
        planteModel.setChemin_image(planteDTO.getChemin_image());
        return planteModel;
    }

    /**
     * Convertit une liste de PlanteModel en une liste de PlanteDTO
     * @param planteModels la liste de modèles à convertir
     * @return la liste de DTOs convertie
     */
    public List<PlanteDTO> mapListModelsToDTO(List<PlanteModel> planteModels){
        if (planteModels == null){
            return null;
        }
        return planteModels.stream()
                .map(this::mapModelToDTO)
                .toList();
    }
}
