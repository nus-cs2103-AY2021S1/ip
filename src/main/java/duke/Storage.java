package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class handleds the interaction with the DukeMan's memory.
 * @author Dominic Siew Zhen Yu
 */

public class Storage {

    private File memory;

    /**
     *
     * @param filePath
     */

    public Storage(String filePath) {
        File file = new File(filePath);


        try {
            if (!file.exists()){
                file.getParentFile().mkdir();
                file.createNewFile();System.out.println("hello");
            }
            this.memory = file;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File load() {
        return this.memory;
    }

    public void addTaskToMemory(String event) {
        try {
            FileWriter fw = null;
            fw = new FileWriter(this.memory, true);
            fw.write(event + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
