package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelPrice extends ContentPanelState {
    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
