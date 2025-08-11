package ViewPackage.CRUD;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.BikeTableModel;
import ModelsPackage.BrandModel;
import ModelsPackage.StationModel;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BikeAdminPanel extends JPanel {
    private final Controller controller;
    private final Container container;

    private JTable bikeTable;
    private BikeTableModel tableModel;

    private JTextField serialField;
    private JCheckBox electricCheck;
    private JSpinner dateSpinner;
    private JSpinner batteryField;
    private JSpinner kmField;
    private JComboBox<BrandModel> brandBox;
    private JComboBox<StationModel> stationBox;


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
        batteryField = new JSpinner(new SpinnerNumberModel(0, 0, 999999999, 20));
        kmField = new JSpinner(new SpinnerNumberModel(0, 0, 999999999, 50));

        brandBox = new JComboBox<>();
        stationBox = new JComboBox<>();

        // Remplissage des ComboBox
        try {
            List<BrandModel> brands = controller.getAllBrands();
            for (BrandModel b : brands) brandBox.addItem(b);

            List<StationModel> stations = controller.getAllStations();
            for (StationModel s : stations) stationBox.addItem(s);

        } catch (DataAccessException e) {
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
        serialField.setEditable(false);

        String[] columns = {"Numéro", "Électrique", "Date achat", "Batterie", "KM", "Marque", "Station"};
        tableModel = new BikeTableModel(new ArrayList<>());
        bikeTable = new JTable(tableModel);

        bikeTable.getSelectionModel().addListSelectionListener(e -> {
            int row = bikeTable.getSelectedRow();
            if(row >= 0){
                serialField.setText(String.valueOf((int) tableModel.getValueAt(row, 0)));
                electricCheck.setSelected((Boolean) tableModel.getValueAt(row, 1)); 
                dateSpinner.setValue(Date.valueOf(tableModel.getValueAt(row, 2).toString()));
                batteryField.setValue((Integer) tableModel.getValueAt(row, 3));
                kmField.setValue((Integer) tableModel.getValueAt(row, 4));


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
            }
        });

        add(new JScrollPane(bikeTable), BorderLayout.EAST);

        loadBikeTable();
    }

    private void handleInsert(){
        try {
            boolean isElectric = electricCheck.isSelected();
            Date buyingDate = new Date(((java.util.Date) dateSpinner.getValue()).getTime());
            Integer battery = ((Integer) batteryField.getValue()) == 0 ? null : (Integer) batteryField.getValue();
            Integer km = ((Integer) kmField.getValue()) == 0 ? null : (Integer) kmField.getValue();


            BrandModel brand = (BrandModel) brandBox.getSelectedItem();
            StationModel station = (StationModel) stationBox.getSelectedItem();
            if (brand == null || station == null) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une marque et une station.");
                return;
            }

            BikeModel bike = new BikeModel( isElectric, buyingDate,
                    battery != null ? battery : 0,
                    km != null ? km : 0,
                    brand, station);

            controller.insertBike(bike);
            JOptionPane.showMessageDialog(this, "Vélo ajouté avec succès !");

            loadBikeTable();
            // pour reset les champs
            emptyForm();

        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Erreur lors de l'ajout : "+ex.getMessage());
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
        try{
            int serial = Integer.parseInt(serialField.getText().trim());
            boolean isElectric = electricCheck.isSelected();
            Date buyingDate = new Date(((java.util.Date) dateSpinner.getValue()).getTime());
            Integer battery = ((Integer) batteryField.getValue()) == 0 ? null : (Integer) batteryField.getValue();
            Integer km = ((Integer) kmField.getValue()) == 0 ? null : (Integer) kmField.getValue();

            BrandModel brand = (BrandModel) brandBox.getSelectedItem();
            StationModel station = (StationModel) stationBox.getSelectedItem();

            BikeModel updatedBike = new BikeModel(
                    serial, isElectric, buyingDate,
                    battery != null ? battery : 0,
                    km != null ? km : 0,
                    brand, station
            );

            controller.updateBike(updatedBike);
            JOptionPane.showMessageDialog(this, "Vélo mis à jour");
            loadBikeTable();

        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Erreur de modification : "+ ex.getMessage());
        }
    }

    private void loadBikeTable() {
        try {
            List<BikeModel> bikes = controller.getAllBikes();
            ((BikeTableModel) tableModel).setBikes(bikes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void emptyForm() {
        serialField.setText("");
        electricCheck.setSelected(false);
        dateSpinner.setValue(new java.util.Date());
        batteryField.setValue(0);
        kmField.setValue(0);
        brandBox.setSelectedIndex(0);
        stationBox.setSelectedIndex(0);
    }
}

