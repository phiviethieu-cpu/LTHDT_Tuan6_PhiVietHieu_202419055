/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package thietbi;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private String maPhong;
    private String tenPhong;
    private int sucChua;
    private List<Device> danhSachThietBi = new ArrayList<>();

    public ClassRoom(String maPhong, String tenPhong, int sucChua) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.sucChua = sucChua;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public List<Device> getDanhSachThietBi() {
        return danhSachThietBi;
    }

    public void addDevice(Device device) {
        if (device == null)
            throw new IllegalArgumentException("Khong them duoc thiet bi null");
        if (findDevice(device.getMa()) != null)
            throw new IllegalArgumentException("Thiet bi " + device.getMa() + " da co trong phong");
        danhSachThietBi.add(device);
    }

    public boolean removeDevice(String deviceId) {
        Device tb = findDevice(deviceId);
        if (tb == null)
            return false;
        return danhSachThietBi.remove(tb);
    }

    public Device findDevice(String deviceId) {
        for (Device tb : danhSachThietBi) {
            if (tb.getMa().equals(deviceId))
                return tb;
        }
        return null;
    }

    public double calculateAnnualMaintenanceCost() {
        double tong = 0;
        // de bai cam if/switch theo loai thiet bi, moi lop con tu tinh chi phi cua no
        for (Device tb : danhSachThietBi) {
            tong += tb.tinhChiPhiBaoTri();
        }
        return tong;
    }

    public List<Device> getDevicesRequiringMaintenance() {
        int namHienTai = Year.now().getValue();
        List<Device> ketQua = new ArrayList<>();
        for (Device tb : danhSachThietBi) {
            // nen dung > 5, thiet bi dung dung 5 nam chua can bao tri
            if (tb.getTrangThai() == DeviceStatus.UNDER_MAINTENANCE || namHienTai - tb.getNamSuDung() > 5)
                ketQua.add(tb);
        }
        return ketQua;
    }
}
