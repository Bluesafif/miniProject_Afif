package pabrik.mindomie.repository;

import pabrik.mindomie.model.Packaging;

import java.util.List;

public interface PackagingRepository {
    void savePackaging(Packaging packaging);

    void updatePackaging(Packaging packaging);

    List<Packaging> findAll();

    Packaging findById(String idpackaging);

    boolean isPackagingExist (String namapackaging);

    List<Packaging> findAllAvailable();
}
