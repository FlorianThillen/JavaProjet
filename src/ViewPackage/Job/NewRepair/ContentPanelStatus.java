package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

import javax.swing.*;

public class ContentPanelStatus extends ContentPanelState{
    public ContentPanelStatus() {
        setNextState(new ContentPanelPrice());
    }

    @Override
    public JComponent getInputComponent() throws DataAccesException {
        return null;
    }
}
