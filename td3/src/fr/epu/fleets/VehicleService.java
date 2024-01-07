package fr.epu.fleets;

import fr.epu.vehicles.ElectricVehicle;

public class VehicleService {
    private ElectricVehicle[] availableVehicles;
    private ElectricVehicle[] vehiclesInRepair;

    //private static final int MAX_NB_VEHICLES=100;

    private int maxNbVehicle;

    public VehicleService(int maxNbVehicle) {
        this.maxNbVehicle = maxNbVehicle;
        this.availableVehicles = new ElectricVehicle[maxNbVehicle];
        this.vehiclesInRepair = new ElectricVehicle[maxNbVehicle];
    }

    public ElectricVehicle[] getAvailableVehicles() {
        return availableVehicles;
    }

    public ElectricVehicle[] getVehiclesInRepair() {
        return vehiclesInRepair;
    }

    public int getMaxNbVehicle() {
        return maxNbVehicle;
    }

    public int availablePlaces(ElectricVehicle[] vehicles) {
        int count = 0;
        for (ElectricVehicle vehicle : vehicles) {
            if (vehicle == null) {
                count++;
            }
        }
        return count;
    }

    public void addAvailableVehicle(ElectricVehicle vehicle) {
        if (availablePlaces(this.availableVehicles) > 0) {
            //for(int i=0; i<availableVehicles.length; i++){
            for (int i = 0; i < getMaxNbVehicle(); i++) {
                if (availableVehicles[i] == null) {
                    this.availableVehicles[i] = vehicle;
                    break;
                }
            }

        }
    }

    public void addVehicleInRepair(ElectricVehicle vehicle) {
        if (availablePlaces(this.vehiclesInRepair) > 0) {
            for (int i = 0; i < getMaxNbVehicle(); i++) {
                if (vehiclesInRepair[i] == null) {
                    this.vehiclesInRepair[i] = vehicle;
                    break;
                }
            }
        }
    }

    public boolean isPresentIn(ElectricVehicle ev, ElectricVehicle[] vehicles) {
        boolean res = false;
        for (ElectricVehicle e : vehicles) {
            if (e == ev) {
                res = true;
                break;
            }
        }
        return res;
    }

    public boolean moveVehicleToRepair(ElectricVehicle vehicle) {
        if (isPresentIn(vehicle, this.availableVehicles)) {
            for (int i = 0; i < getMaxNbVehicle(); i++) {
                if (availableVehicles[i] == vehicle) {
                    addVehicleInRepair(vehicle);
                    availableVehicles[i] = null;
                    //chaque voiture devrait être unique sinon pour plus de sécu on ajoute un "break"
                }
            }
            return true;
        }
        return false;
    }

    public boolean moveVehicleToAvailable(ElectricVehicle vehicle) {
        if (isPresentIn(vehicle, this.vehiclesInRepair)) {
            System.out.println("Présent");
            for (int i = 0; i < getMaxNbVehicle(); i++) {
                if (vehiclesInRepair[i] == vehicle) {
                    this.addAvailableVehicle(vehicle);
                    this.vehiclesInRepair[i] = null;
                    //chaque voiture devrait être unique sinon pour plus de sécu on ajoute un "break"
                }
            }
            return true;
        }
        return false;
    }


    public ElectricVehicle findVehicleWithMaxDistance(ElectricVehicle[] vehicles) {
        double vehicleMaxDistance = 0;
        int indice = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && vehicleMaxDistance < vehicles[i].distanceCanMoveWithCurrentCharge()) {
                indice = i;
                vehicleMaxDistance = vehicles[i].distanceCanMoveWithCurrentCharge();
            }
        }
        if (vehicleMaxDistance > 0) {
            return vehicles[indice];
        } else {
            return null;
        }

    }

    public ElectricVehicle findVehicleWithMaxRange(ElectricVehicle[] vehicles) {
        double vehicleMaxRange = 0;
        int indice = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null && vehicleMaxRange < vehicles[i].distanceCanMoveWithBatteryCapacityMax()) {
                indice = i;
                vehicleMaxRange = vehicles[i].distanceCanMoveWithBatteryCapacityMax();
            }
        }
        if(vehicleMaxRange>0){
            return vehicles[indice];
        }else {
            return null;
        }


    }


    public ElectricVehicle findAvailableVehicleWithMaxDistance() {
        return findVehicleWithMaxDistance(this.availableVehicles);
    }


    public ElectricVehicle findVehicleWithMaxRangeInAvailableAndInRepair() {
        ElectricVehicle a = findVehicleWithMaxRange(this.availableVehicles);
        ElectricVehicle b = findVehicleWithMaxRange(this.vehiclesInRepair);

        if(a!=null && b!=null){
            if (a.distanceCanMoveWithBatteryCapacityMax() >= b.distanceCanMoveWithBatteryCapacityMax()) {
                return a;
            } else {
                return b;
            }
        }else if (a!=null && b==null){
            return a;
        }else if (a==null && b!=null){
            return b;
        }
        return null;
    }


    public int getNbOfVehiclesInRepair() {
        //return this.vehiclesInRepair.length-availablePlaces(vehiclesInRepair);
        return this.maxNbVehicle - availablePlaces(vehiclesInRepair);
    }

    public int getNbOfAvailableVehiclesInCharge() {
        int count = 0;
        for (ElectricVehicle vehicle : this.availableVehicles) {
            if (vehicle != null && vehicle.isConnected()) {
                count++;
            }
        }
        return count;
    }

    public int getNbOfAvailableVehicles() {
        return this.maxNbVehicle - availablePlaces(availableVehicles);
    }


}
