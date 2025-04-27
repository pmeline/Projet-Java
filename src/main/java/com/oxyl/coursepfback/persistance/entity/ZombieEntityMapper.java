package com.oxyl.coursepfback.persistance.entity;
import com.oxyl.coursepfback.model.ZombieModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre ZombieEntity et ZombieModel
 */
@Component
public class ZombieEntityMapper {
    
    /**
     * Convertit une ZombieEntity en ZombieModel
     * @param entity l'entité à convertir
     * @return le modèle converti
     */
    public ZombieModel mapEntityToModel(ZombieEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ZombieModel(
            entity.getId_zombie(),
            entity.getNom(),
            entity.getPoint_de_vie(),
            entity.getAttaque_par_seconde(),
            entity.getDegat_attaque(),
            entity.getVitesse_de_deplacement(),
            entity.getChemin_image(),
            entity.getId_map()
        );
    }

    /**
     * Convertit un ZombieModel en ZombieEntity
     * @param model le modèle à convertir
     * @return l'entité convertie
     */
    public ZombieEntity mapModelToEntity(ZombieModel model) {
        if (model == null) {
            return null;
        }
        return new ZombieEntity(
            model.getId_zombie(),
            model.getNom(),
            model.getPoint_de_vie(),
            model.getAttaque_par_seconde(),
            model.getDegat_attaque(),
            model.getVitesse_de_deplacement(),
            model.getChemin_image(),
            model.getId_map()
        );
    }

    /**
     * Convertit une liste de ZombieEntity en une liste de ZombieModel
     * @param zombieEntities la liste d'entités à convertir
     * @return la liste de modèles convertie
     */
    public List<ZombieModel> mapListEntityToModel(List<ZombieEntity> zombieEntities) {
        if (zombieEntities == null) {
            return null;
        }
        return zombieEntities.stream()
            .map(this::mapEntityToModel)
            .collect(Collectors.toList());
    }
}
