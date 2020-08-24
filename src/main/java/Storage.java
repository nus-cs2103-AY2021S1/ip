import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }

    TaskList getTasksFromFile() {
        File dir = new File(file.getParent());
        try {
            if (dir.mkdir()) {
                System.out.println("Setting up file paths...");
            }
            if (file.createNewFile()) {
                System.out.println("Uh oh, I couldn't find any saved tasks, "
                        + "so I just made a new save for you!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TaskList(file);
    }

    void write(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(file.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(taskList.write());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
