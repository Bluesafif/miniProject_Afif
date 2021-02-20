package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.model.Packaging;

import java.util.List;
import java.util.UUID;

@Repository("PackagingRepository")
public class PackagingRepositoryImpl implements PackagingRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void savePackaging(Packaging packaging) {
        String uuid = String.valueOf(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO packaging (idPackaging, namaPackaging, hargaPackaging, statusPackaging) VALUES (?,?,?,1)",
                uuid, packaging.getNamaPackaging(), packaging.getHargaPackaging());
    }

    @Override
    public void updatePackaging(Packaging packaging) {
        jdbcTemplate.update("UPDATE packaging SET namaPackaging =?, hargaPackaging=? WHERE idPackaging=?",
                packaging.getNamaPackaging(), packaging.getHargaPackaging(), packaging.getIdPackaging());
    }

    @Override
    public List<Packaging> findAll() {
        return jdbcTemplate.query("SELECT * FROM packaging",
                (rs, rowNum)->
                        new Packaging(
                                rs.getString("idPackaging"),
                                rs.getString("namaPackaging"),
                                rs.getInt("hargaPackaging"),
                                rs.getBoolean("statusPackaging")
                        )
        );
    }

    @Override
    public Packaging findById(String idPackaging) {
        return jdbcTemplate.queryForObject("SELECT * FROM packaging WHERE idPackaging="+idPackaging+"",
                (rs, rowNum)->
                        new Packaging(
                                rs.getString("idPackaging"),
                                rs.getString("namaPackaging"),
                                rs.getInt("hargaPackaging"),
                                rs.getBoolean("statusPackaging")
                        )
        );
    }

    @Override
    public boolean isPackagingExist(String namaPackaging) {
        String query = "SELECT COUNT(*) FROM packaging WHERE namaPackaging =?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, namaPackaging);
        return count > 0;
    }

    @Override
    public List<Packaging> findAllAvailable() {
        return jdbcTemplate.query("SELECT * FROM packaging WHERE statusPackaging=1",
                (rs, rowNum)->
                        new Packaging(
                                rs.getString("idPackaging"),
                                rs.getString("namaPackaging"),
                                rs.getInt("hargaPackaging"),
                                rs.getBoolean("statusPackaging")
                        )
        );
    }
}
