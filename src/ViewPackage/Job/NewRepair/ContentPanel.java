package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    ContentPanelState state;

    public ContentPanel() throws DataAccessException {
//      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setState(new ContentPanelLocality());
    }

    private void setState(ContentPanelState state) throws DataAccessException {
        this.state = state;
        updateInputComponents();
    }

    private void updateInputComponents() {
        removeAll();
        ArrayList<JComponent> comps = state.getInputComponents();
        setLayout(new GridLayout(comps.size() / 2, comps.size() % 3, 0,0));
        for(JComponent comp: comps) {
            add(comp);
            comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        setMaximumSize(getPreferredSize());
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
