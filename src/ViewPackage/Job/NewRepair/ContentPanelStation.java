package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelStation extends ContentPanelState{
    public ContentPanelStation() {
        setNextState(new ContentPanelBike());
    }

    @Override
    public JComponent getInputComponent() throws DataAccesException {
        ArrayList<StationModel> stations = controller.getStationsFromLocality((LocalityModel) getPreviousState().getInputValue());

        DefaultListModel<StationModel> listModel = new DefaultListModel<>();
        listModel.addAll(stations);

        JList<StationModel> list = new JList<>(listModel);
        list.addListSelectionListener(e -> {
            setInputValue(list.getSelectedValue());
        });
        return list;
    }
}
