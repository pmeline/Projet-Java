package com.oxyl.coursepfback.PERSISTANCE.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.oxyl.coursepfback.PERSISTANCE.DAO.PlanteEntity;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import com.oxyl.coursepfback.MODEL.PlanteModel;

@Repository
public class PlanteDAO implements PlanteDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PlanteEntity> getAllPlantes() {
        String sql = "select * from plante";
        return jdbcTemplate.query(sql, new PlanteRowMapper());
    }

    public PlanteEntity getById(Long id) {
        String sql = "select * from plante where id = ?";
        List<PlanteEntity> plantes = jdbcTemplate.query(sql, new PlanteRowMapper(), id);
        return plantes.isEmpty() ? null : plantes.get(0);
    }

    public void createPlante(PlanteEntity plante) {
        String sql = "INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, plante.getNom(), plante.getPoint_de_vie(), plante.getAttaque_par_seconde(), plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(), plante.getChemin_image());
    }

    public PlanteEntity readPlante(Long id) {
        String sql = "SELECT * FROM Plante WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PlanteRowMapper(), id);
    }

    public void deletePlante(Long id) {
        String sql = "DELETE FROM Plante WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updatePlante(PlanteEntity plante) {
        String sql = "UPDATE Plante SET point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE nom = ?";
        jdbcTemplate.update(sql, plante.getPoint_de_vie(), plante.getAttaque_par_seconde(), plante.getDegat_attaque(), plante.getCout(), plante.getSoleil_par_seconde(), plante.getEffet(), plante.getChemin_image(), plante.getNom());
    }


    private static class PlanteRowMapper implements RowMapper<PlanteEntity> {
        @Override
        public PlanteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlanteEntity plante = new PlanteEntity();
            plante.setId(rs.getLong("id"));
            plante.setNom(rs.getString("nom"));
            plante.setPoint_de_vie(rs.getInt("point_de_vie"));
            plante.setAttaque_par_seconde(rs.getFloat("attaque_par_seconde"));
            plante.setDegat_attaque(rs.getInt("degat_attaque"));
            plante.setCout(rs.getInt("cout"));
            plante.setSoleil_par_seconde(rs.getFloat("soleil_par_seconde"));
            plante.setEffet(rs.getString("effet"));
            plante.setChemin_image(rs.getString("chemin_image"));
            return plante;
        }
    }
}
