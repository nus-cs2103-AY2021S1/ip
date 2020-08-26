import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskStorage {

    private TaskListManager taskListManager = new TaskListManager();

    public TaskStorage(TaskListManager taskListManager){
        this.taskListManager = taskListManager;
    }

    private static final String FILE_PATH = "/Users/yyutong/Desktop/iP/data/duke.txt";

    public void saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskListManager.getListOfTasks()) {
                writer.write(task.showContent());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
