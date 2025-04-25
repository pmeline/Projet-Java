package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZombieService implements ZombieServiceInterface {

    private final ZombieRepository zombieRepository;
    private final MapService mapService;

    @Autowired
    public ZombieService(ZombieRepository zombieRepository, @Lazy MapService mapService) {
        this.zombieRepository = zombieRepository;
        this.mapService = mapService;
    }

    @Override
    public void createZombie(ZombieModel zombieModel) {
        if (zombieModel == null) {
            throw new IllegalArgumentException("Le zombie ne peut pas être null");
        }
        if (zombieModel.getNom() == null || zombieModel.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du zombie ne peut pas être vide");
        }
        if (zombieModel.getPoint_de_vie() <= 0) {
            throw new IllegalArgumentException("Les points de vie doivent être positifs");
        }
        if (zombieModel.getAttaque_par_seconde() <= 0) {
            throw new IllegalArgumentException("L'attaque par seconde doit être positive");
        }
        if (zombieModel.getDegat_attaque() <= 0) {
            throw new IllegalArgumentException("Les dégâts d'attaque doivent être positifs");
        }
        if (zombieModel.getVitesse_de_deplacement() <= 0) {
            throw new IllegalArgumentException("La vitesse de déplacement doit être positive");
        }
        if (zombieModel.getId_map() == null) {
            throw new IllegalArgumentException("L'id de la map ne peut pas être null");
        }
        
        // Vérifie si la map existe
        mapService.getMap(zombieModel.getId_map());
        
        zombieRepository.createZombie(zombieModel);
    }

    @Override
    public ZombieModel getZombie(Long id_zombie) {
        if (id_zombie == null) {
            throw new IllegalArgumentException("L'id du zombie ne peut pas être null");
        }
        ZombieModel zombie = zombieRepository.getZombie(id_zombie);
        if (zombie == null) {
            throw new IllegalArgumentException("Le zombie avec l'id " + id_zombie + " n'existe pas");
        }
        return zombie;
    }

    @Override
    public List<ZombieModel> getAllZombies() {
        return zombieRepository.getAllZombies();
    }

    @Override
    public List<ZombieModel> getZombiesByMapId(Long id_map) {
        if (id_map == null) {
            throw new IllegalArgumentException("L'id de la map ne peut pas être null");
        }
        // Vérifie si la map existe
        mapService.getMap(id_map);
        
        List<ZombieModel> zombies = zombieRepository.getZombiesByMapId(id_map);
        if (zombies == null) {
            throw new IllegalArgumentException("Erreur lors de la récupération des zombies pour la map " + id_map);
        }
        return zombies;
    }

    @Override
    public void updateZombie(ZombieModel zombieModel) {
        if (zombieModel == null) {
            throw new IllegalArgumentException("Le zombie ne peut pas être null");
        }
        
        // Récupère le zombie existant
        ZombieModel existingZombie = getZombie(zombieModel.getId_zombie());
        
        // Met à jour uniquement les champs non null
        if (zombieModel.getNom() != null && !zombieModel.getNom().trim().isEmpty()) {
            existingZombie.setNom(zombieModel.getNom());
        }
        if (zombieModel.getPoint_de_vie() != null) {
            if (zombieModel.getPoint_de_vie() <= 0) {
                throw new IllegalArgumentException("Les points de vie doivent être positifs");
            }
            existingZombie.setPoint_de_vie(zombieModel.getPoint_de_vie());
        }
        if (zombieModel.getAttaque_par_seconde() != null) {
            if (zombieModel.getAttaque_par_seconde() <= 0) {
                throw new IllegalArgumentException("L'attaque par seconde doit être positive");
            }
            existingZombie.setAttaque_par_seconde(zombieModel.getAttaque_par_seconde());
        }
        if (zombieModel.getDegat_attaque() != null) {
            if (zombieModel.getDegat_attaque() <= 0) {
                throw new IllegalArgumentException("Les dégâts d'attaque doivent être positifs");
            }
            existingZombie.setDegat_attaque(zombieModel.getDegat_attaque());
        }
        if (zombieModel.getVitesse_de_deplacement() != null) {
            if (zombieModel.getVitesse_de_deplacement() <= 0) {
                throw new IllegalArgumentException("La vitesse de déplacement doit être positive");
            }
            existingZombie.setVitesse_de_deplacement(zombieModel.getVitesse_de_deplacement());
        }
        if (zombieModel.getId_map() != null) {
            existingZombie.setId_map(zombieModel.getId_map());
        }
        
        zombieRepository.updateZombie(existingZombie);
    }

    @Override
    public void deleteZombie(Long id_zombie) {
        if (id_zombie == null) {
            throw new IllegalArgumentException("L'id du zombie ne peut pas être null");
        }
        
        // Vérifie si le zombie existe
        ZombieModel existingZombie = zombieRepository.getZombie(id_zombie);
        if (existingZombie == null) {
            throw new IllegalArgumentException("Le zombie avec l'id " + id_zombie + " n'existe pas");
        }
        
        zombieRepository.deleteZombie(id_zombie);
    }
}
