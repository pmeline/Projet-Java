package com.oxyl.coursepfback.dto;

import com.oxyl.coursepfback.model.ZombieModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre ZombieModel et ZombieDTO
 */
@Component
public class ZombieDTOMapper {

    /**
     * Convertit un ZombieModel en ZombieDTO
     * @param zombieModel le modèle à convertir
     * @return le DTO converti
     */
    public ZombieDTO mapModelToDTO(ZombieModel zombieModel) {
        if (zombieModel == null) {
            return null;
        }

        ZombieDTO zombieDTO = new ZombieDTO();
        zombieDTO.setId_zombie(zombieModel.getId_zombie());
        zombieDTO.setNom(zombieModel.getNom());
        zombieDTO.setPoint_de_vie(zombieModel.getPoint_de_vie());
        zombieDTO.setAttaque_par_seconde(zombieModel.getAttaque_par_seconde());
        zombieDTO.setDegat_attaque(zombieModel.getDegat_attaque());
        zombieDTO.setVitesse_de_deplacement(zombieModel.getVitesse_de_deplacement());
        zombieDTO.setChemin_image(zombieModel.getChemin_image());
        zombieDTO.setId_map(zombieModel.getId_map());
        return zombieDTO;
    }

    /**
     * Convertit un ZombieDTO en ZombieModel
     * @param zombieDTO le DTO à convertir
     * @return le modèle converti
     */
    public ZombieModel mapDTOToModel(ZombieDTO zombieDTO) {
        if (zombieDTO == null) {
            return null;
        }
        ZombieModel zombieModel = new ZombieModel();
        zombieModel.setId_zombie(zombieDTO.getId_zombie());
        zombieModel.setNom(zombieDTO.getNom());
        zombieModel.setPoint_de_vie(zombieDTO.getPoint_de_vie());
        zombieModel.setAttaque_par_seconde(zombieDTO.getAttaque_par_seconde());
        zombieModel.setDegat_attaque(zombieDTO.getDegat_attaque());
        zombieModel.setVitesse_de_deplacement(zombieDTO.getVitesse_de_deplacement());
        zombieModel.setChemin_image(zombieDTO.getChemin_image());
        zombieModel.setId_map(zombieDTO.getId_map());
        return zombieModel;
    }

    /**
     * Convertit une liste de ZombieModel en une liste de ZombieDTO
     * @param zombieModels la liste de modèles à convertir
     * @return la liste de DTOs convertie
     */
    public List<ZombieDTO> mapListModelToDTO(List<ZombieModel> zombieModels) {
        if (zombieModels == null) {
            return null;
        }
        return zombieModels.stream()
                .map(this::mapModelToDTO)
                .collect(Collectors.toList());
    }

}
