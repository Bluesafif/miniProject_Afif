package pabrik.mindomie.model;

public class Bahan {
    private String idBahan;
    private String namaBahan;
    private int qty;
    private int hargaBahan;
    private boolean statusBahan;

    public Bahan(String idBahan, String namaBahan, int qty, int hargaBahan, boolean statusBahan) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.qty = qty;
        this.hargaBahan = hargaBahan;
        this.statusBahan = statusBahan;
    }

    public String getIdBahan() {
        return idBahan;
    }

    public void setIdBahan(String idBahan) {
        this.idBahan = idBahan;
    }

    public String getNamaBahan() {
        return namaBahan;
    }

    public void setNamaBahan(String namaBahan) {
        this.namaBahan = namaBahan;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getHargaBahan() {
        return hargaBahan;
    }

    public void setHargaBahan(int hargaBahan) {
        this.hargaBahan = hargaBahan;
    }

    public boolean isStatusBahan() {
        return statusBahan;
    }

    public void setStatusBahan(boolean statusBahan) {
        this.statusBahan = statusBahan;
    }
}
