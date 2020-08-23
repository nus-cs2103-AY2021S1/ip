import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Storage {

    private String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = DukeFileReader.readStorageTasks(filepath);
        return tasks;
    }
}
