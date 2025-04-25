package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.repository.PlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant les opérations liées aux plantes
 */
@Service
public class PlanteService implements PlanteServiceInterface {

    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    /**
     * Crée une nouvelle plante
     * @param planteModel la plante à créer
     * @throws IllegalArgumentException si les données de la plante sont invalides
     */
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

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante
     * @return la plante correspondante
     * @throws IllegalArgumentException si l'identifiant est null ou si la plante n'existe pas
     */
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

    /**
     * Récupère toutes les plantes
     * @return la liste de toutes les plantes
     */
    @Override
    public List<PlanteModel> getAllPlantes() {
        return planteRepository.getAllPlantes();
    }

    /**
     * Met à jour une plante existante
     * @param planteModel la plante avec les nouvelles données
     * @throws IllegalArgumentException si les données sont invalides
     */
    @Override
    public void updatePlante(PlanteModel planteModel) {
        if (planteModel == null) {
            throw new IllegalArgumentException("La plante ne peut pas être null");
        }
        
        PlanteModel existingPlante = getPlante(planteModel.getId_plante());
        
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

    /**
     * Supprime une plante
     * @param id_plante l'identifiant de la plante à supprimer
     * @throws IllegalArgumentException si l'identifiant est null ou si la plante n'existe pas
     */
    @Override
    public void deletePlante(Long id_plante) {
        if (id_plante == null) {
            throw new IllegalArgumentException("L'id de la plante ne peut pas être null");
        }
        
        PlanteModel existingPlante = planteRepository.getPlante(id_plante);
        if (existingPlante == null) {
            throw new IllegalArgumentException("La plante avec l'id " + id_plante + " n'existe pas");
        }
        
        planteRepository.deletePlante(id_plante);
    }
}
