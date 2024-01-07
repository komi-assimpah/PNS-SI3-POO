package fr.epu.vehicles;

public class ElectricBike extends ElectricVehicle {
    private int DEFAULT_PEDAL_ASSIST_LEVEL = 0;
    private int pedalAssitLevel;
    private double[] energyConsumptionLevels;


    public ElectricBike(double batteryCapacity, double[] energyConsumptionLevels) {
        super(batteryCapacity);
        this.energyConsumptionLevels = energyConsumptionLevels;
        this.pedalAssitLevel = DEFAULT_PEDAL_ASSIST_LEVEL;
    }


    public int getNumberOfAvailableLevels(){
        return energyConsumptionLevels.length;
    }

    public int getPedalAssistLevel() {
        return pedalAssitLevel;
    }

    public void setPedalAssistLevel(int newAssitLevel) {
        int nbLevel = getNumberOfAvailableLevels();
        if(newAssitLevel >= nbLevel){
            this.pedalAssitLevel = nbLevel;
        }else if (newAssitLevel<=0){
            this.pedalAssitLevel = 0;
        }else {
            this.pedalAssitLevel = newAssitLevel;
        }
    }

    @Override
    public double getEnergyConsumptionPerKilometer(){
        double eCPk = getEnergyConsumptionForAssistLevel(pedalAssitLevel);
        return eCPk;
    }


    public double[] getEnergyConsumptionLevels() {
        return energyConsumptionLevels;
    }

    public double getEnergyConsumptionForAssistLevel(int level) {
        return energyConsumptionLevels[level];
    }
}
