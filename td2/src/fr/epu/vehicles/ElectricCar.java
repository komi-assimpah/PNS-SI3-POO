package fr.epu.vehicles;

public class ElectricCar extends ElectricVehicle{
    private boolean coolingSystemActive;
    public static final double COOLING_SYSTEM_FACTOR = 1.2;
    private String licensePlate;
    private String model;


    public ElectricCar(double batteryCapacity, String licensePlate, String model) {
        super(batteryCapacity);
        this.coolingSystemActive=false;
        this.licensePlate = licensePlate;
        this.model = model;

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



}
