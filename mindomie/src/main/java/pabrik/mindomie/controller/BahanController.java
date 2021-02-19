package pabrik.mindomie.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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
        if (bahanList.isEmpty()) {
            return new ResponseEntity<>(bahanList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bahanList, HttpStatus.OK);
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

        return new ResponseEntity<>(bahan, HttpStatus.CREATED);
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

        bahanService.updateBahan(bahan1);
        return new ResponseEntity<>(bahan1, HttpStatus.OK);
    }
}
