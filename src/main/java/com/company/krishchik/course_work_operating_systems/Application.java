package com.company.krishchik.course_work_operating_systems;


import com.company.krishchik.course_work_operating_systems.Chart.CpuLoaderChart;
import com.company.krishchik.course_work_operating_systems.Chart.MemoryLoaderChart;
import com.company.krishchik.course_work_operating_systems.Info.GpuInfo;
import com.company.krishchik.course_work_operating_systems.Info.MemoryInfo;
import com.company.krishchik.course_work_operating_systems.Info.OSInfo;
import com.company.krishchik.course_work_operating_systems.Info.ProcessorInfo;
import com.company.krishchik.course_work_operating_systems.menu.Menu;
import com.company.krishchik.course_work_operating_systems.menu.MenuEntry;
import com.sun.management.OperatingSystemMXBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Map;

public class Application {

    private static OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public static void main(String[] args) throws Exception {
        /*CpuLoaderChart cpuLoaderChart = new CpuLoaderChart();
        cpuLoaderChart.go();*/






        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Menu menu = new Menu();
        ProcessorInfo processorInfo = new ProcessorInfo();
        GpuInfo gpuInfo = new GpuInfo();
        OSInfo osInfo = new OSInfo();
        MemoryInfo memoryInfo = new MemoryInfo();
        CpuLoaderChart cpuLoaderChart = new CpuLoaderChart();
        MemoryLoaderChart memoryLoaderChart = new MemoryLoaderChart();

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");


        menu.addEntry(new MenuEntry("1.information about processor") {
            @Override
            public void run() throws IOException {

                for(Map.Entry<String, String> entry : processorInfo.getInfo().entrySet())
                {
                    System.out.println(entry.getKey()+"  =  "+entry.getValue());
                }
            }
        });

        menu.addEntry(new MenuEntry("2.information about GPU") {
            @Override
            public void run() throws IOException {
                for(Map.Entry<String, String> entry : gpuInfo.getInfo().entrySet())
                {
                    System.out.println(entry.getKey()+"  =  "+entry.getValue());
                }
            }
        });

        menu.addEntry(new MenuEntry("3.information about OS") {
            @Override
            public void run() throws IOException {
                for(Map.Entry<String, String> entry : osInfo.getInfo().entrySet())
                {
                    System.out.println(entry.getKey()+"  =  "+entry.getValue());
                }
            }
        });

        menu.addEntry(new MenuEntry("4.information about operating memory") {
            @Override
            public void run() throws IOException {
                for(Map.Entry<String, String> entry : memoryInfo.getInfo().entrySet())
                {
                    System.out.println(entry.getKey()+"  =  "+entry.getValue());
                }
            }
        });

        menu.addEntry(new MenuEntry("5.CPU load chart") {
            @Override
            public void run() throws IOException {
                cpuLoaderChart.go();
            }
        });

        menu.addEntry(new MenuEntry("6.Operating memory load chart") {
            @Override
            public void run() throws IOException {

                memoryLoaderChart.go();
            }
        });



        menu.run();
    }
}







