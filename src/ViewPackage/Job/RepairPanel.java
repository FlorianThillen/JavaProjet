package ViewPackage.Job;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Vector;

import ControllerPackage.Controller;
import jdk.incubator.vector.VectorOperators;

public class RepairPanel extends JPanel {
    private Controller controller;

    public RepairPanel(Container container, Controller controller) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.controller = controller;

        JPanel titlePanel = new JPanel();
        JPanel contentPanel = new JPanel();

        titlePanel.add(new JLabel("Title"));
        JComboBox combox = new JComboBox<>(query_localities());
        combox.setMaximumRowCount(6);
        contentPanel.add(combox);

        add(titlePanel);
        add(contentPanel);
    }

    Vector<String> query_localities() {
        Vector<String> localities = new Vector<String>();
        for(int i_choice = 0; i_choice < 20; i_choice++) {
            localities.add(String.format("Test%d", i_choice));
        }
        return localities;
    }


}
