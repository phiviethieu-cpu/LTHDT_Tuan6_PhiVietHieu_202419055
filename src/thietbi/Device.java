package thietbi;

import java.time.Year;

public abstract class Device {
    private final String ma;
    private final String ten;
    private int namSuDung;
    private double giaMua;
    private DeviceStatus trangThai;

    public Device(String ma, String ten, int namSuDung, double giaMua) {
        if (ma == null || ma.trim().isEmpty())
            throw new IllegalArgumentException("Ma thiet bi khong duoc rong");
        this.ma = ma.trim();
        this.ten = ten;
        // goi setter de constructor dung chung dieu kien kiem tra
        setNamSuDung(namSuDung);
        setGiaMua(giaMua);
        this.trangThai = DeviceStatus.AVAILABLE;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public int getNamSuDung() {
        return namSuDung;
    }

    public void setNamSuDung(int namSuDung) {
        if (namSuDung > Year.now().getValue())
            throw new IllegalArgumentException("Nam su dung khong hop le: " + namSuDung);
        this.namSuDung = namSuDung;
    }

    public double getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(double giaMua) {
        if (giaMua <= 0)
            throw new IllegalArgumentException("Gia mua phai > 0");
        this.giaMua = giaMua;
    }

    public DeviceStatus getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(DeviceStatus trangThai) {
        this.trangThai = trangThai;
    }

    public abstract double tinhChiPhiBaoTri();

    @Override
    public String toString() {
        return String.format("%s - %s (%d), gia %.0f, %s", ma, ten, namSuDung, giaMua, trangThai);
    }
}
