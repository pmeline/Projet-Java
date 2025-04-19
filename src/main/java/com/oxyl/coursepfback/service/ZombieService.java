package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.MapModel;
import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZombieService implements ZombieServiceInterface {

    private final ZombieRepository zombieRepository;
    private final MapService mapService;

    @Autowired
    public ZombieService(ZombieRepository zombieRepository, MapService mapService) {
        this.zombieRepository = zombieRepository;
        this.mapService = mapService;
    }

    @Override
    public void createZombie(ZombieModel zombieModel) {
        if (zombieModel.getId_map() != null) {
            MapModel map = mapService.getMap(zombieModel.getId_map());
            if (map == null) {
                throw new IllegalArgumentException("La map avec l'id " + zombieModel.getId_map() + " n'existe pas.");
            }
        }
        zombieRepository.createZombie(zombieModel);
    }

    @Override
    public ZombieModel getZombie(Long id_zombie) {
        return zombieRepository.getZombie(id_zombie);
    }

    @Override
    public List<ZombieModel> getAllZombies() {
        return zombieRepository.getAllZombies();
    }

    @Override
    public void updateZombie(ZombieModel zombieModel) {
        if (zombieModel.getId_map() != null) {
            MapModel map = mapService.getMap(zombieModel.getId_map());
            if (map == null) {
                throw new IllegalArgumentException("La map avec l'id " + zombieModel.getId_map() + " n'existe pas.");
            }
        }
        zombieRepository.updateZombie(zombieModel);
    }

    @Override
    public void deleteZombie(Long id_zombie) {
        zombieRepository.deleteZombie(id_zombie);
    }


}
