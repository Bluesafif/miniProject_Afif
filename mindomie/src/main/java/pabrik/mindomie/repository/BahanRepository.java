package pabrik.mindomie.repository;

import pabrik.mindomie.model.Bahan;

import java.util.List;

public interface BahanRepository {
    void saveBahan(Bahan bahan);

    void updateBahan(Bahan bahan);

    List<Bahan> findAll(String paginationSelect);

    Bahan findById(String idBahan);

    boolean isBahanExist (String namaBahan);

    List<Bahan> findAllAvailable();

    void status(Bahan bahan);
}
