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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MapEntity> getAllMaps() {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }

    @Override
    public MapEntity getMap(Long id) {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?";
        List<MapEntity> maps = jdbcTemplate.query(sql, new Object[]{id}, new int[]{java.sql.Types.BIGINT}, new MapRowMapper());
        return maps.isEmpty() ? null : maps.get(0);
    }

    @Override
    public void createMap(MapEntity map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
            map.getLigne(),
            map.getColonne(),
            map.getChemin_image()
        );
    }

    @Override
    public void updateMap(MapEntity map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        int rowsAffected = jdbcTemplate.update(sql,
            map.getLigne(),
            map.getColonne(),
            map.getChemin_image(),
            map.getId_map()
        );
        
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("La map avec l'id " + map.getId_map() + " n'existe pas");
        }
    }

    @Override
    public void deleteMap(Long id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, new Object[]{id}, new int[]{java.sql.Types.BIGINT});
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
