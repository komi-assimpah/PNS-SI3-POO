package fr.epu;

import java.util.logging.Logger;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Utilisation du logger au lieu de System.out.println
        LOGGER.info("Hello world!");
    }
}
