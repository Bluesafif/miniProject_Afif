package pabrik.mindomie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pabrik.mindomie.model.Ekspedisi;
import pabrik.mindomie.repository.EkspedisiRepository;

import java.util.List;

@Service("EkspedisiService")
public class EkspedisiServiceImpl implements EkspedisiService{

    @Autowired
    EkspedisiRepository ekspedisiRepository;

    @Override
    public void saveEkspedisi(Ekspedisi ekspedisi) {
        synchronized (this){
            ekspedisiRepository.saveEkspedisi(ekspedisi);
        }
    }

    @Override
    public void updateEkspedisi(Ekspedisi ekspedisi) {
        synchronized (this){
            ekspedisiRepository.updateEkspedisi(ekspedisi);
        }
    }

    @Override
    public List<Ekspedisi> findAll() {
        List<Ekspedisi> ekspedisiList = ekspedisiRepository.findAll();
        return ekspedisiList;
    }

    @Override
    public Ekspedisi findById(String idEkspedisi) {
        Ekspedisi ekspedisi;
        try{
            ekspedisi = ekspedisiRepository.findById(idEkspedisi);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            ekspedisi = null;
        }
        return ekspedisi;
    }

    @Override
    public boolean isEkspedisiExist(String namaEkspedisi) {
        return ekspedisiRepository.isEkspedisiExist(namaEkspedisi);
    }

    @Override
    public List<Ekspedisi> findAllAvailable() {
        List<Ekspedisi> ekspedisiListAvailable = ekspedisiRepository.findAllAvailable();
        return ekspedisiListAvailable;
    }

    @Override
    public void status(Ekspedisi ekspedisi) {
        synchronized (this){
            ekspedisiRepository.status(ekspedisi);
        }
    }
}
