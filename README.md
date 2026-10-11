# W5 - Hợp Đồng Bảo Trì & Quản Lý Thiết Bị Lab

## Các tính năng chính
- **Quản lý thiết bị đa dạng:** Hỗ trợ lưu trữ và quản lý `Máy tính` (Computer), `Máy in` (Printer), và `Máy chiếu` (Projector).
- **Phân tách nghiệp vụ mạng:** Sử dụng interface `INetworkable` để cung cấp khả năng kết nối mạng linh hoạt cho một số thiết bị cụ thể mà không làm phình cấu trúc gốc.
- **Quản lý phòng thực hành:** Áp dụng kết tập để cấp phát thiết bị vào các phòng lab.
- **Tính toán chi phí bảo trì:** Sử dụng tính đa hình để tự động hóa việc tính toán chi phí bảo trì chuyên biệt tùy thuộc vào đặc thù của từng loại máy.
- **Báo cáo định kỳ:** Tự động lọc danh sách các thiết bị tới hạn bảo trì (ví dụ: đang bị lỗi hoặc có tuổi thọ trên 5 năm).

## Kiến trúc hướng đối tượng
- **`Device` (Abstract):** Đóng vai trò là lớp nền tảng. Chứa hàm abstract `CalculateAnnualMaintenanceCost()` ép các thiết bị cụ thể phải tự định nghĩa công thức bảo trì (ứng dụng Đa hình).
- **`INetworkable` (Interface):** Tách rời tính năng mạng để linh hoạt gán cho từng máy (Interface Segregation).
- **Các thiết bị chuyên biệt (`Computer`, `Printer`, `Projector`):** Chứa dữ liệu riêng. Đặc biệt, tạo thêm lớp `NetworkPrinter` kế thừa từ `Printer` để gán thêm mạng, xử lý bài toán "chỉ vài máy in có mạng".
- **`LabRoom` (Quản lý - Kết tập):** Chứa thiết bị nhưng không "sở hữu độc quyền" (nếu hủy phòng, máy vẫn tồn tại). Nó có thể duyệt qua danh sách và gọi tính tiền hàng loạt mà không cần lệnh `if-else` phân loại thiết bị rườm rà (Đa hình).

## Hướng dẫn chạy chương trình

**Yêu cầu:** JDK 11 trở lên (`java --version` để kiểm tra).

**Bước 1** – Mở terminal, di chuyển đến thư mục:
```bash
cd "FILE_PATH"
```

**Bước 2** – Biên dịch toàn bộ source:
```bash
javac -d out src/*.java
```
> Lệnh này compile tất cả file `.java` trong `src/` và xuất `.class` vào thư mục `out/`.

**Bước 3** – Chạy chương trình:
```bash
java -cp out Main
```

**Tóm tắt (chạy nhanh 1 dòng):**
```bash
javac -d out src/*.java && java -cp out Main
```

> **Lưu ý:** Nếu tiếng Việt hiển thị lỗi font trên PowerShell, chạy `chcp 65001` trước để chuyển sang encoding UTF-8.
