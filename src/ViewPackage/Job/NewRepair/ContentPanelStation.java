package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

public class ContentPanelStation extends ContentPanelState{
    public ContentPanelStation() throws DataAccesException {
        setNextState(new ContentPanelBike());

        ArrayList<StationModel> stations = controller.getStationsFromLocality((LocalityModel) getPreviousState().getInputValue());

        DefaultListModel<StationModel> listModel = new DefaultListModel<>();
        listModel.addAll(stations);

        setInputComponent(new JList<>(listModel));
    }

    @Override
    public StationModel getInputValue() throws DataAccesException {
        return ((JList<StationModel>)getInputComponent()).getSelectedValue();
    }
}
