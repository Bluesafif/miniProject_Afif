package pabrik.mindomie.service;

import pabrik.mindomie.model.Packaging;

import java.util.List;

public interface PackagingService {
    void savePackaging(Packaging packaging);

    void updatePackaging(Packaging packaging);

    List<Packaging> findAll();

    Packaging findById(String idpackaging);

    boolean isPackagingExist (String namapackaging);

    List<Packaging> findAllAvailable();

    void status(Packaging packaging);
}
