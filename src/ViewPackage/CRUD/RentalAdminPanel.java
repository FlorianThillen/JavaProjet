package ViewPackage.CRUD;

import DataAccessPackage.RentalDAO;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.RentalModel;
import ModelsPackage.SubscriptionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class RentalAdminPanel extends JPanel {
    private final JTextField idField = new JTextField(10);
    private final JTextField startDateField = new JTextField(10);
    private final JTextField returnDateField = new JTextField(10);
    private final JTextField commentField = new JTextField(10);
    private final JCheckBox hadIssueCheck = new JCheckBox("Problème rencontré");
    private final JTextField bikeIdField = new JTextField(10);
    private final JTextField subIdField = new JTextField(10);

    private final JButton addButton = new JButton("Ajouter");
    private final JButton updateButton = new JButton("Modifier");
    private final JButton deleteButton = new JButton("Supprimer");

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"ID", "Début", "Retour", "Commentaire", "Problème", "ID Vélo", "ID Abonné"}, 0);
    private final JTable rentalTable = new JTable(tableModel);

    private final RentalDAO rentalDAO = new RentalDAO();

    public RentalAdminPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Formulaire ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulaire de Location"));

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Date début (YYYY-MM-DD):"));
        formPanel.add(startDateField);
        formPanel.add(new JLabel("Date retour (YYYY-MM-DD):"));
        formPanel.add(returnDateField);
        formPanel.add(new JLabel("Commentaire:"));
        formPanel.add(commentField);
        formPanel.add(new JLabel("ID Vélo:"));
        formPanel.add(bikeIdField);
        formPanel.add(new JLabel("ID Abonnement:"));
        formPanel.add(subIdField);
        formPanel.add(hadIssueCheck);
        formPanel.add(new JLabel());

        // --- Boutons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // --- Table ---
        rentalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(rentalTable);
        tableScrollPane.setPreferredSize(new Dimension(800, 200));

        // --- Zone centrale : Formulaire + Boutons + Table ---
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(tableScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // --- Événements ---
        addButton.addActionListener(e -> insertRental());
        updateButton.addActionListener(e -> updateRental());
        deleteButton.addActionListener(e -> deleteRental());
        rentalTable.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());

        // --- Chargement initial ---
        loadRentals();
    }

    private void loadRentals() {
        tableModel.setRowCount(0);
        try {
            List<RentalModel> rentals = rentalDAO.getAllRentals();
            for (RentalModel rental : rentals) {
                tableModel.addRow(new Object[]{
                        rental.getRentalId(),
                        rental.getStartDate(),
                        rental.getReturnDate(),
                        rental.getComment(),
                        rental.isHadIssue(),
                        rental.getBike().getSerialNumber(),
                        rental.getSubscription().getCardNumber()
                });
            }
        } catch (DataAccessException e) {
            showError("Erreur chargement des locations : " + e.getMessage());
        }
    }

    private void fillFormFromTable() {
        int selectedRow = rentalTable.getSelectedRow();
        if (selectedRow >= 0) {
            idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            startDateField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            returnDateField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            commentField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            hadIssueCheck.setSelected((Boolean) tableModel.getValueAt(selectedRow, 4));
            bikeIdField.setText(tableModel.getValueAt(selectedRow, 5).toString());
            subIdField.setText(tableModel.getValueAt(selectedRow, 6).toString());
        }
    }

    private RentalModel getRentalFromForm() {
        try {
            int id = Integer.parseInt(idField.getText());
            LocalDate startDate = LocalDate.parse(startDateField.getText());
            LocalDate returnDate = LocalDate.parse(returnDateField.getText());
            String comment = commentField.getText();
            boolean hadIssue = hadIssueCheck.isSelected();
            int bikeId = Integer.parseInt(bikeIdField.getText());
            int subId = Integer.parseInt(subIdField.getText());

            BikeModel bike = new BikeModel();
            bike.setSerialNumber(bikeId);

            SubscriptionModel sub = new SubscriptionModel();
            sub.setCardNumber(subId);

            return new RentalModel(id, startDate, returnDate, comment, hadIssue, sub, bike);

        } catch (Exception e) {
            showError("Erreur dans le formulaire : " + e.getMessage());
            return null;
        }
    }

    private void insertRental() {
        RentalModel rental = getRentalFromForm();
        if (rental == null) return;

        try {
            rentalDAO.insertRental(rental);
            loadRentals();
            clearForm();
        } catch (DataAccessException e) {
            showError("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    private void updateRental() {
        RentalModel rental = getRentalFromForm();
        if (rental == null) return;

        try {
            rentalDAO.updateRental(rental);
            loadRentals();
            clearForm();
        } catch (DataAccessException e) {
            showError("Erreur lors de la modification : " + e.getMessage());
        }
    }

    private void deleteRental() {
        try {
            int id = Integer.parseInt(idField.getText());
            rentalDAO.deleteRental(id);
            loadRentals();
            clearForm();
        } catch (Exception e) {
            showError("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    private void clearForm() {
        idField.setText("");
        startDateField.setText("");
        returnDateField.setText("");
        commentField.setText("");
        hadIssueCheck.setSelected(false);
        bikeIdField.setText("");
        subIdField.setText("");
        rentalTable.clearSelection();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
