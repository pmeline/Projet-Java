package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ZombieDAO implements ZombieDAOInterface{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ZombieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ZombieEntity> getAllZombies(){
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, new ZombieRowMapper());
    }

    @Override
    public ZombieEntity getZombie(Long id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        List<ZombieEntity> zombies = jdbcTemplate.query(sql, new ZombieRowMapper(), id);
        return zombies.isEmpty() ? null : zombies.get(0);
    }

    @Override
    public List<ZombieEntity> getZombiesByMapId(Long id_map) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, new ZombieRowMapper(), id_map);
    }


    @Override
    public void createZombie(ZombieEntity zombie) {
        String sql = "INSERT INTO zombie ( nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getChemin_image(), zombie.getId_map());
    }

    @Override
    public void updateZombie(ZombieEntity zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        jdbcTemplate.update(sql, zombie.getNom(), zombie.getPoint_de_vie(), zombie.getAttaque_par_seconde(), zombie.getDegat_attaque(), zombie.getVitesse_de_deplacement(), zombie.getChemin_image(), zombie.getId_map(), zombie.getId());
    }

    @Override
    public void deleteZombie(Long id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ZombieRowMapper implements RowMapper<ZombieEntity> {
        @Override
        public ZombieEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ZombieEntity zombie = new ZombieEntity();
            zombie.setId(rs.getLong("id_zombie"));
            zombie.setNom(rs.getString("nom"));
            zombie.setPoint_de_vie(rs.getInt("point_de_vie"));
            zombie.setAttaque_par_seconde(rs.getDouble("attaque_par_seconde"));
            zombie.setDegat_attaque(rs.getInt("degat_attaque"));
            zombie.setVitesse_de_deplacement(rs.getDouble("vitesse_de_deplacement"));
            zombie.setChemin_image(rs.getString("chemin_image"));
            zombie.setId_map(rs.getLong("id_map"));
            return zombie;
        }
    }

}
