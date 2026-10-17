/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package thietbi;

public class Micro extends TeachingDevice {
    private boolean khongDay;

    public Micro(String ma, String ten, int namSuDung, double giaMua, boolean khongDay) {
        // muon toi da 1 ngay theo tinh huong kiem thu 4 cua de bai
        super(ma, ten, namSuDung, giaMua, 1, 4, 20000);
        this.khongDay = khongDay;
    }

    @Override
    public double tinhChiPhiBaoTri() {
        // micro khong day ton them tien pin
        return getGiaMua() * 0.1 + (khongDay ? 50000 : 0);
    }
}
