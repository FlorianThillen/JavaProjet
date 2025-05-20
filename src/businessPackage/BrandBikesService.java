package businessPackage;

import DataAccesPackage.BikeDAO;
import DataAccesPackage.BrandDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;

import java.util.Vector;

public class BrandBikesService {
    public Vector<String> getAllBrandNames() throws DataAccesException {
        Vector<String> names = new Vector<>();
        for (BrandModel brand: new BrandDAO().getAllBrands()) {
            names.add(brand.getName());
        }
        return names;
    }

    public Vector<BikeModel> getBikesFromBrand(String brandName) throws DataAccesException {
        return new BikeDAO().getBikesFromBrand(brandName);
    }
}
