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

Ngoại lệ thêm ngoài đề:

- `NgayTraKhongHopLeException`: ngày trả trước ngày mượn.
- `TietMuonKhongHopLeException`: tiết mượn sai (từ tiết lớn hơn đến tiết, hoặc số tiết vượt mức tối đa của thiết bị).

## Quy ước

- "Số tiết tối đa" là số lượng tiết được mượn liên tiếp, ví dụ micro tối đa 4 tiết thì mượn tiết 5-8 vẫn hợp lệ.
- Giới hạn số thiết bị (giảng viên 3, trợ giảng 2) tính trên cả phiếu, kể cả thiết bị đã trả. Muốn mượn thêm thì lập phiếu mới.
- Thiết bị chưa trả thì phí trễ tính đến ngày truyền vào `calculateTotalLateFee(ngayTinh)`. Bản không tham số tính đến hôm nay.
- Trạng thái thiết bị chỉ đổi qua các hàm `markAsBorrowed`, `danhDauSanSang`, `chuyenSangBaoTri`, `ketThucBaoTri`. Mỗi hàm kiểm tra trạng thái hiện tại trước khi đổi.

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
