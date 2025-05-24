package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelBike extends ContentPanelState {
    private final JList<BikeModel> list = new JList<>();

    public ContentPanelBike(StationModel station) throws DataAccesException {
        BikeModel[] bikes = controller.getBikesFromStation(station).toArray(BikeModel[]::new);
        list.setListData(bikes);

        ArrayList<JComponent> comps = new ArrayList<>();
        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccesException {
        ContentPanelRepair nextState = new ContentPanelRepair(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
