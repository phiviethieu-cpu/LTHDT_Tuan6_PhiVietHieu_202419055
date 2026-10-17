/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
import thietbi.ClassRoom;
import thietbi.Device;
import thietbi.DieuKhienMayChieu;
import thietbi.DieuKhienDieuHoa;
import thietbi.Micro;
import nguoimuon.Lecturer;
import nguoimuon.TeachingAssistant;
import phieumuon.BorrowingSlip;
import ngoaile.BorrowingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("He thong quan ly thiet bi va phieu muon");

        kiemTraPhongHoc();
        kiemTraLuongThanhCong();
        kiemTraLuongNgoaiLe();
    }

    private static void kiemTraPhongHoc() {
        System.out.println("\nPhong hoc:");
        ClassRoom phong = new ClassRoom("D3-301", "Phong D3-301", 60);
        phong.addDevice(new DieuKhienMayChieu("PR01", "Dieu khien may chieu Epson", 2018, 300000));
        phong.addDevice(new DieuKhienDieuHoa("AC01", "Dieu khien dieu hoa Daikin", 2023, 200000));
        phong.addDevice(new Micro("MC01", "Micro khong day Shure", 2024, 1500000, true));

        try {
            phong.addDevice(new Micro("MC01", "Micro trung ma", 2024, 1000000, false));
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.printf("Chi phi bao tri nam: %.0f%n", phong.calculateAnnualMaintenanceCost());
        System.out.println("Thiet bi can bao tri:");
        for (Device tb : phong.getDevicesRequiringMaintenance()) {
            System.out.println("  " + tb);
        }
    }

    private static void kiemTraLuongThanhCong() {
        System.out.println("\nLuong thanh cong:");
        Lecturer giangVien = new Lecturer("GV01", "Nguyen Van A");
        DieuKhienMayChieu mayChieu = new DieuKhienMayChieu("PR02", "Dieu khien may chieu Sony", 2022, 350000);
        DieuKhienDieuHoa dieuHoa = new DieuKhienDieuHoa("AC02", "Dieu khien dieu hoa LG", 2021, 250000);
        Micro micro = new Micro("MC02", "Micro khong day Sennheiser", 2023, 2000000, true);

        LocalDate ngayMuon = LocalDate.of(2026, 10, 1);
        BorrowingSlip phieu = new BorrowingSlip("PM01", giangVien, "D3-301", ngayMuon);
        List<BorrowingSlip> dsPhieu = new ArrayList<>();
        dsPhieu.add(phieu);

        try {
            phieu.addDevice(mayChieu, 2, 1, 3);
            phieu.addDevice(micro, 1, 1, 3);
            System.out.println(phieu);

            double phiTre = phieu.returnDevice("PR02", ngayMuon.plusDays(2));
            System.out.printf("Tra PR02 dung han, phi tre: %.0f%n", phiTre);

            phiTre = phieu.returnDevice("MC02", ngayMuon.plusDays(3));
            System.out.printf("Tra MC02 muon 2 ngay, phi tre: %.0f%n", phiTre);
        } catch (BorrowingException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        // phieu2 co tinh khong tra de co du lieu cho danh sach "chua tra"
        BorrowingSlip phieu2 = new BorrowingSlip("PM02", giangVien, "D3-302", ngayMuon);
        dsPhieu.add(phieu2);
        try {
            phieu2.addDevice(dieuHoa, 3, 4, 6);
        } catch (BorrowingException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        // co dinh ngay tinh phi de lan chay nao cung ra cung ket qua
        LocalDate ngayTinh = LocalDate.of(2026, 10, 10);
        System.out.println("Phieu tra muon (tinh den " + ngayTinh + "):");
        for (BorrowingSlip p : dsPhieu) {
            if (p.coThietBiTraMuon(ngayTinh))
                System.out.printf("  %s, tong phi tre %.0f%n", p.getSlipId(), p.calculateTotalLateFee(ngayTinh));
        }
        System.out.println("Phieu chua tra:");
        for (BorrowingSlip p : dsPhieu) {
            if (p.coThietBiChuaTra())
                System.out.println("  " + p.getSlipId());
        }
    }

    private static void kiemTraLuongNgoaiLe() {
        System.out.println("\nLuong ngoai le:");
        LocalDate ngayMuon = LocalDate.of(2026, 10, 1);
        Lecturer giangVien = new Lecturer("GV02", "Tran Thi B");
        TeachingAssistant troGiang = new TeachingAssistant("TG01", "Le Van C");

        DieuKhienDieuHoa dieuHoaHong = new DieuKhienDieuHoa("AC03", "Dieu khien dieu hoa hong", 2020, 200000);
        dieuHoaHong.chuyenSangBaoTri();
        DieuKhienMayChieu mayChieu1 = new DieuKhienMayChieu("PR03", "Dieu khien may chieu 1", 2022, 300000);
        DieuKhienMayChieu mayChieu2 = new DieuKhienMayChieu("PR04", "Dieu khien may chieu 2", 2022, 300000);
        Micro micro1 = new Micro("MC03", "Micro co day", 2022, 500000, false);
        Micro micro2 = new Micro("MC04", "Micro khong day", 2022, 1500000, true);

        BorrowingSlip phieuGV = new BorrowingSlip("PM10", giangVien, "D3-401", ngayMuon);
        BorrowingSlip phieuTG = new BorrowingSlip("PM11", troGiang, "D3-402", ngayMuon);

        try {
            phieuGV.addDevice(dieuHoaHong, 1);
        } catch (BorrowingException e) {
            System.out.println("1. " + e.getMessage());
        }

        try {
            phieuTG.addDevice(mayChieu1, 1);
            phieuTG.addDevice(mayChieu2, 1);
            phieuTG.addDevice(micro2, 1);
        } catch (BorrowingException e) {
            System.out.println("2. " + e.getMessage());
            System.out.println("   Trang thai MC04 sau loi: " + micro2.getTrangThai());
        }

        try {
            phieuGV.addDevice(micro1, 1);
            phieuGV.addDevice(micro1, 1);
        } catch (BorrowingException e) {
            System.out.println("3. " + e.getMessage());
        }

        try {
            phieuGV.addDevice(micro2, 3);
        } catch (BorrowingException e) {
            System.out.println("4. " + e.getMessage());
        }

        try {
            phieuGV.returnDevice("PR03", ngayMuon.plusDays(1));
        } catch (BorrowingException e) {
            System.out.println("5. " + e.getMessage());
        }

        try {
            phieuGV.returnDevice("MC03", ngayMuon.plusDays(1));
            phieuGV.returnDevice("MC03", ngayMuon.plusDays(1));
        } catch (BorrowingException e) {
            System.out.println("6. " + e.getMessage());
        }

        try {
            phieuTG.returnDevice("PR03", ngayMuon.minusDays(1));
        } catch (BorrowingException e) {
            System.out.println("7. " + e.getMessage());
        } finally {
            System.out.println("   Trang thai PR03 sau loi: " + mayChieu1.getTrangThai());
        }

        // tinh huong them: micro chi duoc muon toi da 4 tiet
        Micro micro3 = new Micro("MC05", "Micro co day 2", 2022, 500000, false);
        try {
            phieuGV.addDevice(micro3, 1, 1, 6);
        } catch (BorrowingException e) {
            System.out.println("8. " + e.getMessage());
            System.out.println("   Trang thai MC05 sau loi: " + micro3.getTrangThai());
        }
    }
}
