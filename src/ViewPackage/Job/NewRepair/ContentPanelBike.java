package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelBike extends ContentPanelState {
    private final JList<BikeModel> list = new JList<>();

    public ContentPanelBike(StationModel station) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        String text = String.format("Vélos de %s : ", station.getName());
        comps.add(new JLabel(text));

        BikeModel[] bikes = controller.getBikesFromStation(station).toArray(BikeModel[]::new);
        list.setListData(bikes);

        comps.add(list);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelRepair nextState = new ContentPanelRepair(list.getSelectedValue());
        nextState.setPreviousState(this);
        return nextState;
    }
}
