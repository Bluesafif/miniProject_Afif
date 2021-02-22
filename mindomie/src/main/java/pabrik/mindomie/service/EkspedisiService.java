package pabrik.mindomie.service;

import pabrik.mindomie.model.Ekspedisi;

import java.util.List;

public interface EkspedisiService {
    void saveEkspedisi(Ekspedisi ekspedisi);

    void updateEkspedisi(Ekspedisi ekspedisi);

    List<Ekspedisi> findAll();

//    List<Ekspedisi> findAll(int page, int limit);

    Ekspedisi findById(String idEkspedisi);

    boolean isEkspedisiExist (String namaEkspedisi);

    List<Ekspedisi> findAllAvailable();

    void status(Ekspedisi ekspedisi);
}
