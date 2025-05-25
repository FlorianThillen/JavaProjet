package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;

import javax.swing.*;

public class ContentPanel extends JPanel {
    ContentPanelState state;

    public ContentPanel() throws DataAccessException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setState(new ContentPanelLocality());
    }

    private void setState(ContentPanelState state) throws DataAccessException {
        this.state = state;
        updateInputComponents();
    }

    private void updateInputComponents() throws DataAccessException {
        removeAll();
        for(JComponent comp: state.getInputComponents()) {
            add(comp);
        }
        revalidate();
        repaint();
    }

    public void goNextState() throws DataAccessException {
        ContentPanelState nextState = state.getNextState();
        if (nextState != null) setState(nextState);
    }

    public void goPreviousState() throws DataAccessException {
        ContentPanelState previousState = state.getPreviousState();
        if (previousState != null) setState(previousState);
    }
}
