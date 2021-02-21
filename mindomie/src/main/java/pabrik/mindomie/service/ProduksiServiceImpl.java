package pabrik.mindomie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pabrik.mindomie.model.Produksi;
import pabrik.mindomie.repository.ProduksiRepository;

import java.util.List;

@Service("ProduksiService")
public class ProduksiServiceImpl implements ProduksiService {

    @Autowired
    ProduksiRepository produksiRepository;

    @Override
    public void saveProduksi(Produksi produksi) {
        synchronized (this){
            produksiRepository.saveProduksi(produksi);
        }
    }

    @Override
    public void updateProduksi(Produksi produksi) {

    }

    @Override
    public List<Produksi> findAll() {
        List<Produksi> produksiList = produksiRepository.findAll();
        return produksiList;
    }

    @Override
    public Produksi findById(String idProduksi) {
        return null;
    }
}
