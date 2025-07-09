package com.sage.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.sage.model.vulnerability.VulnerabilityModel;
import com.sage.model.weakness.WeaknessModel;
import com.sage.repository.VulnerabilityRepository;
import com.sage.repository.WeaknessRepository;
import com.sage.utility.FileReaderUtility;
import com.sage.utility.JsonParser;

public class FileService {
    private static final Logger LOGGER = Logger.getLogger(FileService.class.getName());

    private VulnerabilityRepository vulnerabilityRepository = new VulnerabilityRepository();

    public boolean processFile(String filePath) {
        File file;
        try {
            file = FileReaderUtility.getInstance().readFile(filePath);

            if (file != null) {
                JsonNode fileTree = JsonParser.asJsonNodeObject(file);

                ArrayList<VulnerabilityModel> vulnerabilityModelList = new ArrayList<>();
                for (JsonNode vulnerabilityJson : fileTree.get("vulnerabilities")) {
                    VulnerabilityModel vulnerabilityModel = VulnerabilityModel.fromJsonNode(vulnerabilityJson);

                    ArrayList<WeaknessModel> weaknessModelList = this.extractWeaknesses(vulnerabilityJson, vulnerabilityModel);

                    vulnerabilityModel.setWeaknesses(weaknessModelList);
                    vulnerabilityModelList.add(vulnerabilityModel);
                }

                this.vulnerabilityRepository.saveAll(vulnerabilityModelList);

                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            LOGGER.severe("[FileService] Failed to process file: " + e.getMessage());
            return false;
        }
    }

    private ArrayList<WeaknessModel> extractWeaknesses(JsonNode vulnerabilityJson, VulnerabilityModel vulnerabilityModel) {
        ArrayList<WeaknessModel> weaknessModelList = new ArrayList<>();
        try {
            for (JsonNode weaknessVulnerability : vulnerabilityJson.get("identifiers")) {
                if (weaknessVulnerability == null) { continue; }
                WeaknessModel weaknessModel = WeaknessModel.fromJsonNode(vulnerabilityModel, weaknessVulnerability);
                weaknessModelList.add(weaknessModel);
            }
        } catch (Exception e) {
            LOGGER.severe("[FileService] Failed to extract weaknesses: " + e.getMessage());
            throw e;
        }
        return weaknessModelList;

    }
}
