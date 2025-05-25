package ViewPackage.search;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandBikesModel;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class SearchBrandBikePanel extends JPanel {
    private final Controller controller;

    public SearchBrandBikePanel(Container contentContainer, Controller controller) throws DataAccessException {
        this.controller = controller;
        setLayout(new BorderLayout());

        // All the elements
        JPanel topPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        JButton returnButton = new JButton("Return");
        JPanel outputPanel = new JPanel();
        BrandBikesModel bikesModel = new BrandBikesModel(new ArrayList<>());

        topPanel.add(new JLabel("Marque de vélo :"));

        JComboBox<String> brandComBox = new JComboBox<>(this.controller.getAllBrandNames());
        topPanel.add(brandComBox);

        ButtonPanel.add(confirmButton);
        ButtonPanel.add(returnButton);

        confirmButton.addActionListener(e -> {
            try {
                ArrayList<BikeModel> bikes = controller.getBikes(brandComBox.getSelectedItem().toString());
                bikesModel.setData(bikes);
            } catch (DataAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.addActionListener(e -> {
            contentContainer.removeAll();
            contentContainer.add(new WelcomePanel());
            contentContainer.revalidate();
            contentContainer.repaint();
        });

        topPanel.add(ButtonPanel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(bikesModel)));
    }
}
