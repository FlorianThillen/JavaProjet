package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class BikeAnimation extends JPanel implements Runnable {
    private int x = 0;
    private int y;

    public BikeAnimation() {
        new Thread(this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        y = getHeight() - 90;
        drawRoad(g);
        drawBike(g, x, y);
    }

    private void drawRoad(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, getHeight() - 50, getWidth(), 50);
        g.setColor(Color.WHITE);
        for (int i = 0; i < getWidth(); i += 40) {
            g.fillRect(i, getHeight() - 25, 20, 5);
        }
    }

    private void drawBike(Graphics g, int x, int y) {
        // Roues
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 40, 40);         // Roue avant
        g.drawOval(x + 70, y, 40, 40);    // Roue arrière

        // Cadre
        g.drawLine(x + 20, y + 20, x + 70, y - 10);
        g.drawLine(x + 90, y + 20, x + 70, y - 10);
        g.drawLine(x + 20, y + 20, x + 40, y - 10);
        g.drawLine(x + 40, y - 10, x + 70, y - 10);

        // Guidon
        g.drawLine(x + 70, y - 10, x + 70, y - 40);
        g.drawLine(x + 60, y - 40, x + 80, y - 40);

        // Selle
        g.drawLine(x + 40, y - 10, x + 40, y - 30);
        g.drawLine(x + 35, y - 30, x + 45, y - 30);

        // Bonhomme
        int headX = x + 40;
        int headY = y - 50;

        // Tête couleur peau
        g.setColor(new Color(255, 220, 180));
        g.fillOval(headX - 5, headY - 10, 10, 10);

        // Casque rouge
        g.setColor(Color.RED);
        g.fillArc(headX - 6, headY - 12, 12, 8, 0, 180);

        // Corps en bleu (buste épaissi)
        g.setColor(Color.BLUE);
        g.fillRect(headX - 4, headY, 8, 20); // rectangle pour le torse

        // Bras
        g.drawLine(headX, headY + 5, x + 70, y - 40);  // bras 1
        g.drawLine(headX, headY + 5, x + 60, y - 40);  // bras 2

        // Jambes en gris
        g.setColor(Color.DARK_GRAY);
        g.drawLine(headX, headY + 20, x + 50, y + 10);  // jambe 1
        g.drawLine(headX, headY + 20, x + 55, y + 10);  // jambe 2
    }

    @Override
    public void run() {
        while (true) {
            x += 5;
            if (x > getWidth()) {
                x = -140;
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
