package pabrik.mindomie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pabrik.mindomie.model.Packaging;
import pabrik.mindomie.service.PackagingService;
import pabrik.mindomie.util.CustomErrorType;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pabrik/master")
public class PackagingController {

    public static final Logger logger = LoggerFactory.getLogger(PackagingController.class);

    @Autowired
    PackagingService packagingService;

    //------------------Get All Data------------------//

    @RequestMapping(value = "/packaging", method = RequestMethod.GET)
    public ResponseEntity<List<Packaging>> listAllPackaging(@RequestParam Map<Object, Object> pagination){
        String paginationSelect = "";
        if (pagination.containsKey("limit")){
            paginationSelect += " LIMIT " + pagination.get("limit");
        }
        if(pagination.containsKey("offset")){
            paginationSelect += " OFFSET " + pagination.get("offset");
        }
        List<Packaging> packagingList = packagingService.findAll(paginationSelect);
        if (packagingList.isEmpty()) {
            return new ResponseEntity<>(packagingList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(packagingList, HttpStatus.OK);
    }

    //------------------Get All Data is Available------------------//

    @RequestMapping(value = "/packaging/available", method = RequestMethod.GET)
    public ResponseEntity<List<Packaging>> listAllPackagingAvailable(){
        List<Packaging> packagingListAvailable = packagingService.findAllAvailable();
        if (packagingListAvailable.isEmpty()) {
            return new ResponseEntity<>(packagingListAvailable, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(packagingListAvailable, HttpStatus.OK);
    }

    //------------------Save a Data------------------//

    @RequestMapping(value = "/packaging", method = RequestMethod.POST)
    public ResponseEntity<?> savePackaging(@RequestBody Packaging packaging){
        logger.info("Menyimpan Kemasan : {} ", packaging);

        if (packagingService.isPackagingExist(packaging.getNamaPackaging())) {
            logger.error("Tidak dapat menyimpan kemasan! Kemasan dengan nama {} sudah tersedia!", packaging.getNamaPackaging());
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan kemasan! Kemasan dengan nama " +
                    packaging.getNamaPackaging() + " sudah tersedia!"), HttpStatus.CONFLICT);
        }

        packagingService.savePackaging(packaging);

        return new ResponseEntity<>("Data Berhasil Ditambahkan!", HttpStatus.CREATED);
    }

    //------------------Update a Data------------------//

    @RequestMapping(value = "/packaging/{idPackaging}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePackaging(@PathVariable("idPackaging") String idPackaging, @RequestBody Packaging packaging) {
        logger.info("Mengubah kemasan dengan id {}", idPackaging);

        Packaging packaging1 = packagingService.findById(idPackaging);

        if (packaging1 == null) {
            logger.error("Tidak dapat mengubah data Kemasan. Kemasan dengan id {} tidak tersedia.", idPackaging);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data Kemasan. Kemasan dengan id "
                    + idPackaging + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        } else if (packagingService.isPackagingExist(packaging.getNamaPackaging())) {
            logger.error("Tidak dapat menyimpan kemasan! Kemasan dengan nama {} sudah tersedia!", packaging.getNamaPackaging());
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat menyimpan kemasan! Kemasan dengan nama " +
                    packaging.getNamaPackaging() + " sudah tersedia!"), HttpStatus.CONFLICT);
        }

        packaging1.setNamaPackaging(packaging.getNamaPackaging());
        packaging1.setHargaPackaging(packaging.getHargaPackaging());

        packagingService.updatePackaging(packaging1);
        return new ResponseEntity<>("Data Berhasil Diubah!", HttpStatus.OK);
    }

    //------------------Get One Data Only------------------//

    @RequestMapping(value = "/packaging/{idPackaging}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("idPackaging") String idPackaging) {
        logger.info("Mencari kemasan dengan id {}", idPackaging);
        Packaging packaging = packagingService.findById(idPackaging);
        if (packaging == null) {
            logger.error("Kemasan dengan id {} tidak ada.", idPackaging);
            return new ResponseEntity<>(new CustomErrorType("Kemasan dengan id " + idPackaging  + " tidak ada."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(packaging, HttpStatus.OK);
    }

    //------------------Switching Status One Data Only------------------//

    @RequestMapping(value = "packaging/status/{idPackaging}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusPackaging(@PathVariable("idPackaging") String idPackaging) {
        logger.info("Mengubah status packaging dengan id {} menjadi disabled", idPackaging);

        Packaging packaging1 = packagingService.findById(idPackaging);

        if (packaging1 == null) {
            logger.error("Tidak dapat mengubah data Packaging. Packaging dengan id {} tidak tersedia.", idPackaging);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah data packaging. Packaging dengan id "
                    + idPackaging + " tidak tersedia."),
                    HttpStatus.NOT_FOUND);
        }

        if (packaging1.isStatusPackaging() == true){
            packaging1.setStatusPackaging(false);
        }else{
            packaging1.setStatusPackaging(true);
        }

        packagingService.status(packaging1);
        return new ResponseEntity<>("Status Berhasil Diubah!", HttpStatus.OK);
    }
}
