package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.repository.PlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanteService implements PlanteServiceInterface {

    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    @Override
    public void createPlante(PlanteModel planteModel) {
        planteRepository.createPlante(planteModel);
    }

    @Override
    public PlanteModel getPlante(Long id_plante) {
        return planteRepository.getPlante(id_plante);
    }

    @Override
    public List<PlanteModel> getAllPlantes() {
        return planteRepository.getAllPlantes();
    }

    @Override
    public void updatePlante(PlanteModel planteModel) {
        planteRepository.updatePlante(planteModel);
    }

    @Override
    public void deletePlante(Long id_plante) {
        planteRepository.deletePlante(id_plante);
    }
}
