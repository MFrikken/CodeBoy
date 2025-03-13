package com.sage.utility;

import java.io.File;
import java.util.logging.Logger;

public class FileReader {
    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    private static FileReader instance;
    private static String lastFilePath;
    private static File file;

    private FileReader() {}

    public static FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }

    public static File readFile(String filePath) {
        if (filePath !=null && lastFilePath.equals(filePath)) {
            return file;
        }

            file = new File(filePath);
            if (file.exists()) {
                lastFilePath = filePath;
                return file;
            } else {
                LOGGER.severe(String.format("[FileReader] Could not read from file:{%s}", filePath));
                return null;
            }       
    }
}
