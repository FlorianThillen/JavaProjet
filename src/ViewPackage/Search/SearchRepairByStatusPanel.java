package ViewPackage.Search;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchRepairByStatusPanel extends JPanel {

    private final JComboBox<String> statusComboBox;
    private final JTable resultTable;
    private final RepairSearchTableModel tableModel;
    private final Controller controller;

    public SearchRepairByStatusPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(8, 8));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Statut de réparation :"));

        statusComboBox = new JComboBox<>();
        try {
            loadStatusLabels();
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erreur lors du chargement des statuts : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        topPanel.add(statusComboBox);

        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(e -> performSearch());
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new RepairSearchTableModel();
        resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        resultTable.setAutoCreateRowSorter(true); // tri
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            if (!tableModel.getColumnClass(i).equals(Boolean.class)) {
                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        resultTable.getColumnModel().getColumn(0).setPreferredWidth(110); // Date
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(80);  // Cout
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Num Série
        resultTable.getColumnModel().getColumn(3).setPreferredWidth(85);  // Électrique
        resultTable.getColumnModel().getColumn(4).setPreferredWidth(70);  // Nb Km
        resultTable.getColumnModel().getColumn(5).setPreferredWidth(120); // Prénom
        resultTable.getColumnModel().getColumn(6).setPreferredWidth(120); // Nom

        TableRowSorter<RepairSearchTableModel> sorter = new TableRowSorter<>(tableModel);
        resultTable.setRowSorter(sorter);

        add(new JScrollPane(resultTable), BorderLayout.CENTER);
    }

    private void loadStatusLabels() throws DataAccessException {
        List<RepairStatusModel> statusList = controller.getAllRepairStatus();
        statusComboBox.removeAllItems();
        for (RepairStatusModel status : statusList) {
            statusComboBox.addItem(status.getStatus());
        }
        if (statusComboBox.getItemCount() > 0) {
            statusComboBox.setSelectedIndex(0);
        }
    }

    private void performSearch() {
        String selectedStatus = (String) statusComboBox.getSelectedItem();
        if (selectedStatus == null) return;

        List<RepairSearchModel> results;
        try {
            results = controller.getRepairsByStatus(selectedStatus);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erreur lors de la recherche : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        tableModel.setRows(results);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Aucun résultat pour le statut sélectionné.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private static class RepairSearchTableModel extends AbstractTableModel {
        private final String[] columns = {
                "Date", "Coût", "N° Série", "Électrique", "Nb Km",
                "Prénom Mécano", "Nom Mécano"
        };

        private final Class<?>[] columnTypes = new Class<?>[]{
                LocalDate.class, Double.class, String.class, Boolean.class, Integer.class,
                String.class, String.class
        };

        private List<RepairSearchModel> rows = new ArrayList<>();

        public void setRows(List<RepairSearchModel> data) {
            this.rows = (data == null) ? new ArrayList<>() : new ArrayList<>(data);
            fireTableDataChanged();
        }

        @Override public int getRowCount() { return rows.size(); }
        @Override public int getColumnCount() { return columns.length; }
        @Override public String getColumnName(int column) { return columns[column]; }
        @Override public Class<?> getColumnClass(int columnIndex) { return columnTypes[columnIndex]; }
        @Override public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            RepairSearchModel r = rows.get(rowIndex);
            switch (columnIndex) {
                case 0: return r.getDate();
                case 1: return r.getCost();
                case 2: return r.getSerialNumber();
                case 3: return r.isElectric();
                case 4: return r.getNbKilometer();
                case 5: return r.getMechanicFirstname();
                case 6: return r.getMechanicLastname();
                default: return null;
            }
        }
    }
}
