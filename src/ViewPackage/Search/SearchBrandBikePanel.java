package ViewPackage.Search;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandBikesModel;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchBrandBikePanel extends JPanel{
    private Controller controller;

    public SearchBrandBikePanel(Container contentContainer, Controller controller) throws DataAccesException {
        this.controller = controller;

        // All the elements
        JPanel titlePanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        JButton returnButton = new JButton("Return");
        JPanel outputPanel = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titlePanel.add(new JLabel("Veuillez choisir la marque de vélo qui vous intéresse."));
        add(titlePanel);

        JList<String> brandList = new JList<>(this.controller.getAllBrandNames());
        inputPanel.add(brandList);
        add(inputPanel);

        ButtonPanel.add(confirmButton);
        ButtonPanel.add(returnButton);
        add(ButtonPanel);

        add(outputPanel);


        confirmButton.addActionListener(e -> {
            outputPanel.removeAll();
            try {
                outputPanel.add(getUpdatedOutputPane(brandList.getSelectedValue()));
                outputPanel.repaint();
                outputPanel.revalidate();
            } catch (DataAccesException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.addActionListener(e -> {
            contentContainer.removeAll();
            contentContainer.add(new WelcomePanel());
            contentContainer.revalidate();
            contentContainer.repaint();
        });
    }

    private JScrollPane getUpdatedOutputPane(String brandName) throws DataAccesException {
        ArrayList<BikeModel> bikes = controller.getBikes(brandName);
        BrandBikesModel bikesModel = new BrandBikesModel(bikes);
        return new JScrollPane(new JTable(bikesModel));
    }
}
