package com.company.krishchik.course_work_operating_systems.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HardwareInfoUtils {

    private static final String CRLF = "\r\n";

    private HardwareInfoUtils() {
    }


    public static String executeCommand(String... command) throws IOException {
        String commandOutput = null;
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            commandOutput = readData(processBuilder.start());

        return commandOutput;
    }

    private static String readData(Process process) throws IOException {
        StringBuilder commandOutput = new StringBuilder();
        BufferedReader processOutput;
            processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = processOutput.readLine()) != null) {
                if (!line.isEmpty()) {
                    commandOutput.append(line).append(CRLF);
                }
            }
                if (processOutput != null) {
                    processOutput.close();
                }
        return commandOutput.toString();
    }

}
