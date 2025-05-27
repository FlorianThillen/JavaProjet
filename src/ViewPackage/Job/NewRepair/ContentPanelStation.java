package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelStation extends ContentPanelState {
    private final JComboBox<StationModel> comBox = new JComboBox<>();

    public ContentPanelStation(LocalityModel locality) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        comps.add(new JLabel("Localité choisie : "));
        comps.add(new JLabel(locality.getName()));
        String text = String.format("Stations de %s : ", locality.getName());
        comps.add(new JLabel(text));

        StationModel[] stations = controller.getStationsFromLocality(locality).toArray(StationModel[]::new);
        for(StationModel station: stations)
            comBox.addItem(station);

        comps.add(comBox);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelBike nextState = new ContentPanelBike((StationModel) comBox.getSelectedItem());
        nextState.setPreviousState(this);
        return nextState;
    }
}
