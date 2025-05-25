package ViewPackage.job.newrepair;

import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.MechanicModel;
import ModelsPackage.RepairModel;
import ModelsPackage.RepairStatusModel;
import jdk.jshell.Snippet;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContentPanelRepair extends ContentPanelState {
    private final JComboBox<MechanicModel> mechanicsComBox = new JComboBox<>();
    private final JComboBox<RepairStatusModel> statusComBox = new JComboBox<>();
    private final BikeModel bike;
    private final JTextField costField = new JTextField();

    public ContentPanelRepair(BikeModel bike) throws DataAccessException {
        ArrayList<JComponent> comps = new ArrayList<>();

        comps.add(new JLabel("Mécaniciens disponible : "));
        MechanicModel[] mechanics = controller.getAllMechanics().toArray(MechanicModel[]::new);
        for(MechanicModel mechanic: mechanics)
            mechanicsComBox.addItem(mechanic);
        comps.add(mechanicsComBox);

        comps.add(new JLabel("Statut de la réparation : "));
        RepairStatusModel[] status = controller.getAllRepairStatus().toArray(RepairStatusModel[]::new);
        for(RepairStatusModel singleStatus: status)
            statusComBox.addItem(singleStatus);
        comps.add(statusComBox);

        comps.add(new JLabel("Coût de la réparation : "));
        comps.add(costField);

        this.bike = bike;

        setInputComponents(comps);
    }

    private float getCost() {
        return Float.parseFloat(costField.getText());
    }

    @Override
    public ContentPanelState getNextState() throws DataAccessException {
        try {
            RepairModel newRepair = new RepairModel(
                    100,
                    (int) getCost(),
                    Date.valueOf(LocalDate.now()),
                    (RepairStatusModel) statusComBox.getSelectedItem(),
                    (MechanicModel) mechanicsComBox.getSelectedItem(),
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
}
