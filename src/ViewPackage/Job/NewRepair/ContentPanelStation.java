package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelStation extends ContentPanelState{
    public ContentPanelStation() {
        setNextState(new ContentPanelBike());
    }

    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
