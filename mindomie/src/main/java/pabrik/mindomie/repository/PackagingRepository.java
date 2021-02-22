package pabrik.mindomie.repository;

import pabrik.mindomie.model.Packaging;

import java.util.List;

public interface PackagingRepository {
    void savePackaging(Packaging packaging);

    void updatePackaging(Packaging packaging);

    List<Packaging> findAll();

//    List<Packaging> findAll(int page, int limit);

    Packaging findById(String idPackaging);

    boolean isPackagingExist (String namaPackaging);

    List<Packaging> findAllAvailable();

    void status(Packaging packaging);
}
