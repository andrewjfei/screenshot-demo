package dev.andrewjfei.screenshotdemo;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) {
        try {
            // Register global native listener
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException nativeHookException) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(nativeHookException.getMessage());

            System.exit(1);
        }

        // Add screenshot key listener as a global key listener
        GlobalScreen.addNativeKeyListener(new ScreenshotKeyListener());
    }
}