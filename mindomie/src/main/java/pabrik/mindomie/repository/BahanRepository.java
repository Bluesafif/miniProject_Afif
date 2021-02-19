package pabrik.mindomie.repository;

import pabrik.mindomie.model.Bahan;

import java.util.List;

public interface BahanRepository {
    void saveBahan(Bahan bahan);

    void updateBahan(Bahan bahan);

    List<Bahan> findAll();

    Bahan findById(String idBahan);

    boolean isBahanExist (String namaBahan);
}
