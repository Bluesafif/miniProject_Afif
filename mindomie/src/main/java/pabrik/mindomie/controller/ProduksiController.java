package pabrik.mindomie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pabrik.mindomie.model.Produksi;
import pabrik.mindomie.service.ProduksiService;

import java.util.List;

@RestController
@RequestMapping("/pabrik")
public class ProduksiController {
    public static final Logger logger = LoggerFactory.getLogger(ProduksiController.class);

    @Autowired
    ProduksiService produksiService;

    //------------------Get All Data------------------//

    @RequestMapping(value = "/produksi", method = RequestMethod.GET)
    public ResponseEntity<List<Produksi>> listAllProduksi(){
        List<Produksi> produksiList = produksiService.findAll();
        if (produksiList.isEmpty()) {
            return new ResponseEntity<>(produksiList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produksiList, HttpStatus.OK);
    }
}
