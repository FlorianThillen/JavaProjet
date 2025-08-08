package ViewPackage.Search;

import ControllerPackage.Controller;
import ModelsPackage.RentalDateSearchModel;
import ModelsPackage.RentalSearchTableModel;
import ViewPackage.WelcomePanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.List;

public class SearchRentalDatePanel extends JPanel{
    private Controller controller;
    private JTable resultTable;
    private JSpinner spinnerStart;
    private JSpinner spinnerEnd;

    public SearchRentalDatePanel(Container container, Controller controller) {
        this.controller = controller;

        setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel(new FlowLayout());

        spinnerStart = new JSpinner(new SpinnerDateModel());
        spinnerStart.setEditor(new JSpinner.DateEditor(spinnerStart, "yyyy-MM-dd"));

        spinnerEnd = new JSpinner(new SpinnerDateModel());
        spinnerEnd.setEditor(new JSpinner.DateEditor(spinnerEnd, "yyyy-MM-dd"));

        JButton searchButton = new JButton("Rechercher");
        JButton backButton = new JButton("Retour");

        filterPanel.add(new JLabel("Date début :"));
        filterPanel.add(spinnerStart);
        filterPanel.add(new JLabel("Date fin :"));
        filterPanel.add(spinnerEnd);
        filterPanel.add(searchButton);
        filterPanel.add(backButton);

        add(filterPanel, BorderLayout.NORTH);

        resultTable = new JTable();

        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new WelcomePanel());
            container.revalidate();
            container.repaint();
        });

        searchButton.addActionListener(e -> {
            Date start = new Date(((java.util.Date) spinnerStart.getValue()).getTime());
            Date end = new Date(((java.util.Date) spinnerEnd.getValue()).getTime());

            try {
                List<RentalDateSearchModel> results = controller.getRentalsBetweenDates(start, end);
                resultTable.setModel(new RentalSearchTableModel(results));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });
    }
}