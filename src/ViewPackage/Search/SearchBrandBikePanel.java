package ViewPackage.Search;

import ViewPackage.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchBrandBikePanel extends JPanel{
    public SearchBrandBikePanel(Container contentContainer) {
        JPanel titlePanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        JButton returnButton = new JButton("Return");
        JPanel outputPanel = new JPanel();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titlePanel.add(new JLabel("Veuillez choisir la marque de vélo qui vous intéresse."));
        add(titlePanel);

        inputPanel.add(new JList<>(new String[]{"B1", "B2"}));
        add(inputPanel);

        ButtonPanel.add(confirmButton);
        ButtonPanel.add(returnButton);
        add(ButtonPanel);

        add(outputPanel);


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputPanel.removeAll();
                outputPanel.add(getTable());
                outputPanel.repaint();
                outputPanel.revalidate();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentContainer.removeAll();
                contentContainer.add(new WelcomePanel());
                contentContainer.revalidate();
                contentContainer.repaint();
            }
        });
    }

    private JScrollPane getTable() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Serial Number");
        columnNames.add("Is Electric");
        columnNames.add("Station");
        columnNames.add("Street");
        columnNames.add("Numero");
        columnNames.add("Postal Code");
        columnNames.add("Locality");

        Vector<Vector<String>> infos = new Vector<>();
        return new JScrollPane(new JTable(infos, columnNames));
    }
}
