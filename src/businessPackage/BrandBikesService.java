package BusinessPackage;

import DataAccessPackage.BikeDAO;
import DataAccessPackage.BrandDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;

import java.util.ArrayList;
import java.util.Vector;

public class BrandBikesService {
    public Vector<String> getAllBrandNames() throws DataAccessException {
        Vector<String> names = new Vector<>();
        for (BrandModel brand: new BrandDAO().getAllBrands()) {
            names.add(brand.getName());
        }
        return names;
    }

    public ArrayList<BikeModel> getBikesFromBrand(String brandName) throws DataAccessException {
        return new BikeDAO().getBikesFromBrand(brandName);
    }
}
