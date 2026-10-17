/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package ngoaile;

public class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String deviceId) {
        super("Thiet bi " + deviceId + " da co trong phieu");
    }
}
