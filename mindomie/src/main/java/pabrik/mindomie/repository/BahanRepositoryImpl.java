package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pabrik.mindomie.model.Bahan;

import java.util.List;
import java.util.UUID;

@Repository("BahanRepository")
public class BahanRepositoryImpl implements BahanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBahan(Bahan bahan) {
        String uuid = String.valueOf(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO bahan (idBahan, namaBahan, hargaBahan, qty, statusBahan) VALUES (?,?,?,?,1)",
                uuid, bahan.getNamaBahan(), bahan.getHargaBahan(), bahan.getQty());
    }

    @Override
    public void updateBahan(Bahan bahan) {
        jdbcTemplate.update("UPDATE bahan SET namaBahan =?, hargaBahan=?, qty=?, statusBahan=? WHERE idBahan=?",
                bahan.getNamaBahan(), bahan.getHargaBahan(), bahan.getQty(), bahan.isStatusBahan(), bahan.getIdBahan());
    }

    @Override
    public List<Bahan> findAll() {
//    public List<Bahan> findAll(int page, int limit) {
//        int numPages;
//        numPages =jdbcTemplate.query("SELECT COUNT(*) AS count FROM bahan",
//                (rs, rowNum)->
//                        rs.getInt("count")).get(0);
//
//        //validatePage
//        if (page < 1) page = 1;
//        if (page > numPages) page = numPages;
//
//        int start = (page - 1) * limit;

        List<Bahan> bahanList;
        bahanList = jdbcTemplate.query("SELECT * FROM bahan",
//        bahanList = jdbcTemplate.query("SELECT * FROM bahan"+start+","+limit+"",
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getInt("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan")
                        )
        );
        return bahanList;
    }

    @Override
    public Bahan findById(String idBahan) {
        return jdbcTemplate.queryForObject("SELECT * FROM bahan WHERE idBahan='"+idBahan+"'",
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getInt("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan")
                        )
        );
    }

    @Override
    public boolean isBahanExist(String namaBahan) {
        String query = "SELECT COUNT(*) FROM bahan WHERE namaBahan =?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, namaBahan);
        return count > 0;
    }

    @Override
    public List<Bahan> findAllAvailable() {
        return jdbcTemplate.query("SELECT * FROM bahan WHERE statusBahan=1",
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getInt("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan")
                        )
        );
    }

    @Override
    public void status(Bahan bahan) {
        jdbcTemplate.update("UPDATE bahan SET statusBahan=? WHERE idBahan=?",
                bahan.isStatusBahan(), bahan.getIdBahan());
    }
}
