package com.company.krishchik.course_work_operating_systems.Info;

import com.profesorfalken.wmi4java.WMI4Java;
import com.profesorfalken.wmi4java.WMIClass;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MemoryInfo {

    public Map<String, String> getInfo() {
        return parseInfo();
    }

    protected Map<String, String> parseInfo() {
        Map<String, String> memoryDataMap = new TreeMap<>();
        Map<String, String> memoryMap = new HashMap<>();
        memoryMap.putAll(WMI4Java.get().VBSEngine().getWMIObject(WMIClass.WIN32_PHYSICALMEMORY));

        memoryDataMap.put("Capacity", convertBytesToGBytes(memoryMap.get("Capacity")));
        memoryDataMap.put("MaxVoltage", memoryMap.get("MaxVoltage"));
        memoryDataMap.put("Speed", memoryMap.get("Speed"));
        memoryDataMap.put("Manufacturer", memoryMap.get("Manufacturer"));
        return memoryDataMap;
    }

    private String convertBytesToGBytes(String bytesString) {
        double bytes = Double.valueOf(bytesString);
        bytes = bytes/1024/1024/1024;
        return bytes +" gb";
    }

}
