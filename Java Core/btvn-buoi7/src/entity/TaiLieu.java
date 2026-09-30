package entity;

public class TaiLieu {
    private int maTaiLieu;
    private String tenXuatNhaXuatBan;
    private int soBanPhatHanh;

    public TaiLieu(){

    }

    public TaiLieu(int maTaiLieu, String tenXuatNhaXuatBan, int soBanPhatHanh) {
        this.maTaiLieu = maTaiLieu;
        this.tenXuatNhaXuatBan = tenXuatNhaXuatBan;
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public int getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(int maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenXuatNhaXuatBan() {
        return tenXuatNhaXuatBan;
    }

    public void setTenXuatNhaXuatBan(String tenXuatNhaXuatBan) {
        this.tenXuatNhaXuatBan = tenXuatNhaXuatBan;
    }

    public int getSoBanPhatHanh() {
        return soBanPhatHanh;
    }

    public void setSoBanPhatHanh(int soBanPhatHanh) {
        this.soBanPhatHanh = soBanPhatHanh;
    }
}