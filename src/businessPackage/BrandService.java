package businessPackage;

import DataAccesPackage.BrandDAO;
import DataAccesPackage.StationDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandModel;

import java.util.List;

public class BrandService {
    private final BrandDAO brandDAO;

    public BrandService(){
        this.brandDAO =new BrandDAO();
    }


    public List<BrandModel> getAllBrands() throws DataAccesException {
        return brandDAO.getAllBrands();
    }


}
