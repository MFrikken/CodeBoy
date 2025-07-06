package com.sage.utility;

public class JSConsoleBridge {
    public void log(String message) {
        System.out.println("[JS Console] " + message);
    }

    public void error(String message) {
        System.err.println("[JS Console] " + message);
    }
}
