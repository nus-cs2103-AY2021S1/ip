package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.InvalidFilePathException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Represents the storage class. Storage class is a class consists of methods
 * related to storing the list of tasks and updating the file used for
 * storage.
 */
public class Storage {

    // The default file path if the user does not provide the file path.
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private String filePath;
    private String directoryPath;

    /**
     * Constructs a storage object.
     *
     * @param filePath The file path.
     * @param directoryPath The directory path.
     * @throws InvalidFilePathException If the file path is invalid.
     */
    public Storage(String filePath, String directoryPath) {
        try {
            File directory = new File(directoryPath);
            if (!isValidFilePath(filePath)) {
                throw new InvalidFilePathException("The storage file should end with '.txt'");
            } else {
                if (!directory.exists()) {
                    directory.mkdir();
                }
                this.filePath = filePath;
                this.directoryPath = directoryPath;
                assert directory.exists() : "Failed to create the directory";
            }
        } catch (InvalidFilePathException e) {
            e.getMessage();
        }
    }

    /**
     * Checks if the file path is valid.
     *
     * @param filepath The file path.
     * @return True if the file path is valid, false otherwise.
     */
    private static boolean isValidFilePath(String filepath) {
        return filepath.endsWith(".txt");
    }

    /**
     * Returns the list of tasks stored in the file.
     *
     * @return The list of tasks stored in the file.
     */
    public TaskList read() {
        try {
            ArrayList<Task> listOfTasks = new ArrayList<>();
            File storageFile = new File(filePath);
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] wordsParsed = currentLine.split("\\|");
                String whetherIsDone = wordsParsed[1].equals("\u2713")
                        ? "true"
                        : "false";
                switch (wordsParsed[0]) {
                case "todo":
                    Task todo = new Todo(wordsParsed[2]);
                    todo.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(todo);
                    break;
                case "deadline":
                    Task deadline = new Deadline(wordsParsed[2], wordsParsed[3]);
                    deadline.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(deadline);
                    break;
                case "event":
                    Task event = new Event(wordsParsed[2], wordsParsed[3]);
                    event.setWhetherTaskDone(whetherIsDone);
                    listOfTasks.add(event);
                    break;
                default:
                    break;
                }
            }
            scanner.close();
            return new TaskList(listOfTasks);
        } catch (IOException e) {
            //System.out.println(e.toString());
            return new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param listOfTasks The list of tasks.
     */
    public void write(ArrayList<Task> listOfTasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                fileWriter.write(task.writeToFile() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }



}
