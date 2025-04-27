package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.MapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO (Data Access Object) pour la gestion des maps dans la base de données
 */
@Repository
public class MapDAO implements MapDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructeur avec injection de dépendance
     * @param jdbcTemplate le template JDBC pour les opérations de base de données
     */
    @Autowired
    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Récupère toutes les maps de la base de données
     * @return la liste de toutes les maps
     */
    @Override
    public List<MapEntity> getAllMaps() {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }

    /**
     * Récupère une map par son identifiant
     * @param id l'identifiant de la map à récupérer
     * @return la map correspondante ou null si non trouvée
     */
    @Override
    public MapEntity getMap(Long id) {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM map WHERE id_map = ?";
        List<MapEntity> maps = jdbcTemplate.query(sql, new MapRowMapper(), id);
        return maps.isEmpty() ? null : maps.get(0);
    }

    /**
     * Crée une nouvelle map dans la base de données
     * @param map la map à créer
     */
    @Override
    public void createMap(MapEntity map) {
        String sql = "INSERT INTO map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
            map.getLigne(),
            map.getColonne(),
            map.getChemin_image()
        );
    }

    /**
     * Met à jour une map existante dans la base de données
     * @param map la map à mettre à jour
     */
    @Override
    public void updateMap(MapEntity map) {
        String sql = "UPDATE map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        jdbcTemplate.update(sql,
            map.getLigne(),
            map.getColonne(),
            map.getChemin_image(),
            map.getId_map()
        );
    }

    /**
     * Supprime une map de la base de données
     * @param id l'identifiant de la map à supprimer
     */
    @Override
    public void deleteMap(Long id) {
        String sql = "DELETE FROM map WHERE id_map = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Classe interne pour mapper les résultats de la base de données vers des objets MapEntity
     */
    private static class MapRowMapper implements RowMapper<MapEntity> {
        /**
         * Convertit une ligne de résultat SQL en objet MapEntity
         * @param rs le ResultSet contenant les données
         * @param rowNum le numéro de la ligne
         * @return un objet MapEntity
         * @throws SQLException en cas d'erreur SQL
         */
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
