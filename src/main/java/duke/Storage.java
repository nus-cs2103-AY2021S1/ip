package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private File memory;

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
