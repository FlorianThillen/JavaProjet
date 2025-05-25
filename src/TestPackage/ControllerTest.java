package TestPackage;

import ControllerPackage.Controller;
import ExceptionsPackage.InvalidUserInputException;

import java.util.Calendar;
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
        assertThrows(InvalidUserInputException.class, () -> {
           controller.getRentalsBetweenDates(
                   new java.sql.Date(2020,3,20),
                   new java.sql.Date(2004,6,4)
                   );
        });
    }

    @org.junit.jupiter.api.Test
    void getStationsStatus() {
        assertThrows(InvalidUserInputException.class, () ->
        {
            controller.getStationsStatus(-89, 74);
        });
        assertThrows(InvalidUserInputException.class, () ->
        {
            controller.getStationsStatus(87, 6);
        });
        assertThrows(InvalidUserInputException.class, () ->
        {
            controller.getStationsStatus(64, 3);
        });
        assertThrows(InvalidUserInputException.class, () ->
        {
            controller.getStationsStatus(0, 0);
        });
    }
}