package pabrik.mindomie.repository;

import pabrik.mindomie.model.Produksi;

import java.util.List;

public interface ProduksiRepository {
    void saveProduksi(Produksi produksi);

    void updateProduksi(Produksi produksi);

    List<Produksi> findAll();

//    List<Produksi> findAll(int page, int limit);

    Produksi findById(String idBOP);

    void status(Produksi produksi);

    List<Produksi> findAllAvailable();

    List<Produksi> findAllLaporan();

//    List<Produksi> findAllLaporan(int page, int limit);

    Produksi findAllLaporanById(String idBOP);

    Produksi findAllLaporanByDate(String tanggal);
}
