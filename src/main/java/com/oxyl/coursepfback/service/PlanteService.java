package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.persistance.repository.PlanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant les opérations liées aux plantes
 */
@Service
public class PlanteService implements PlanteServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(PlanteService.class);
    private final PlanteRepository planteRepository;

    @Autowired
    public PlanteService(PlanteRepository planteRepository) {
        this.planteRepository = planteRepository;
    }

    /**
     * Crée une nouvelle plante
     * @param planteModel la plante à créer
     * @throws ValidationException si les données de la plante sont invalides
     */
    @Override
    public void createPlante(PlanteModel planteModel) {
        logger.debug("Création d'une nouvelle plante: {}", planteModel);
        if (planteModel == null) {
            logger.error("Tentative de création d'une plante null");
            throw new ValidationException("La plante ne peut pas être null");
        }
        if (planteModel.getNom() == null || planteModel.getNom().trim().isEmpty()) {
            logger.error("Tentative de création d'une plante avec un nom vide");
            throw new ValidationException("Le nom de la plante ne peut pas être vide");
        }
        if (planteModel.getPoint_de_vie() <= 0) {
            logger.error("Tentative de création d'une plante avec des points de vie négatifs: {}", planteModel.getPoint_de_vie());
            throw new ValidationException("Les points de vie doivent être positifs");
        }
        if (planteModel.getAttaque_par_seconde() <= 0) {
            logger.error("Tentative de création d'une plante avec une attaque par seconde négative: {}", planteModel.getAttaque_par_seconde());
            throw new ValidationException("L'attaque par seconde doit être positive");
        }
        if (planteModel.getDegat_attaque() <= 0) {
            logger.error("Tentative de création d'une plante avec des dégâts d'attaque négatifs: {}", planteModel.getDegat_attaque());
            throw new ValidationException("Les dégâts d'attaque doivent être positifs");
        }
        if (planteModel.getCout() <= 0) {
            logger.error("Tentative de création d'une plante avec un coût négatif: {}", planteModel.getCout());
            throw new ValidationException("Le coût doit être positif");
        }
        if (planteModel.getSoleil_par_seconde() <= 0) {
            logger.error("Tentative de création d'une plante avec un soleil par seconde négatif: {}", planteModel.getSoleil_par_seconde());
            throw new ValidationException("Le soleil par seconde doit être positif");
        }
        if (planteModel.getEffet() == null || planteModel.getEffet().trim().isEmpty()) {
            logger.error("Tentative de création d'une plante avec un effet vide");
            throw new ValidationException("L'effet ne peut pas être vide");
        }
        if (planteModel.getChemin_image() == null || planteModel.getChemin_image().trim().isEmpty()) {
            logger.error("Tentative de création d'une plante avec un chemin d'image vide");
            throw new ValidationException("Le chemin de l'image ne peut pas être vide");
        }
        
        planteRepository.createPlante(planteModel);
        logger.info("Plante créée avec succès: {}", planteModel);
    }

    /**
     * Récupère une plante par son identifiant
     * @param id_plante l'identifiant de la plante
     * @return la plante correspondante
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si la plante n'existe pas
     */
    @Override
    public PlanteModel getPlante(Long id_plante) {
        logger.debug("Récupération de la plante avec l'id: {}", id_plante);
        if (id_plante == null) {
            logger.error("Tentative de récupération d'une plante avec un id null");
            throw new ValidationException("L'id de la plante ne peut pas être null");
        }
        PlanteModel plante = planteRepository.getPlante(id_plante);
        if (plante == null) {
            logger.error("Plante non trouvée avec l'id: {}", id_plante);
            throw new NotFoundException("La plante avec l'id " + id_plante + " n'existe pas");
        }
        logger.debug("Plante trouvée: {}", plante);
        return plante;
    }

    /**
     * Récupère toutes les plantes
     * @return la liste de toutes les plantes
     */
    @Override
    public List<PlanteModel> getAllPlantes() {
        logger.debug("Récupération de toutes les plantes");
        List<PlanteModel> plantes = planteRepository.getAllPlantes();
        logger.debug("{} plantes trouvées", plantes.size());
        return plantes;
    }

    /**
     * Met à jour une plante existante
     * @param planteModel la plante avec les nouvelles données
     * @throws ValidationException si les données sont invalides
     * @throws NotFoundException si la plante n'existe pas
     */
    @Override
    public void updatePlante(PlanteModel planteModel) {
        if (planteModel == null) {
            logger.error("Tentative de mise à jour d'une plante null");
            throw new ValidationException("La plante ne peut pas être null");
        }

        PlanteModel existingPlante = planteRepository.getPlante(planteModel.getId_plante());
        if (existingPlante == null) {
            logger.error("Tentative de mise à jour d'une plante inexistante avec l'id: {}", planteModel.getId_plante());
            throw new NotFoundException("La plante avec l'id " + planteModel.getId_plante() + " n'existe pas");
        }

        if (planteModel.getNom() != null) {
            String nom = planteModel.getNom().trim();
            if (nom.isEmpty()) {
                logger.error("Tentative de mise à jour d'une plante avec un nom vide");
                throw new ValidationException("Le nom de la plante ne peut pas être vide");
            }
            if (nom.length() > 50) {
                logger.error("Tentative de mise à jour d'une plante avec un nom trop long");
                throw new ValidationException("Le nom de la plante ne peut pas dépasser 50 caractères");
            }
            existingPlante.setNom(nom);
        }

        if (planteModel.getPoint_de_vie() != null) {
            if (planteModel.getPoint_de_vie() <= 0) {
                logger.error("Tentative de mise à jour d'une plante avec des points de vie invalides: {}", planteModel.getPoint_de_vie());
                throw new ValidationException("Les points de vie doivent être strictement positifs");
            }
            if (planteModel.getPoint_de_vie() > 1000) {
                logger.error("Tentative de mise à jour d'une plante avec des points de vie trop élevés: {}", planteModel.getPoint_de_vie());
                throw new ValidationException("Les points de vie ne peuvent pas dépasser 1000");
            }
            existingPlante.setPoint_de_vie(planteModel.getPoint_de_vie());
        }

        if (planteModel.getAttaque_par_seconde() != null) {
            if (planteModel.getAttaque_par_seconde() <= 0) {
                logger.error("Tentative de mise à jour d'une plante avec une attaque par seconde invalide: {}", planteModel.getAttaque_par_seconde());
                throw new ValidationException("L'attaque par seconde doit être strictement positive");
            }
            if (planteModel.getAttaque_par_seconde() > 10) {
                logger.error("Tentative de mise à jour d'une plante avec une attaque par seconde trop élevée: {}", planteModel.getAttaque_par_seconde());
                throw new ValidationException("L'attaque par seconde ne peut pas dépasser 10");
            }
            existingPlante.setAttaque_par_seconde(planteModel.getAttaque_par_seconde());
        }

        if (planteModel.getDegat_attaque() != null) {
            if (planteModel.getDegat_attaque() <= 0) {
                logger.error("Tentative de mise à jour d'une plante avec des dégâts d'attaque invalides: {}", planteModel.getDegat_attaque());
                throw new ValidationException("Les dégâts d'attaque doivent être strictement positifs");
            }
            if (planteModel.getDegat_attaque() > 100) {
                logger.error("Tentative de mise à jour d'une plante avec des dégâts d'attaque trop élevés: {}", planteModel.getDegat_attaque());
                throw new ValidationException("Les dégâts d'attaque ne peuvent pas dépasser 100");
            }
            existingPlante.setDegat_attaque(planteModel.getDegat_attaque());
        }

        if (planteModel.getCout() != null) {
            if (planteModel.getCout() <= 0) {
                logger.error("Tentative de mise à jour d'une plante avec un coût invalide");
                throw new ValidationException("Le coût doit être positif");
            }
            existingPlante.setCout(planteModel.getCout());
        }

        if (planteModel.getSoleil_par_seconde() != null) {
            if (planteModel.getSoleil_par_seconde() < 0) {
                logger.error("Tentative de mise à jour d'une plante avec un soleil par seconde invalide");
                throw new ValidationException("Le soleil par seconde ne peut pas être négatif");
            }
            existingPlante.setSoleil_par_seconde(planteModel.getSoleil_par_seconde());
        }

        if (planteModel.getEffet() != null) {
            if (planteModel.getEffet().trim().isEmpty()) {
                logger.error("Tentative de mise à jour d'une plante avec un effet vide");
                throw new ValidationException("L'effet ne peut pas être vide");
            }
            existingPlante.setEffet(planteModel.getEffet());
        }

        if (planteModel.getChemin_image() != null) {
            String cheminImage = planteModel.getChemin_image().trim();
            if (cheminImage.isEmpty()) {
                logger.error("Tentative de mise à jour d'une plante avec un chemin d'image vide");
                throw new ValidationException("Le chemin de l'image ne peut pas être vide");
            }
            if (!cheminImage.matches("^[a-zA-Z0-9_\\-\\.]+\\.(png|jpg|jpeg|gif)$")) {
                logger.error("Tentative de mise à jour d'une plante avec un chemin d'image invalide: {}", cheminImage);
                throw new ValidationException("Le chemin de l'image doit être un nom de fichier valide avec l'extension .png, .jpg, .jpeg ou .gif");
            }
            existingPlante.setChemin_image(cheminImage);
        }

        planteRepository.updatePlante(existingPlante);
        logger.info("Plante mise à jour avec succès: {}", existingPlante);
    }

    /**
     * Supprime une plante
     * @param id_plante l'identifiant de la plante à supprimer
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si la plante n'existe pas
     */
    @Override
    public void deletePlante(Long id_plante) {
        logger.debug("Suppression de la plante avec l'id: {}", id_plante);
        if (id_plante == null) {
            logger.error("Tentative de suppression d'une plante avec un id null");
            throw new ValidationException("L'id de la plante ne peut pas être null");
        }
        
        PlanteModel existingPlante = planteRepository.getPlante(id_plante);
        if (existingPlante == null) {
            logger.error("Tentative de suppression d'une plante inexistante avec l'id: {}", id_plante);
            throw new NotFoundException("La plante avec l'id " + id_plante + " n'existe pas");
        }
        
        planteRepository.deletePlante(id_plante);
        logger.info("Plante supprimée avec succès: {}", existingPlante);
    }
}
