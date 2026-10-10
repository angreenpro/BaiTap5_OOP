/**
 * Nguyễn Phúc Trường An
 * 202419022
 */

import enums.DeviceStatus;
import interfaces.INetworkable;
import models.*;
import rooms.LabRoom;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("=".repeat(60));
        System.out.println("  CHƯƠNG TRÌNH QUẢN LÝ THIẾT BỊ PHÒNG LAB - W05A");
        System.out.println("=".repeat(60));

        // Khởi tạo dữ liệu mẫu
        System.out.println("\n--- KHỞI TẠO DỮ LIỆU MẪU ---");

        // Tạo các Computer
        Computer pc1 = new Computer("PC001", "Dell OptiPlex 7090", 2021, 25000000,
                DeviceStatus.Active, 16, "Intel Core i7-11700", true);
        Computer pc2 = new Computer("PC002", "HP ProDesk 400 G7", 2019, 18000000,
                DeviceStatus.Active, 8, "Intel Core i5-10500", false);
        Computer pc3 = new Computer("PC003", "Lenovo ThinkCentre M70s", 2018, 20000000,
                DeviceStatus.UnderMaintenance, 16, "Intel Core i7-10700", true);

        // Tạo các Printer (máy in thường - không có mạng)
        Printer printer1 = new Printer("PR001", "Canon LBP 2900", 2020, 5000000,
                DeviceStatus.Active, "Laser", 50000, false);
        Printer printer2 = new Printer("PR002", "Epson L3150", 2019, 7000000,
                DeviceStatus.Active, "Phun mực", 120000, true);

        // Tạo NetworkPrinter (máy in có kết nối mạng)
        NetworkPrinter netPrinter1 = new NetworkPrinter("NP001", "HP LaserJet Pro M428fdn", 2021, 12000000,
                DeviceStatus.Active, "Laser", 200000, true);

        // Tạo các Projector
        Projector projector1 = new Projector("PJ001", "Epson EB-X51", 2020, 15000000,
                DeviceStatus.Active, 3800, 2500);
        Projector projector2 = new Projector("PJ002", "BenQ MH733", 2017, 22000000,
                DeviceStatus.Retired, 4000, 3500);

        System.out.println("Đã tạo 8 thiết bị mẫu thành công.");

        // Tạo phòng Lab
        LabRoom lab = new LabRoom("LAB01", "Phòng Lab Tin học A1", 10);
        System.out.println("Đã tạo phòng: " + lab);

        // Thêm thiết bị vào phòng
        System.out.println("\n--- THÊM THIẾT BỊ VÀO PHÒNG ---");

        lab.addDevice(pc1);
        lab.addDevice(pc2);
        lab.addDevice(pc3);
        lab.addDevice(printer1);
        lab.addDevice(printer2);
        lab.addDevice(netPrinter1);
        lab.addDevice(projector1);
        lab.addDevice(projector2);
        System.out.println("Đã thêm 8 thiết bị vào phòng thành công.");

        // Kiểm thử thêm thiết bị trùng mã
        System.out.println("\n[Test] Thử thêm thiết bị trùng mã 'PC001':");
        try {
            Computer pcDuplicate = new Computer("PC001", "Máy tính trùng", 2022, 10000000,
                    DeviceStatus.Active, 8, "Intel Core i3", false);
            lab.addDevice(pcDuplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("  => Lỗi: " + e.getMessage());
        }

        // Hiển thị danh sách thiết bị
        System.out.println("\n--- DANH SÁCH THIẾT BỊ TRONG PHÒNG ---");
        System.out.println(lab);
        List<Device> allDevices = lab.getDevices();
        for (int i = 0; i < allDevices.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, allDevices.get(i));
        }

        // Tính tổng chi phí bảo trì hàng năm
        System.out.println("\n--- TỔNG CHI PHÍ BẢO TRÌ HÀNG NĂM ---");
        double totalCost = lab.calculateTotalAnnualMaintenanceCost();
        System.out.printf("Tổng chi phí bảo trì của phòng %s: %,.0f VNĐ%n", lab.getRoomName(), totalCost);

        System.out.println("\nChi tiết từng thiết bị:");
        for (Device d : allDevices) {
            System.out.printf("  - %s (%s): %,.0f VNĐ%n",
                    d.getName(), d.getId(), d.calculateAnnualMaintenanceCost());
        }

        // Danh sách thiết bị cần bảo trì
 
        System.out.println("\n--- THIẾT BỊ CẦN BẢO TRÌ ---");
        System.out.println("(Trạng thái UnderMaintenance hoặc sử dụng > 5 năm)");
        List<Device> maintenanceDevices = lab.getDevicesRequiringMaintenance();
        if (maintenanceDevices.isEmpty()) {
            System.out.println("  Không có thiết bị nào cần bảo trì.");
        } else {
            for (Device d : maintenanceDevices) {
                System.out.printf("  - %s (Trạng thái: %s, Năm SD: %d)%n",
                        d, d.getStatus(), d.getYearOfUse());
            }
        }

        // Kiểm tra và cấp IP cho thiết bị INetworkable (instanceof)

        System.out.println("\n--- KẾT NỐI MẠNG CHO THIẾT BỊ INetworkable ---");
        int ipCounter = 1;
        for (Device d : allDevices) {
            if (d instanceof INetworkable) {
                INetworkable networkDevice = (INetworkable) d;
                String ip = "192.168.1." + (100 + ipCounter);
                networkDevice.connect(ip);
                System.out.printf("  [Kết nối] %s => IP: %s, Đã kết nối: %s%n",
                        d.getName(), networkDevice.getIpAddress(), networkDevice.isConnected());
                ipCounter++;
            }
        }

        // Hiển thị trạng thái mạng sau khi kết nối
        System.out.println("\nTrạng thái mạng sau khi kết nối:");
        for (Device d : allDevices) {
            if (d instanceof INetworkable) {
                INetworkable networkDevice = (INetworkable) d;
                System.out.printf("  - %s (%s): IP=%s, Kết nối=%s%n",
                        d.getName(), d.getId(),
                        networkDevice.getIpAddress(),
                        networkDevice.isConnected() ? "Có" : "Không");
            }
        }

        // Test ngắt kết nối
        System.out.println("\n[Test] Ngắt kết nối máy tính PC001:");
        pc1.disconnect();
        System.out.printf("  %s: IP=%s, Kết nối=%s%n",
                pc1.getName(), pc1.getIpAddress(), pc1.isConnected() ? "Có" : "Không");

        System.out.println("\n[Test] Thử ngắt kết nối lần nữa (đã ngắt rồi):");
        pc1.disconnect();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  KẾT THÚC CHƯƠNG TRÌNH KIỂM THỬ");
        System.out.println("=".repeat(60));
    }
}
