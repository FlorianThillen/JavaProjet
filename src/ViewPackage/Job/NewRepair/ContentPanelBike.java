package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelBike extends ContentPanelState {
    public ContentPanelBike() {
        setNextState(new ContentPanelMechanic());
    }

    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
