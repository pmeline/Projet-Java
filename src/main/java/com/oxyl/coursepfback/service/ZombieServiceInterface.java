package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.ZombieModel;

import java.util.List;

public interface ZombieServiceInterface {
    void createZombie(ZombieModel zombie);
    ZombieModel getZombie(Long id_zombie);
    List<ZombieModel> getAllZombies();
    List<ZombieModel> getZombiesByMapId(Long id_map);
    void updateZombie(ZombieModel zombie);
    void deleteZombie(Long id_zombie);
}
