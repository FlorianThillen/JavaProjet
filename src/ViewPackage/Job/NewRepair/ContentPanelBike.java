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

        comps.add(new JLabel("Localité choisie : "));
        comps.add(new JLabel(station.getLocality().getName()));

        comps.add(new JLabel("Station choisie : "));
        comps.add(new JLabel(station.getName()));

        String text = String.format("Vélos de %s : ", station.getName());
        comps.add(new JLabel(text));

        BikeModel[] bikes = controller.getBikesFromStation(station).toArray(BikeModel[]::new);
        if (bikes.length > 0) {
            for(BikeModel bike: bikes)
                comBox.addItem(bike);

            comps.add(comBox);
        } else {
            comps.add(new JLabel("Aucun vélo disponible."));
        }
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        try {
            ContentPanelRepair nextState = new ContentPanelRepair((BikeModel) comBox.getSelectedItem());
            nextState.setPreviousState(this);
            return nextState;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Aucun vélo n'a été choisi!");
            return null;
        }
    }
}
