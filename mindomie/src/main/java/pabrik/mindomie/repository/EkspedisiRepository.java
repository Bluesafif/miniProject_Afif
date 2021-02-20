package pabrik.mindomie.repository;

import pabrik.mindomie.model.Ekspedisi;

import java.util.List;

public interface EkspedisiRepository {
    void saveEkspedisi(Ekspedisi ekspedisi);

    void updateEkspedisi(Ekspedisi ekspedisi);

    List<Ekspedisi> findAll();

    Ekspedisi findById(String idEkspedisi);

    boolean isEkspedisiExist (String namaEkspedisi);

    List<Ekspedisi> findAllAvailable();

    void status(Ekspedisi ekspedisi);
}