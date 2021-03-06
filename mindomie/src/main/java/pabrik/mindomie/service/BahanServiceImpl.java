package pabrik.mindomie.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.repository.BahanRepository;

import java.util.List;

@Service("BahanService")
public class BahanServiceImpl implements BahanService{

    @Autowired
    BahanRepository bahanRepository;

    @Override
    public void saveBahan(Bahan bahan) {
        synchronized (this) {
            bahanRepository.saveBahan(bahan);
        }
    }

    @Override
    public void updateBahan(Bahan bahan) {
        synchronized (this){
            bahanRepository.updateBahan(bahan);
        }
    }

    @Override
    public List<Bahan> findAll(String paginationSelect) {
        List<Bahan> bahanList = bahanRepository.findAll(paginationSelect);
        return bahanList;
    }

    @Override
    public Bahan findById(String idBahan) {
        Bahan bahan;
        try{
            bahan = bahanRepository.findById(idBahan);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            bahan = null;
        }
        return bahan;
    }

    @Override
    public boolean isBahanExist(String namaBahan) {
        return bahanRepository.isBahanExist(namaBahan);
    }

    @Override
    public List<Bahan> findAllAvailable() {
        List<Bahan> bahanListAvailable = bahanRepository.findAllAvailable();
        return bahanListAvailable;
    }

    @Override
    public void status(Bahan bahan) {
        synchronized (this){
            bahanRepository.status(bahan);
        }
    }
}
