package fr.epu.vehicles;

import fr.epu.rentals.RentableItem;

public class ElectricCar extends ElectricVehicle implements RentableItem {
    private boolean coolingSystemActive;
    public static final double COOLING_SYSTEM_FACTOR = 1.2;
    private String licensePlate;
    private String model;
    private boolean isAvailable;



    public ElectricCar(double batteryCapacity, String licensePlate, String model) {
        super(batteryCapacity);
        this.coolingSystemActive=false;
        this.licensePlate = licensePlate;
        this.model = model;
        this.isAvailable=true;

    }

    public ElectricCar(double batteryCapacity, String licensePlate) {
        this(batteryCapacity, licensePlate, "undefined");
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }


    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }


    public boolean isOnCoolingSystem() {
        return coolingSystemActive;
    }


    public boolean turnOnCoolingSystem(){
        if(!this.coolingSystemActive){
            this.coolingSystemActive=true;
            return true;
        }else {
            return false;
        }
    }

    public boolean turnOffCoolingSystem(){
        if(this.coolingSystemActive){
            this.coolingSystemActive=false;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public double getEnergyConsumptionPerKilometer(){
        double currentEnergyConsumption = super.getEnergyConsumptionPerKilometer();
        if(coolingSystemActive){
            return currentEnergyConsumption * COOLING_SYSTEM_FACTOR;
        }else {
            return currentEnergyConsumption;
        }
    }

    /* ********** */
    /* RentableItem    */
    /* ********** */
    @Override
    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Override
    public String getName() {
        return getLicensePlate();
    }

    @Override
    public boolean match(String description) {
        return getModel().contains(description) || getLicensePlate().contains(description);
    }


}
