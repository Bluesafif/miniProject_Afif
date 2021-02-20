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
        List<Packaging> packagingList = packagingRepository.findAll();
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
}
