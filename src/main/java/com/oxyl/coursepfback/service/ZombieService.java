package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZombieService implements ZombieServiceInterface {

    private final ZombieRepository zombieRepository;

    @Autowired
    public ZombieService(ZombieRepository zombieRepository) {
        this.zombieRepository = zombieRepository;
    }

    @Override
    public void createZombie(ZombieModel zombieModel) {
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
        zombieRepository.updateZombie(zombieModel);
    }

    @Override
    public void deleteZombie(Long id_zombie) {
        zombieRepository.deleteZombie(id_zombie);
    }


}
