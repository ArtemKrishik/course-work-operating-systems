package com.company.krishchik.course_work_operating_systems.Info;

import java.util.*;

import com.profesorfalken.wmi4java.WMI4Java;
import com.profesorfalken.wmi4java.WMIClass;

public class ProcessorInfo {

    public TreeMap<String, String> getInfo() {
        return parseInfo();
    }

    protected TreeMap<String, String> parseInfo() {
        TreeMap<String, String> processorDataMap = new TreeMap<String, String>();
         Map<String, String> dataMap = WMI4Java.get().VBSEngine().getWMIObject(WMIClass.WIN32_PROCESSOR);

        String lineInfos = dataMap.get("Description");
        String[] infos = lineInfos.split("\\s+");

        processorDataMap.put("1.model name", dataMap.get("Name"));
        processorDataMap.put("9.vendor_id", dataMap.get("Manufacturer"));
        processorDataMap.put("4.cpu cores", dataMap.get("NumberOfCores"));
        processorDataMap.put("7.L3 Cache", dataMap.get("L3CacheSize"));
        processorDataMap.put("6.L2 Cache", dataMap.get("L2CacheSize"));
        processorDataMap.put("8.Voltage", dataMap.get("CurrentVoltage"));
        processorDataMap.put("5.thread count", dataMap.get("ThreadCount"));
        processorDataMap.put("2.clock speed", dataMap.get("CurrentClockSpeed"));
        processorDataMap.put("3.max clock speed", dataMap.get("MaxClockSpeed"));

        return processorDataMap;
    }

}
