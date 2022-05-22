package com.company.krishchik.course_work_operating_systems.Chart;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import com.sun.management.OperatingSystemMXBean;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;


public class MemoryLoaderChart extends JFrame{

    private static OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    MySwingWorker mySwingWorker;
    SwingWrapper<XYChart> sw;
    XYChart chart;
    public static void main(String[] args) throws Exception {

        CpuLoaderChart cpuLoad = new CpuLoaderChart();
        cpuLoad.go();

    }

    public void go() {

        Container container = this.getContentPane();
        // Create Chart
        chart = QuickChart.getChart("загруженность оперативной памяти", "время", "занято, Gb", "randomWalk", new double[]{0}, new double[]{0});
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);

        // Show it
        sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();

        mySwingWorker = new MySwingWorker();
        mySwingWorker.execute();

    }

    private class MySwingWorker extends SwingWorker<Boolean, double[]> {

        LinkedList<Double> fifo = new LinkedList<Double>();

        public MySwingWorker() {

            fifo.add(0.0);
        }

        private static double getFreeMemory() throws InterruptedException {
            Thread.sleep(50);
            return operatingSystemMXBean.getFreeMemorySize();

        }

        @Override
        protected Boolean doInBackground() throws Exception {

            while (!isCancelled()) {

                fifo.add(5.9-(getFreeMemory()/1024/1024/1024));
                if (fifo.size() > 500) {
                    fifo.removeFirst();
                }

                double[] array = new double[fifo.size()];
                for (int i = 0; i < fifo.size(); i++) {
                    array[i] = fifo.get(i);
                }
                publish(array);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("MySwingWorker shut down.");
                }

            }

            return true;
        }

        @Override
        protected void process(List<double[]> chunks) {

            double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

            chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
            sw.repaintChart();

            long start = System.currentTimeMillis();
            long duration = System.currentTimeMillis() - start;
            try {
                Thread.sleep(400 - duration);
            } catch (InterruptedException e) {
            }

        }
    }
}