package pabrik.mindomie.repository;

import pabrik.mindomie.model.Produksi;

import java.util.List;

public interface ProduksiRepository {
    void saveProduksi(Produksi produksi);

    void updateProduksi(Produksi produksi);

    List<Produksi> findAll(String paginationSelect);

    Produksi findById(String idBOP);

    void status(Produksi produksi);

    List<Produksi> findAllAvailable();

    List<Produksi> findAllLaporan(String paginationSelect);

    Produksi findAllLaporanById(String idBOP);

    Produksi findAllLaporanByDate(String tanggal);
}
