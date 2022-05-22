package com.company.krishchik.course_work_operating_systems.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum DirectXInfoLoader {

    get;

    private boolean loaded = false;
    private Document directXData;

    private DirectXInfoLoader() {
        this.load();
        this.loaded = true;
    }

    private void load() {
        File tempFile;
        try {
            tempFile = File.createTempFile("dxdata_" + System.currentTimeMillis(), ".tmp",  new File("C:/"));

            tempFile.deleteOnExit();
            HardwareInfoUtils.executeCommand("dxdiag", "/x", tempFile.getAbsolutePath().toString());
        } catch (IOException ex) {
            return;
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            this.directXData = dBuilder.parse(tempFile);
            this.directXData.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
        }

    }

    public List<Map<String, String>> getDisplayInfo() {
        List<Map<String, String>> displays = new ArrayList<>();

        Node rootNode = directXData.getElementsByTagName("DisplayDevices").item(0);

        NodeList nodeList = rootNode.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Map<String, String> display = new HashMap<>();
            Node node = nodeList.item(i);
            NodeList deviceData = node.getChildNodes();

            for (int j = 0; j < deviceData.getLength(); j++) {
                Node deviceInfo = deviceData.item(j);
                if (deviceInfo != null && deviceInfo.getNodeType() == Node.ELEMENT_NODE) {
                    display.put(deviceInfo.getNodeName(), deviceInfo.getTextContent());
                }
            }

            displays.add(display);
        }

        return displays;
    }

}
