package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.MechanicModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelRepair extends ContentPanelState {
    public ContentPanelRepair(BikeModel selectedValue) throws DataAccesException {
        ArrayList<MechanicModel> Mechanics = controller.getAllMechanics();

        DefaultListModel<MechanicModel> listModel = new DefaultListModel<>();
        listModel.addAll(Mechanics);

        setInputComponent(new JList<>(listModel));
    }
}
