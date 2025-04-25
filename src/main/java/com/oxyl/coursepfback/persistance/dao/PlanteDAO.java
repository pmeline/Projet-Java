package com.oxyl.coursepfback.persistance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.oxyl.coursepfback.persistance.entity.PlanteEntity;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

@Repository
public class PlanteDAO implements PlanteDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlanteEntity> getAllPlantes() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, new PlanteRowMapper());
    }

    @Override
    public PlanteEntity getPlante(Long id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        return jdbcTemplate.queryForObject(sql, new PlanteRowMapper(), id);
    }


    @Override
    public void createPlante(PlanteEntity plante) {
        String sql = "INSERT INTO plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPoint_de_vie(),
                plante.getAttaque_par_seconde(),
                plante.getDegat_attaque(),
                plante.getCout(),
                plante.getSoleil_par_seconde(),
                plante.getEffet(),
                plante.getChemin_image());
    }

    @Override
    public void deletePlante(Long id_plante) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id_plante);
    }

    @Override
    public void updatePlante(PlanteEntity plante) {
        String sql = "UPDATE plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ?, nom = ? WHERE id_plante = ?";
        int rowsAffected = jdbcTemplate.update(sql, 
            plante.getPoint_de_vie(), 
            plante.getAttaque_par_seconde(), 
            plante.getDegat_attaque(), 
            plante.getCout(), 
            plante.getSoleil_par_seconde(), 
            plante.getEffet(), 
            plante.getChemin_image(), 
            plante.getNom(), 
            plante.getId_plante()
        );
        
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("La plante avec l'id " + plante.getId_plante() + " n'existe pas");
        }
    }



    private static class PlanteRowMapper implements RowMapper<PlanteEntity> {
        @Override
        public PlanteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlanteEntity plante = new PlanteEntity();
            plante.setId_plante(rs.getLong("id_plante"));
            plante.setNom(rs.getString("nom"));
            plante.setPoint_de_vie(rs.getInt("point_de_vie"));
            plante.setAttaque_par_seconde(rs.getDouble("attaque_par_seconde"));
            plante.setDegat_attaque(rs.getInt("degat_attaque"));
            plante.setCout(rs.getInt("cout"));
            plante.setSoleil_par_seconde(rs.getDouble("soleil_par_seconde"));
            plante.setEffet(rs.getString("effet"));
            plante.setChemin_image(rs.getString("chemin_image"));
            return plante;
        }
    }
}
