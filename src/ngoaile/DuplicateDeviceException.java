package ngoaile;

public class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String deviceId) {
        super("Thiet bi " + deviceId + " da co trong phieu");
    }
}
