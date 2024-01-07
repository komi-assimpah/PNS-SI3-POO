package fr.epu.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricCarTest extends ElectricVehicleTest {

    ElectricCar car;
    final String licensePlate = "AB-123-CD";
    final String brand = "Tesla";
    final String model = "Model S";

    @Override
    @BeforeEach
    void setUp() {
        car = new ElectricCar(batteryCapacity, "AB-123-CD", "Tesla Model S");
        ev = new ElectricCar(batteryCapacity, licensePlate);
    }

    @Test
    void testInitCar() {
        assertEquals(0, car.getCurrentCharge());
        assertEquals(licensePlate, car.getLicensePlate());
        assertFalse(car.isOnCoolingSystem());
        assertEquals(brand + " " + model, car.getModel());
        assertEquals("undefined", ((ElectricCar) ev).getModel());

    }

    @Test
    void testTurnOnCoolingSystem() {
        assertFalse(car.isOnCoolingSystem());
        car.turnOnCoolingSystem();
        assertTrue(car.isOnCoolingSystem());
        car.turnOffCoolingSystem();
        assertFalse(car.isOnCoolingSystem());
    }


    @Test
    void testGetEnergyConsumptionPerKilometer() {
        double energyPerKm = car.getEnergyConsumptionPerKilometer();
        assertEquals(0.2, energyPerKm);
        car.turnOnCoolingSystem();
        assertEquals(0.2 * ElectricCar.COOLING_SYSTEM_FACTOR,
                car.getEnergyConsumptionPerKilometer());
        assertTrue(energyPerKm < car.getEnergyConsumptionPerKilometer());
        car.turnOffCoolingSystem();
        assertEquals(0.2, car.getEnergyConsumptionPerKilometer());
    }


    @Test
    void testInitialiseEC(){
        assertEquals(0,ev.getCurrentCharge());
        assertEquals(30, car.getBatteryCapacity());
        assertEquals("Tesla Model S", car.getModel());
        assertEquals("AB-123-CD", car.getLicensePlate());
    }

    @Test
    void testInitialiseWithoutModel(){
        car = new ElectricCar(batteryCapacity, "AF-32-X5");
        assertEquals("undefined", car.getModel());
        assertFalse(car.isOnCoolingSystem());
    }

    @Test
    void testCoolingSystem(){
        assertEquals(0.2,car.getEnergyConsumptionPerKilometer());
        assertFalse(car.isOnCoolingSystem());
        car.turnOnCoolingSystem();
        assertTrue(car.isOnCoolingSystem());
        assertEquals(0.24, car.getEnergyConsumptionPerKilometer());
        car.turnOffCoolingSystem();
        assertFalse(car.isOnCoolingSystem());
        assertEquals(0.2, car.getEnergyConsumptionPerKilometer());
    }

    @Test
    void testMaxRangeOfCar() {
        double maxRange = car.distanceCanMoveWithBatteryCapacityMax();
        assertEquals(150, maxRange);
        car.turnOnCoolingSystem();
        assertTrue(maxRange > car.distanceCanMoveWithBatteryCapacityMax());
        assertEquals(150 / ElectricCar.COOLING_SYSTEM_FACTOR,
                car.distanceCanMoveWithBatteryCapacityMax());
        car.turnOffCoolingSystem();
        assertEquals(150, car.distanceCanMoveWithBatteryCapacityMax());
    }



}
