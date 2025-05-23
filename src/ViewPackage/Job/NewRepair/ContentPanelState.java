package ViewPackage.Job.NewRepair;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ContentPanelState {
    final Controller controller = new Controller();
    private ContentPanelState previousState;
    private ArrayList<JComponent> inputComponents;

    public void setInputComponents(ArrayList<JComponent> inputComponents) {
        this.inputComponents = inputComponents;
    }

    public ArrayList<JComponent> getInputComponents() throws DataAccesException { return inputComponents; };

    public void setPreviousState(ContentPanelState previousState) {
        this.previousState = previousState;
    }

    abstract ContentPanelState getNextState() throws DataAccesException;

    public ContentPanelState getPreviousState() {
        return previousState;
    }
}
