package TestPackage;

import ModelsPackage.StationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationModelTest {
    private StationModel station;

    @BeforeEach
    void setUp() {
        station = new StationModel();
    }

    @Test
    void setStreetNumber() {
        int testStreetNum = 20;
        station.setStreetNumber(testStreetNum);
        assertEquals(testStreetNum, station.getStreetNumber());

        assertThrows(IllegalArgumentException.class,()->{
            station.setStreetNumber(-341);
        });
    }
}