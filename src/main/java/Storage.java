import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final Path FILE_PATH;

    public Storage(Path filePath) {
        this.FILE_PATH = filePath;
    }

    public void storeList(List<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH.toString());

            for (Task task : taskList) {
                writer.write(task.fileString() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> loadList() {
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader reader = java.nio.file.Files.newBufferedReader(FILE_PATH);

            String line;
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stringList;
    }
}
