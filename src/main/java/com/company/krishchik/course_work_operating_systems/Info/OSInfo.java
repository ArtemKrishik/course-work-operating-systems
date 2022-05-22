package com.company.krishchik.course_work_operating_systems.Info;

import com.profesorfalken.wmi4java.WMI4Java;
import com.profesorfalken.wmi4java.WMIClass;

import java.util.Map;
import java.util.TreeMap;

public class OSInfo {

    public Map<String, String> getInfo() {
        return parseInfo();
    }


    protected TreeMap<String, String> parseInfo() {
        Map<String, String> osData
                = WMI4Java.get().VBSEngine().getWMIObject(WMIClass.WIN32_OPERATINGSYSTEM);
        TreeMap<String, String> osDataMap = new TreeMap<>();
        osDataMap.put("8.LocalTime", normalizeBootUpDate(osData.get("LocalDateTime")));
        osDataMap.put("7.LastBootTime", normalizeBootUpDate(osData.get("LastBootUpTime")));
        osDataMap.put("6.InstallDate", normalizeBootUpDate(osData.get("InstallDate")));
        osDataMap.put("1.Name", osData.get("Caption"));
        osDataMap.put("3.SystemName", osData.get("CSName"));
        osDataMap.put("5.CountryCode", osData.get("CountryCode"));
        osDataMap.put("4.BootDevice", osData.get("BootDevice"));
        osDataMap.put("2.Manufacturer", osData.get("Manufacturer"));

        return osDataMap;
    }

    private static String normalizeBootUpDate(String rawBootUpTime) {
        String date;
        date = Integer.valueOf(rawBootUpTime.substring(0, 4))+".";
        date+= Integer.valueOf(rawBootUpTime.substring(4, 6))+".";
        date+=Integer.valueOf(rawBootUpTime.substring(6, 8))+" ";
        date+= Integer.valueOf(rawBootUpTime.substring(8, 10))+":";
        date+= Integer.valueOf(rawBootUpTime.substring(10, 12)) +":";
        date+= Integer.valueOf(rawBootUpTime.substring(12, 14)).toString();
        return date;
    }

}
