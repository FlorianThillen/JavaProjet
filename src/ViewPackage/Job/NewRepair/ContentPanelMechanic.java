package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.MechanicModel;
import ModelsPackage.StationModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelMechanic extends ContentPanelState {
    public ContentPanelMechanic() throws DataAccesException {
        setNextState(new ContentPanelStatus());

        ArrayList<MechanicModel> Mechanics = controller.;

        DefaultListModel<MechanicModel> listModel = new DefaultListModel<>();
        listModel.addAll(Mechanics);

        setInputComponent(new JList<>(listModel));
    }

    @Override
    public MechanicModel getInputValue() throws DataAccesException {
        return ((JList<MechanicModel>)getInputComponent()).getSelectedValue();
    }
}
