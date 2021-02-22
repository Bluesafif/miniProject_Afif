package pabrik.mindomie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pabrik.mindomie.model.Ekspedisi;
import pabrik.mindomie.service.EkspedisiService;
import pabrik.mindomie.util.CustomErrorType;

import java.util.List;

@RestController
@RequestMapping("/pabrik/master")
public class EkspedisiController {
    public static final Logger logger = LoggerFactory.getLogger(EkspedisiController.class);

    @Autowired
    EkspedisiService ekspedisiService;

    //------------------Get All Data------------------//

    @RequestMapping(value = "/ekspedisi", method = RequestMethod.GET)
    public ResponseEntity<List<Ekspedisi>> listAllEkspedisi(){
        List<Ekspedisi> ekspedisiList = ekspedisiService.findAll();
//        List<Ekspedisi> ekspedisiList = ekspedisiService.findAll(int page, int limit);
        if (ekspedisiList.isEmpty()) {
            return new ResponseEntity<>(ekspedisiList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ekspedisiList, HttpStatus.OK);
    }

    //------------------Get All Data is Available------------------//

    @RequestMapping(value = "/ekspedisi/available", method = RequestMethod.GET)
    public ResponseEntity<List<Ekspedisi>> listAllEkspedisiAvailable(){
        List<Ekspedisi> ekspedisiListAvailable = ekspedisiService.findAllAvailable();
        if (ekspedisiListAvailable.isEmpty()) {
            return new ResponseEntity<>(ekspedisiListAvailable, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ekspedisiListAvailable, HttpStatus.OK);
    }

    //------------------Save a Data------------------//

    @RequestMapping(value = "/ekspedisi", method = RequestMethod.POST)
    public ResponseEntity<?> saveEkspedisi(@RequestBody Ekspedisi ekspedisi){
        logger.info("Menyimpan Ekspedisi : {} ", ekspedisi);

        if (ekspedisiService.isEkspedisiExist(ekspedisi.getNamaEkspedisi())) {
            logger.error("Tidak dapat menyimpan ekspedisi! Ekspedisi dengan nama {} sudah tersedia!", ekspedisi.getNamaEkspedisi());
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan ekspedisi! Ekspedisi dengan nama " +
                    ekspedisi.getNamaEkspedisi() + " sudah tersedia!"), HttpStatus.CONFLICT);
        }

        ekspedisiService.saveEkspedisi(ekspedisi);

        return new ResponseEntity<>("Data Berhasil Ditambahkan!", HttpStatus.CREATED);
    }

    //------------------Update a Data------------------//

    @RequestMapping(value = "/ekspedisi/{idEkspedisi}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEkspedisi(@PathVariable("idEkspedisi") String idEkspedisi, @RequestBody Ekspedisi ekspedisi) {
        logger.info("Mengubah ekspedisi dengan id {}", idEkspedisi);

        Ekspedisi ekspedisi1 = ekspedisiService.findById(idEkspedisi);

        if (ekspedisi1 == null) {
            logger.error("Tidak dapat mengubah data Ekspedisi. Ekspedisi dengan id {} tidak tersedia.", idEkspedisi);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data ekspedisi. Ekspedisi dengan id "
                    + idEkspedisi + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        } else if (ekspedisiService.isEkspedisiExist(ekspedisi.getNamaEkspedisi())) {
            logger.error("Tidak dapat menyimpan ekspedisi! Ekspedisi dengan nama {} sudah tersedia!", ekspedisi.getNamaEkspedisi());
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan ekspedisi! Ekspedisi dengan nama " +
                    ekspedisi.getNamaEkspedisi() + " sudah tersedia!"), HttpStatus.CONFLICT);
        }

        ekspedisi1.setNamaEkspedisi(ekspedisi.getNamaEkspedisi());
        ekspedisi1.setHargaEkspedisi(ekspedisi.getHargaEkspedisi());

        ekspedisiService.updateEkspedisi(ekspedisi1);
        return new ResponseEntity<>("Data Berhasil Diubah!", HttpStatus.OK);
    }

    //------------------Get One Data Only------------------//

    @RequestMapping(value = "/ekspedisi/{idEkspedisi}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("idEkspedisi") String idEkspedisi) {
        logger.info("Mencari ekspedisi dengan id {}", idEkspedisi);
        Ekspedisi ekspedisi = ekspedisiService.findById(idEkspedisi);
        if (ekspedisi == null) {
            logger.error("Ekspedisi dengan id {} tidak ada.", idEkspedisi);
            return new ResponseEntity<>(new CustomErrorType("Ekspedisi dengan id " + idEkspedisi  + " tidak ada."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ekspedisi, HttpStatus.OK);
    }

    //------------------Switching Status One Data Only------------------//

    @RequestMapping(value = "ekspedisi/status/{idEkspedisi}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusEkspedisi(@PathVariable("idEkspedisi") String idEkspedisi) {
        logger.info("Mengubah status ekspedisi dengan id {} menjadi disabled", idEkspedisi);

        Ekspedisi ekspedisi1 = ekspedisiService.findById(idEkspedisi);

        if (ekspedisi1 == null) {
            logger.error("Tidak dapat mengubah data Ekspedisi. Ekspedisi dengan id {} tidak tersedia.", idEkspedisi);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data ekspedisi. Ekspedisi dengan id "
                    + idEkspedisi + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        }

        if (ekspedisi1.isStatusEkspedisi() == true){
            ekspedisi1.setStatusEkspedisi(false);
        }else{
            ekspedisi1.setStatusEkspedisi(true);
        }

        ekspedisiService.status(ekspedisi1);
        return new ResponseEntity<>("Status Berhasil Diubah!", HttpStatus.OK);
    }
}
