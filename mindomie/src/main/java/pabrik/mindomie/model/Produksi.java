package pabrik.mindomie.model;

import java.util.Date;
import java.util.List;

public class Produksi {
    private String idProduksi;
    private Date tglProduksi;
    private boolean statusProduksi;
    List<Bahan> bahanList;

    public Produksi(String idProduksi, Date tglProduksi, boolean statusProduksi, List<Bahan> bahanList) {
        this.idProduksi = idProduksi;
        this.tglProduksi = tglProduksi;
        this.statusProduksi = statusProduksi;
        this.bahanList = bahanList;
    }

    public String getIdProduksi() {
        return idProduksi;
    }

    public void setIdProduksi(String idProduksi) {
        this.idProduksi = idProduksi;
    }

    public Date getTglProduksi() {
        return tglProduksi;
    }

    public void setTglProduksi(Date tglProduksi) {
        this.tglProduksi = tglProduksi;
    }

    public boolean isStatusProduksi() {
        return statusProduksi;
    }

    public void setStatusProduksi(boolean statusProduksi) {
        this.statusProduksi = statusProduksi;
    }

    public List<Bahan> getBahanList() {
        return bahanList;
    }

    public void setBahanList(List<Bahan> bahanList) {
        this.bahanList = bahanList;
    }
}
