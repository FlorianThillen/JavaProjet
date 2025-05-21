package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelStatus extends ContentPanelState{
    public ContentPanelStatus() {
        setNextState(new ContentPanelPrice());
    }

    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
