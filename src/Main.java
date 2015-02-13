import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.MovingPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by cullycross on 2/12/15.
 */
public class Main {

    //form size
    private final static int FORM_WIDTH = 700;
    private final static int FORM_HEIGHT = 500;

    private final static int TIME_BETWEEN_ITERATIONS = 300; //set here delay time(ms)

    public static void main(String... args) {
        final JFrame frame = new JFrame("For my shining stars!");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(FORM_WIDTH, FORM_HEIGHT);
        GridLayout layout = new GridLayout(1,2);
        frame.setLayout(layout);

        frame.add(new MovingPanel());

        //объект кривой
        final XYSeries series = new XYSeries("data");
         //данные
        final XYDataset dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Chart",
                "F, N", "S, mkm^2", dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        //chart.getXYPlot().getDomainAxis().setRange(0.0d, 260d);
        //chart.getXYPlot().getRangeAxis().setRange(0.0d, 260d);

        frame.getContentPane().add(chartPanel);

        frame.setVisible(true);

        final Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
                series.add(MovingPanel.F, Math.PI * MovingPanel.d);
                if (!MovingPanel.sRun) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, TIME_BETWEEN_ITERATIONS);
    }
}
