package ViewPackage.Job.NewRepair;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelState {
    final Controller controller = new Controller();
    private ContentPanelState previousState;
    private ArrayList<JComponent> inputComponents;

    public void setInputComponents(ArrayList<JComponent> inputComponents) {
        this.inputComponents = inputComponents;
    }

    public ArrayList<JComponent> getInputComponents() { return inputComponents; };

    public void setPreviousState(ContentPanelState previousState) {
        this.previousState = previousState;
    }

    public ContentPanelState getNextState() throws DataAccessException {
        return null;
    };

    public ContentPanelState getPreviousState() {
        return previousState;
    }
}
