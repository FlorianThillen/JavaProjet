package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.MechanicModel;
import ModelsPackage.RepairModel;
import ModelsPackage.RepairStatusModel;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContentPanelRepair extends ContentPanelState {
    private final JList<MechanicModel> mechanicsList = new JList<>();
    private final JList<RepairStatusModel> statusList = new JList<>();
    private final CostPanel costPanel = new CostPanel();
    private BikeModel bike;

    public ContentPanelRepair(BikeModel bike) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        MechanicModel[] mechanics = controller.getAllMechanics().toArray(MechanicModel[]::new);
        mechanicsList.setListData(mechanics);
        comps.add(mechanicsList);

        RepairStatusModel[] status = controller.getAllRepairStatus().toArray(RepairStatusModel[]::new);
        statusList.setListData(status);
        comps.add(statusList);

        comps.add(costPanel);

        this.bike = bike;

        setInputComponents(comps);
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        try {
            RepairModel newRepair = new RepairModel(
                    100,
                    costPanel.getCost(),
                    Date.valueOf(LocalDate.now()),
                    statusList.getSelectedValue(),
                    mechanicsList.getSelectedValue(),
                    bike
            );
            controller.saveNewRepair(newRepair);

            ContentPanelState nextState = new ContentPanelSuccess();
            nextState.setPreviousState(this);
            return nextState;
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Le prix inserez n'est pas valide."
            );

            return null;
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur lors de l'insertion de la nouvelle reparation."
            );

            throw e;
        }
    }

    private class CostPanel extends JPanel {
        private final JTextField costField = new JTextField();

        public CostPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(new JLabel("Coût:"));
            this.add(costField);
        }

        public int getCost() throws NumberFormatException {
            return Math.round(Float.parseFloat(costField.getText()));
        }
    }
}
