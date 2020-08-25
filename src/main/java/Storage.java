import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public File load() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        return file;
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter updateFile = new FileWriter(this.filePath);
        ArrayList<Task> tasks = taskList.getTaskList();
        for (Task task : tasks) {
            updateFile.write(task.writeToFile() + "\n");
        }
        updateFile.close();
    }

}
