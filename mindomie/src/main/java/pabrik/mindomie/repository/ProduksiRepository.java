package pabrik.mindomie.repository;

import pabrik.mindomie.model.Produksi;

import java.util.List;

public interface ProduksiRepository {
    void saveProduksi(Produksi produksi);

    void updateProduksi(Produksi produksi);

    List<Produksi> findAll();

    Produksi findById(String idProduksi);
}