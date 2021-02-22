package pabrik.mindomie.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pabrik.mindomie.model.Bahan;
import pabrik.mindomie.service.BahanService;
import pabrik.mindomie.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/pabrik/master")
public class BahanController {

    public static final Logger logger = LoggerFactory.getLogger(BahanController.class);

    @Autowired
    BahanService bahanService;

    //------------------Get All Data------------------//

    @RequestMapping(value = "/bahanbaku", method = RequestMethod.GET)
    public ResponseEntity<List<Bahan>> listAllBahan(){
        List<Bahan> bahanList = bahanService.findAll();
//        List<Bahan> bahanList = bahanService.findAll(int page, int limit);
        if (bahanList.isEmpty()) {
            return new ResponseEntity<>(bahanList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bahanList, HttpStatus.OK);
    }

    //------------------Get All Data is Available------------------//

    @RequestMapping(value = "/bahanbaku/available", method = RequestMethod.GET)
    public ResponseEntity<List<Bahan>> listAllBahanAvailable(){
        List<Bahan> bahanListAvailable = bahanService.findAllAvailable();
        if (bahanListAvailable.isEmpty()) {
            return new ResponseEntity<>(bahanListAvailable, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bahanListAvailable, HttpStatus.OK);
    }

    //------------------Save a Data------------------//

    @RequestMapping(value = "/bahanbaku", method = RequestMethod.POST)
    public ResponseEntity<?> saveBahan(@RequestBody Bahan bahan){
        logger.info("Menyimpan Bahan : {} ", bahan);

        if (bahanService.isBahanExist(bahan.getNamaBahan())) {
            logger.error("Tidak dapat menyimpan bahan! Bahan dengan nama {} sudah tersedia!", bahan.getNamaBahan());
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan bahan! Bahan dengan nama " +
                    bahan.getNamaBahan() + " sudah tersedia!"), HttpStatus.CONFLICT);
        }

        bahanService.saveBahan(bahan);

        return new ResponseEntity<>("Data Berhasil Ditambahkan!", HttpStatus.CREATED);
    }

    //------------------Update a Data------------------//

    @RequestMapping(value = "/bahanbaku/{idBahan}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBahan(@PathVariable("idBahan") String idBahan, @RequestBody Bahan bahan) {
        logger.info("Mengubah bahan dengan id {}", idBahan);

        Bahan bahan1 = bahanService.findById(idBahan);

        if (bahan1 == null) {
            logger.error("Tidak dapat mengubah data Bahan Baku. Bahan dengan id {} tidak tersedia.", idBahan);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data Bahan Baku. Bahan dengan id "
                    + idBahan + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        }

        bahan1.setNamaBahan(bahan.getNamaBahan());
        bahan1.setHargaBahan(bahan.getHargaBahan());
        bahan1.setQty(bahan.getQty());
        if (bahan.getQty() == 0){
            bahan1.setStatusBahan(false);
        }else{
            bahan1.setStatusBahan(true);
        }

        bahanService.updateBahan(bahan1);
        return new ResponseEntity<>("Data Berhasil Diubah!", HttpStatus.OK);
    }

    //------------------Get One Data Only------------------//

    @RequestMapping(value = "/bahanbaku/{idBahan}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("idBahan") String idBahan) {
        logger.info("Mencari bahan dengan id {}", idBahan);
        Bahan bahan = bahanService.findById(idBahan);
        if (bahan == null) {
            logger.error("Bahan dengan id {} tidak ada.", idBahan);
            return new ResponseEntity<>(new CustomErrorType("Bahan dengan id " + idBahan  + " tidak ada."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bahan, HttpStatus.OK);
    }

    //------------------Switching Status One Data Only------------------//

    @RequestMapping(value = "/bahanbaku/status/{idBahan}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusBahan(@PathVariable("idBahan") String idBahan) {
        logger.info("Mengubah status bahan dengan id {} menjadi disabled", idBahan);

        Bahan bahan1 = bahanService.findById(idBahan);

        if (bahan1 == null) {
            logger.error("Tidak dapat mengubah data Bahan. Bahan dengan id {} tidak tersedia.", idBahan);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data bahan. Bahan Baku dengan id "
                    + idBahan + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        }

        if (bahan1.isStatusBahan() == true){
            bahan1.setStatusBahan(false);
        }else{
            bahan1.setStatusBahan(true);
        }

        bahanService.status(bahan1);
        return new ResponseEntity<>("Status Berhasil Diubah!", HttpStatus.OK);
    }
}
