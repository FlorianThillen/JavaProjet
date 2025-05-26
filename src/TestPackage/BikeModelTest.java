package TestPackage;

import ModelsPackage.BikeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeModelTest {
    private BikeModel bike;

    @BeforeEach
    void setUp() {
        bike = new BikeModel();
    }

    @Test
    void setSerialNumber() {
        final int testSerialNumber = 2;
        bike.setSerialNumber(testSerialNumber);
        assertEquals(bike.getSerialNumber(), testSerialNumber);

        assertThrows(IllegalArgumentException.class, ()->{
            bike.setSerialNumber(-2);
        });
    }

    @Test
    void setBatteryLevel() {
        final int testBatteryLevel = 20;
        bike.setBatteryLevel(testBatteryLevel);
        assertEquals(bike.getBatteryLevel(), testBatteryLevel);

        assertThrows(IllegalArgumentException.class, ()->{
            bike.setBatteryLevel(-80);
        });
        assertThrows(IllegalArgumentException.class, ()->{
            bike.setBatteryLevel(41892);
        });
    }

    @Test
    void setNbKilometers() {
        final int testKmCount = 20;
        bike.setBatteryLevel(testKmCount);
        assertEquals(bike.getBatteryLevel(), testKmCount);

        assertThrows(IllegalArgumentException.class, ()->{
            bike.setNbKilometers(-80);
        });
    }
}