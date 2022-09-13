package dev.andrewjfei.screenshotdemo;

/**
 * Contains a list of all the file formats which are supported for generating a screenshot.
 *
 * @author andrewjfei
 */
public enum FileFormat {
    JPG("jpg"), PNG("png"), BMP("bmp");

    private String value;

    FileFormat(String value) {
        this.value = value;
    }

    /**
     * Get the lowercase string value of the file format.
     * @return the value of the file format.
     */
    public String getValue() {
        return value;
    }
}
