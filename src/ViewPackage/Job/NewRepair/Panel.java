package ViewPackage.Job.NewRepair;

import javax.swing.*;
import java.awt.*;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;

public class Panel extends JPanel {
    private Controller controller;

    public Panel(Container container, Controller controller) throws DataAccesException {
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
}
