# LTHDT Tuần 6 - Hệ thống quản lý thiết bị và phiếu mượn

Sinh viên: Phí Việt Hiếu - 202419055

## Cấu trúc

```text
src/
  thietbi/    DeviceStatus, Device, TeachingDevice, DieuKhienDieuHoa, DieuKhienMayChieu, Micro, ClassRoom
  nguoimuon/  IBorrower, Lecturer, TeachingAssistant
  phieumuon/  BorrowItem, BorrowingSlip
  ngoaile/    BorrowingException và các ngoại lệ con
  Main.java   chạy thử luồng thành công và các tình huống lỗi
```

`NgayTraKhongHopLeException` là ngoại lệ thêm ngoài đề, dùng khi ngày trả trước ngày mượn.

## Biên dịch và chạy

Git Bash:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out Main
```
