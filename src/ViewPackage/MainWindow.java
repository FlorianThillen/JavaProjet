package ViewPackage;

import ControllerPackage.Controller;
//import ViewPackage.CRUD.*;
//import ViewPackage.Job.*;
import ViewPackage.Job.ListAndStatsPanel;
import ViewPackage.Job.RepairPanel;
import ViewPackage.Search.SearchBrandBikePanel;
import ViewPackage.Search.SearchRentalDatePanel;
import ViewPackage.Search.SearchRepairByStatusPanel;
//import ViewPackage.Search.SearchFamilyPanel;
//import ViewPackage.Search.SearchSubscriptionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private final Container mainContainer, contentContainer, animationContainer;
    private final Controller controller;
    private final JMenuBar menuBar;
    //    // Menus
//    private final JMenu crudMember;
    private final JMenu researches;
    //Animation
    private BikeAnimation bikeAnimation;

    public MainWindow() {
        super("Libia Vélo");
        setBounds(200, 200, 1200, 800);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        animationContainer = new JPanel(new BorderLayout());
        contentContainer = new JPanel(new BorderLayout());
        contentContainer.add(new WelcomePanel());
        contentContainer.setVisible(true);
        animationContainer.setVisible(true);

        mainContainer = getContentPane();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.add(contentContainer);
        mainContainer.add(animationContainer);
        mainContainer.setVisible(true);
        controller = new Controller();
//
        this.menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
//        crudMember = new JMenu("Membre");
        researches = new JMenu("Recherche");
        JMenu businessTask = new JMenu("Tâche métier");
//        menuBar.add(crudMember);
        menuBar.add(researches);
        menuBar.add(businessTask);

        //CRUD
//        setCrudMenu();

        //Research
        setSearchMenu();

        //BusinessTask
        JMenuItem AddNewRepairMenuItm = new JMenuItem("New repair");
        JMenuItem listAndStatMenu = new JMenuItem("Liste et Statistiques");
        businessTask.add(AddNewRepairMenuItm);
        businessTask.add(listAndStatMenu);
        AddNewRepairMenuItm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new RepairPanel(contentContainer, controller));
                contentContainer.revalidate();
            }
        });

        listAndStatMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new ListAndStatsPanel(contentContainer, controller));
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });

        //Bike animation
        setAnimation();

        this.setVisible(true);
        mainContainer.repaint();
        revalidate();
    }

    //    private void setCrudMenu() {
//        // CRUD
//        JMenuItem addMember = new JMenuItem("Ajouter un membre");
//        JMenuItem memberList = new JMenuItem("Liste des membres");
//        JMenuItem updateMember = new JMenuItem("Modifier ou supprimer un membre");
//        crudMember.add(addMember);
//        crudMember.add(memberList);
//        crudMember.add(updateMember);
//        addMember.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                contentContainer.removeAll();
//                contentContainer.add(new AddMemberPanel(contentContainer, controller));
//                contentContainer.revalidate();
//                contentContainer.repaint();
//            }
//        });
//        memberList.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                contentContainer.removeAll();
//                contentContainer.add(new MemberListPanel(contentContainer, controller));
//                contentContainer.revalidate();
//                contentContainer.repaint();
//            }
//        });
//        updateMember.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                contentContainer.removeAll();
//                contentContainer.add(new SearchMemberPanel(contentContainer, controller));
//                contentContainer.revalidate();
//                contentContainer.repaint();
//            }
//        });
//
//
//    }
    private void setSearchMenu() {
        JMenuItem menuSearchBrandBike = new JMenuItem("Recherche vélos d'une certaine marque");
        JMenuItem menuSearchRentalDate = new JMenuItem("Recherche locations entre 2 dates");
        JMenuItem menuSearchRepair = new JMenuItem("Recherche statut réparation ");
        //        JMenuItem menuSearchFamilySub = new JMenuItem("Recherche abonnement familial");
        researches.add(menuSearchBrandBike);
        researches.add(menuSearchRentalDate);
        researches.add(menuSearchRepair);
        //        researches.add(menuSearchSubscriptions);
        //        researches.add(menuSearchFamilySub);
        //
        menuSearchBrandBike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new SearchBrandBikePanel(contentContainer, controller));
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });

        menuSearchRentalDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new SearchRentalDatePanel(contentContainer, controller));
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });

        menuSearchRepair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new SearchRepairByStatusPanel(controller));
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });
//        menuSearchSubscriptions.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                contentContainer.removeAll();
//                contentContainer.add(new SearchSubscriptionPanel(controller, contentContainer));
//                contentContainer.revalidate();
//                contentContainer.repaint();
//            }
//        });
    }

    private void setAnimation() {
        bikeAnimation = new BikeAnimation();
        JPanel bikePanel = new JPanel(new BorderLayout());
        bikePanel.setPreferredSize(new Dimension(1200, 160));
        bikePanel.add(bikeAnimation, BorderLayout.CENTER);
        animationContainer.add(bikePanel, BorderLayout.SOUTH);
    }
}
