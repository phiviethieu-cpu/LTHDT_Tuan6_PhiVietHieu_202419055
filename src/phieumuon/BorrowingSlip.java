/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package phieumuon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import nguoimuon.IBorrower;
import thietbi.TeachingDevice;
import thietbi.DeviceStatus;
import ngoaile.BorrowingException;
import ngoaile.InvalidBorrowPeriodException;
import ngoaile.DuplicateDeviceException;
import ngoaile.BorrowLimitExceededException;
import ngoaile.DeviceUnavailableException;
import ngoaile.DeviceNotInSlipException;

public class BorrowingSlip {
    private final String slipId;
    private final IBorrower borrower;
    private final String teachingRoom;
    private final LocalDate borrowedDate;
    private final List<BorrowItem> items = new ArrayList<>();

    public BorrowingSlip(String slipId, IBorrower borrower, String teachingRoom, LocalDate borrowedDate) {
        if (slipId == null || slipId.trim().isEmpty())
            throw new IllegalArgumentException("Thieu ma phieu");
        if (borrower == null)
            throw new IllegalArgumentException("Phieu phai co nguoi muon");
        if (teachingRoom == null || teachingRoom.trim().isEmpty())
            throw new IllegalArgumentException("Thieu phong day");
        this.slipId = slipId;
        this.borrower = borrower;
        this.teachingRoom = teachingRoom;
        this.borrowedDate = borrowedDate;
    }

    public String getSlipId() {
        return slipId;
    }

    public IBorrower getBorrower() {
        return borrower;
    }

    public String getTeachingRoom() {
        return teachingRoom;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public List<BorrowItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // de bai chi cho addDevice(device, borrowDays), khong co tiet nen mac dinh tiet 1
    public void addDevice(TeachingDevice device, int borrowDays) throws BorrowingException {
        addDevice(device, borrowDays, 1, 1);
    }

    public void addDevice(TeachingDevice device, int borrowDays, int fromPeriod, int toPeriod)
            throws BorrowingException {
        validateBorrowing(device, borrowDays);

        // tao item truoc roi moi doi trang thai: neu tiet sai thi constructor nem loi
        // khi thiet bi van con AVAILABLE, phieu khong bi thay doi gi
        BorrowItem item = new BorrowItem(device, borrowedDate, borrowedDate.plusDays(borrowDays),
                fromPeriod, toPeriod);
        device.markAsBorrowed();
        items.add(item);
    }

    private void validateBorrowing(TeachingDevice device, int borrowDays) throws BorrowingException {
        if (device == null)
            throw new IllegalArgumentException("Thiet bi null");
        if (borrowDays <= 0 || borrowDays > device.getSoNgayMuonToiDa())
            throw new InvalidBorrowPeriodException(device.getMa(), borrowDays, device.getSoNgayMuonToiDa());
        // kiem tra trung truoc kiem tra trang thai: thiet bi da co trong phieu thi dang BORROWED,
        // neu dao thu tu se bao "khong san sang" thay vi "trung thiet bi"
        if (timMucMuon(device.getMa()) != null)
            throw new DuplicateDeviceException(device.getMa());
        if (items.size() >= borrower.getMaximumDevices())
            throw new BorrowLimitExceededException(borrower.getBorrowerId(), borrower.getMaximumDevices());
        if (device.getTrangThai() != DeviceStatus.AVAILABLE)
            throw new DeviceUnavailableException(device.getMa(), device.getTrangThai());
    }

    public double returnDevice(String deviceId, LocalDate returnedDate) throws BorrowingException {
        if (deviceId == null || deviceId.trim().isEmpty())
            throw new IllegalArgumentException("Chua nhap ma thiet bi");
        BorrowItem item = timMucMuon(deviceId);
        if (item == null)
            throw new DeviceNotInSlipException(deviceId);
        // ghi ngay tra truoc: neu ngay tra sai thi thiet bi van giu BORROWED
        item.returnDevice(returnedDate);
        item.getDevice().danhDauSanSang();
        return item.tinhPhiTre();
    }

    public double calculateTotalLateFee() {
        double tong = 0;
        for (BorrowItem item : items) {
            tong += item.tinhPhiTre();
        }
        return tong;
    }

    public boolean coThietBiTraMuon() {
        for (BorrowItem item : items) {
            if (item.getSoNgayTre() > 0)
                return true;
        }
        return false;
    }

    public boolean coThietBiChuaTra() {
        for (BorrowItem item : items) {
            if (!item.isReturned())
                return true;
        }
        return false;
    }

    private BorrowItem timMucMuon(String deviceId) {
        for (BorrowItem item : items) {
            if (item.getDevice().getMa().equals(deviceId))
                return item;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder chuoi = new StringBuilder();
        chuoi.append("Phieu ").append(slipId).append(" - ").append(borrower)
                .append(" - phong ").append(teachingRoom).append(" - ngay ").append(borrowedDate);
        for (BorrowItem item : items) {
            chuoi.append("\n  ").append(item);
        }
        return chuoi.toString();
    }
}
