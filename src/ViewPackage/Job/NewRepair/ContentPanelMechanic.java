package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelMechanic extends ContentPanelState {
    public ContentPanelMechanic() {
        setNextState(new ContentPanelStatus());
    }

    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
