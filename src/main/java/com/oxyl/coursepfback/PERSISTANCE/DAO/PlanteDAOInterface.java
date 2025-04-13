package com.oxyl.coursepfback.PERSISTANCE.DAO;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteDAOInterface {
    void createPlante(PlanteEntity plante);
    void updatePlante(PlanteEntity plante);
    void deletePlante(Long id);
    PlanteEntity readPlante(Long id);
    List<PlanteEntity> getAllPlantes();
}
