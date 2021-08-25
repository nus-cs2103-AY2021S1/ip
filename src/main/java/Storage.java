import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private File memory;

    public Storage(String filePath) {
        File file = new File(filePath);
        memory = file;
    }

    public File load() {
        return memory;
    }

    public void addTaskToMemory(String event) {
        try {
            FileWriter fw = null;
            fw = new FileWriter(memory, true);
            fw.write(event + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
