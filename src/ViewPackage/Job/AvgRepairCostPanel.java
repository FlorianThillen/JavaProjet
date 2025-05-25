package ViewPackage.job;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccessException;
import ModelsPackage.BrandRepairCostModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class AvgRepairCostPanel extends JPanel{
    private final Controller controller;
    private final Container container;
    private JTable resultTable;

    public AvgRepairCostPanel(Container container,Controller controller){
        this.controller=controller;
        this.container=container;

        setLayout(new BorderLayout());

        //top
        JLabel titleLabel = new JLabel("Coût moyen des réparations par marque");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // mid
        String[] columns = {"Marque", "Coût moyen (€)"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        resultTable = new JTable(model);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // bas
        JButton backButton = new JButton("Retour");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new ListAndStatsPanel(container, controller));
            container.revalidate();
            container.repaint();
        });

        loadData(model);
    }

    private void loadData(DefaultTableModel model){
        try{
            List<BrandRepairCostModel> stats = controller.getAverageRepairCostPeBrand();
            for (BrandRepairCostModel stat : stats){
                model.addRow(new Object[]{
                        stat.getBrandName(),
                        String.format("%.2f",stat.getAverageCost()),
                        });
            }
        }catch (DataAccessException e){
            JOptionPane.showMessageDialog(this,"Erreur , "+ e.getMessage());
        }
    }
}
