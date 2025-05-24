package ViewPackage.Job.NewRepair;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelSuccess extends ContentPanelState {
    public ContentPanelSuccess() {

        JLabel label = new JLabel("Successfully saved a new repair into the database");

        ArrayList<JComponent> comps = new ArrayList<>();
        comps.add(label);
        setInputComponents(comps);
    }
}
