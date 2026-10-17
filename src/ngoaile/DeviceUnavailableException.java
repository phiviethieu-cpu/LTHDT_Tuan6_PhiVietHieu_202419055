/****************/
// Ma sinh vien: 202419055
// Ho ten: Phi Viet Hieu
/****************/
package ngoaile;

import thietbi.DeviceStatus;

public class DeviceUnavailableException extends BorrowingException {
    private final String deviceId;
    private final DeviceStatus currentStatus;

    public DeviceUnavailableException(String deviceId, DeviceStatus currentStatus) {
        super("Thiet bi " + deviceId + " khong san sang, trang thai hien tai: " + currentStatus);
        this.deviceId = deviceId;
        this.currentStatus = currentStatus;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceStatus getCurrentStatus() {
        return currentStatus;
    }
}
