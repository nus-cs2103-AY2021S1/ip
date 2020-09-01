import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public TaskList taskList;
    static private final String HOME = System.getProperty("user.home");
    static private final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "IdeaProjects", "ip");
    static private final java.nio.file.Path FILE = java.nio.file.Paths.get(HOME, "IdeaProjects", "ip", "iPStorage");

    public static Storage initialiseStorage() throws IOException {
        Storage returnStorage = new Storage();
        returnStorage.taskList = TaskList.retrieveTaskList(FILE);
        return returnStorage;
    }

    public void writeTask(Task task, PrintWriter printWriter) throws IOException {
        Class taskType = task.getClass();

        if (taskType.equals(Event.class)) {
            printWriter.print("E ~ ");
        } else if (taskType.equals(Deadline.class)) {
            printWriter.print("D ~ ");
        } else {
            printWriter.print("T ~ ");
        }

        printWriter.print(task.isDone ? "1 ~ " : "0 ~ ");
        printWriter.print(task.getDescription() + "\n");

    }


    public void saveToDisk() throws IOException {
        boolean directoryExists = java.nio.file.Files.exists(PATH);

        if (!directoryExists) {
            Files.createDirectory(PATH);
        }

        FileWriter fileWriter = new FileWriter(FILE.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Task task : taskList.getList()) {
            writeTask(task, printWriter);
        }
        printWriter.close();
    }

    public void loadFromDisk() throws IOException {
        taskList = TaskList.retrieveTaskList(FILE);
    }
}
