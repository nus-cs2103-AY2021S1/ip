package duke;

import java.io.File;
import java.io.IOException;

public class FileOpener {
    /**
     * openFile gracefully handles any exception while opening a text file, or
     * creates the file if it doesn't exist
     * Useful in storing tasks in and out of hard drive.
     * @param path RELATIVE path to the file
     * @return File type object, which can be read and written into
     */
    public static File openFile(String path) {
        File prevTasks = new File(path);
        File returnFile = null;
        try {
            if (!prevTasks.exists()) {
                // New user detected. Populate data from sample file, and create prevTasks.txt
                returnFile = new File("sampleTasks.txt");
                prevTasks.createNewFile();
            } else {
                returnFile = prevTasks;
                returnFile.setWritable(true);
            }
            returnFile.setReadable(true);

        } catch (IOException e) {
            System.out.println(e);
        }

        assert returnFile.exists() : "File should exist before being returned";
        return returnFile;
    }
}
