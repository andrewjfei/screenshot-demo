package dev.andrewjfei.screenshotdemo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class for file creation and modification.
 *
 * @author andrewjfei
 */
public class FileUtil {
    public static final String DATE_TIME_PATTERN = "ddMMyy-HHmmss";

    /**
     * A utility method for creating a {@code File} with a formatted file name utilising the current date time.
     * @param path the path of the {@code File} to be returned. Can be an absolute or relative path.
     * @param name the main name of the {@code File} to be returned. Usually, this method is called for a number
     *             of files which can be grouped together, hence sharing the same "main" name.
     * @param fileFormat the format the of the {@code File} to be returned.
     * @return a file with a unique name based on name provided and the current date time the method is called.
     *
     * @see FileFormat
     */
    public static File getFileWithDate(String path, String name, FileFormat fileFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        String formattedDate = simpleDateFormat.format(new Date());
        return new File(path + name + "-" + formattedDate + "." + fileFormat.getValue());
    }
}
