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
                uuid, produksi.getTglProduksi(), produksi.getTotalKm(), produksi.getIdEkspedisi(), produksi.getIdPackaging());

        for (Bahan bahan : produksi.getBahanList()){
            String uuid2 = String.valueOf(UUID.randomUUID());
            jdbcTemplate.update("INSERT INTO produksiDetail (idDetail, idBOP, idBahan, qtyPemakaian) VALUES (?,?,?,?)",
                    uuid2, produksi.getIdBOP(), bahan.getIdBahan(), bahan.getQtyPemakaian());
        }
    }

    @Override
    public void updateProduksi(Produksi produksi) {

    }

    @Override
    public List<Produksi> findAll() {
        List<Produksi> produksiList;
        produksiList = jdbcTemplate.query("SELECT * FROM produksi",
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
                                    rs.getInt("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getInt("qtyPemakaian")
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
                                rs.getInt("qty"),
                                rs.getInt("hargaBahan"),
                                rs.getBoolean("statusBahan"),
                                rs.getInt("qtyPemakaian")
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
                                    rs.getInt("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getInt("qtyPemakaian")
                            )
            );
            produksi.setBahanList(bahanList);
        }
        return produksiList;
    }

    @Override
    public List<Produksi> findAllLaporan() {
        List<Produksi> laporanList;
        laporanList = jdbcTemplate.query("SELECT idBOP, tglTransaksi, totalKM, idEkspedisi, idPackaging FROM produksi",
                (rs, rowNum)->
                        new Produksi(
                                rs.getString("idBOP"),
                                rs.getDate("tglTransaksi"),
                                rs.getFloat("totalKm"),
                                rs.getString("idEkspedisi"),
                                rs.getString("idPackaging")
                        )
        );
        for (Produksi laporan : laporanList){
            List<Bahan> bahanList = new ArrayList<>();
            String idBop = laporan.getIdBOP();
            bahanList = jdbcTemplate.query("SELECT b.*, a.qtyPemakaian FROM produksiDetail a JOIN bahan b on a.idBahan=b.idBahan WHERE a.idBOP='"+idBop+"'",
                    (rs, rowNum)->
                            new Bahan(
                                    rs.getString("idBahan"),
                                    rs.getString("namaBahan"),
                                    rs.getInt("qty"),
                                    rs.getInt("hargaBahan"),
                                    rs.getBoolean("statusBahan"),
                                    rs.getInt("qtyPemakaian")
                            )
            );
            laporan.setBahanList(bahanList);
        }
        return laporanList;
    }
}
