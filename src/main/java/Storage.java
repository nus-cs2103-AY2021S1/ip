import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void addToList(Task task) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.output());
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    public void deleteTask(List<Task> list) throws InvalidCommandException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : list) {
                fw.write(task.output());
            }
            fw.close();
        } catch (IOException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
