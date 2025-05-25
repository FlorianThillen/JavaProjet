package ViewPackage.Job.NewRepair;

import javax.swing.*;
import ExceptionsPackage.DataAccessException;

import java.awt.*;

public class Panel extends JPanel {
    public Panel() throws DataAccessException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        ContentPanel contentPanel = new ContentPanel();
        JPanel confirmationPanel = new JPanel();

        JLabel title = new JLabel("Enregistrement d'une nouvelle réparation.");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(title);

        JButton returnButton = new JButton("Return");
        JButton confirmButton = new JButton("Confirm");
        confirmationPanel.setLayout(new FlowLayout());
        confirmationPanel.add(returnButton);
        confirmationPanel.add(confirmButton);

        add(titlePanel);
        add(contentPanel);
        add(confirmationPanel);

        returnButton.addActionListener(e -> {
            try {
                contentPanel.goPreviousState();
            } catch (DataAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
        confirmButton.addActionListener(e -> {
            try {
                contentPanel.goNextState();
            } catch (DataAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
