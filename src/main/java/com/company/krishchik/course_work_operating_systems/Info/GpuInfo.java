package com.company.krishchik.course_work_operating_systems.Info;

import com.company.krishchik.course_work_operating_systems.Util.DirectXInfoLoader;
import com.profesorfalken.jsensors.JSensors;

import java.util.*;

public class GpuInfo {

    protected TreeMap<String, String> parseInfo() {
        TreeMap<String, String> graphicsCardDataMap = new TreeMap<>();

        DirectXInfoLoader directXinfo = DirectXInfoLoader.get;
        List<Map<String, String>> rawDisplayInfoMap = directXinfo.getDisplayInfo();
        List<com.profesorfalken.jsensors.model.components.Gpu> gpus = JSensors.get.components().gpus;

        int numDevice = 0;
        for (final Map<String, String> displayInfoMap : rawDisplayInfoMap) {
            graphicsCardDataMap.put("2.name_" + numDevice, displayInfoMap.get("CardName"));
            graphicsCardDataMap.put("4.manufacturer_" + numDevice, displayInfoMap.get("Manufacturer"));
            graphicsCardDataMap.put("3.chip_type_" + numDevice, displayInfoMap.get("ChipType"));
            graphicsCardDataMap.put("6.device_type_" + numDevice, displayInfoMap.get("DeviceType"));
            graphicsCardDataMap.put("7.dedicated_memory_" + numDevice, displayInfoMap.get("DedicatedMemory"));
            graphicsCardDataMap.put("8.shared_memory_" + numDevice, displayInfoMap.get("SharedMemory"));
            graphicsCardDataMap.put("9.display_memory_" + numDevice, displayInfoMap.get("DisplayMemory"));

            if (gpus.size() > numDevice) {
                com.profesorfalken.jsensors.model.components.Gpu gpu = gpus.get(numDevice);
                if (gpu.sensors.temperatures != null && !gpu.sensors.temperatures.isEmpty()) {
                    graphicsCardDataMap.put("5.temperature_" + numDevice,
                            String.valueOf(gpu.sensors.temperatures.get(0).value.intValue()));
                }
            }
            numDevice++;
        }

        graphicsCardDataMap.put("1.numOfGraphicsCards", String.valueOf(numDevice));

        return graphicsCardDataMap;
    }

    public TreeMap<String, String> getInfo() {
        return parseInfo();
    }
}
