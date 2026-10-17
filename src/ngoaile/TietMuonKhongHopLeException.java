/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package ngoaile;

// Bo sung: tiet muon sai (tu tiet > den tiet, hoac vuot so tiet toi da cua thiet bi)
public class TietMuonKhongHopLeException extends BorrowingException {
    private final String maThietBi;
    private final int tuTiet;
    private final int denTiet;

    public TietMuonKhongHopLeException(String maThietBi, int tuTiet, int denTiet, int soTietToiDa) {
        super("Thiet bi " + maThietBi + ": tiet " + tuTiet + "-" + denTiet
                + " khong hop le (toi da " + soTietToiDa + " tiet)");
        this.maThietBi = maThietBi;
        this.tuTiet = tuTiet;
        this.denTiet = denTiet;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public int getTuTiet() {
        return tuTiet;
    }

    public int getDenTiet() {
        return denTiet;
    }
}
