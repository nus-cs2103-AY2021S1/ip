import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public TaskList taskList;
    static private final String HOME = System.getProperty("user.home");
    static private final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "data");
    static private final java.nio.file.Path FILE = java.nio.file.Paths.get(HOME, "data", "iPStorage.txt");

    public static Storage initialiseStorage() throws IOException, ToDoException, eventException, deadlineException {
        Storage returnStorage = new Storage();
        returnStorage.taskList = TaskList.retrieveTaskList(FILE);
        returnStorage.loadFromDisk();
        return returnStorage;
    }

    /**
     * Called when Duke is initialised
     * @throws IOException
     */
    public void loadFromDisk() throws IOException, ToDoException, eventException, deadlineException {
        taskList = TaskList.retrieveTaskList(FILE);
    }

    /**
     * Writes a specific task to the hard disc. Called by saveToDisk() method
     * @param task
     * @param printWriter
     * @throws IOException
     */
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
        printWriter.print(task.getDescription());
        printWriter.print(" ~ " + task.getDuration());
        printWriter.print("\n");
    }

    /**
     * Called every time a change is made to the list. Updates the data stored on hard disc
     * @throws IOException
     */
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
}
