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
        if (planteModel == null) {
            throw new IllegalArgumentException("La plante ne peut pas être null");
        }
        if (planteModel.getNom() == null || planteModel.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la plante ne peut pas être vide");
        }
        if (planteModel.getPoint_de_vie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }
        if (planteModel.getAttaque_par_seconde() <= 0) {
            throw new IllegalArgumentException("L'attaque par seconde doit être positive");
        }
        if (planteModel.getDegat_attaque() <= 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque doivent être positifs");
        }
        if (planteModel.getCout() <= 0) {
            throw new IllegalArgumentException("Le coût doit être positif");
        }
        if (planteModel.getSoleil_par_seconde() <= 0) {
            throw new IllegalArgumentException("Le soleil par seconde doit être positif");
        }
        
        planteRepository.createPlante(planteModel);
    }

    @Override
    public PlanteModel getPlante(Long id_plante) {
        if (id_plante == null) {
            throw new IllegalArgumentException("L'id de la plante ne peut pas être null");
        }
        PlanteModel plante = planteRepository.getPlante(id_plante);
        if (plante == null) {
            throw new IllegalArgumentException("La plante avec l'id " + id_plante + " n'existe pas");
        }
        return plante;
    }

    @Override
    public List<PlanteModel> getAllPlantes() {
        return planteRepository.getAllPlantes();
    }

    @Override
    public void updatePlante(PlanteModel planteModel) {
        if (planteModel == null) {
            throw new IllegalArgumentException("La plante ne peut pas être null");
        }
        
        // Récupère la plante existante
        PlanteModel existingPlante = getPlante(planteModel.getId_plante());
        
        // Met à jour uniquement les champs non null
        if (planteModel.getNom() != null && !planteModel.getNom().trim().isEmpty()) {
            existingPlante.setNom(planteModel.getNom());
        }
        if (planteModel.getPoint_de_vie() != null) {
            if (planteModel.getPoint_de_vie() <= 0) {
                throw new IllegalArgumentException("Les points de vie doivent être positifs");
            }
            existingPlante.setPoint_de_vie(planteModel.getPoint_de_vie());
        }
        if (planteModel.getAttaque_par_seconde() != null) {
            if (planteModel.getAttaque_par_seconde() <= 0) {
                throw new IllegalArgumentException("L'attaque par seconde doit être positive");
            }
            existingPlante.setAttaque_par_seconde(planteModel.getAttaque_par_seconde());
        }
        if (planteModel.getDegat_attaque() != null) {
            if (planteModel.getDegat_attaque() <= 0) {
                throw new IllegalArgumentException("Les dégâts d'attaque doivent être positifs");
            }
            existingPlante.setDegat_attaque(planteModel.getDegat_attaque());
        }
        if (planteModel.getCout() != null) {
            if (planteModel.getCout() <= 0) {
                throw new IllegalArgumentException("Le coût doit être positif");
            }
            existingPlante.setCout(planteModel.getCout());
        }
        if (planteModel.getSoleil_par_seconde() != null) {
            if (planteModel.getSoleil_par_seconde() <= 0) {
                throw new IllegalArgumentException("Le soleil par seconde doit être positif");
            }
            existingPlante.setSoleil_par_seconde(planteModel.getSoleil_par_seconde());
        }
        if (planteModel.getEffet() != null) {
            existingPlante.setEffet(planteModel.getEffet());
        }
        if (planteModel.getChemin_image() != null) {
            existingPlante.setChemin_image(planteModel.getChemin_image());
        }
        
        planteRepository.updatePlante(existingPlante);
    }

    @Override
    public void deletePlante(Long id_plante) {
        if (id_plante == null) {
            throw new IllegalArgumentException("L'id de la plante ne peut pas être null");
        }
        
        // Vérifie si la plante existe
        PlanteModel existingPlante = planteRepository.getPlante(id_plante);
        if (existingPlante == null) {
            throw new IllegalArgumentException("La plante avec l'id " + id_plante + " n'existe pas");
        }
        
        planteRepository.deletePlante(id_plante);
    }
}
