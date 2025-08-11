package ViewPackage.CRUD;

import BusinessPackage.RentalService;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BikeModel;
import ModelsPackage.RentalModel;
import ModelsPackage.SubscriptionModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class RentalAdminPanel extends JPanel {

    private final JTextField idField = new JTextField(10);

    // Dates avec JSpinner (format yyyy-MM-dd)
    private final JSpinner startDateSpinner = new JSpinner(
            new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    private final JSpinner returnDateSpinner = new JSpinner(
            new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH));
    private final JCheckBox noReturnDateCheck = new JCheckBox("Pas encore de date de retour");

    // Commentaire
    private final JTextField commentField = new JTextField(10);

    // Menus déroulants pour FK
    private final JComboBox<Integer> bikeCombo = new JComboBox<>();
    private final JComboBox<Integer> subCombo  = new JComboBox<>();

    private final JCheckBox hadIssueCheck = new JCheckBox("Problème rencontré");

    private final JButton addButton = new JButton("Ajouter");
    private final JButton updateButton = new JButton("Modifier");
    private final JButton deleteButton = new JButton("Supprimer");

    private final RentalService rentalService;

    private final RentalTableModel tableModel = new RentalTableModel();
    private final JTable rentalTable = new JTable(tableModel);

    private Border defaultBorder;

    public RentalAdminPanel(RentalService rentalService) {
        this.rentalService = rentalService;
        initUI();
        bindEvents();
        loadReferenceData();
        loadRentals();
    }

    public RentalAdminPanel() {
        this(new RentalService());
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        defaultBorder = idField.getBorder();

        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));
        returnDateSpinner.setEditor(new JSpinner.DateEditor(returnDateSpinner, "yyyy-MM-dd"));

        // Formulaire
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulaire de Location"));

        formPanel.add(new JLabel("ID *"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Date début *"));
        formPanel.add(startDateSpinner);

        formPanel.add(new JLabel("Date retour"));
        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        returnPanel.add(returnDateSpinner);
        returnPanel.add(noReturnDateCheck);
        formPanel.add(returnPanel);

        formPanel.add(new JLabel("Commentaire"));
        formPanel.add(commentField);

        formPanel.add(new JLabel("ID Vélo *"));
        formPanel.add(bikeCombo);

        formPanel.add(new JLabel("ID Abonnement *"));
        formPanel.add(subCombo);

        formPanel.add(hadIssueCheck);
        formPanel.add(new JLabel("* Champ obligatoire"));

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Table
        rentalTable.setFillsViewportHeight(true);
        rentalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rentalTable.setAutoCreateRowSorter(true);

        TableRowSorter<RentalTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setComparator(1, Comparator.nullsLast(Comparator.naturalOrder())); // start
        sorter.setComparator(2, Comparator.nullsLast(Comparator.naturalOrder())); // return
        rentalTable.setRowSorter(sorter);

        JScrollPane tableScrollPane = new JScrollPane(rentalTable);
        tableScrollPane.setPreferredSize(new Dimension(900, 260));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(tableScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // Checkbox “pas de date de retour”
        noReturnDateCheck.addActionListener(e -> {
            boolean disabled = noReturnDateCheck.isSelected();
            returnDateSpinner.setEnabled(!disabled);
        });
        noReturnDateCheck.setSelected(false);
    }

    private void bindEvents() {
        addButton.addActionListener(e -> insertRental());
        updateButton.addActionListener(e -> updateRental());
        deleteButton.addActionListener(e -> deleteRental());
        rentalTable.getSelectionModel().addListSelectionListener(e -> fillFormFromTable());
    }

    private void loadReferenceData() {
        try {
            bikeCombo.removeAllItems();
            for (Integer id : rentalService.getAllBikeIds()) bikeCombo.addItem(id);

            subCombo.removeAllItems();
            for (Integer id : rentalService.getAllSubscriptionIds()) subCombo.addItem(id);

            if (bikeCombo.getItemCount() > 0) bikeCombo.setSelectedIndex(0);
            if (subCombo.getItemCount() > 0)  subCombo.setSelectedIndex(0);
        } catch (DataAccessException e) {
            showError("Erreur lors du chargement des IDs: " + e.getMessage());
        }
    }

    private void clearErrors() {
        resetField(idField);
        resetField(commentField);
    }

    private void resetField(JTextField field) {
        field.setBorder(defaultBorder);
        field.setToolTipText(null);
    }

    private void markError(JTextField field, String tooltip) {
        field.setBorder(BorderFactory.createLineBorder(Color.RED));
        field.setToolTipText(tooltip);
    }

    private Integer parsePositiveInt(String text) {
        try {
            int v = Integer.parseInt(text.trim());
            return (v > 0) ? v : null;
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDate spinnerToLocalDate(JSpinner spinner) {
        Date d = (Date) spinner.getValue();
        return Instant.ofEpochMilli(d.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void setSpinnerFromLocalDate(JSpinner spinner, LocalDate date) {
        Date d = (date == null)
                ? new Date()
                : Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        spinner.setValue(d);
    }

    private RentalModel validateAndBuildModel() {
        clearErrors();
        List<String> errors = new ArrayList<>();

        Integer id = parsePositiveInt(idField.getText());
        if (id == null) {
            markError(idField, "ID invalide (entier > 0)");
            errors.add("ID : doit être un entier strictement positif.");
        }

        LocalDate startDate = spinnerToLocalDate(startDateSpinner);
        if (startDate == null) {
            errors.add("Date début : invalide.");
        }

        LocalDate returnDate = null;
        if (!noReturnDateCheck.isSelected()) {
            returnDate = spinnerToLocalDate(returnDateSpinner);
            if (startDate != null && returnDate != null && returnDate.isBefore(startDate)) {
                errors.add("Date retour : doit être postérieure ou égale à la date de début.");
            }
        }

        String comment = commentField.getText();
        if (comment != null && !comment.isEmpty() && comment.length() > 255) {
            markError(commentField, "Commentaire trop long (max 255)");
            errors.add("• Commentaire : 255 caractères maximum.");
        }

        Integer bikeId = (Integer) bikeCombo.getSelectedItem();
        Integer subId  = (Integer) subCombo.getSelectedItem();
        if (bikeId == null) errors.add("• Sélectionnez un ID Vélo.");
        if (subId  == null) errors.add("• Sélectionnez un ID Abonnement.");

        if (!errors.isEmpty()) {
            showError(String.join("\n", errors));
            return null;
        }

        BikeModel bike = new BikeModel();
        bike.setSerialNumber(bikeId);

        SubscriptionModel sub = new SubscriptionModel();
        sub.setCardNumber(subId);

        return new RentalModel(
                id,
                startDate,
                returnDate,
                (comment == null || comment.isEmpty()) ? null : comment,
                hadIssueCheck.isSelected(),
                sub,
                bike
        );
    }

    private void loadRentals() {
        try {
            List<RentalModel> rentals = rentalService.getAllRentals();
            tableModel.setRows(rentals);
        } catch (DataAccessException e) {
            showError(e.getMessage());
        }
    }

    private void fillFormFromTable() {
        int selectedRow = rentalTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = rentalTable.convertRowIndexToModel(selectedRow);
            RentalModel r = tableModel.getRentalAt(modelRow);

            idField.setText(String.valueOf(r.getRentalId()));
            setSpinnerFromLocalDate(startDateSpinner, r.getStartDate());

            if (r.getReturnDate() == null) {
                noReturnDateCheck.setSelected(true);
                returnDateSpinner.setEnabled(false);
                setSpinnerFromLocalDate(returnDateSpinner, LocalDate.now());
            } else {
                noReturnDateCheck.setSelected(false);
                returnDateSpinner.setEnabled(true);
                setSpinnerFromLocalDate(returnDateSpinner, r.getReturnDate());
            }

            commentField.setText(r.getComment() == null ? "" : r.getComment());
            hadIssueCheck.setSelected(r.isHadIssue());

            selectComboValue(bikeCombo, r.getBike().getSerialNumber());
            selectComboValue(subCombo, r.getSubscription().getCardNumber());
        }
    }

    private void selectComboValue(JComboBox<Integer> combo, int value) {
        ComboBoxModel<Integer> model = combo.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (Objects.equals(model.getElementAt(i), value)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.addItem(value);
        combo.setSelectedItem(value);
    }

    private void insertRental() {
        RentalModel rental = validateAndBuildModel();
        if (rental == null) return;
        try {
            rentalService.insertRental(rental);
            loadRentals();
            clearForm();
            JOptionPane.showMessageDialog(this,
                    "Location ajoutée avec succès (ID = " + rental.getRentalId() + ")",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (DataAccessException e) {
            showError(e.getMessage());
        }
    }

    private void updateRental() {
        RentalModel rental = validateAndBuildModel();
        if (rental == null) return;
        try {
            rentalService.updateRental(rental);
            loadRentals();
            clearForm();
            JOptionPane.showMessageDialog(this,
                    "Location modifiée avec succès (ID = " + rental.getRentalId() + ")",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (DataAccessException e) {
            showError(e.getMessage());
        }
    }

    private void deleteRental() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            rentalService.deleteRental(id);
            loadRentals();
            clearForm();
            JOptionPane.showMessageDialog(this,
                    "Location supprimée avec succès (ID = " + id + ")",
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            showError("ID : doit être un entier strictement positif.");
        } catch (DataAccessException e) {
            showError(e.getMessage());
        }
    }

    private void clearForm() {
        idField.setText("");
        startDateSpinner.setValue(new Date());
        noReturnDateCheck.setSelected(false);
        returnDateSpinner.setEnabled(true);
        returnDateSpinner.setValue(new Date());
        commentField.setText("");
        hadIssueCheck.setSelected(false);
        rentalTable.clearSelection();
        clearErrors();
        if (bikeCombo.getItemCount() > 0) bikeCombo.setSelectedIndex(0);
        if (subCombo.getItemCount() > 0)  subCombo.setSelectedIndex(0);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private static class RentalTableModel extends AbstractTableModel {
        private final String[] columns = {
                "ID", "Début", "Retour", "Commentaire", "Problème", "ID Vélo", "ID Abonné"
        };
        private final Class<?>[] columnTypes = {
                Integer.class, LocalDate.class, LocalDate.class, String.class,
                Boolean.class, Integer.class, Integer.class
        };

        private List<RentalModel> rows = new ArrayList<>();

        public void setRows(List<RentalModel> data) {
            this.rows = (data == null) ? new ArrayList<>() : new ArrayList<>(data);
            fireTableDataChanged();
        }

        public RentalModel getRentalAt(int rowIndex) {
            return rows.get(rowIndex);
        }

        @Override public int getRowCount() { return rows.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int column) { return columns[column]; }
        @Override public Class<?> getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
        @Override public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            RentalModel r = rows.get(rowIndex);
            switch (columnIndex) {
                case 0: return r.getRentalId();
                case 1: return r.getStartDate();
                case 2: return r.getReturnDate();
                case 3: return r.getComment();
                case 4: return r.isHadIssue();
                case 5: return r.getBike().getSerialNumber();
                case 6: return r.getSubscription().getCardNumber();
                default: return null;
            }
        }
    }
}
