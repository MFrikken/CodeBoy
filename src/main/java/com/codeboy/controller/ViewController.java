package com.codeboy.controller;

import com.codeboy.utility.FileReaderUtility;
import com.codeboy.utility.JavaScriptBridge;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

import java.io.File;
import java.util.function.Consumer;

// TODO:
// https://stackoverflow.com/questions/32564195/load-a-new-page-in-javafx-webview
public class ViewController {

    private JSObject window;

    public ViewController(WebEngine webEngine) {
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("viewController", new ViewController(webEngine));
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
        });
    }
}
