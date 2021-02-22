package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
//    public List<Packaging> findAll(int page, int limit) {
//        int numPages;
//        numPages =jdbcTemplate.query("SELECT COUNT(*) AS count FROM packaging",
//                (rs, rowNum)->
//                        rs.getInt("count")).get(0);
//
//        //validatePage
//        if (page < 1) page = 1;
//        if (page > numPages) page = numPages;
//
//        int start = (page - 1) * limit;
        List<Packaging> packagingList;
        packagingList = jdbcTemplate.query("SELECT * FROM packaging",
//        packagingList = jdbcTemplate.query("SELECT * FROM packaging"+start+","+limit+"",
                (rs, rowNum)->
                        new Packaging(
                                rs.getString("idPackaging"),
                                rs.getString("namaPackaging"),
                                rs.getInt("hargaPackaging"),
                                rs.getBoolean("statusPackaging")
                        )
        );
        return packagingList;
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

    @Override
    public void status(Packaging packaging) {
        jdbcTemplate.update("UPDATE packaging SET statusPackaging=? WHERE idPackaging=?",
                packaging.isStatusPackaging(), packaging.getIdPackaging());
    }
}
