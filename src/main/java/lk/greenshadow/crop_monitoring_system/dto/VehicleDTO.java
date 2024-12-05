package lk.greenshadow.crop_monitoring_system.dto;

import jakarta.validation.constraints.NotEmpty;

public class VehicleDTO {

    @NotEmpty(message = "Code is required")
    private String code;

    @NotEmpty(message = "Plate Number is required")
    private String plateNumber;
    private String category;
    private String fuelType;
    private String staff;
    private String remarks;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
