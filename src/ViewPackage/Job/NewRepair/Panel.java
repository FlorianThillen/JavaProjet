package ViewPackage.Job.NewRepair;

import javax.swing.*;
import ExceptionsPackage.DataAccesException;

public class Panel extends JPanel {
    public Panel() throws DataAccesException {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        ContentPanel contentPanel = new ContentPanel();
        JPanel confirmationPanel = new JPanel();

        titlePanel.add(new JLabel("Title"));

        JButton returnButton = new JButton("Return");
        JButton confirmButton = new JButton("Confirm");
        confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.X_AXIS));
        confirmationPanel.add(returnButton);
        confirmationPanel.add(confirmButton);

        add(titlePanel);
        add(contentPanel);
        add(confirmationPanel);

        returnButton.addActionListener(e -> {
            try {
                contentPanel.goPreviousState();
            } catch (DataAccesException ex) {
                throw new RuntimeException(ex);
            }
        });

        confirmButton.addActionListener(e -> {
            try {
                contentPanel.goNextState();
            } catch (DataAccesException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
