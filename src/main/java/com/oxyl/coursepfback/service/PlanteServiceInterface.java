package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.PlanteModel;

import java.util.List;

public interface PlanteServiceInterface {
    void createPlante(PlanteModel plante);
    PlanteModel getPlante(Long id_plante);
    List<PlanteModel> getAllPlantes();
    void updatePlante(PlanteModel plante);
    void deletePlante(Long id_plante);
}
