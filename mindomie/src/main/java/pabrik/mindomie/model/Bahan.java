package pabrik.mindomie.model;

public class Bahan {
    private String idBahan;
    private String namaBahan;
    private float qty;
    private int hargaBahan;
    private boolean statusBahan;
    private float qtyPemakaian;
    private float totalHargaBahan;

    public Bahan() {
    }

    public Bahan(String idBahan, String namaBahan, float qty, int hargaBahan, boolean statusBahan, float qtyPemakaian) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.qty = qty;
        this.hargaBahan = hargaBahan;
        this.statusBahan = statusBahan;
        this.qtyPemakaian = qtyPemakaian;
    }

    public Bahan(String idBahan, String namaBahan, float qty, int hargaBahan, boolean statusBahan) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.qty = qty;
        this.hargaBahan = hargaBahan;
        this.statusBahan = statusBahan;
    }

    public Bahan(String idBahan, String namaBahan, float qty, int hargaBahan, boolean statusBahan, float qtyPemakaian, float totalHargaBahan) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.qty = qty;
        this.hargaBahan = hargaBahan;
        this.statusBahan = statusBahan;
        this.qtyPemakaian = qtyPemakaian;
        this.totalHargaBahan = totalHargaBahan;
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

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
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

    public float getQtyPemakaian() {
        return qtyPemakaian;
    }

    public void setQtyPemakaian(float qtyPemakaian) {
        this.qtyPemakaian = qtyPemakaian;
    }

    public float getTotalHargaBahan() {
        return totalHargaBahan;
    }

    public void setTotalHargaBahan(float totalHargaBahan) {
        this.totalHargaBahan = totalHargaBahan;
    }

    @Override
    public String toString() {
        return "Bahan{" +
                "idBahan='" + idBahan + '\'' +
                ", namaBahan='" + namaBahan + '\'' +
                ", qty=" + qty +
                ", hargaBahan=" + hargaBahan +
                ", statusBahan=" + statusBahan +
                ", qtyPemakaian=" + qtyPemakaian +
                ", totalHargaBahan=" + totalHargaBahan +
                '}';
    }
}
