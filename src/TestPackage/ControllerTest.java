package TestPackage;

import ControllerPackage.Controller;
import ModelsPackage.BikeModel;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getRentalsBetweenDates() {
        assertThrows(IllegalArgumentException.class, () -> {
           controller.getRentalsBetweenDates(
                   new java.sql.Date(2020,3,20),
                   new java.sql.Date(2004,6,4)
                   );
        });
    }

    @org.junit.jupiter.api.Test
    void getStationsStatus() {
        assertThrows(IllegalArgumentException.class, () ->
        {
            controller.getStationsStatus(-89, 74);
        });
        assertThrows(IllegalArgumentException.class, () ->
        {
            controller.getStationsStatus(87, 6);
        });
        assertThrows(IllegalArgumentException.class, () ->
        {
            controller.getStationsStatus(64, 3);
        });
        assertThrows(IllegalArgumentException.class, () ->
        {
            controller.getStationsStatus(0, 0);
        });
    }

    @org.junit.jupiter.api.Test
    void updateBike() {
        assertThrows(IllegalArgumentException.class, ()->{
            controller.updateBike(new BikeModel());
        });
        assertThrows(IllegalArgumentException.class, ()->{
            controller.updateBike(new BikeModel(
                    64432,
                    true,
                    Date.from(Instant.now()),
                    68,
                    200,
                    null,
                    null
            ));
        });
        assertThrows(IllegalArgumentException.class, ()->{
            controller.updateBike(null);
        });
    }
}