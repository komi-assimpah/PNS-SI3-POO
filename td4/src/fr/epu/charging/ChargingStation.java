package fr.epu.charging;

public class ChargingStation{
    private String stationName;
    private int availableChargingPoints;
    private EnergyProvider energyProvider;

    //Q26
    public ChargingStation(String stationName, int availableChargingPoints, EnergyProvider energyProvider){
        this.stationName = stationName;
        this.availableChargingPoints = availableChargingPoints;
        this.energyProvider = energyProvider;
    }

    //Q26
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
     * Q27
     * Connects a vehicle to a charging point and charges it to full.
     * @param vehicle the vehicle to charge
     * @return the amount of energy charged to the vehicle
     */
    public double connectToChargingPoint(ChargeableItem vehicle) {
        double chargeAdded=0;
        if(availableChargingPoints>0 && vehicle.connect()){
            this.availableChargingPoints--;
            chargeAdded = vehicle.chargeToFull();
        }
        return chargeAdded;
    }

    /**
     * Q30
     * disconnect a vehicle from a charging point
     * @param vehicle the vehicle to charge
     * @return the status the attempt
     */
    public boolean disconnectFromChargingPoint(ChargeableItem vehicle){
        if(vehicle.disConnect()){
            this.availableChargingPoints++;
            return true;
        }
        return false;
    }
}
