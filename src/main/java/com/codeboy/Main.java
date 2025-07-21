package com.codeboy;

import com.codeboy.controller.ViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.logging.Logger;

public class Main extends Application {
    static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        new ViewController(webEngine);

        webEngine.setOnError(event -> LOGGER.warning("[Frontend] An error occured on the frontend: " + event.getMessage()));

        File htmlFile = new File(getClass().getResource("/html/index.html").getFile());
        webEngine.load(htmlFile.toURI().toString());

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("CodeBoy Desktop");

        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();

            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                java.awt.Image dockIcon = defaultToolkit.getImage(getClass().getResource("/logos/macbook_dock_icon_1024x1024.png"));
                taskbar.setIconImage(dockIcon);
            }
        } else {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/logos/macbook_dock_icon_1024x1024.png")));
        }
        stage.show();
    }
}