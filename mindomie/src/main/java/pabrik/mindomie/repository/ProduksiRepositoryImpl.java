package pabrik.mindomie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.model.Produksi;

import java.util.List;
import java.util.UUID;

@Repository("ProduksiRepository")
public class ProduksiRepositoryImpl implements ProduksiRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveProduksi(Produksi produksi) {
        String uuid = String.valueOf(UUID.randomUUID());
        jdbcTemplate.update("INSERT INTO produksi (idProduksi, tglProduksi, statusProduksi) VALUES (?,?,0)",
                uuid, produksi.getTglProduksi());

        for (Bahan bahan : produksi.getBahanList()){
            String uuid2 = String.valueOf(UUID.randomUUID());
            jdbcTemplate.update("INSERT INTO produksiDetail (idDetail, idProduksi, idBahan, qty) VALUES (?,?,?,?)",
                    uuid2, produksi.getIdProduksi(), bahan.getIdBahan(), )
        }
    }

    @Override
    public void updateProduksi(Produksi produksi) {

    }

    @Override
    public List<Produksi> findAll() {
        return null;
    }

    @Override
    public Produksi findById(String idProduksi) {
        return null;
    }
}
