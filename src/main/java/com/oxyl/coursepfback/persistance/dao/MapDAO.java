package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.MapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MapDAO implements MapDAOInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MapEntity> getAllMaps() {
        String sql = "SELECT * FROM map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }
    @Override
    public MapEntity getMap(Long id) {
        String sql = "SELECT * FROM map WHERE id_map = ?";
        return jdbcTemplate.queryForObject(sql, new MapRowMapper(), id);
    }

    @Override
    public void createMap(MapEntity map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getChemin_image());
    }

    @Override
    public void updateMap(MapEntity map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getChemin_image(), map.getId_map());
    }

    @Override
    public void deleteMap(Long id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update("UPDATE zombie SET id_map = NULL WHERE id_map = ?", id);
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                // Aucune ligne supprimée - l'ID n'existe peut-être pas
                throw new RuntimeException("Aucune map trouvée avec l'ID: " + id);
            }
        } catch (Exception e) {
            // Capturer l'exception précise (DataIntegrityViolationException pour les contraintes)
            throw new RuntimeException("Erreur lors de la suppression de la map: " + e.getMessage(), e);
        }
    }

    private static class MapRowMapper implements RowMapper<MapEntity> {
        @Override
        public MapEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MapEntity map = new MapEntity();
            map.setId_map(rs.getLong("id_map"));
            map.setLigne(rs.getInt("ligne"));
            map.setColonne(rs.getInt("colonne"));
            map.setChemin_image(rs.getString("chemin_image"));
            return map;
        }
    }
}
