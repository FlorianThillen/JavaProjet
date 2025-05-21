package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;

import javax.swing.*;
import java.util.Arrays;
import java.util.Vector;

public class ContentPanel extends JPanel {
    ContentPanelState state;
    JList<String> list = new JList<>();

    public ContentPanel() throws DataAccesException {
        add(list);
        setState(new ContentPanelLocality());
    }

    public void setState(ContentPanelState state) throws DataAccesException {
        this.state = state;
        list.setListData(state.getChoices());
    }

    public void goNextState() {
        state.
    }

    public void goPreviousState() {

    }
}
