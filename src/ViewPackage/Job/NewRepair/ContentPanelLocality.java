package ViewPackage.Job.NewRepair;

import ExceptionsPackage.DataAccesException;
import ModelsPackage.LocalityModel;

import javax.swing.*;
import javax.swing.event.TableColumnModelListener;
import java.util.ArrayList;

public class ContentPanelLocality extends ContentPanelState {
    public ContentPanelLocality() {
        setNextState(new ContentPanelStation());
    }

    @Override
    public JComponent getInputComponent() throws DataAccesException {
        ArrayList<LocalityModel> localities = controller.getLocalities();

        DefaultListModel<LocalityModel> listModel = new DefaultListModel<>();
        listModel.addAll(localities);

        JList<LocalityModel> list = new JList<>(listModel);
        list.addListSelectionListener(e -> {
            setInputValue(list.getSelectedValue());
        });

        return list;
    }
}
