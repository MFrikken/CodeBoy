package com.codeboy.utility;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.logging.Logger;

import javafx.stage.FileChooser;

public class FileReaderUtility {
    private static final Logger LOGGER = Logger.getLogger(FileReaderUtility.class.getName());

    private static FileReaderUtility instance;
    private String lastFilePath;
    private File file;

    private FileReaderUtility() {
        lastFilePath = null;
        file = null;
    }

    public static FileReaderUtility getInstance() {
        if (instance == null) {
            instance = new FileReaderUtility();
        }
        return instance;
    }

    public File readFile(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new InvalidParameterException("Given file path must not be null or empty.");
        }
        if (filePath.equals(this.lastFilePath)) {
            return this.file;
        }

        this.file = new File(filePath);
        if (this.file.exists()) {
            this.lastFilePath = filePath;
            return this.file;
        } else {
            LOGGER.severe(String.format("[FileReaderUtility] Could not read file from: {%s}", filePath));
            return null;
        }
    }

    public String getContent(File file) throws FileNotFoundException {
        if (file != null) {
            throw new InvalidParameterException("Given file must not be null.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                content.append(line + " ");
                line = reader.readLine();
            }
            return content.toString();
        } catch (IOException e) {
            LOGGER.severe(String.format("[FileReaderUtility] Could not read from file: {s%}", file.getAbsolutePath()));
            throw new RuntimeException(e);
        }
    }

    // only for view
    public String getFilePath() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON FILES", "*.json"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null)
            return file.getAbsolutePath();

        throw new FileNotFoundException();
    }
}
