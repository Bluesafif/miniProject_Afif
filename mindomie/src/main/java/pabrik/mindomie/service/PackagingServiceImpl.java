package pabrik.mindomie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pabrik.mindomie.model.Packaging;
import pabrik.mindomie.repository.PackagingRepository;

import java.util.List;

@Service("PackagingService")
public class PackagingServiceImpl implements PackagingService{

    @Autowired
    PackagingRepository packagingRepository;

    @Override
    public void savePackaging(Packaging packaging) {
        synchronized (this){
            packagingRepository.savePackaging(packaging);
        }
    }

    @Override
    public void updatePackaging(Packaging packaging) {
        synchronized (this){
            packagingRepository.updatePackaging(packaging);
        }
    }

    @Override
    public List<Packaging> findAll() {
//    public List<Packaging> findAll(int page, int limit) {
        List<Packaging> packagingList = packagingRepository.findAll();
//        List<Packaging> packagingList = packagingRepository.findAll(int page, int limit);
        return packagingList;
    }

    @Override
    public Packaging findById(String idPackaging) {
        Packaging packaging;
        try{
            packaging = packagingRepository.findById(idPackaging);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            packaging = null;
        }
        return packaging;
    }

    @Override
    public boolean isPackagingExist(String namaPackaging) {
        return packagingRepository.isPackagingExist(namaPackaging);
    }

    @Override
    public List<Packaging> findAllAvailable() {
        List<Packaging> packagingListAvailable = packagingRepository.findAllAvailable();
        return packagingListAvailable;
    }

    @Override
    public void status(Packaging packaging) {
        synchronized (this){
            packagingRepository.status(packaging);
        }
    }
}
