package com.oxyl.coursepfback.persistance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.oxyl.coursepfback.persistance.entity.PlanteEntity;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

/**
 * DAO (Data Access Object) pour la gestion des plantes dans la base de données
 */
@Repository
public class PlanteDAO implements PlanteDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructeur avec injection de dépendance
     * @param jdbcTemplate le template JDBC pour les opérations de base de données
     */
    @Autowired
    public PlanteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Récupère toutes les plantes de la base de données
     * @return la liste de toutes les plantes
     */
    @Override
    public List<PlanteEntity> getAllPlantes() {
        String sql = "SELECT * FROM plante";
        return jdbcTemplate.query(sql, new PlanteRowMapper());
    }

    /**
     * Récupère une plante par son identifiant
     * @param id l'identifiant de la plante à récupérer
     * @return la plante correspondante
     */
    @Override
    public PlanteEntity getPlante(Long id) {
        String sql = "SELECT * FROM plante WHERE id_plante = ?";
        return jdbcTemplate.queryForObject(sql, new PlanteRowMapper(), id);
    }

    /**
     * Crée une nouvelle plante dans la base de données
     * @param plante la plante à créer
     */
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

    /**
     * Supprime une plante de la base de données
     * @param id_plante l'identifiant de la plante à supprimer
     */
    @Override
    public void deletePlante(Long id_plante) {
        String sql = "DELETE FROM plante WHERE id_plante = ?";
        jdbcTemplate.update(sql, id_plante);
    }

    /**
     * Met à jour une plante existante dans la base de données
     * @param plante la plante à mettre à jour
     * @throws IllegalArgumentException si la plante n'existe pas
     */
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

    /**
     * Classe interne pour mapper les résultats de la base de données vers des objets PlanteEntity
     */
    private static class PlanteRowMapper implements RowMapper<PlanteEntity> {
        /**
         * Convertit une ligne de résultat SQL en objet PlanteEntity
         * @param rs le ResultSet contenant les données
         * @param rowNum le numéro de la ligne
         * @return un objet PlanteEntity
         * @throws SQLException en cas d'erreur SQL
         */
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
