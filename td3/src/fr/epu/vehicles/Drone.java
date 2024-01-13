package fr.epu.vehicles;

import fr.epu.tracking.Position;
import fr.epu.tracking.Trackable;


public class Drone extends ElectricVehicle implements Trackable {
    private boolean isFlying;
    private static final Position BASE_POSITION = new Position(0, 0);

    public Drone(double batteryCapacity, double energyConsumptionPerKilometer) {
        super(batteryCapacity, energyConsumptionPerKilometer);
        this.isFlying = false;
    }

    public Drone(double batteryCapacity) {
        super(batteryCapacity);
        this.isFlying = false;
    }

    @Override
    public Position getPosition() {
        Position p = new Position(Math.random(), Math.random());
        if (isFlying) {
            return p;
        }
        else
            return BASE_POSITION;
    }



    public void takeOff() {
        isFlying = true;
    }
    public void returnToBase() {
        isFlying = false;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
