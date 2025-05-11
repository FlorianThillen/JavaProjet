package ViewPackage.Job;

import ControllerPackage.Controller;
import ExceptionsPackage.DataAccesException;
import ModelsPackage.UnpaidSubscriptionModel;

import javax.naming.ldap.Control;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class UnpaidMembersPanel extends JPanel {
    private final Controller controller;
    private final Container container;
    private JTable resultTable;

    public UnpaidMembersPanel(Container container,Controller controller){
        this.container=container;
        this.controller=controller;

        setLayout(new BorderLayout());

        //top pannel
        JLabel title= new JLabel("Membres avec abonnement non payé");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // middle pannel
        String[] columns = {"Prénom", "Nom", "Email"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        resultTable = new JTable(model);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // vottom button
        JButton backButton = new JButton("Retour");
        JPanel bottomPannel = new JPanel();
        bottomPannel.add(backButton);
        add(bottomPannel,BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new ListAndStatsPanel(container, controller));
            container.revalidate();
            container.repaint();
        });

        loadData(model);

    }

    private void loadData(DefaultTableModel model){
        try {
            List<UnpaidSubscriptionModel> unpaidList = controller.getUnpaidSubscriptions();
            for (UnpaidSubscriptionModel u : unpaidList) {
                model.addRow(new Object[]{
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail()
                });
            }
        } catch (DataAccesException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération : " + e.getMessage());
        }
    }


}
