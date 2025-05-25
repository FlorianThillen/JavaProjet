package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelBike extends ContentPanelState {
    private final JComboBox<BikeModel> comBox = new JComboBox<>();

    public ContentPanelBike(StationModel station) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        String text = String.format("Vélos de %s : ", station.getName());
        comps.add(new JLabel(text));

        BikeModel[] bikes = controller.getBikesFromStation(station).toArray(BikeModel[]::new);
        for(BikeModel bike: bikes)
            comBox.addItem(bike);

        comps.add(comBox);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelRepair nextState = new ContentPanelRepair((BikeModel) comBox.getSelectedItem());
        nextState.setPreviousState(this);
        return nextState;
    }
}
