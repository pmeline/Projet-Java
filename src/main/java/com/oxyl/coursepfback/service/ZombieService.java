package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.exception.NotFoundException;
import com.oxyl.coursepfback.exception.ValidationException;
import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service gérant les opérations liées aux zombies
 */
@Service
public class ZombieService implements ZombieServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(ZombieService.class);
    private final ZombieRepository zombieRepository;
    private final MapService mapService;

    @Autowired
    public ZombieService(ZombieRepository zombieRepository, @Lazy MapService mapService) {
        this.zombieRepository = zombieRepository;
        this.mapService = mapService;
    }

    /**
     * Crée un nouveau zombie
     * @param zombieModel le zombie à créer
     * @throws ValidationException si les données du zombie sont invalides
     */
    @Override
    public void createZombie(ZombieModel zombieModel) {
        logger.debug("Création d'un nouveau zombie: {}", zombieModel);
        if (zombieModel == null) {
            logger.error("Tentative de création d'un zombie null");
            throw new ValidationException("Le zombie ne peut pas être null");
        }
        if (zombieModel.getNom() == null || zombieModel.getNom().trim().isEmpty()) {
            logger.error("Tentative de création d'un zombie avec un nom vide");
            throw new ValidationException("Le nom du zombie ne peut pas être vide");
        }
        if (zombieModel.getPoint_de_vie() <= 0) {
            logger.error("Tentative de création d'un zombie avec des points de vie négatifs: {}", zombieModel.getPoint_de_vie());
            throw new ValidationException("Les points de vie doivent être positifs");
        }
        if (zombieModel.getAttaque_par_seconde() <= 0) {
            logger.error("Tentative de création d'un zombie avec une attaque par seconde négative: {}", zombieModel.getAttaque_par_seconde());
            throw new ValidationException("L'attaque par seconde doit être positive");
        }
        if (zombieModel.getDegat_attaque() <= 0) {
            logger.error("Tentative de création d'un zombie avec des dégâts d'attaque négatifs: {}", zombieModel.getDegat_attaque());
            throw new ValidationException("Les dégâts d'attaque doivent être positifs");
        }
        if (zombieModel.getVitesse_de_deplacement() <= 0) {
            logger.error("Tentative de création d'un zombie avec une vitesse de déplacement négative: {}", zombieModel.getVitesse_de_deplacement());
            throw new ValidationException("La vitesse de déplacement doit être positive");
        }
        if (zombieModel.getId_map() == null) {
            logger.error("Tentative de création d'un zombie sans id de map");
            throw new ValidationException("L'id de la map ne peut pas être null");
        }
        if (zombieModel.getChemin_image() == null || zombieModel.getChemin_image().trim().isEmpty()) {
            logger.error("Tentative de création d'un zombie avec un chemin d'image vide");
            throw new ValidationException("Le chemin de l'image ne peut pas être vide");
        }
        
        try {
            mapService.getMap(zombieModel.getId_map());
        } catch (NotFoundException e) {
            logger.error("Tentative de création d'un zombie avec une map inexistante: {}", zombieModel.getId_map());
            throw new ValidationException("La map avec l'id " + zombieModel.getId_map() + " n'existe pas");
        }
        
        zombieRepository.createZombie(zombieModel);
        logger.info("Zombie créé avec succès: {}", zombieModel);
    }

    /**
     * Récupère un zombie par son identifiant
     * @param id_zombie l'identifiant du zombie
     * @return le zombie correspondant
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si le zombie n'existe pas
     */
    @Override
    public ZombieModel getZombie(Long id_zombie) {
        logger.debug("Récupération du zombie avec l'id: {}", id_zombie);
        if (id_zombie == null) {
            logger.error("Tentative de récupération d'un zombie avec un id null");
            throw new ValidationException("L'id du zombie ne peut pas être null");
        }
        ZombieModel zombie = zombieRepository.getZombie(id_zombie);
        if (zombie == null) {
            logger.error("Zombie non trouvé avec l'id: {}", id_zombie);
            throw new NotFoundException("Le zombie avec l'id " + id_zombie + " n'existe pas");
        }
        logger.debug("Zombie trouvé: {}", zombie);
        return zombie;
    }

    /**
     * Récupère tous les zombies
     * @return la liste de tous les zombies
     */
    @Override
    public List<ZombieModel> getAllZombies() {
        logger.debug("Récupération de tous les zombies");
        List<ZombieModel> zombies = zombieRepository.getAllZombies();
        logger.debug("{} zombies trouvés", zombies.size());
        return zombies;
    }

    /**
     * Récupère tous les zombies d'une map
     * @param id_map l'identifiant de la map
     * @return la liste des zombies de la map
     * @throws ValidationException si l'identifiant de la map est null ou si la map n'existe pas
     */
    @Override
    public List<ZombieModel> getZombiesByMapId(Long id_map) {
        logger.debug("Récupération des zombies de la map avec l'id: {}", id_map);
        if (id_map == null) {
            logger.error("Tentative de récupération des zombies avec un id de map null");
            throw new ValidationException("L'id de la map ne peut pas être null");
        }
        try {
            mapService.getMap(id_map);
        } catch (NotFoundException e) {
            logger.error("Tentative de récupération des zombies d'une map inexistante: {}", id_map);
            throw new ValidationException("La map avec l'id " + id_map + " n'existe pas");
        }
        List<ZombieModel> zombies = zombieRepository.getZombiesByMapId(id_map);
        logger.debug("{} zombies trouvés pour la map {}", zombies.size(), id_map);
        return zombies;
    }

    /**
     * Met à jour un zombie existant
     * @param zombieModel le zombie avec les nouvelles données
     * @throws ValidationException si les données sont invalides
     * @throws NotFoundException si le zombie n'existe pas
     */
    @Override
    public void updateZombie(ZombieModel zombieModel) {
        if (zombieModel == null) {
            logger.error("Tentative de mise à jour d'un zombie null");
            throw new ValidationException("Le zombie ne peut pas être null");
        }

        ZombieModel existingZombie = zombieRepository.getZombie(zombieModel.getId_zombie());
        if (existingZombie == null) {
            logger.error("Tentative de mise à jour d'un zombie inexistant avec l'id: {}", zombieModel.getId_zombie());
            throw new NotFoundException("Le zombie avec l'id " + zombieModel.getId_zombie() + " n'existe pas");
        }

        if (zombieModel.getNom() != null) {
            String nom = zombieModel.getNom().trim();
            if (nom.isEmpty()) {
                logger.error("Tentative de mise à jour d'un zombie avec un nom vide");
                throw new ValidationException("Le nom du zombie ne peut pas être vide");
            }
            if (nom.length() > 50) {
                logger.error("Tentative de mise à jour d'un zombie avec un nom trop long");
                throw new ValidationException("Le nom du zombie ne peut pas dépasser 50 caractères");
            }
            existingZombie.setNom(nom);
        }

        if (zombieModel.getPoint_de_vie() != null) {
            if (zombieModel.getPoint_de_vie() <= 0) {
                logger.error("Tentative de mise à jour d'un zombie avec des points de vie invalides: {}", zombieModel.getPoint_de_vie());
                throw new ValidationException("Les points de vie doivent être strictement positifs");
            }
            if (zombieModel.getPoint_de_vie() > 1000) {
                logger.error("Tentative de mise à jour d'un zombie avec des points de vie trop élevés: {}", zombieModel.getPoint_de_vie());
                throw new ValidationException("Les points de vie ne peuvent pas dépasser 1000");
            }
            existingZombie.setPoint_de_vie(zombieModel.getPoint_de_vie());
        }

        if (zombieModel.getAttaque_par_seconde() != null) {
            if (zombieModel.getAttaque_par_seconde() <= 0) {
                logger.error("Tentative de mise à jour d'un zombie avec une attaque par seconde invalide: {}", zombieModel.getAttaque_par_seconde());
                throw new ValidationException("L'attaque par seconde doit être strictement positive");
            }
            if (zombieModel.getAttaque_par_seconde() > 10) {
                logger.error("Tentative de mise à jour d'un zombie avec une attaque par seconde trop élevée: {}", zombieModel.getAttaque_par_seconde());
                throw new ValidationException("L'attaque par seconde ne peut pas dépasser 10");
            }
            existingZombie.setAttaque_par_seconde(zombieModel.getAttaque_par_seconde());
        }

        if (zombieModel.getDegat_attaque() != null) {
            if (zombieModel.getDegat_attaque() <= 0) {
                logger.error("Tentative de mise à jour d'un zombie avec des dégâts d'attaque invalides: {}", zombieModel.getDegat_attaque());
                throw new ValidationException("Les dégâts d'attaque doivent être strictement positifs");
            }
            if (zombieModel.getDegat_attaque() > 100) {
                logger.error("Tentative de mise à jour d'un zombie avec des dégâts d'attaque trop élevés: {}", zombieModel.getDegat_attaque());
                throw new ValidationException("Les dégâts d'attaque ne peuvent pas dépasser 100");
            }
            existingZombie.setDegat_attaque(zombieModel.getDegat_attaque());
        }

        if (zombieModel.getVitesse_de_deplacement() != null) {
            if (zombieModel.getVitesse_de_deplacement() <= 0) {
                logger.error("Tentative de mise à jour d'un zombie avec une vitesse de déplacement invalide: {}", zombieModel.getVitesse_de_deplacement());
                throw new ValidationException("La vitesse de déplacement doit être strictement positive");
            }
            if (zombieModel.getVitesse_de_deplacement() > 5) {
                logger.error("Tentative de mise à jour d'un zombie avec une vitesse de déplacement trop élevée: {}", zombieModel.getVitesse_de_deplacement());
                throw new ValidationException("La vitesse de déplacement ne peut pas dépasser 5");
            }
            existingZombie.setVitesse_de_deplacement(zombieModel.getVitesse_de_deplacement());
        }

        if (zombieModel.getChemin_image() != null) {
            String cheminImage = zombieModel.getChemin_image().trim();
            if (cheminImage.isEmpty()) {
                logger.error("Tentative de mise à jour d'un zombie avec un chemin d'image vide");
                throw new ValidationException("Le chemin de l'image ne peut pas être vide");
            }
            if (!cheminImage.matches("^[a-zA-Z0-9_\\-\\.]+\\.(png|jpg|jpeg|gif)$")) {
                logger.error("Tentative de mise à jour d'un zombie avec un chemin d'image invalide: {}", cheminImage);
                throw new ValidationException("Le chemin de l'image doit être un nom de fichier valide avec l'extension .png, .jpg, .jpeg ou .gif");
            }
            existingZombie.setChemin_image(cheminImage);
        }

        if (zombieModel.getId_map() != null) {
            try {
                MapModel map = mapService.getMap(zombieModel.getId_map());
                if (map == null) {
                    logger.error("Tentative de mise à jour d'un zombie avec une map inexistante: {}", zombieModel.getId_map());
                    throw new ValidationException("La map avec l'id " + zombieModel.getId_map() + " n'existe pas");
                }
                existingZombie.setId_map(zombieModel.getId_map());
            } catch (NotFoundException e) {
                logger.error("Tentative de mise à jour d'un zombie avec une map inexistante: {}", zombieModel.getId_map());
                throw new ValidationException("La map avec l'id " + zombieModel.getId_map() + " n'existe pas");
            }
        }

        zombieRepository.updateZombie(existingZombie);
        logger.info("Zombie mis à jour avec succès: {}", existingZombie);
    }

    /**
     * Supprime un zombie
     * @param id_zombie l'identifiant du zombie à supprimer
     * @throws ValidationException si l'identifiant est null
     * @throws NotFoundException si le zombie n'existe pas
     */
    @Override
    public void deleteZombie(Long id_zombie) {
        logger.debug("Suppression du zombie avec l'id: {}", id_zombie);
        if (id_zombie == null) {
            logger.error("Tentative de suppression d'un zombie avec un id null");
            throw new ValidationException("L'id du zombie ne peut pas être null");
        }
        
        ZombieModel existingZombie = zombieRepository.getZombie(id_zombie);
        if (existingZombie == null) {
            logger.error("Tentative de suppression d'un zombie inexistant avec l'id: {}", id_zombie);
            throw new NotFoundException("Le zombie avec l'id " + id_zombie + " n'existe pas");
        }
        
        zombieRepository.deleteZombie(id_zombie);
        logger.info("Zombie supprimé avec succès: {}", existingZombie);
    }
}
