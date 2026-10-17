/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package ngoaile;

public class DeviceAlreadyReturnedException extends BorrowingException {
    public DeviceAlreadyReturnedException(String deviceId) {
        super("Thiet bi " + deviceId + " da duoc tra truoc do");
    }
}
