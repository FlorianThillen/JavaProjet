package ViewPackage.Job.NewRepair;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;

import java.awt.desktop.AppReopenedEvent;

public abstract class ContentPanelState {
    final Controller controller = new Controller();
    private ContentPanelState previousState;
    private ContentPanelState nextState;

    abstract String[] getChoices() throws DataAccesException;

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
}
