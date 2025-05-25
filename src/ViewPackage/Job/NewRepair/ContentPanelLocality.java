package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelLocality extends ContentPanelState {
    private final JList<LocalityModel> list = new JList<>();

    public ContentPanelLocality() throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        comps.add(new JLabel("Localités disponibles : "));

        LocalityModel[] localities = controller.getLocalities().toArray(LocalityModel[]::new);
        list.setListData(localities);

        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelStation nextState = new ContentPanelStation(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
