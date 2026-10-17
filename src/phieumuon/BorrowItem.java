/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package phieumuon;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ngoaile.DeviceAlreadyReturnedException;
import ngoaile.NgayTraKhongHopLeException;
import ngoaile.TietMuonKhongHopLeException;
import thietbi.TeachingDevice;

public class BorrowItem {
    private final TeachingDevice device;
    private final LocalDate borrowedDate;
    private final LocalDate dueDate;
    private int fromPeriod;
    private int toPeriod;
    private LocalDate returnedDate;

    public BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate,
                      int fromPeriod, int toPeriod) throws TietMuonKhongHopLeException {
        if (device == null)
            throw new IllegalArgumentException("Thiet bi khong duoc null");
        if (borrowedDate == null || dueDate == null)
            throw new IllegalArgumentException("Ngay muon va han tra khong duoc null");
        if (dueDate.isBefore(borrowedDate))
            throw new IllegalArgumentException("Han tra truoc ngay muon");
        kiemTraTiet(device, fromPeriod, toPeriod);
        this.device = device;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.fromPeriod = fromPeriod;
        this.toPeriod = toPeriod;
    }

    public TeachingDevice getDevice() {
        return device;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(int fromPeriod) throws TietMuonKhongHopLeException {
        kiemTraTiet(device, fromPeriod, toPeriod);
        this.fromPeriod = fromPeriod;
    }

    public int getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(int toPeriod) throws TietMuonKhongHopLeException {
        kiemTraTiet(device, fromPeriod, toPeriod);
        this.toPeriod = toPeriod;
    }

    // so tiet toi da la so luong tiet duoc muon, khong phai so thu tu tiet cuoi
    private static void kiemTraTiet(TeachingDevice device, int fromPeriod, int toPeriod)
            throws TietMuonKhongHopLeException {
        if (fromPeriod < 1 || toPeriod < fromPeriod || toPeriod - fromPeriod + 1 > device.getSoTietToiDa())
            throw new TietMuonKhongHopLeException(device.getMa(), fromPeriod, toPeriod, device.getSoTietToiDa());
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public boolean isReturned() {
        return returnedDate != null;
    }

    public void returnDevice(LocalDate returnedDate)
            throws DeviceAlreadyReturnedException, NgayTraKhongHopLeException {
        if (returnedDate == null)
            throw new IllegalArgumentException("Ngay tra khong duoc null");
        if (isReturned())
            throw new DeviceAlreadyReturnedException(device.getMa());
        if (returnedDate.isBefore(borrowedDate))
            throw new NgayTraKhongHopLeException("Ngay tra " + returnedDate
                    + " truoc ngay muon " + borrowedDate);
        this.returnedDate = returnedDate;
    }

    // da tra thi tinh den ngay tra, chua tra thi tinh den ngayTinh truyen vao
    // (truyen ngay vao de ket qua khong phu thuoc ngay chay chuong trinh)
    public long getSoNgayTre(LocalDate ngayTinh) {
        LocalDate ngayKetThuc = isReturned() ? returnedDate : ngayTinh;
        long soNgay = ChronoUnit.DAYS.between(dueDate, ngayKetThuc);
        return soNgay > 0 ? soNgay : 0;
    }

    public double tinhPhiTre(LocalDate ngayTinh) {
        return getSoNgayTre(ngayTinh) * device.getPhiTreMoiNgay();
    }

    @Override
    public String toString() {
        return String.format("%s | muon %s, han %s, tiet %d-%d, %s",
                device.getMa(), borrowedDate, dueDate, fromPeriod, toPeriod,
                isReturned() ? "tra " + returnedDate : "chua tra");
    }
}
