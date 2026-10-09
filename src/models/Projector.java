package models;

import enums.DeviceStatus;
import java.time.Year;

public class Projector extends Device {

    private int brightness;
    private int lampHoursUsed;

    public Projector(String id, String name, int yearOfUse, double purchasePrice, DeviceStatus status,
                     int brightness, int lampHoursUsed) {
        super(id, name, yearOfUse, purchasePrice, status);
        this.brightness = brightness;
        this.lampHoursUsed = lampHoursUsed;
    }

    // Getters

    public int getBrightness() {
        return brightness;
    }

    public int getLampHoursUsed() {
        return lampHoursUsed;
    }

    // Setters

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setLampHoursUsed(int lampHoursUsed) {
        this.lampHoursUsed = lampHoursUsed;
    }

    // 3% giá mua + 1tr5 nếu dùng bóng > 3000 giờ
    @Override
    public double calculateAnnualMaintenanceCost() {
        double cost = getPurchasePrice() * 0.03;
        if (lampHoursUsed > 3000) {
            cost += 1500000;
        }
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Projector[ID=%s, Tên=%s, Độ sáng=%d lumens, Giờ bóng đèn=%d]",
                getId(), getName(), brightness, lampHoursUsed);
    }
}
