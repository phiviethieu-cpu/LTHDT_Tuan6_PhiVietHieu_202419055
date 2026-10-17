/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package thietbi;

public abstract class TeachingDevice extends Device {
    private int soNgayMuonToiDa;
    private int soTietToiDa;
    private double phiTreMoiNgay;

    // gioi han muon va phi tre do tung lop con truyen vao, khong cho sua sau khi tao
    public TeachingDevice(String ma, String ten, int namSuDung, double giaMua,
                          int soNgayMuonToiDa, int soTietToiDa, double phiTreMoiNgay) {
        super(ma, ten, namSuDung, giaMua);
        this.soNgayMuonToiDa = soNgayMuonToiDa;
        this.soTietToiDa = soTietToiDa;
        this.phiTreMoiNgay = phiTreMoiNgay;
    }

    public int getSoNgayMuonToiDa() {
        return soNgayMuonToiDa;
    }

    public int getSoTietToiDa() {
        return soTietToiDa;
    }

    public double getPhiTreMoiNgay() {
        return phiTreMoiNgay;
    }

    public void markAsBorrowed() {
        setTrangThai(DeviceStatus.BORROWED);
    }

    public void danhDauSanSang() {
        setTrangThai(DeviceStatus.AVAILABLE);
    }
}
