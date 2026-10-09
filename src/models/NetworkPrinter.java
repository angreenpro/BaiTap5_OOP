package models;

import enums.DeviceStatus;
import interfaces.INetworkable;

public class NetworkPrinter extends Printer implements INetworkable {

    private String ipAddress;
    private boolean connected;

    public NetworkPrinter(String id, String name, int yearOfUse, double purchasePrice, DeviceStatus status,
                          String printerType, int pagesPrinted, boolean isColorPrinter) {
        super(id, name, yearOfUse, purchasePrice, status, printerType, pagesPrinted, isColorPrinter);
        this.ipAddress = "";
        this.connected = false;
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
            System.out.println("Máy in mạng " + getName() + " chưa kết nối mạng.");
            return;
        }
        this.ipAddress = "";
        this.connected = false;
    }

    @Override
    public String toString() {
        return String.format("NetworkPrinter[ID=%s, Tên=%s, Loại=%s, Số trang=%d, In màu=%s, IP=%s, Kết nối=%s]",
                getId(), getName(), getPrinterType(), getPagesPrinted(),
                isColorPrinter() ? "Có" : "Không",
                ipAddress.isEmpty() ? "N/A" : ipAddress, connected ? "Có" : "Không");
    }
}
