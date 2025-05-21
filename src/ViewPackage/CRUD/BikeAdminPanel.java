package ViewPackage.CRUD;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.BikeModel;
import ModelsPackage.BrandModel;
import ModelsPackage.StationModel;
import ViewPackage.Job.ListAndStatsPanel;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BikeAdminPanel extends JPanel {
    private final Controller controller;
    private final Container container;

    private JTable bikeTable;
    private DefaultTableModel tableModel;

    private JTextField serialField;
    private JCheckBox electricCheck;
    private JSpinner dateSpinner;
    private JTextField batteryField;
    private JTextField kmField;
    private JComboBox<BrandModel> brandBox;
    private JComboBox<StationModel> stationBox;
    private Integer serialBeforeEdit = null;


    public BikeAdminPanel(Container container,Controller controller) {
        this.container = container;
        this.controller = controller;

        setLayout(new BorderLayout());

        // top
        JLabel title = new JLabel("Opération sur la table vélo (bike)", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // mid

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        serialField = new JTextField();
        electricCheck = new JCheckBox();
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        batteryField = new JTextField();
        kmField = new JTextField();

        brandBox = new JComboBox<>();
        stationBox = new JComboBox<>();

        // Remplissage des ComboBox
        try {
            List<BrandModel> brands = controller.getAllBrands();
            for (BrandModel b : brands) brandBox.addItem(b);

            List<StationModel> stations = controller.getAllStations();
            for (StationModel s : stations) stationBox.addItem(s);

        } catch (DataAccesException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des marques/stations.");
        }

        formPanel.add(new JLabel("Numéro de série :"));
        formPanel.add(serialField);
        formPanel.add(new JLabel("Électrique :"));
        formPanel.add(electricCheck);
        formPanel.add(new JLabel("Date d’achat :"));
        formPanel.add(dateSpinner);
        formPanel.add(new JLabel("Niveau batterie (facultatif) :"));
        formPanel.add(batteryField);
        formPanel.add(new JLabel("Kilomètres (facultatif) :"));
        formPanel.add(kmField);
        formPanel.add(new JLabel("Marque :"));
        formPanel.add(brandBox);
        formPanel.add(new JLabel("Station :"));
        formPanel.add(stationBox);

        add(formPanel, BorderLayout.CENTER);

        // bas

        JButton addButton = new JButton("Ajouter");
        JButton deleteButton = new JButton("Supprimer");
        JButton editButton = new JButton("Modifier");
        JButton backButton = new JButton("Retour");


        JPanel btnPanel = new JPanel();
        btnPanel.add(addButton);
        btnPanel.add(deleteButton);
        btnPanel.add(editButton);
        btnPanel.add(backButton);
        add(btnPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> handleInsert());
        deleteButton.addActionListener(e -> handleDelete());
        editButton.addActionListener(e -> handleEdit());

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new WelcomePanel());
            container.revalidate();
            container.repaint();
        });

        String[] columns = {"Numéro", "Électrique", "Date achat", "Batterie", "KM", "Marque", "Station"};
        tableModel = new DefaultTableModel(columns, 0);
        bikeTable = new JTable(tableModel);

        bikeTable.getSelectionModel().addListSelectionListener(e -> {
            int row = bikeTable.getSelectedRow();
            if(row >= 0){
                serialField.setText(tableModel.getValueAt(row, 0).toString());
                electricCheck.setSelected("Oui".equals(tableModel.getValueAt(row, 1)));
                dateSpinner.setValue(Date.valueOf(tableModel.getValueAt(row, 2).toString()));
                batteryField.setText(tableModel.getValueAt(row, 3).toString());
                kmField.setText(tableModel.getValueAt(row, 4).toString());

                // Sélection de la bonne marque dans la comboBox
                String brandName = tableModel.getValueAt(row, 5).toString();
                for (int i = 0; i < brandBox.getItemCount(); i++) {
                    if (brandBox.getItemAt(i).getName().equals(brandName)) {
                        brandBox.setSelectedIndex(i);
                        break;
                    }
                }
                // Sélection de la bonne station dans la comboBox
                String stationName = tableModel.getValueAt(row, 6).toString();
                for (int i = 0; i < stationBox.getItemCount(); i++) {
                    if (stationBox.getItemAt(i).getName().equals(stationName)) {
                        stationBox.setSelectedIndex(i);
                        break;
                    }
                }

                serialBeforeEdit = Integer.parseInt(serialField.getText());
            }
        });

        add(new JScrollPane(bikeTable), BorderLayout.EAST);

        loadBikeTable();
    }

    private void handleInsert(){
        try {
            int serial = Integer.parseInt(serialField.getText().trim());
            boolean isElectric = electricCheck.isSelected();
            Date buyingDate = new Date(((java.util.Date) dateSpinner.getValue()).getTime());

            Integer battery = batteryField.getText().isBlank() ? null : Integer.parseInt(batteryField.getText().trim());
            Integer km = kmField.getText().isBlank() ? null : Integer.parseInt(kmField.getText().trim());

            BrandModel brand = (BrandModel) brandBox.getSelectedItem();
            StationModel station = (StationModel) stationBox.getSelectedItem();
            if (brand == null || station == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une marque et une station.");
                return;
            }

            BikeModel bike = new BikeModel(serial, isElectric, buyingDate,
                    battery != null ? battery : 0,
                    km != null ? km : 0,
                    brand, station);

            controller.insertBike(bike);
            JOptionPane.showMessageDialog(this, "Vélo ajouté avec succès !");

            loadBikeTable();
            // pour reset les champs
            emptyForm();


        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Certian champs numérique sont invalides");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Erreur : "+ex.getMessage());
        }
    }
    private void handleDelete(){
        int[] selectedRows = bikeTable.getSelectedRows();

        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this,"Veuillez sélectioner un vélo à supprimer");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,"Voulez vous vraiment supprimer les vélos sélectionnés ?",
                "Confirmation",JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION){
            return;
        }

        try{
            for (int row : selectedRows){
                int serialNumber = (int) tableModel.getValueAt(row,0); // indice 0 = numéro de série
                controller.deleteBike(serialNumber);
            }

            JOptionPane.showMessageDialog(this,"Suppression effectué");
            loadBikeTable();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Erreur lors de la suppression : "+ ex.getMessage());
        }

    }

    private void handleEdit(){
        if (serialBeforeEdit == null){
            JOptionPane.showMessageDialog(this,"Veuillez sélectionner un vélo à modifier");
            return;
        }

        try{
            int serial = Integer.parseInt(serialField.getText().trim());
            boolean isElectric = electricCheck.isSelected();
            Date buyingDate = new Date(((java.util.Date) dateSpinner.getValue()).getTime());
            Integer battery = batteryField.getText().isBlank() ? null : Integer.parseInt(batteryField.getText().trim());
            Integer km = kmField.getText().isBlank() ? null : Integer.parseInt(kmField.getText().trim());

            BrandModel brand = (BrandModel) brandBox.getSelectedItem();
            StationModel station = (StationModel) stationBox.getSelectedItem();

            BikeModel updatedBike = new BikeModel(
                    serial, isElectric, buyingDate,
                    battery != null ? battery : 0,
                    km != null ? km : 0,
                    brand, station
            );

            controller.updateBike(updatedBike, serialBeforeEdit);
            JOptionPane.showMessageDialog(this, "Vélo mis à jour");
            loadBikeTable();
            serialBeforeEdit = null;

        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Erreur de modification : "+ ex.getMessage());
        }
    }

    private void loadBikeTable() {
        tableModel.setRowCount(0);

        try {
            List<BikeModel> bikes = controller.getAllBikes();
            for (BikeModel b : bikes) {
                tableModel.addRow(new Object[]{
                        b.getSerialNumber(),
                        b.isElectric() ? "Oui" : "Non",
                        b.getBuyingDate(),
                        b.getBatteryLevel(),
                        b.getNbKilometers(),
                        b.getBrand().getName(),
                        b.getStation().getName()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void emptyForm() {
        serialField.setText("");
        electricCheck.setSelected(false);
        dateSpinner.setValue(new java.util.Date());
        batteryField.setText("");
        kmField.setText("");
        brandBox.setSelectedIndex(0);
        stationBox.setSelectedIndex(0);
    }



}

