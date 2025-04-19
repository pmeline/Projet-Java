package com.oxyl.coursepfback.persistance.dao;


import com.oxyl.coursepfback.persistance.entity.PlanteEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteDAOInterface {
    void createPlante(PlanteEntity plante);
    void updatePlante(PlanteEntity plante);
    void deletePlante(Long id);
    PlanteEntity getPlante(Long id_plante);
    List<PlanteEntity> getAllPlantes();
}
