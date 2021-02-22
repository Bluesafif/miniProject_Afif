package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.model.Produksi;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("ProduksiRepository")
public class ProduksiRepositoryImpl implements ProduksiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveProduksi(Produksi produksi) {
        String uuid = String.valueOf(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO produksi (idBOP, tglTransaksi, totalKm, idEkspedisi, idPackaging, statusProduksi) VALUES (?,?,?,?,?,0)",
                uuid, produksi.getTglTransaksi(), produksi.getTotalKm(), produksi.getIdEkspedisi(), produksi.getIdPackaging());
        for (Bahan bahan : produksi.getBahanList()){
            String uuid2 = String.valueOf(UUID.randomUUID());
            jdbcTemplate.update("INSERT INTO produksiDetail (idDetail, idBOP, idBahan, qtyPemakaian) VALUES (?,?,?,?)",
                    uuid2, uuid, bahan.getIdBahan(), bahan.getQtyPemakaian());
            jdbcTemplate.update("UPDATE bahan a JOIN produksiDetail b SET a.qty=a.qty-b.qtyPemakaian WHERE a.idBahan=? AND b.idDetail=?",
                    bahan.getIdBahan(), uuid2);
        }
    }

    @Override
    public void updateProduksi(Produksi produksi) {
        jdbcTemplate.update("DELETE FROM produksiDetail WHERE idBOP=?", produksi.getIdBOP());

        for (Bahan bahan : produksi.getBahanList()){
            String uuid = String.valueOf(UUID.randomUUID());
            jdbcTemplate.update("INSERT INTO produksiDetail (idDetail, idBOP, idBahan, qtyPemakaian) VALUES (?,?,?)",
                    uuid, produksi.getIdBOP(), bahan.getIdBahan(), bahan.getQtyPemakaian());
            jdbcTemplate.update("UPDATE bahan a JOIN produksiDetail b SET a.qty=a.qty-b.qtyPemakaian WHERE a.idBahan=? AND b.idDetail=?",
                    bahan.getIdBahan(), uuid);
        }
    }

    @Override
    public List<Produksi> findAll() {
//    public List<Produksi> findAll(int page, int limit) {
//        int numPages;
//        numPages =jdbcTemplate.query("SELECT COUNT(*) AS count FROM produksi",
//                (rs, rowNum)->
//                        rs.getInt("count")).get(0);
//
//        //validatePage
//        if (page < 1) page = 1;
//        if (page > numPages) page = numPages;
//
//        int start = (page - 1) * limit;
        List<Produksi> produksiList;
        produksiList = jdbcTemplate.query("SELECT * FROM produksi",
//        produksiList = jdbcTemplate.query("SELECT * FROM produksi"+start+","+limit+"",
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi")
                        )
        );
        for (Produksi produksi : produksiList){
            List<Bahan> bahanList = new ArrayList<>();
            String idBop = produksi.getIdBOP();
            bahanList = jdbcTemplate.query("SELECT b.*, a.qtyPemakaian FROM produksiDetail a JOIN bahan b on a.idBahan=b.idBahan WHERE a.idBOP='"+idBop+"'",
                    (rs, rowNum)->
                            new Bahan(
                                    rs.getString("idBahan"),
                                    rs.getString("namaBahan"),
                                    rs.getFloat("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getFloat("qtyPemakaian")
                            )
            );
            produksi.setBahanList(bahanList);
        }
        return produksiList;
    }

    @Override
    public Produksi findById(String idBOP) {
        Produksi produksi;
        produksi = jdbcTemplate.queryForObject("SELECT * FROM produksi WHERE idBOP=?",
                new Object[]{idBOP},
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi")
                        )
        );

        produksi.setBahanList(jdbcTemplate.query("SELECT b.*, a.qtyPemakaian FROM produksiDetail a JOIN bahan b on a.idBahan=b.idBahan WHERE a.idBOP=?",
                new Object[]{idBOP},
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getFloat("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan"),
                                rs.getFloat("qtyPemakaian")
                        )
        ));
        return produksi;
    }

    @Override
    public void status(Produksi produksi) {
        jdbcTemplate.update("UPDATE produksi SET statusProduksi=? WHERE idBOP=?",
                produksi.isStatusProduksi(), produksi.getIdBOP());
    }

    @Override
    public List<Produksi> findAllAvailable() {
        List<Produksi> produksiList;
        produksiList = jdbcTemplate.query("SELECT * FROM produksi WHERE statusProduksi=1",
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi")
                        )
        );
        for (Produksi produksi : produksiList){
            List<Bahan> bahanList = new ArrayList<>();
            String idBop = produksi.getIdBOP();
            bahanList = jdbcTemplate.query("SELECT b.*, a.qtyPemakaian FROM produksiDetail a JOIN bahan b on a.idBahan=b.idBahan WHERE a.idBOP='"+idBop+"'",
                    (rs, rowNum)->
                            new Bahan(
                                    rs.getString("idBahan"),
                                    rs.getString("namaBahan"),
                                    rs.getFloat("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getFloat("qtyPemakaian")
                            )
            );
            produksi.setBahanList(bahanList);
        }
        return produksiList;
    }

    @Override
    public List<Produksi> findAllLaporan() {
//    public List<Produksi> findAllLaporan(int page, int limit) {
//        int numPages;
//        numPages =jdbcTemplate.query("SELECT COUNT(*) AS count FROM produksi",
//                (rs, rowNum)->
//                        rs.getInt("count")).get(0);
//
//        //validatePage
//        if (page < 1) page = 1;
//        if (page > numPages) page = numPages;
//
//        int start = (page - 1) * limit;
        List<Produksi> laporanList;
        laporanList = jdbcTemplate.query("SELECT a.*, b.hargaEkspedisi*a.totalKm AS \"hargaTotalEkspedisi\", c.hargaPackaging, " +
                        "(SUM((b.hargaEkspedisi*a.totalKm)+c.hargaPackaging+((d.qtyPemakaian*0.001)*e.hargaBahan))) AS \"totalBiayaProduksi\" " +
                        "FROM produksi a JOIN ekspedisi b JOIN packaging c JOIN produksiDetail d JOIN bahan e " +
                        "ON a.idEkspedisi=b.idEkspedisi AND a.idPackaging=c.idPackaging AND a.idBOP=d.idBOP AND d.idBahan=e.idBahan " +
                        "GROUP BY a.idBOP",

//        laporanList = jdbcTemplate.query("SELECT a.*, b.hargaEkspedisi*a.totalKm AS \"hargaTotalEkspedisi\", c.hargaPackaging, " +
//                        "(SUM((b.hargaEkspedisi*a.totalKm)+c.hargaPackaging+((d.qtyPemakaian*0.001)*e.hargaBahan))) AS \"totalBiayaProduksi\" " +
//                        "FROM produksi a JOIN ekspedisi b JOIN packaging c JOIN produksiDetail d JOIN bahan e " +
//                        "ON a.idEkspedisi=b.idEkspedisi AND a.idPackaging=c.idPackaging AND a.idBOP=d.idBOP AND d.idBahan=e.idBahan " +
//                        "GROUP BY a.idBOP"+start+","+limit+"",
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi"),
                                rs.getFloat("hargaTotalEkspedisi"),
                                rs.getFloat("hargaPackaging"),
                                rs.getFloat("totalBiayaProduksi")
                        )
        );
        for (Produksi laporan : laporanList){
            List<Bahan> bahanList = new ArrayList<>();
            String idBop = laporan.getIdBOP();
            bahanList = jdbcTemplate.query("SELECT b.idBahan, b.namaBahan, b.qty*0.001 AS \"qty\", b.hargaBahan," +
                            "b.statusBahan, a.qtyPemakaian*0.001 AS \"qtyPemakaian\", (a.qtyPemakaian*0.001)*b.hargaBahan AS \"totalHargaBahan\"" +
                            "FROM produksiDetail a JOIN bahan b ON a.idBahan=b.idBahan WHERE a.idBOP='"+idBop+"'",
                    (rs, rowNum)->
                            new Bahan(
                                    rs.getString("idBahan"),
                                    rs.getString("namaBahan"),
                                    rs.getFloat("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getFloat("qtyPemakaian"),
                                    rs.getFloat("totalHargaBahan")
                            )
            );
            laporan.setBahanList(bahanList);
        }
        return laporanList;
    }

    @Override
    public Produksi findAllLaporanById(String idBOP) {
        Produksi laporan;
        laporan = jdbcTemplate.queryForObject("SELECT a.*, b.hargaEkspedisi*a.totalKm AS \"hargaTotalEkspedisi\", c.hargaPackaging, " +
                        "(SUM((b.hargaEkspedisi*a.totalKm)+c.hargaPackaging+((d.qtyPemakaian*0.001)*e.hargaBahan))) AS \"totalBiayaProduksi\" " +
                        "FROM produksi a JOIN ekspedisi b JOIN packaging c JOIN produksiDetail d JOIN bahan e " +
                        "ON a.idEkspedisi=b.idEkspedisi AND a.idPackaging=c.idPackaging AND a.idBOP=d.idBOP AND d.idBahan=e.idBahan " +
                        "WHERE a.idBOP=?",
                new Object[]{idBOP},
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi"),
                                rs.getFloat("hargaTotalEkspedisi"),
                                rs.getFloat("hargaPackaging"),
                                rs.getFloat("totalBiayaProduksi")
                        )
        );

        laporan.setBahanList(jdbcTemplate.query("SELECT b.idBahan, b.namaBahan, b.qty*0.001 AS \"qty\", b.hargaBahan," +
                        "b.statusBahan, a.qtyPemakaian*0.001 AS \"qtyPemakaian\", (a.qtyPemakaian*0.001)*b.hargaBahan AS \"totalHargaBahan\"" +
                        "FROM produksiDetail a JOIN bahan b ON a.idBahan=b.idBahan WHERE a.idBOP=?",
                new Object[]{idBOP},
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getFloat("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan"),
                                rs.getFloat("qtyPemakaian"),
                                rs.getFloat("totalHargaBahan")
                        )
        ));
        return laporan;
    }

    @Override
    public Produksi findAllLaporanByDate(String tanggal) {
        Produksi laporan;
        laporan = jdbcTemplate.queryForObject("SELECT a.*, b.hargaEkspedisi*a.totalKm AS \"hargaTotalEkspedisi\", c.hargaPackaging, " +
                        "(SUM((b.hargaEkspedisi*a.totalKm)+c.hargaPackaging+((d.qtyPemakaian*0.001)*e.hargaBahan))) AS \"totalBiayaProduksi\" " +
                        "FROM produksi a JOIN ekspedisi b JOIN packaging c JOIN produksiDetail d JOIN bahan e " +
                        "ON a.idEkspedisi=b.idEkspedisi AND a.idPackaging=c.idPackaging AND a.idBOP=d.idBOP AND d.idBahan=e.idBahan " +
                        "WHERE "+tanggal,
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging"),
                                rs.getBoolean("statusProduksi"),
                                rs.getFloat("hargaTotalEkspedisi"),
                                rs.getFloat("hargaPackaging"),
                                rs.getFloat("totalBiayaProduksi")
                        )
        );

        laporan.setBahanList(jdbcTemplate.query("SELECT b.idBahan, b.namaBahan, b.qty*0.001 AS \"qty\", b.hargaBahan," +
                        "b.statusBahan, a.qtyPemakaian*0.001 AS \"qtyPemakaian\", (a.qtyPemakaian*0.001)*b.hargaBahan AS \"totalHargaBahan\"" +
                        "FROM produksiDetail a JOIN bahan b ON a.idBahan=b.idBahan WHERE a.idBOP=?",
                new Object[]{tanggal},
                (rs, rowNum)->
                        new Bahan(
                                rs.getString("idBahan"),
                                rs.getString("namaBahan"),
                                rs.getFloat("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan"),
                                rs.getFloat("qtyPemakaian"),
                                rs.getFloat("totalHargaBahan")
                        )
        ));
        return laporan;
    }

}
