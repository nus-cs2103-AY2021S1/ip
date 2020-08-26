import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // TODO: 26/8/20 Add more relevant error for parsing
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (var br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = TaskParser.parse(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    // TODO: 26/8/20 consider a more robust check
    public boolean hasSavedTasks() {
        return new File(this.filePath).exists();
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File saveFile = new File(this.filePath);
        if (!saveFile.exists()) {
            boolean directoryCreated = saveFile.getParentFile().mkdirs();
            if (!directoryCreated) {
                throw new IOException("Unable to create parent directories to save file");
            }
            boolean saveFileCreated = saveFile.createNewFile();
            if (!saveFileCreated) {
                throw new IOException("Unable to create save file");
            }
        }

        // Use PrintWriter wrapping BufferedWriter in FileWriter
        try (var out = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath)))) {
            for (Task task : tasks) {
                out.println(task.toSaveString());
            }
        }
    }

}
