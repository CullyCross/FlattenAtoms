package utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cullycross on 2/12/15.
 */
public class MovingPanel extends JPanel {

    private final int D = 200; //diameter
    private final int X = 40; //x coord
    private final float COEFFICIENT = (float)((4f/3f) * 0.07 * Math.sqrt(D/2));

    private int yFirst = 40;
    private int ySecond = yFirst + D + 10;

    private final int yCenter = ySecond - yFirst + (int)(0.175 * D); //MAGIC CONSTANTS

    public static volatile double F;
    public static volatile double d;

    public static boolean sRun = true;

    public MovingPanel() {
        super();
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        update();

        g.setColor(Color.BLUE);
        g.fillOval(X, yFirst,
                D, D);
        g.fillOval(X, ySecond,
                D, D);
        g.setColor(Color.BLACK);
        g.drawLine(X/2, yCenter, (int)(6.5*X), yCenter);
    }

    private void update() {
        d = (D - (ySecond - yFirst))/2;
        if(d < 0) d = 0;
        F = COEFFICIENT * d * Math.sqrt(d);

        if(d < 0.2 * (D/2)) {
            yFirst++;
            ySecond--;
        } else {
            sRun = false;
        }
    }
}
