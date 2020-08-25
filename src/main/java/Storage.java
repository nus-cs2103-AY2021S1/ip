import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.StandardSocketOptions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";
    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    public void readFile() {
        // load the file first
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f = new File(FILE_PATH);
        try {
            if (f.createNewFile()) {
                // file does not exist
                System.out.println("A data file has been created for you");
            } else {
                // file exist
                System.out.println("Here are your existing tasks");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    System.out.println(taskStr);
                    // load the task
                    Task savedTask = Parser.parseTaskFromStorage(taskStr);
                    taskList.tasks.add(savedTask);
                }
            }
            System.out.println("What do you want to do today?");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks(TaskList taskList) {
        if (taskList.tasks.size() == 0) {
            return;
        } else {
            writeToFile(FILE_PATH, taskList.tasks.get(0).getData());
            for (int i = 1; i < taskList.tasks.size(); i++) {
                String taskData = taskList.tasks.get(i).getData();
                appendToFile(FILE_PATH, taskData);
            }
        }
    }

    public void appendToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
