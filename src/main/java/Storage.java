import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final Path filePath;

    private void openFile() throws Exception {
        if (!Files.exists(this.filePath)) {
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        }
    }

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws Exception {
        ArrayList<Task> tasks = new ArrayList<Task>();

        openFile();
        BufferedReader reader = Files.newBufferedReader(this.filePath);
        int taskCount = Integer.parseInt(reader.readLine());
        for(int i = 0; i < taskCount; ++i) {
            tasks.add(TaskParser.parseTask(reader));
        }
        reader.close();
        return tasks;
    }

    public void saveData(ArrayList<Task> tasks) throws Exception {
        openFile();
        BufferedWriter writer = Files.newBufferedWriter(this.filePath);
        writer.write(String.valueOf(tasks.size()));
        writer.newLine();
        for(Task task : tasks) {
            writer.write(task.toString());
            writer.newLine();
        }
        writer.close();
    }
}
