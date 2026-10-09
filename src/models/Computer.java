package models;

import enums.DeviceStatus;
import interfaces.INetworkable;
import java.time.Year;

public class Computer extends Device implements INetworkable {

    private int ramCapacity; // GB
    private String cpuType;
    private boolean hasDiscreteGpu;
    private String ipAddress;
    private boolean connected;

    public Computer(String id, String name, int yearOfUse, double purchasePrice, DeviceStatus status,
                    int ramCapacity, String cpuType, boolean hasDiscreteGpu) {
        super(id, name, yearOfUse, purchasePrice, status);
        this.ramCapacity = ramCapacity;
        this.cpuType = cpuType;
        this.hasDiscreteGpu = hasDiscreteGpu;
        this.ipAddress = "";
        this.connected = false;
    }

    // Getters

    public int getRamCapacity() {
        return ramCapacity;
    }

    public String getCpuType() {
        return cpuType;
    }

    public boolean isHasDiscreteGpu() {
        return hasDiscreteGpu;
    }

    // Setters

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public void setHasDiscreteGpu(boolean hasDiscreteGpu) {
        this.hasDiscreteGpu = hasDiscreteGpu;
    }

    // INetworkable

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void connect(String ipAddress) {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ IP không được rỗng.");
        }
        this.ipAddress = ipAddress;
        this.connected = true;
    }

    @Override
    public void disconnect() {
        if (!connected) {
            System.out.println("Máy tính " + getName() + " chưa kết nối mạng.");
            return;
        }
        this.ipAddress = "";
        this.connected = false;
    }

    // 5% giá mua + 2% nếu có GPU rời + 1% nếu dùng > 5 năm
    @Override
    public double calculateAnnualMaintenanceCost() {
        double cost = getPurchasePrice() * 0.05;
        if (hasDiscreteGpu) {
            cost += getPurchasePrice() * 0.02;
        }
        int yearsUsed = Year.now().getValue() - getYearOfUse();
        if (yearsUsed > 5) {
            cost += getPurchasePrice() * 0.01;
        }
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Computer[ID=%s, Tên=%s, RAM=%dGB, CPU=%s, GPU rời=%s, IP=%s, Kết nối=%s]",
                getId(), getName(), ramCapacity, cpuType, hasDiscreteGpu ? "Có" : "Không",
                ipAddress.isEmpty() ? "N/A" : ipAddress, connected ? "Có" : "Không");
    }
}
