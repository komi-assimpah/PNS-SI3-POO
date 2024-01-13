package fr.epu.charging;

public interface ChargeableItem {
    double chargeToFull();

    boolean connect();

    boolean disConnect();

    boolean isConnected();
}
