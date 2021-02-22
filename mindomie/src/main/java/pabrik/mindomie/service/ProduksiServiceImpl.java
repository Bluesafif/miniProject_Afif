package pabrik.mindomie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        synchronized (this){
            produksiRepository.updateProduksi(produksi);
        }
    }

    @Override
    public List<Produksi> findAll(String paginationSelect) {
        List<Produksi> produksiList = produksiRepository.findAll(paginationSelect);
        return produksiList;
    }

    @Override
    public Produksi findById(String idProduksi) {
        Produksi produksi;
        try{
            produksi = produksiRepository.findById(idProduksi);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            produksi = null;
        }
        return produksi;
    }

    @Override
    public void status(Produksi produksi) {
        synchronized (this){
            produksiRepository.status(produksi);
        }
    }

    @Override
    public List<Produksi> findAllAvailable() {
        List<Produksi> produksiListAvailable = produksiRepository.findAllAvailable();
        return produksiListAvailable;
    }

    @Override
    public List<Produksi> findAllLaporan(String paginationSelect) {
        List<Produksi> laporanList = produksiRepository.findAllLaporan(paginationSelect);
        return laporanList;
    }

    @Override
    public Produksi findAllLaporanById(String idBOP) {
        Produksi laporan;
        try{
            laporan = produksiRepository.findAllLaporanById(idBOP);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            laporan = null;
        }
        return laporan;
    }

    @Override
    public Produksi findAllLaporanByDate(String tanggal) {
        Produksi laporan;
        try{
            laporan = produksiRepository.findAllLaporanByDate(tanggal);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            laporan = null;
        }
        return laporan;
    }
}
