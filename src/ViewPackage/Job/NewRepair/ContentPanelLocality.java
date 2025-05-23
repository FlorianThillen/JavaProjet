package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelLocality extends ContentPanelState {
    private final JList<LocalityModel> list = new JList<>();

    public ContentPanelLocality() throws DataAccesException {
        LocalityModel[] localities = controller.getLocalities().toArray(LocalityModel[]::new);
        list.setListData(localities);

        ArrayList<JComponent> comps = new ArrayList<>();
        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    ContentPanelState getNextState() throws DataAccesException {
        ContentPanelStation nextState = new ContentPanelStation(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
