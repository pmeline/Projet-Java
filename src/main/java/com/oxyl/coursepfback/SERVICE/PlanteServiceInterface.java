package com.oxyl.coursepfback.SERVICE;

import com.oxyl.coursepfback.MODEL.PlanteModel;

import java.util.List;

public interface PlanteServiceInterface {
    void createPlante(PlanteModel plante);
    PlanteModel getPlante(Long id);
    List<PlanteModel> getAllPlantes();
    void updatePlante(PlanteModel plante);
    void deletePlante(Long id);
}
