package pabrik.mindomie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.model.Produksi;
import pabrik.mindomie.service.BahanService;
import pabrik.mindomie.service.ProduksiService;
import pabrik.mindomie.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/pabrik")
public class ProduksiController {
    public static final Logger logger = LoggerFactory.getLogger(ProduksiController.class);

    @Autowired
    ProduksiService produksiService;

    @Autowired
    BahanService bahanService;

    //------------------Get All Data------------------//

    @RequestMapping(value = "/produksi", method = RequestMethod.GET)
    public ResponseEntity<List<Produksi>> listAllProduksi(){
        List<Produksi> produksiList = produksiService.findAll();
        if (produksiList.isEmpty()) {
            return new ResponseEntity<>(produksiList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produksiList, HttpStatus.OK);
    }

    //------------------Get All Data is Available------------------//

    @RequestMapping(value = "/produksi/available", method = RequestMethod.GET)
    public ResponseEntity<List<Produksi>> listAllProduksiAvailable(){
        List<Produksi> produksiListAvailable = produksiService.findAllAvailable();
        if (produksiListAvailable.isEmpty()) {
            return new ResponseEntity<>(produksiListAvailable, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produksiListAvailable, HttpStatus.OK);
    }

    //------------------Save a Data------------------//

    @RequestMapping(value = "/produksi", method = RequestMethod.POST)
    public ResponseEntity<?> saveBahan(@RequestBody Produksi produksi){
        logger.info("Menyimpan Produksi : {} ", produksi);

//        for (int i = 0; i<produksi.getBahanList().size(); i++) {
//            if (produksi. == false) {
//                logger.error("Tidak dapat menyimpan data produksi karena status bahan sudah kadaluarsa");
//                return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan data produksi        karena status bahan sudah kadaluarsa"),
//                        HttpStatus.CONFLICT);
//            }
//        }
        produksiService.saveProduksi(produksi);

        return new ResponseEntity<>("Data Berhasil Ditambahkan!", HttpStatus.CREATED);
    }

    //------------------Update a Data------------------//

    //------------------Get One Data Only------------------//

    @RequestMapping(value = "/produksi/{idProduksi}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduksi(@PathVariable("idProduksi") String idProduksi) {
        logger.info("Mencari BOP dengan id {}", idProduksi);
        Produksi produksi = produksiService.findById(idProduksi);
        if (produksi == null) {
            logger.error("BOP dengan id {} tidak ada.", idProduksi);
            return new ResponseEntity<>(new CustomErrorType("BOP dengan id " + idProduksi  + " tidak ada."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produksi, HttpStatus.OK);
    }

    //------------------Switching Status One Data Only------------------//

    @RequestMapping(value = "/produksi/status/{idProduksi}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusBahan(@PathVariable("idProduksi") String idProduksi) {
        logger.info("Mengubah status produksi dengan idBOP {}", idProduksi);

        Produksi produksi1 = produksiService.findById(idProduksi);

        if (produksi1 == null) {
            logger.error("Tidak dapat mengubah status Produksi. Produksi dengan idBOP {} tidak tersedia.", idProduksi);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah status produksi. Produksi dengan idBOP "
                    + idProduksi + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        }

        if (produksi1.isStatusProduksi() == true){
            produksi1.setStatusProduksi(false);
        }else{
            produksi1.setStatusProduksi(true);
        }

        produksiService.status(produksi1);
        return new ResponseEntity<>("Status Berhasil Diubah!", HttpStatus.OK);
    }

    //------------------Get All Report Data------------------//

    @RequestMapping(value = "/laporan", method = RequestMethod.GET)
    public ResponseEntity<List<Produksi>> listAllLaporan(){
        List<Produksi> laporanList = produksiService.findAllLaporan();
        if (laporanList.isEmpty()) {
            return new ResponseEntity<>(laporanList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(laporanList, HttpStatus.OK);
    }
}
