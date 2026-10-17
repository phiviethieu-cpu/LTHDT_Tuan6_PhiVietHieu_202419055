/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package ngoaile;

public class InvalidBorrowPeriodException extends BorrowingException {
    private final String maThietBi;
    private final int soNgayYeuCau;
    private final int soNgayToiDa;

    public InvalidBorrowPeriodException(String maThietBi, int soNgayYeuCau, int soNgayToiDa) {
        super("Thiet bi " + maThietBi + ": so ngay muon " + soNgayYeuCau
                + " khong hop le (toi da " + soNgayToiDa + ")");
        this.maThietBi = maThietBi;
        this.soNgayYeuCau = soNgayYeuCau;
        this.soNgayToiDa = soNgayToiDa;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public int getSoNgayYeuCau() {
        return soNgayYeuCau;
    }

    public int getSoNgayToiDa() {
        return soNgayToiDa;
    }
}
