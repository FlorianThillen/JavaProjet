package businessPackage;

import DataAccesPackage.LocalityDAO;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewRepairService {
    public String[] getLocalityNames() throws DataAccesException {
        ArrayList<String> localityNames = new ArrayList<>();

        ArrayList<LocalityModel> localities = new LocalityDAO().selectAllLocalities();
        Stream<String> stream = localities.stream().map(LocalityModel::getName);
        localityNames = stream.collect(Collectors.toCollection(ArrayList::new));

        return localityNames.toArray(new String[0]);
    }
}
