package BusinessPackage;

import DataAccessPackage.BrandDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BrandModel;

import java.util.List;

public class BrandService {
    private final BrandDAO brandDAO;

    public BrandService(){
        this.brandDAO = new BrandDAO();
    }


    public List<BrandModel> getAllBrands() throws DataAccessException {
        return brandDAO.getAllBrands();
    }


}
