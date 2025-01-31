package com.qulificationRecomendation.qulificationRecomendation.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class PythonSkillsController {
    @GetMapping("/run-script")
    public String runPythonScript() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "/home/mateuszkulec/Dokumenty/QualificationManager/src/main/java/com/qulificationRecomendation/qulificationRecomendation/DL4J/Python/GenerateSkillsDataset.py");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return "Script executed successfully:\n" + output.toString();
            } else {
                return "Script execution failed with exit code " + exitCode + ":\n" + output.toString();
            }
        } catch (Exception e) {
            return "Error occurred while executing script: " + e.getMessage();
        }
    }
}