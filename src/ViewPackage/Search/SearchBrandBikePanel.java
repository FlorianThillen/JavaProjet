package ViewPackage.Search;

import ControllerPackage.Controller;
import DataAccesPackage.BrandDAO;
import ExceptionsPackage.ConnectionException;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
        /*try {
            JList<String> brandNames = getBrandList();
            inputPanel.add(brandNames);
        } catch (DataAccesException e) {
            throw new DataAccesException("Erreur de récupération des marques", e);
        }*/
        inputPanel.add(getBrandList());
        add(inputPanel);

        ButtonPanel.add(confirmButton);
        ButtonPanel.add(returnButton);
        add(ButtonPanel);

        add(outputPanel);


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputPanel.removeAll();
                try {
                    outputPanel.add(getUpdatedOutputPane("test"));
                    outputPanel.repaint();
                    outputPanel.revalidate();
                } catch (ConnectionException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new WelcomePanel());
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });
    }

    private JScrollPane getUpdatedOutputPane(String brandName) throws ConnectionException {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Serial Number");
        columnNames.add("Is Electric");
        columnNames.add("Station");
        columnNames.add("Street");
        columnNames.add("Numero");
        columnNames.add("Postal Code");
        columnNames.add("Locality");

        Vector<Vector<String>> bikeInfos = new Vector<>();

        Vector<BikeModel> bikes = controller.getBikes(brandName);
        for (BikeModel bike: bikes) {
            Vector<String> row = new Vector<>();

            row.add(Integer.toString(bike.getSerialNumber()));
            row.add(Boolean.toString(bike.isElectric()));
//             row.add(Station)
//             row.add(StreetName)
//             row.add(StreetNum)
//             row.add(Postal Code)
//             row.add(Locality)

            bikeInfos.add(row);
        }

        return new JScrollPane(new JTable(bikeInfos, columnNames));
    }

    private JList<String> getBrandList() throws DataAccesException {
        BrandDAO dao = new BrandDAO();
        Vector<String> brandNames = new Vector<>();
        for (BrandModel brand: dao.getAllBrands()) {
            brandNames.add(brand.getName());
        }
        return new JList<String>(brandNames);
    }
}
