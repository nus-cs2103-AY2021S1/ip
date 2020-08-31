package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.tasks.TaskList;
import duke.tasks.Task;


public class Storage {

    private TaskList taskList = new TaskList();

    public Storage(TaskList taskList){
        this.taskList = taskList;
    }

    private static final String FILE_PATH = "/Users/yyutong/Desktop/iP/data/duke.txt";

    public void saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList.getListOfTasks()) {
                writer.write(task.showContent());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
