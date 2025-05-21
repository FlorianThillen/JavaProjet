package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.LocalityModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

public class ContentPanelBike extends ContentPanelState {
    public ContentPanelBike() throws DataAccesException {
        setNextState(new ContentPanelMechanic());

        ArrayList<BikeModel> bikes = controller.getBikesFromStation((StationModel) getPreviousState().getInputValue());

        DefaultListModel<BikeModel> listModel = new DefaultListModel<>();
        listModel.addAll(bikes);

        setInputComponent(new JList<>(listModel));
    }

    @Override
    public BikeModel getInputValue() throws DataAccesException {
        return ((JList<BikeModel>)getInputComponent()).getSelectedValue();
    }
}
