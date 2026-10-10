package rooms;

import models.Device;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;

public class LabRoom {

    private String roomId;
    private String roomName;
    private int capacity;
    private List<Device> devices;

    public LabRoom(String roomId, String roomName, int capacity) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã phòng không được rỗng.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Sức chứa phải lớn hơn 0.");
        }
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.devices = new ArrayList<>();
    }

    // Getters

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Device> getDevices() {
        return devices;
    }

    // Setters

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Thêm thiết bị - kiểm tra null và ID trùng
    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không được null.");
        }
        for (Device d : devices) {
            if (d.getId().equals(device.getId())) {
                throw new IllegalArgumentException("Thiết bị với mã '" + device.getId() + "' đã tồn tại trong phòng.");
            }
        }
        if (devices.size() >= capacity) {
            throw new IllegalStateException("Phòng đã đầy, không thể thêm thiết bị.");
        }
        devices.add(device);
    }

    // Xoá thiết bị theo ID
    public boolean removeDevice(String deviceId) {
        return devices.removeIf(d -> d.getId().equals(deviceId));
    }

    // Tìm thiết bị theo ID
    public Device findDevice(String deviceId) {
        for (Device d : devices) {
            if (d.getId().equals(deviceId)) {
                return d;
            }
        }
        return null;
    }

    // Tính tổng chi phí bảo trì hàng năm (Polymorphism)
    public double calculateTotalAnnualMaintenanceCost() {
        double total = 0;
        for (Device d : devices) {
            total += d.calculateAnnualMaintenanceCost();
        }
        return total;
    }

    // Lọc thiết bị cần bảo trì: trạng thái UnderMaintenance hoặc năm sử dụng > 5 năm
    public List<Device> getDevicesRequiringMaintenance() {
        List<Device> result = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (Device d : devices) {
            if (d.getStatus() == enums.DeviceStatus.UnderMaintenance
                    || (currentYear - d.getYearOfUse()) > 5) {
                result.add(d);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("LabRoom[Mã=%s, Tên=%s, Sức chứa=%d, Số thiết bị=%d]",
                roomId, roomName, capacity, devices.size());
    }
}
