package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

public class ContentPanelLocality extends ContentPanelState {
    public ContentPanelLocality() {
        setNextState(new ContentPanelStation());
    }

    @Override
    public String[] getChoices() throws DataAccesException {
        return controller.getLocalityNames();
    }
}
