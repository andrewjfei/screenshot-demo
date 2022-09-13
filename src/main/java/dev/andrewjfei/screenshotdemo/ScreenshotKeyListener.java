package dev.andrewjfei.screenshotdemo;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 * This class listens to native key actions such as key pressed and key released. Based on these actions, this class
 * will determine if any screenshots need to be taken.
 *
 * @author andrewjfei
 */
public class ScreenshotKeyListener implements NativeKeyListener {
    private static final String FILE_PATH =  System.getProperty("user.home") + "/Documents/screenshots/";
    private static final String FILE_NAME = "screenshot";

    private Screenshot screenshot;
    private Set<Integer> activeKeySet;

    public ScreenshotKeyListener() {
        screenshot = new Screenshot(FILE_PATH);
        activeKeySet = new HashSet<>();
    }

    /**
     * This method is triggered when a key is pressed down on the machine.
     * @param e the native key event received from the machine.
     */
    public void nativeKeyPressed(NativeKeyEvent e) {
        activeKeySet.add(e.getKeyCode());

        // Check if screenshot hotkeys are pressed
        if (isScreenshotKeysPressed()) {
            screenshot.fullScreenCapture(FILE_NAME);
        }

        // Unregister global native listener if Esc is pressed
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    /**
     * This method is triggered when a key is released on the machine.
     * @param e the native key event received from the machine.
     */
    public void nativeKeyReleased(NativeKeyEvent e) {
        activeKeySet.remove(e.getKeyCode());
    }

    /**
     * This method checks if the appropriate keys are pressed together at the same time to trigger the screenshot.
     * @return {@code true} if {@code Shift + Command + S} is pressed at the same time, otherwise {@code false}.
     */
    private boolean isScreenshotKeysPressed() {
        // TODO: Check that no other keys apart from Shift + Command + S are pressed
        return activeKeySet.contains(NativeKeyEvent.VC_SHIFT) && activeKeySet.contains(NativeKeyEvent.VC_META) && activeKeySet.contains(NativeKeyEvent.VC_S);
    }
}
