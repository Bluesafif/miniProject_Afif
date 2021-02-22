package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pabrik.mindomie.model.Ekspedisi;

import java.util.List;
import java.util.UUID;

@Repository("EkspedisiRepository")
public class EkspedisiRepositoryImpl implements EkspedisiRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEkspedisi(Ekspedisi ekspedisi) {
        String uuid = String.valueOf(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO ekspedisi (idEkspedisi, namaEkspedisi, hargaEkspedisi, statusEkspedisi) VALUES (?,?,?,1)",
                uuid, ekspedisi.getNamaEkspedisi(), ekspedisi.getHargaEkspedisi());
    }

    @Override
    public void updateEkspedisi(Ekspedisi ekspedisi) {
        jdbcTemplate.update("UPDATE ekspedisi SET namaEkspedisi =?, hargaEkspedisi=? WHERE idEkspedisi=?",
                ekspedisi.getNamaEkspedisi(), ekspedisi.getHargaEkspedisi(), ekspedisi.getIdEkspedisi());
    }

    @Override
    public List<Ekspedisi> findAll(String paginationSelect) {
        List<Ekspedisi> ekspedisiList;
        ekspedisiList =  jdbcTemplate.query("SELECT * FROM ekspedisi "+paginationSelect,
                (rs, rowNum)->
                        new Ekspedisi(
                                rs.getString("idEkspedisi"),
                                rs.getString("namaEkspedisi"),
                                rs.getInt("hargaEkspedisi"),
                                rs.getBoolean("statusEkspedisi")
                        )
        );
        return ekspedisiList;
    }

    @Override
    public Ekspedisi findById(String idEkspedisi) {
        return jdbcTemplate.queryForObject("SELECT * FROM ekspedisi WHERE idEkspedisi="+idEkspedisi+"",
                (rs, rowNum)->
                        new Ekspedisi(
                                rs.getString("idEkspedisi"),
                                rs.getString("namaEkspedisi"),
                                rs.getInt("hargaEkspedisi"),
                                rs.getBoolean("statusEkspedisi")
                        )
        );
    }

    @Override
    public boolean isEkspedisiExist(String namaEkspedisi) {
        String query = "SELECT COUNT(*) FROM ekspedisi WHERE namaEkspedisi =?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, namaEkspedisi);
        return count > 0;
    }

    @Override
    public List<Ekspedisi> findAllAvailable() {
        return jdbcTemplate.query("SELECT * FROM ekspedisi WHERE statusEkspedisi=1",
                (rs, rowNum)->
                        new Ekspedisi(
                                rs.getString("idEkspedisi"),
                                rs.getString("namaEkspedisi"),
                                rs.getInt("hargaEkspedisi"),
                                rs.getBoolean("statusEkspedisi")
                        )
        );
    }

    @Override
    public void status(Ekspedisi ekspedisi) {
        jdbcTemplate.update("UPDATE ekspedisi SET statusEkspedisi=? WHERE idEkspedisi=?",
                ekspedisi.isStatusEkspedisi(), ekspedisi.getIdEkspedisi());
    }
}
