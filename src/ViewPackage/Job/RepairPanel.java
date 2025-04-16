package ViewPackage.Job;

import javax.swing.*;
import java.awt.*;
//import java.lang.reflect.Array;
import java.util.Vector;

import ControllerPackage.Controller;
//import jdk.incubator.vector.VectorOperators;

public class RepairPanel extends JPanel {
    private Controller controller;

    public RepairPanel(Container container, Controller controller) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.controller = controller;

        JPanel titlePanel = new JPanel();
        JPanel contentPanel = new ContentPanel();
        JPanel confirmationPanel = new JPanel();

        titlePanel.add(new JLabel("Title"));

        confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.X_AXIS));
        confirmationPanel.add(new JButton("Return"));
        confirmationPanel.add(new JButton("Confirm"));

        add(titlePanel);
        add(contentPanel);
        add(confirmationPanel);
    }

    // Content Panel sub class
    private class ContentPanel extends JPanel {

        private JComboBox<String> localitiesCombox = new JComboBox<String>();
        private JComboBox<String> StationsCombox = new JComboBox<String>();
        private JComboBox<String> BikesCombox = new JComboBox<String>();
        private JComboBox<String> MecasCombox = new JComboBox<String>();
        private JComboBox<String> StatesCombox = new JComboBox<String>();

        public ContentPanel() {
            add(localitiesCombox);
            add(StationsCombox);
            add(BikesCombox);
            add(MecasCombox);
            add(StatesCombox);
        }

        JComboBox<String> create_locality_choices() {
            JComboBox<String> combox = new JComboBox<>(query_localities());
            combox.setMaximumRowCount(6);
            return  combox;
        }

        Vector<String> query_localities() {
            Vector<String> localities = new Vector<String>();
            for(int i_choice = 0; i_choice < 20; i_choice++) {
                localities.add(String.format("Test%d", i_choice));
            }
            return localities;
        }

        Vector<String> query_stations() {
            return new Vector<>();
        }

        Vector<String> query_bikes() {
            return new Vector<>();
        }

        Vector<String> query_mechanics() {
            return new Vector<>();
        }

        Vector<String> query_repair_states() {
            return new Vector<>();
        }

        Vector<String> evaluate_price() {
            return new Vector<>();
        }
    }
}
