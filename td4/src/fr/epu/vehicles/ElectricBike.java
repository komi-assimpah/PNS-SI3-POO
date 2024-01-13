package fr.epu.vehicles;

import fr.epu.rentals.RentableItem;

public class ElectricBike extends ElectricVehicle implements RentableItem {
    private int DEFAULT_PEDAL_ASSIST_LEVEL = 0;
    private int pedalAssitLevel;
    private double[] energyConsumptionLevels;
    private boolean isAvailable;

    private String identifier;
    private static final  String PREFIX = "EB-";
    private static int nextIdentifier = 1;



    /**
     * Resets the identifier counter to 1.
     * This method is useful for testing.
     */
    protected static void resetIdentifier() {
        nextIdentifier = 1;
    }


    public ElectricBike(double batteryCapacity, double[] energyConsumptionLevels) {
        super(batteryCapacity);
        this.energyConsumptionLevels = energyConsumptionLevels;
        this.pedalAssitLevel = DEFAULT_PEDAL_ASSIST_LEVEL;


        isAvailable = true;
        this.identifier = PREFIX + nextIdentifier;
        nextIdentifier++;
    }



    public int getNumberOfAvailableLevels(){
        return energyConsumptionLevels.length;
    }

    public int getPedalAssistLevel() {
        return pedalAssitLevel;
    }

    public void setPedalAssistLevel(int newAssitLevel) {
        int nbLevel = this.getNumberOfAvailableLevels();
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

    @Override
    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String getName() {
        return identifier;
    }

    /**
     * Returns true if the description contains the identifier or the description contains the number of energy consumption levels.
     * @param description
     * @return true if the description contains the identifier or the description contains the number of energy consumption levels.
     */
    @Override
    public boolean match(String description) {
        return description.contains(identifier) ||
                description.contains(String.valueOf(this.energyConsumptionLevels.length ) ) ||
                description.contains("*");
    }
}
