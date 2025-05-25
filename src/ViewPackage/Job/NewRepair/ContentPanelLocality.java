package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelLocality extends ContentPanelState {
    private final JComboBox<LocalityModel> comboBox = new JComboBox<>();

    public ContentPanelLocality() throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        comps.add(new JLabel("Localités disponibles : "));

        LocalityModel[] localities = controller.getLocalities().toArray(LocalityModel[]::new);
        for (LocalityModel locality: localities)
            comboBox.addItem(locality);

        comps.add(comboBox);
        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        ContentPanelStation nextState = new ContentPanelStation((LocalityModel) comboBox.getSelectedItem());
        nextState.setPreviousState(this);
        return nextState;
    }
}
