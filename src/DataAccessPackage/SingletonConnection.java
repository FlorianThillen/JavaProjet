package DataAccessPackage;

import ExceptionsPackage.*;

import javax.swing.*;
import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    private SingletonConnection() {
    }

    public static Connection getInstance() throws ConnectionException {
        try {
            if (uniqueConnection == null) {
                JTextField usernameField = new JTextField();
                JPasswordField passwordField = new JPasswordField();

                Object[] message = {
                        "Username:", usernameField,
                        "Password:", passwordField
                };

                int option = JOptionPane.showOptionDialog(null, message, "Please enter your credentials",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                if (option == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    char[] passwordChars = passwordField.getPassword();
                    String password = new String(passwordChars);

                    uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/velodb",
                            username,
                            password);
                } else {
                    throw new ConnectionException("La connexion à la base de données a été annulée.");
                }
            }
            return uniqueConnection;
        } catch (SQLException sqlException) {
            throw new ConnectionException("Erreur lors de la connexion à la base de données libiavelo. -> " + sqlException.getMessage());
        }
    }
    public static void closeConnection() throws ConnectionException {
        if (uniqueConnection != null) {
            try {
                uniqueConnection.close();
                uniqueConnection = null;
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException sqlException) {
                throw new ConnectionException("Erreur lors de la fermeture de la connexion à la base de données");
            }
        }
    }
}