package TestPackage;

import ModelsPackage.BrandModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandModelTest {
    private BrandModel brand;

    @BeforeEach
    void setUp() {
        brand = new BrandModel();
    }

    @Test
    void setWarrantyDuration() {
        int testWarrantyDuration = 4;
        brand.setWarrantyDuration(testWarrantyDuration);
        assertEquals(testWarrantyDuration, brand.getWarrantyDuration());

        assertThrows(IllegalArgumentException.class, ()->{
           brand.setWarrantyDuration(-4);
        });
    }
}