package TestPackage;

import ModelsPackage.RepairModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepairModelTest {
    private RepairModel repair;

    @BeforeEach
    void setUp() {
        repair = new RepairModel();
    }

    @Test
    void setCost() {
        int testCost = 432;
        repair.setCost(testCost);
        assertEquals(testCost, repair.getCost());

        assertThrows(IllegalArgumentException.class,()->{
            repair.setCost(-9847);
        });
    }
}