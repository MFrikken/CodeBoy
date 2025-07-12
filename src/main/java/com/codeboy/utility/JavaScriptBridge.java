package com.codeboy.utility;

public class JavaScriptBridge {
    public void log(String message) {
        System.out.println("[JS Console] " + message);
    }

    public void error(String message) {
        System.err.println("[JS Console] " + message);
    }
}
