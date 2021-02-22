package pabrik.mindomie.model;

import java.util.Date;
import java.util.List;

public class Produksi {
    private String idBOP;
    private Date tglTransaksi;
    private float totalKm;
    private String idEkspedisi;
    private String idPackaging;
    private boolean statusProduksi;
    List<Bahan> bahanList;

    public Produksi() {
    }

    public Produksi(String idBOP, Date tglTransaksi, float totalKm, String idEkspedisi, String idPackaging, boolean statusProduksi) {
        this.idBOP = idBOP;
        this.tglTransaksi = tglTransaksi;
        this.totalKm = totalKm;
        this.idEkspedisi = idEkspedisi;
        this.idPackaging = idPackaging;
        this.statusProduksi = statusProduksi;
    }

    public Produksi(String idBOP, Date tglTransaksi, float totalKm, String idEkspedisi, String idPackaging) {
        this.idBOP = idBOP;
        this.tglTransaksi = tglTransaksi;
        this.totalKm = totalKm;
        this.idEkspedisi = idEkspedisi;
        this.idPackaging = idPackaging;
    }

    public String getIdBOP() {
        return idBOP;
    }

    public void setIdBOP(String idBOP) {
        this.idBOP = idBOP;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(float totalKm) {
        this.totalKm = totalKm;
    }

    public String getIdEkspedisi() {
        return idEkspedisi;
    }

    public void setIdEkspedisi(String idEkspedisi) {
        this.idEkspedisi = idEkspedisi;
    }

    public String getIdPackaging() {
        return idPackaging;
    }

    public void setIdPackaging(String idPackaging) {
        this.idPackaging = idPackaging;
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
