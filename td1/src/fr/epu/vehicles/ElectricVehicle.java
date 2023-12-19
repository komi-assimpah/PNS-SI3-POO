package fr.epu.vehicles;

public class ElectricVehicle {
    private double batteryCapacity;
    private double currentCharge;
    private double energyConsumptionPerKilometer;
    private static final double DEFAULT_ENERGY_CONSUMPTION_PER_KILOMETER=0.2;

    private boolean isConnected = false;


    public double getCurrentCharge() {
        return currentCharge;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getEnergyConsumptionPerKilometer() {
        return energyConsumptionPerKilometer;
    }

    public ElectricVehicle(double batteryCapacity, double energyConsumptionPerKilometer) {
        this.batteryCapacity = batteryCapacity;
        this.currentCharge=0;
        this.energyConsumptionPerKilometer=energyConsumptionPerKilometer;
    }


    /**
     * in case of creation of a vehicle without an ECPK, it put the created vehicle's ECPK at
     * the DEFAULT_ENERGY_CONSUMPTION_PER_KILOMETER value
     * @param batteryCapacity
     */
    public ElectricVehicle(double batteryCapacity) {
        this(batteryCapacity, DEFAULT_ENERGY_CONSUMPTION_PER_KILOMETER);
    }



    /**
     * Q7
     * Charge the battery with the given amount of energy.
     * @param chargeAmount
     * @return true if the battery was charged, false otherwise
     */
    boolean charge(double chargeAmount){
        if(chargeAmount<=0){
            return false;
        }else {
            double toAdd = chargeAmount+this.currentCharge;
            if (toAdd<=batteryCapacity){
                this.currentCharge+=chargeAmount;
                return true;
            }
            return false;
        }
    }

    /**
     * Q8
     * Charge the battery to full capacity.
     * @return the amount of energy that was added to the battery
     */
    public double chargeToFull(){
        double toCharged = this.batteryCapacity-this.currentCharge;
        charge(toCharged);
        return toCharged;
    }

    /**
     * check if vehicle is connected to a CharchingStation
     * @return true if yes, otherwise false
     */
    public boolean isConnected() {
        return isConnected;
    }


    /**
     * try to connect a vehicle to a charging point
     * @return false, if the vehicle was already connected, otherwise return true, if
     * the vehicle has been successfully connected this time
     */


    //teacher's method
    public boolean connect() {
        if (isConnected) {
            return false;
        }
        isConnected = true;
        return true;
    }


    /**
     * discconnect a vehicle from a charging point
     * @return false, if the vehicle was already disconnected, otherwise return true, if
     * the vehicle has been successfully disconnected this time
     */
    public boolean disConnect() {
        if (!isConnected) {
            return false;
        }
        isConnected = false;
        return true;
    }

    //Q40
    public int percentageCharge(){
        return (int) ((currentCharge/batteryCapacity)*100);
    }

    //Q41
    public double distanceCanMoveWithCurrentCharge(){
        return currentCharge/ energyConsumptionPerKilometer;
    }

    //Q42
    public double distanceCanMoveWithBatteryCapacityMax(){
        return this.batteryCapacity/ energyConsumptionPerKilometer;
    }

    public boolean drive(double distance){
        double energyNeededForDistance = distance* getEnergyConsumptionPerKilometer();
        if(energyNeededForDistance<=currentCharge){
            currentCharge-=energyNeededForDistance;
            return true;
        }else {
            return false;
        }
    }
}
