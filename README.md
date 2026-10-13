# LTHDT Tuần 6 - Hệ thống quản lý thiết bị và phiếu mượn

Sinh viên: Phí Việt Hiếu - 202419055

## Cấu trúc

```
src/
  model/      DeviceStatus, Device, TeachingDevice, các thiết bị cụ thể, ClassRoom
  borrower/   IBorrower, Lecturer, TeachingAssistant
  borrowing/  BorrowItem, BorrowingSlip
  exception/  BorrowingException và các ngoại lệ con
  Main.java
```

## Biên dịch và chạy

```
javac -d out $(find src -name "*.java")
java -cp out Main
```
