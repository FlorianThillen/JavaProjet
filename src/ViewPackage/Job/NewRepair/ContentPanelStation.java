package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelStation extends ContentPanelState {
    private final JList<StationModel> list = new JList<>();

    public ContentPanelStation(LocalityModel locality) throws DataAccesException {
        StationModel[] stations = controller.getStationsFromLocality(locality).toArray(StationModel[]::new);
        list.setListData(stations);

        ArrayList<JComponent> comps = new ArrayList<>();
        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    ContentPanelState getNextState() throws DataAccesException {
        ContentPanelBike nextState = new ContentPanelBike(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
