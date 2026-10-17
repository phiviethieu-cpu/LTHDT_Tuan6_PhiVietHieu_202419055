package phieumuon;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ngoaile.DeviceAlreadyReturnedException;
import ngoaile.NgayTraKhongHopLeException;
import thietbi.TeachingDevice;

public class BorrowItem {
    private final TeachingDevice device;
    private final LocalDate borrowedDate;
    private final LocalDate dueDate;
    private int fromPeriod;
    private int toPeriod;
    private LocalDate returnedDate;

    public BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate,
                      int fromPeriod, int toPeriod) {
        if (device == null)
            throw new IllegalArgumentException("Thiet bi khong duoc null");
        if (dueDate.isBefore(borrowedDate))
            throw new IllegalArgumentException("Han tra truoc ngay muon");
        if (fromPeriod < 1 || toPeriod < fromPeriod || toPeriod > device.getSoTietToiDa())
            throw new IllegalArgumentException("Tiet " + fromPeriod + "-" + toPeriod + " khong hop le");
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

    public void setFromPeriod(int fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public int getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(int toPeriod) {
        this.toPeriod = toPeriod;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public boolean isReturned() {
        return returnedDate != null;
    }

    public void returnDevice(LocalDate returnedDate)
            throws DeviceAlreadyReturnedException, NgayTraKhongHopLeException {
        if (isReturned())
            throw new DeviceAlreadyReturnedException(device.getMa());
        if (returnedDate.isBefore(borrowedDate))
            throw new NgayTraKhongHopLeException("Ngay tra " + returnedDate
                    + " truoc ngay muon " + borrowedDate);
        this.returnedDate = returnedDate;
    }

    public long getSoNgayTre() {
        // chua tra thi tinh tre den hom nay, nen ket qua thay doi theo ngay chay chuong trinh
        LocalDate ngayKetThuc = isReturned() ? returnedDate : LocalDate.now();
        long soNgay = ChronoUnit.DAYS.between(dueDate, ngayKetThuc);
        return soNgay > 0 ? soNgay : 0;
    }

    public double tinhPhiTre() {
        return getSoNgayTre() * device.getPhiTreMoiNgay();
    }

    @Override
    public String toString() {
        return String.format("%s | muon %s, han %s, tiet %d-%d, %s",
                device.getMa(), borrowedDate, dueDate, fromPeriod, toPeriod,
                isReturned() ? "tra " + returnedDate : "chua tra");
    }
}
