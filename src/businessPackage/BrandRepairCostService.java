package businessPackage;
import DataAccesPackage.BrandRepairCostDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BrandRepairCostModel;

import javax.xml.crypto.Data;
import java.util.List;
public class BrandRepairCostService {
    private final BrandRepairCostDAO brandRepairCostDAO;

    public BrandRepairCostService(){
        this.brandRepairCostDAO= new BrandRepairCostDAO();
    }
    public List<BrandRepairCostModel> getAverageRepairCostPerBrand()throws DataAccesException{
        return brandRepairCostDAO.getAverageCostPerBrand();
    }
}
