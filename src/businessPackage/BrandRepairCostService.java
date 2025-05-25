package BusinessPackage;
import DataAccessPackage.BrandRepairCostDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BrandRepairCostModel;

import java.util.List;
public class BrandRepairCostService {
    private final BrandRepairCostDAO brandRepairCostDAO;

    public BrandRepairCostService(){
        this.brandRepairCostDAO= new BrandRepairCostDAO();
    }
    public List<BrandRepairCostModel> getAverageRepairCostPerBrand()throws DataAccessException {
        return brandRepairCostDAO.getAverageCostPerBrand();
    }
}
