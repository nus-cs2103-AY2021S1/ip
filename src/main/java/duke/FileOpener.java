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
        File prev_tasks = new File(path);
        try {
            if (!prev_tasks.exists()) {
                prev_tasks.createNewFile();
            }
            prev_tasks.setReadable(true);
            prev_tasks.setWritable(true);
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return prev_tasks;
    }
}
