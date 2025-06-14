package com.sage.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.sage.model.vulnerability.VulnerabilityModel;
import com.sage.repository.VulnerabilityRepository;
import com.sage.repository.WeaknessRepository;
import com.sage.utility.FileReaderUtility;
import com.sage.utility.JsonParser;

public class FileService {
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    private VulnerabilityRepository vulnerabilityRepository = new VulnerabilityRepository();
    private WeaknessRepository weaknessRepository = new WeaknessRepository();

    public boolean processFile(String filePath) {
        File file;
        try {
            file = FileReaderUtility.getInstance().readFile(filePath);

            if (file != null) {
                JsonNode fileTree = JsonParser.asJsonNodeObject(file);
                JsonNode vulnerabilitiesJson = fileTree.get("vulnerabilities");
                int id = 1;
                List<VulnerabilityModel> vulnerabilityModelList = new ArrayList<>();
                for (JsonNode vuln : vulnerabilitiesJson) {
                    vulnerabilityModelList.add(VulnerabilityModel.fromJsonNode(id, vuln));
                    id++;
                }
                this.vulnerabilityRepository.saveAll(vulnerabilityModelList);
                return true;
            }
        } catch (FileNotFoundException e) {
            LOGGER.severe("[FileService] Failed to process file: " + e.getMessage());
        }
        return false;
    }
}
