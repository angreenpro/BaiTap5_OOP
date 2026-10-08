package models;

import enums.DeviceStatus;
import java.time.Year;

public abstract class Device {

    private String id;
    private String name;
    private int yearOfUse;
    private double purchasePrice;
    private DeviceStatus status;

    public Device(String id, String name, int yearOfUse, double purchasePrice, DeviceStatus status) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã thiết bị không được rỗng.");
        }
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("Giá mua phải lớn hơn 0.");
        }
        if (yearOfUse > Year.now().getValue()) {
            throw new IllegalArgumentException("Năm sử dụng không được lớn hơn năm hiện tại.");
        }

        this.id = id;
        this.name = name;
        this.yearOfUse = yearOfUse;
        this.purchasePrice = purchasePrice;
        this.status = status;
    }

    // Getters 

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYearOfUse() {
        return yearOfUse;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    // Setters 


    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfUse(int yearOfUse) {
        this.yearOfUse = yearOfUse;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public abstract double calculateAnnualMaintenanceCost();

    @Override
    public String toString() {
        return String.format("Device[ID=%s, Tên=%s, Năm SD=%d, Giá mua=%.0f, Trạng thái=%s]",
                id, name, yearOfUse, purchasePrice, status);
    }
}
