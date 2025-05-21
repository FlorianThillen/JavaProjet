package ViewPackage.Job.NewRepair;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;

import javax.swing.*;

public abstract class ContentPanelState {
    final Controller controller = new Controller();
    private ContentPanelState previousState;
    private ContentPanelState nextState;
    private Object inputValue;

    abstract JComponent getInputComponent() throws DataAccesException;

    public void setNextState(ContentPanelState nextState) {
        this.nextState = nextState;
        nextState.setPreviousState(this);
    }

    public void setPreviousState(ContentPanelState previousState) {
        this.previousState = previousState;
    }

    public ContentPanelState getNextState() {
        return nextState;
    }

    public ContentPanelState getPreviousState() {
        return previousState;
    }

    public Object getInputValue() {
        return inputValue;
    }

    public void setInputValue(Object inputValue) {
        this.inputValue = inputValue;
    }
}
