import java.io.File;
import java.util.ArrayList;

public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    private ArrayList<Task> tasks;

    public Storage (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void getSavedTasks() {
        // print out the file content
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(FILE_PATH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks() {

    }
}
