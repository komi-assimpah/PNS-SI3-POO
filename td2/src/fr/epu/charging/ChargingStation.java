package fr.epu.charging;

import fr.epu.vehicles.ElectricVehicle;

public class ChargingStation {
    private String stationName;
    private int availableChargingPoints;
    private EnergyProvider energyProvider;

    public ChargingStation(String stationName, int availableChargingPoints, EnergyProvider energyProvider){
        this.stationName = stationName;
        this.availableChargingPoints = availableChargingPoints;
        this.energyProvider = energyProvider;
    }

    public  ChargingStation(String stationName, int availableChargingPoints){
        this(stationName, availableChargingPoints, new EnergyProvider("EDF", "Solar"));
    }

    public String getStationName() {
        return stationName;
    }

    public int getAvailableChargingPoints() {
        return availableChargingPoints;
    }

    public EnergyProvider getEnergyProvider() {
        return energyProvider;
    }

    /**
     * Connects a vehicle to a charging point and charges it to full.
     * @param vehicle the vehicle to charge
     * @return the amount of energy charged to the vehicle
     */
    public double connectToChargingPoint(ElectricVehicle vehicle) {
        double chargeAdded=0;
        if(availableChargingPoints>0 && vehicle.connect()){
            this.availableChargingPoints--;
            chargeAdded = vehicle.chargeToFull();
        }
        return chargeAdded;
    }

    /**
     * disconnect a vehicle from a charging point
     * @param vehicle the vehicle to charge
     * @return the status the attempt
     */
    public boolean disconnectFromChargingPoint(ElectricVehicle vehicle){
        if(vehicle.disConnect()){
            this.availableChargingPoints++;
            return true;
        }
        return false;
    }
}
