package pabrik.mindomie.model;

public class Ekspedisi {
    private String idEkspedisi;
    private String namaEkspedisi;
    private int hargaEkspedisi;
    private boolean statusEkspedisi;

    public Ekspedisi(String idEkspedisi, String namaEkspedisi, int hargaEkspedisi, boolean statusEkspedisi) {
        this.idEkspedisi = idEkspedisi;
        this.namaEkspedisi = namaEkspedisi;
        this.hargaEkspedisi = hargaEkspedisi;
        this.statusEkspedisi = statusEkspedisi;
    }

    public String getIdEkspedisi() {
        return idEkspedisi;
    }

    public void setIdEkspedisi(String idEkspedisi) {
        this.idEkspedisi = idEkspedisi;
    }

    public String getNamaEkspedisi() {
        return namaEkspedisi;
    }

    public void setNamaEkspedisi(String namaEkspedisi) {
        this.namaEkspedisi = namaEkspedisi;
    }

    public int getHargaEkspedisi() {
        return hargaEkspedisi;
    }

    public void setHargaEkspedisi(int hargaEkspedisi) {
        this.hargaEkspedisi = hargaEkspedisi;
    }

    public boolean isStatusEkspedisi() {
        return statusEkspedisi;
    }

    public void setStatusEkspedisi(boolean statusEkspedisi) {
        this.statusEkspedisi = statusEkspedisi;
    }

    @Override
    public String toString() {
        return "Ekspedisi{" +
                "idEkspedisi='" + idEkspedisi + '\'' +
                ", namaEkspedisi='" + namaEkspedisi + '\'' +
                ", hargaEkspedisi=" + hargaEkspedisi +
                ", statusEkspedisi=" + statusEkspedisi +
                '}';
    }
}
