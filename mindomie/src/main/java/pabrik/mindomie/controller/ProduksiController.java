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
import java.util.Map;

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
        }else{
            return new ResponseEntity<>(produksiListAvailable, HttpStatus.OK);
        }
    }

    //------------------Save a Data------------------//

    @RequestMapping(value = "/produksi", method = RequestMethod.POST)
    public ResponseEntity<?> saveBahan(@RequestBody Produksi produksi){
        logger.info("Menyimpan Produksi : {} ", produksi);

        List<Bahan> bahanList = produksi.getBahanList();

        for (int i = 0; i < bahanList.size(); i++) {
            Bahan bahan = bahanService.findById(produksi.getBahanList().get(i).getIdBahan());
            if (produksi.getBahanList().get(i).getQtyPemakaian() > bahan.getQty()) {
                logger.error("Terdapat stok Bahan yang tidak tersedia");
                return new ResponseEntity<>(new CustomErrorType("Tidak dapat membuat produksi. Bahan dengan id " + produksi.getBahanList().get(i).getIdBahan() + " Tidak Tersedia."), HttpStatus.CONFLICT);
            } else if (!bahan.isStatusBahan()) {
                logger.error("Terdapat Bahan yang tidak tersedia!");
                return new ResponseEntity<>(new CustomErrorType("Tidak dapat membuat produksi. Bahan dengan id " + produksi.getBahanList().get(i).getIdBahan() + " Tidak Tersedia."), HttpStatus.CONFLICT);
            }else{
                produksiService.saveProduksi(produksi);
            }
        }

        return new ResponseEntity<>("Data Berhasil Ditambahkan!", HttpStatus.CREATED);
    }

    //------------------Update a Data------------------//

    @RequestMapping(value = "/produksi/{idBOP}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduksi(@PathVariable("idBOP") String idBOP, @RequestBody Produksi produksi){
        logger.info("Mengubah data Produksi dengan idBOP {}", idBOP);
        Produksi produksi1 = produksiService.findById(idBOP);

        if (produksi1 == null){
            logger.error("Tidak dapat mengubah Produksi. Produksi dengan idBOP {} tidak ada.", idBOP);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah Produksi. Produksi dengan idBOP " + idBOP + " tidak ada."),
                    HttpStatus.NOT_FOUND);
        }else {
            try {
                produksiService.updateProduksi(produksi);
                return new ResponseEntity<>("Data Berhasil Di Update",HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Data Gagal Di Update", HttpStatus.BAD_REQUEST);
            }
        }
    }

    //------------------Get One Data Only------------------//

    @RequestMapping(value = "/produksi/{idBOP}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduksi(@PathVariable("idBOP") String idBOP) {
        logger.info("Mencari BOP dengan id {}", idBOP);
        Produksi produksi = produksiService.findById(idBOP);
        if (produksi == null) {
            logger.error("BOP dengan id {} tidak ada.", idBOP);
            return new ResponseEntity<>(new CustomErrorType("BOP dengan id " + idBOP  + " tidak ada."), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(produksi, HttpStatus.OK);
        }
    }

    //------------------Switching Status One Data Only------------------//

    @RequestMapping(value = "/produksi/status/{idBOP}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusBahan(@PathVariable("idBOP") String idBOP) {
        logger.info("Mengubah status produksi dengan idBOP {}", idBOP);

        Produksi produksi1 = produksiService.findById(idBOP);

        if (produksi1 == null) {
            logger.error("Tidak dapat mengubah status Produksi. Produksi dengan idBOP {} tidak tersedia.", idBOP);
            return new ResponseEntity<>(new CustomErrorType("Tidak dapat mengubah status produksi. Produksi dengan idBOP "
                    + idBOP + " tidak tersedia."),
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
        }else{
            return new ResponseEntity<>(laporanList, HttpStatus.OK);
        }
    }

    //------------------Get Only One Report Data------------------//

    @RequestMapping(value = "/laporan/{idBOP}", method = RequestMethod.GET)
    public ResponseEntity<?> listLaporanByIdBOP(@PathVariable("idBOP") String idBOP){
        logger.info("Mencari laporan dengan idBOP {}", idBOP);
        Produksi laporan = produksiService.findAllLaporanById(idBOP);
        if (laporan == null) {
            logger.error("Laporan dengan idBOP {} tidak ada.", idBOP);
            return new ResponseEntity<>(new CustomErrorType("Laporan dengan idBOP " + idBOP  + " tidak ada."), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(laporan, HttpStatus.OK);
        }
    }

    //------------------Get Report Data By Date------------------//

    @RequestMapping(value = "/laporan/", method = RequestMethod.GET)
    public ResponseEntity<?> listLaporanByDate(@RequestParam Map<Object, Object> tanggal){
        logger.info("Mencari laporan berdasarkan tanggal.");
        String tanggalSelect = "(tglTransaksi BETWEEN '" + tanggal.get("tanggalMulai") + "' AND '" + tanggal.get("tanggalAkhir") + "')";
        Produksi laporan = produksiService.findAllLaporanByDate(tanggalSelect);
        if (laporan == null) {
            logger.error("Laporan tidak ada.");
            return new ResponseEntity<>(new CustomErrorType("Laporan dengan tidak ada."), HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(laporan, HttpStatus.OK);
        }
    }
}
