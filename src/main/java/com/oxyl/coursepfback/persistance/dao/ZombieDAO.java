package com.oxyl.coursepfback.persistance.dao;

import com.oxyl.coursepfback.persistance.entity.ZombieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO (Data Access Object) pour la gestion des zombies dans la base de données
 */
@Repository
public class ZombieDAO implements ZombieDAOInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructeur avec injection de dépendance
     * @param jdbcTemplate le template JDBC pour les opérations de base de données
     */
    @Autowired
    public ZombieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Récupère tous les zombies de la base de données
     * @return la liste de tous les zombies
     */
    @Override
    public List<ZombieEntity> getAllZombies() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, new ZombieRowMapper());
    }

    /**
     * Récupère un zombie par son identifiant
     * @param id l'identifiant du zombie à récupérer
     * @return le zombie correspondant ou null si non trouvé
     */
    @Override
    public ZombieEntity getZombie(Long id) {
        String sql = "SELECT * FROM zombie WHERE id_zombie = ?";
        List<ZombieEntity> zombies = jdbcTemplate.query(sql, new ZombieRowMapper(), id);
        return zombies.isEmpty() ? null : zombies.get(0);
    }

    /**
     * Récupère tous les zombies associés à une map
     * @param id_map l'identifiant de la map
     * @return la liste des zombies de la map
     */
    @Override
    public List<ZombieEntity> getZombiesByMapId(Long id_map) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql, new ZombieRowMapper(), id_map);
    }

    /**
     * Crée un nouveau zombie dans la base de données
     * @param zombie le zombie à créer
     */
    @Override
    public void createZombie(ZombieEntity zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPoint_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map());
    }

    /**
     * Met à jour un zombie existant dans la base de données
     * @param zombie le zombie à mettre à jour
     * @throws IllegalArgumentException si le zombie n'existe pas
     */
    @Override
    public void updateZombie(ZombieEntity zombie) {
        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPoint_de_vie(),
                zombie.getAttaque_par_seconde(),
                zombie.getDegat_attaque(),
                zombie.getVitesse_de_deplacement(),
                zombie.getChemin_image(),
                zombie.getId_map(),
                zombie.getId_zombie()
        );
        
        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Le zombie avec l'id " + zombie.getId_zombie() + " n'existe pas");
        }
    }

    /**
     * Supprime un zombie de la base de données
     * @param id l'identifiant du zombie à supprimer
     */
    @Override
    public void deleteZombie(Long id) {
        String sql = "DELETE FROM zombie WHERE id_zombie = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Classe interne pour mapper les résultats de la base de données vers des objets ZombieEntity
     */
    private static class ZombieRowMapper implements RowMapper<ZombieEntity> {
        /**
         * Convertit une ligne de résultat SQL en objet ZombieEntity
         * @param rs le ResultSet contenant les données
         * @param rowNum le numéro de la ligne
         * @return un objet ZombieEntity
         * @throws SQLException en cas d'erreur SQL
         */
        @Override
        public ZombieEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ZombieEntity zombie = new ZombieEntity();
            zombie.setId_zombie(rs.getLong("id_zombie"));
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
