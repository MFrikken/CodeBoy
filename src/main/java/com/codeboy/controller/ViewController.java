package com.codeboy.controller;

import com.codeboy.utility.FileReaderUtility;
import com.codeboy.utility.JavaScriptBridge;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class ViewController {

    private final WebEngine webEngine;

    public ViewController(WebEngine webEngine) {
        this.webEngine = webEngine;
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                injectJavaControllers();
            }
        });
    }

    private void injectJavaControllers() {
        JSObject window = (JSObject) webEngine.executeScript("window");

        window.setMember("viewController", this);
        window.setMember("fileController", FileController.getInstance());
        window.setMember("weaknessController", WeaknessController.getInstance());
        window.setMember("vulnerabilityController", VulnerabilityController.getInstance());
        window.setMember("fileReader", FileReaderUtility.getInstance());

        // TODO: remove later - just for debugging purposes
        window.setMember("javascriptBridge", new JavaScriptBridge());
        webEngine.executeScript("""
                    console.log = function(msg) {
                        javascriptBridge.log(msg);
                    };
                    console.error = function(msg) {
                        javascriptBridge.error(msg);
                    };
                """);
    }
}
