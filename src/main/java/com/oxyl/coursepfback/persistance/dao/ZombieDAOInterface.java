package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZombieDAOInterface {
    void createZombie(ZombieEntity zombie);
    void updateZombie(ZombieEntity zombie);
    void deleteZombie(Long id);
    ZombieEntity getZombie(Long id);
    List<ZombieEntity> getAllZombies();
}
