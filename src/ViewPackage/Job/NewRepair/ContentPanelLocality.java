package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import java.util.ArrayList;

public class ContentPanelLocality extends ContentPanelState {
    public ContentPanelLocality() throws DataAccesException {
        setNextState(new ContentPanelStation());

        ArrayList<LocalityModel> localities = controller.getLocalities();

        DefaultListModel<LocalityModel> listModel = new DefaultListModel<>();
        listModel.addAll(localities);

        setInputComponent(new JList<>(listModel));
    }

    @Override
    public LocalityModel getInputValue() throws DataAccesException {
        return ((JList<LocalityModel>)getInputComponent()).getSelectedValue();
    }
}
