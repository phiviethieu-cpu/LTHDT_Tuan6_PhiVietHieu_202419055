package thietbi;

public class DieuKhienDieuHoa extends TeachingDevice {
    public DieuKhienDieuHoa(String ma, String ten, int namSuDung, double giaMua) {
        super(ma, ten, namSuDung, giaMua, 5, 6, 10000);
    }

    @Override
    public double tinhChiPhiBaoTri() {
        return getGiaMua() * 0.05;
    }
}
