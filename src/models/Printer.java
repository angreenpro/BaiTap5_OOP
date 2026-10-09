package models;

import enums.DeviceStatus;

public class Printer extends Device {

    private String printerType;
    private int pagesPrinted;
    private boolean isColorPrinter;

    public Printer(String id, String name, int yearOfUse, double purchasePrice, DeviceStatus status,
                   String printerType, int pagesPrinted, boolean isColorPrinter) {
        super(id, name, yearOfUse, purchasePrice, status);
        this.printerType = printerType;
        this.pagesPrinted = pagesPrinted;
        this.isColorPrinter = isColorPrinter;
    }

    // Getters

    public String getPrinterType() {
        return printerType;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public boolean isColorPrinter() {
        return isColorPrinter;
    }

    // Setters

    public void setPrinterType(String printerType) {
        this.printerType = printerType;
    }

    public void setPagesPrinted(int pagesPrinted) {
        this.pagesPrinted = pagesPrinted;
    }

    public void setColorPrinter(boolean colorPrinter) {
        this.isColorPrinter = colorPrinter;
    }

    // 4% giá mua + 500k nếu in > 100k trang + 300k nếu in màu
    @Override
    public double calculateAnnualMaintenanceCost() {
        double cost = getPurchasePrice() * 0.04;
        if (pagesPrinted > 100000) {
            cost += 500000;
        }
        if (isColorPrinter) {
            cost += 300000;
        }
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Printer[ID=%s, Tên=%s, Loại=%s, Số trang=%d, In màu=%s]",
                getId(), getName(), printerType, pagesPrinted, isColorPrinter ? "Có" : "Không");
    }
}
