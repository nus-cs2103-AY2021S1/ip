package duke;

import java.io.File;
import java.io.IOException;

public class FileOpener {
    public static File openFile(String path) {
        File prev_tasks = new File("prev_tasks.txt");
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