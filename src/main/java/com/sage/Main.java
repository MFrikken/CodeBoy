package com.sage;

import java.io.File;

import com.sage.controller.WeaknessController;
import com.sage.model.weakness.WeaknessDto;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        File htmlFile = new File(getClass().getResource("/html/index.html").getFile());
        webEngine.load(htmlFile.toURI().toString());

        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("javaApp", new JavaBridge());

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 1500, 900);
        stage.setScene(scene);
        stage.setTitle("Sage UI");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/sage-icon.png")));
        stage.show();

        WeaknessDto weakness = new WeaknessDto("semgrep_id", "eslint.detect-eval-with-expression", "eslint.detect-eval-with-expression", "https://semgrep.dev/r/gitlab.eslint.detect-eval-with-expression");
        if (WeaknessController.getInstance().save(weakness))
            System.out.println("Fetching...\nFetched" + WeaknessController.getInstance().fetchById("0").toString());
    }

    class JavaBridge {
        public void showMessage(String message) {
            System.out.println("Message from JS: " + message);
        }
    }
}