package fr.epu.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {
    Drone dronee;

    @BeforeEach
    void setUp(){
        dronee = new Drone(30);
    }

    @Test
    void testInitialiseDrone() {
        Drone drone = new Drone(30, 0.2);
        assertEquals(30, drone.getBatteryCapacity());
        assertEquals(0, drone.getCurrentCharge());
        assertEquals(0.2, drone.getEnergyConsumptionPerKilometer());
    }


    @Test
    void testInitialiseVE() {
        assertEquals(30, dronee.getBatteryCapacity());//verifie l’égalité
        assertEquals(0, dronee.getCurrentCharge());
    }

    @Test
    void testChargeValid() {
        assertTrue((dronee.charge( 10)));
        assertEquals(10,dronee.getCurrentCharge());
        assertTrue((dronee.charge( 20)));
        assertEquals(30,dronee.getCurrentCharge());
    }

    @Test
    void testChargeNotValid() {
        assertFalse((dronee.charge( 100)));
        assertEquals(0,dronee.getCurrentCharge());
        assertTrue((dronee.charge( 10)));
        assertEquals(10,dronee.getCurrentCharge());
        assertFalse((dronee.charge( 21)));
        assertEquals(10,dronee.getCurrentCharge());
    }

    @Test
    void testCheckChargeParameter() {
        assertFalse((dronee.charge(-10)));
        assertEquals(0, dronee.getCurrentCharge());
    }
    @Test
    void testChargeWith0() {
        assertFalse((dronee.charge(0)));
        assertEquals(0, dronee.getCurrentCharge());
    }

    @Test
    void testChargeToFull() {
        double charge = dronee.chargeToFull();
        assertEquals(30, dronee.getCurrentCharge());
        assertEquals(30, charge);
        dronee = new Drone(30);
        dronee.charge(10);
        charge= dronee.chargeToFull();
        assertEquals(30, dronee.getCurrentCharge());
        assertEquals(20, charge);
    }

    @Test
    void testGetEnergyConsumptionPerKilometer() {
        assertEquals(0.2, dronee.getEnergyConsumptionPerKilometer());
    }

    @Test
    void testConnexions() {
        ElectricVehicle cityCar = new ElectricVehicle(30);
        assertFalse(cityCar.isConnected());
        assertTrue(cityCar.connect());
        assertTrue(cityCar.isConnected());
        assertFalse(cityCar.connect());
        assertTrue(cityCar.isConnected());
        cityCar.disConnect();
        assertFalse(cityCar.isConnected());
        assertFalse(cityCar.disConnect());
        assertFalse(cityCar.isConnected());
    }

    @Test
    void testGetPercentageCharge() {
        assertTrue((dronee.charge( 15)));
        assertEquals(50, dronee.percentageCharge());
        assertTrue((dronee.charge( 5)));
        assertEquals(66, dronee.percentageCharge());
    }
}
