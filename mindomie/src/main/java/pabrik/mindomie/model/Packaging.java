package pabrik.mindomie.model;

public class Packaging {
    private String idPackaging;
    private String namaPackaging;
    private int hargaPackaging;
    private boolean statusPackaging;

    public Packaging(String idPackaging, String namaPackaging, int hargaPackaging, boolean statusPackaging) {
        this.idPackaging = idPackaging;
        this.namaPackaging = namaPackaging;
        this.hargaPackaging = hargaPackaging;
        this.statusPackaging = statusPackaging;
    }

    public String getIdPackaging() {
        return idPackaging;
    }

    public void setIdPackaging(String idPackaging) {
        this.idPackaging = idPackaging;
    }

    public String getNamaPackaging() {
        return namaPackaging;
    }

    public void setNamaPackaging(String namaPackaging) {
        this.namaPackaging = namaPackaging;
    }

    public int getHargaPackaging() {
        return hargaPackaging;
    }

    public void setHargaPackaging(int hargaPackaging) {
        this.hargaPackaging = hargaPackaging;
    }

    public boolean isStatusPackaging() {
        return statusPackaging;
    }

    public void setStatusPackaging(boolean statusPackaging) {
        this.statusPackaging = statusPackaging;
    }
}
