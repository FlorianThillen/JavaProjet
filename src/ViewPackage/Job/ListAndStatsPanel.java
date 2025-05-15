package ViewPackage.Job;

import ControllerPackage.Controller;
import ViewPackage.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class ListAndStatsPanel extends JPanel{
    private Controller controller;
    private Container container;

    public ListAndStatsPanel (Container container, Controller controller){
        this.controller = controller;
        this.container = container;

        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();

        JLabel titleLabel = new JLabel("Liste et Statistiques");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // Middle pannel
        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JButton unpaidMembersBtn = new JButton("Personnes sans abonnement payé");
        JButton bikesPerStationBtn = new JButton("Nombre de vélos par station");
        JButton avgRepairCostBtn = new JButton("Coût moyen des réparations par marque");

        unpaidMembersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bikesPerStationBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        avgRepairCostBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // glue for a dynamic display , like the % in html/css
        centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(unpaidMembersBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(bikesPerStationBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(avgRepairCostBtn);

        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);


        // Bottom pannel
        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Retour");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // === Actions ===
        unpaidMembersBtn.addActionListener(e -> {
            container.removeAll();
            container.add(new UnpaidMembersPanel(container, controller));
            container.revalidate();
            container.repaint();
        });

        bikesPerStationBtn.addActionListener(e -> {
            container.removeAll();
            container.add(new BikesPerStationPanel(container, controller));
            container.revalidate();
            container.repaint();
        });

        avgRepairCostBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fonction 'coûts de réparation' à implémenter.");
        });

        backButton.addActionListener(e -> {
            container.removeAll();
            container.add(new WelcomePanel());
            container.revalidate();
            container.repaint();
        });

    }
}
