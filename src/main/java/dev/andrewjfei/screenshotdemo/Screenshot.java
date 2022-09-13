package dev.andrewjfei.screenshotdemo;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 * A class utilised to take screenshots.
 *
 * @author andrewjfei
 */
public class Screenshot {
    private String filePath;

    private Rectangle screenshotRectangle;

    public Screenshot(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method which takes a screenshot of the entire screen and then saves that screenshot to a destination located
     * at {@code /User/{username}/Desktop/screenshots}.
     */
    public void fullScreenCapture(String name) {
        setFullScreen();

        try {
            // Take screenshot using Robot
            Robot robot = new Robot();
            Image screenshotImage = robot.createMultiResolutionScreenCapture(screenshotRectangle)
                    .getResolutionVariant(3024, 1964);

            // Get a file object with correct file path
            File screenshotFile = FileUtil.getFileWithDate(filePath, name, FileFormat.PNG);

            // Make directories if they don't exist
            screenshotFile.mkdirs();

            // Write image to file
            ImageIO.write((RenderedImage) screenshotImage, FileFormat.PNG.getValue(), screenshotFile);
            System.out.println("Screenshot taken on " + new Date() + ".");
        } catch (AWTException awtException) {
            System.out.println("AWT Exception");
        } catch (IOException ioException) {
            System.out.println("IO Exception");
        }
    }

    /**
     * Set the {@code screenshotRectangle} field to the full screen size of the machine.
     */
    private void setFullScreen() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenshotRectangle = new Rectangle(toolkit.getScreenSize());
    }

}
