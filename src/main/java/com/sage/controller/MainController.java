package com.sage.controller;

import java.util.HashMap;
import java.util.logging.Logger;
import java.util.List;

import com.sage.service.FileService;
import com.sage.service.VulnerabilityService;

public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private static MainController instance;
    private static FileService fileService;
    private static VulnerabilityService vulnerabilityService;

    private MainController() {

        fileService = new FileService();
        vulnerabilityService = new VulnerabilityService();
    }

    public static MainController getInstance() {
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public HashMap<String, Integer> process(String filePath) {
        System.out.println("Processing file: " + filePath);
        if (fileService.processFile(filePath))
            return vulnerabilityService.getStatistics();
        LOGGER.severe(String.format("[MainController] Failed to process SAST-Report-File: %s", filePath));
        throw new InternalError("File: " + filePath + " could not be processed\n");
    }

    public void fetchAllVulnerabilities() {
        vulnerabilityService.getAll();
    }
}
