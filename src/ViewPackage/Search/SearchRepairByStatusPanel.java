package ViewPackage.Search;

import ControllerPackage.Controller;
import ModelsPackage.RepairSearchModel;
import ModelsPackage.RepairStatusModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchRepairByStatusPanel extends JPanel {
    private JComboBox<String> statusComboBox;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private Controller controller;

    public SearchRepairByStatusPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Statut de réparation :"));

        statusComboBox = new JComboBox<>();
        loadStatusLabels();
        topPanel.add(statusComboBox);

        JButton searchButton = new JButton("Rechercher");
        searchButton.addActionListener(e -> performSearch());
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);


        String[] columnNames = {
                "Date", "Coût", "N° Série", "Électrique", "Nb Km",
                "Prénom Mécano", "Nom Mécano"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);
    }

    private void loadStatusLabels() {
        /*List<RepairStatusModel> statusList = controller.getAllRepairStatus();
        for (RepairStatusModel status : statusList) {
            statusComboBox.addItem(status.getStatus());
        }*/ // A ENLEVER QD ON AURA LA BD
    }

    private void performSearch() {
        String selectedStatus = (String) statusComboBox.getSelectedItem();
        if (selectedStatus == null) return;

        List<RepairSearchModel> results = controller.getRepairsByStatus(selectedStatus);

        tableModel.setRowCount(0);

        for (RepairSearchModel r : results) {
            tableModel.addRow(new Object[]{
                    r.getDate(),
                    r.getCost(),
                    r.getSerialNumber(),
                    r.isElectric() ? "Oui" : "Non",
                    r.getNbKilometer(),
                    r.getMechanicFirstname(),
                    r.getMechanicLastname()
            });
        }
    }
}
