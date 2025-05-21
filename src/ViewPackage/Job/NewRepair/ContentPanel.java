package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

import javax.swing.*;

public class ContentPanel extends JPanel {
    ContentPanelState state;
    JComponent inputComponent;

    public ContentPanel() throws DataAccesException {
        setState(new ContentPanelLocality());
    }

    private void setState(ContentPanelState state) throws DataAccesException {
        this.state = state;
        updateInputComponent();
    }

    private void updateInputComponent() throws DataAccesException {
        if (inputComponent != null) remove(inputComponent);
        inputComponent = state.getInputComponent();
        add(inputComponent);
        revalidate();
        repaint();
    }

    public void goNextState() throws DataAccesException {
        ContentPanelState nextState = state.getNextState();
        if (nextState != null) setState(nextState);
    }

    public void goPreviousState() throws DataAccesException {
        ContentPanelState previousState = state.getPreviousState();
        if (previousState != null) setState(previousState);
    }
}
