package fr.epu.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.epu.vehicles.ElectricVehicle;

import static org.junit.jupiter.api.Assertions.*;

class ElectricVehicleTest {
    ElectricVehicle ev;
    double batteryCapacity = 30;

    @BeforeEach
    void setUp(){
        ev = new ElectricVehicle(30);
    }

    @Test
    void testInitialiseVE() {
        assertEquals(30, ev.getBatteryCapacity());//verifie l’égalité
        assertEquals(0, ev.getCurrentCharge());
    }

    @Test
    void testChargeValid() {
        assertTrue((ev.charge( 10)));
        assertEquals(10,ev.getCurrentCharge());
        assertTrue((ev.charge( 20)));
        assertEquals(30,ev.getCurrentCharge());
    }

    @Test
    void testChargeNotValid() {
        assertFalse((ev.charge( 100)));
        assertEquals(0,ev.getCurrentCharge());
        assertTrue((ev.charge( 10)));
        assertEquals(10,ev.getCurrentCharge());
        assertFalse((ev.charge( 21)));
        assertEquals(10,ev.getCurrentCharge());
    }

    @org.junit.jupiter.api.Test
    void testCheckChargeParameter() {
        assertFalse((ev.charge(-10)));
        assertEquals(0, ev.getCurrentCharge());
    }
    @org.junit.jupiter.api.Test
    void testChargeWith0() {
        assertFalse((ev.charge(0)));
        assertEquals(0, ev.getCurrentCharge());
    }

    @Test
    void testChargeToFull() {
        double charge = ev.chargeToFull();
        assertEquals(30, ev.getCurrentCharge());
        assertEquals(30, charge);
        ev = new ElectricVehicle(30);
        ev.charge(10);
        charge= ev.chargeToFull();
        assertEquals(30, ev.getCurrentCharge());
        assertEquals(20, charge);
    }

    @Test
    void testGetEnergyConsumptionPerKilometer() {
        assertEquals(0.2, ev.getEnergyConsumptionPerKilometer());
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
        assertTrue((ev.charge( 15)));
        assertEquals(50, ev.percentageCharge());
        assertTrue((ev.charge( 5)));
        assertEquals(66, ev.percentageCharge());
    }

    @Test
    void distanceCanMoveWithCurrentCharge() {
        assertTrue((ev.charge( 10)));
        assertEquals(50, ev.distanceCanMoveWithCurrentCharge());

    }

    @Test
    void distanceCanMoveWithBatteryCapacityMax() {
        assertTrue((ev.charge( 10)));
        assertEquals(150, ev.distanceCanMoveWithBatteryCapacityMax());
    }


    @Test
    void drive() {
        assertTrue((ev.charge( 10)));
        assertTrue( ev.drive(40));
        assertFalse( ev.drive(60));
    }

}
