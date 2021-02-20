package pabrik.mindomie.service;

import pabrik.mindomie.model.Bahan;

import java.util.List;

public interface BahanService {
    void saveBahan(Bahan bahan);

    void updateBahan(Bahan bahan);

    List<Bahan> findAll();

    Bahan findById(String idBahan);

    boolean isBahanExist (String namaBahan);

    List<Bahan> findAllAvailable();

    void status(Bahan bahan);
}
