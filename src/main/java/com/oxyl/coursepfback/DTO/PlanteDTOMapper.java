package com.oxyl.coursepfback.DTO;

import com.oxyl.coursepfback.MODEL.PlanteModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanteDTOMapper {

    public PlanteDTO mapModelToDTO(PlanteModel planteModel){
        if (planteModel == null){
            return null;
        }

        PlanteDTO planteDTO = new PlanteDTO();
        planteDTO.setId(planteModel.getId());
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

    public PlanteModel mapDTOToModel(PlanteDTO planteDTO) {
        if (planteDTO == null) {
            return null;
        }

        PlanteModel planteModel = new PlanteModel();
        planteModel.setId(planteDTO.getId());
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

    public List<PlanteDTO> mapListModelsToDTO(List<PlanteModel> planteModels){
        if (planteModels == null){
            return null;
        }

        return planteModels.stream()
                .map(this::mapModelToDTO)
                .toList();
    }

}
