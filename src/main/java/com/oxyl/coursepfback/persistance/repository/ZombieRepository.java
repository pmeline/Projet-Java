package com.oxyl.coursepfback.persistance.repository;

import com.oxyl.coursepfback.model.ZombieModel;
import com.oxyl.coursepfback.persistance.dao.ZombieDAO;
import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import com.oxyl.coursepfback.persistance.entity.ZombieEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ZombieRepository {

    private final ZombieDAO zombieDAO;
    private final ZombieEntityMapper mapper;

    @Autowired
    public ZombieRepository(ZombieDAO zombieDAO, ZombieEntityMapper mapper) {
        this.zombieDAO = zombieDAO;
        this.mapper = mapper;
    }

    public List<ZombieModel> getAllZombies() {
        return mapper.mapListEntityToModel(zombieDAO.getAllZombies());
    }

    public ZombieModel getZombie(Long id_zombie) {
        return mapper.mapEntityToModel(zombieDAO.getZombie(id_zombie));
    }

    public List<ZombieModel> getZombiesByMapId(Long id_map) {
        return mapper.mapListEntityToModel(zombieDAO.getZombiesByMapId(id_map));
    }

    public void createZombie(ZombieModel zombieModel) {
        ZombieEntity zombieEntity = mapper.mapModelToEntity(zombieModel);
        zombieDAO.createZombie(zombieEntity);
    }

    public void updateZombie(ZombieModel zombieModel) {
        ZombieEntity zombieEntity = mapper.mapModelToEntity(zombieModel);
        zombieDAO.updateZombie(zombieEntity);
    }

    public void deleteZombie(Long id_zombie) {
        zombieDAO.deleteZombie(id_zombie);
    }

}
