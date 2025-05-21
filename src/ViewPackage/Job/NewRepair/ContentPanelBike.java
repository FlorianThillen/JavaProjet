package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelBike extends ContentPanelState {
    public ContentPanelBike() {
        setNextState(new ContentPanelMechanic());
    }

    @Override
    public JComponent getInputComponent() throws DataAccesException {
        return null;
    }
}
