package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

import javax.swing.*;

public class ContentPanelMechanic extends ContentPanelState {
    public ContentPanelMechanic() {
        setNextState(new ContentPanelStatus());
    }

    @Override
    public JComponent getInputComponent() throws DataAccesException {
        return null;
    }
}
