package ViewPackage.Job;
import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.StationBikeNbModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BikesPerStationPanel extends JPanel{
    private final Controller controller;
    private final Container container;
    private JTable resultTable;
    private JSpinner spinnerMin;
    private JSpinner spinnerMax;

    public BikesPerStationPanel(Container container,Controller controller){
        this.container=container;
        this.controller=controller;

        setLayout(new BorderLayout());

        // top
        JPanel filterPannel = new JPanel();
        spinnerMin = new JSpinner((new SpinnerNumberModel(0,0,1000,5))); // val par defaut / min / max / incrementation
        spinnerMax = new JSpinner((new SpinnerNumberModel(10,0,1000,5)));
        JButton searchButton = new JButton("Rechercher");
        JButton backButton = new JButton("Retour");

        filterPannel.add(new JLabel("Min :"));
        filterPannel.add(spinnerMin);
        filterPannel.add(new JLabel("max :"));
        filterPannel.add(spinnerMax);

        filterPannel.add(searchButton);
        filterPannel.add(backButton);

        add(filterPannel,BorderLayout.NORTH);

        // mid
        String[] columns = {"Station", "Nombre de vélos", "Statut"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        resultTable = new JTable(model);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // listener
        searchButton.addActionListener(e -> {
            int min = (Integer) spinnerMin.getValue();
            int max = (Integer) spinnerMax.getValue();

            if (min > max) {
                JOptionPane.showMessageDialog(this, "Min ne peut pas être supérieur à Max ");
                return;
            }

            model.setRowCount(0);

            try {
                List<StationBikeNbModel> results = controller.getStationsStatus(min, max);
                for (StationBikeNbModel stat : results) {
                    model.addRow(new Object[]{
                            stat.getStationName(),
                            stat.getBikeCount(),
                            stat.getStatus()
                    });
                }
            } catch (DataAccessException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new ListAndStatsPanel(container, controller));
            container.revalidate();
            container.repaint();
        });

    }
}
