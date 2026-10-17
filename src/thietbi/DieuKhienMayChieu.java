/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package thietbi;

public class DieuKhienMayChieu extends TeachingDevice {
    public DieuKhienMayChieu(String ma, String ten, int namSuDung, double giaMua) {
        super(ma, ten, namSuDung, giaMua, 3, 6, 15000);
    }

    @Override
    public double tinhChiPhiBaoTri() {
        return getGiaMua() * 0.08;
    }
}
