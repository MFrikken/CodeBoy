package com.sage;

import com.sage.controller.MainController;
import com.sage.controller.VulnerabilityController;
import com.sage.controller.WeaknessController;
import com.sage.utility.FileReaderUtility;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;
import java.util.logging.Logger;

public class Main extends Application {
    static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        File htmlFile = new File(getClass().getResource("/html/index.html").getFile());
        webEngine.load(htmlFile.toURI().toString());

        // Wait until WebView is fully loaded before injecting WeaknessController
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("mainController", MainController.getInstance());
                window.setMember("weaknessController", WeaknessController.getInstance());
                window.setMember("vulnerabilityController", VulnerabilityController.getInstance());
                window.setMember("fileReader", FileReaderUtility.getInstance());
            }
        });

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("Sage UI");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/logos/taskbar_icon.png")));
        stage.show();
    }
}