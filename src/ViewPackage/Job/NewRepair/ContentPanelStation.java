package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelStation extends ContentPanelState {
    private final JList<StationModel> list = new JList<>();

    public ContentPanelStation(LocalityModel locality) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        String text = String.format("Stations de %s : ", locality.getName());
        comps.add(new JLabel(text));

        StationModel[] stations = controller.getStationsFromLocality(locality).toArray(StationModel[]::new);
        list.setListData(stations);

        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelBike nextState = new ContentPanelBike(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
