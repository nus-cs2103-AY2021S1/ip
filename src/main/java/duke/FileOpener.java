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
        try {
            if (!prevTasks.exists()) {
                prevTasks.createNewFile();
            }
            prevTasks.setReadable(true);
            prevTasks.setWritable(true);

        } catch (IOException e) {
            System.out.println(e);
        }

        return prevTasks;
    }
}
