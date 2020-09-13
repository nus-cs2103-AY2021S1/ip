package duke;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import duke.task.Task;
import duke.task.TaskList;

/** Storage class to manage the saving of TaskList into hard disk */
public class Storage {
    // Reads the datetime from the string representation of tasks as "Month day year" such as "Jan 10 2020".
    private static final DateTimeFormatter STORAGE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);

    private final Path filePath;
    private final Path folderPath;

    /**
     * Initializes an instance of Storage based the file path passed to it.
     *
     * @param filePath The path of file which stores data.
     */
    public Storage(String filePath) {
        String currentRelativePath = Paths.get(".").toString();
        this.filePath = Paths.get(currentRelativePath, filePath);
        this.folderPath = Paths.get(currentRelativePath, "data");
    }

    /**
     * Loads the TaskList that is stored in <kbd>data/duke.text</kbd> file.
     * Handles the cases where either file or folder is not created.
     *
     * @return A TaskList to be used by the program.
     * @throws DukeException If there are I/O exceptions occurred when reading the file.
     */
    public TaskList load() throws DukeException {
        TaskList taskList;
        try {
            createPath();
            List<String> tasksInFile = Files.readAllLines(filePath, Charset.defaultCharset());
            taskList = this.loadTaskList(tasksInFile);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I/O Error! Check your file property"
                    + Arrays.toString(e.getStackTrace()));
        }
        return taskList;
    }

    /**
     * Creates the directory and/or file if they have not been created yet.
     *
     * @throws IOException If folderPath or filePath are parsed wrongly.
     */
    public void createPath() throws IOException {
        if (Files.notExists(folderPath)) {
            Files.createDirectory(folderPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Returns a loaded TaskList for program data storage from the information in the text file.
     * An empty TaskList is return if content in file is empty.
     *
     * @param tasksInFile The list of tasks stored in the file.
     * @return A TaskList based on the tasks recorded in the file.
     * @throws DukeException if the tasks in data file are not parsed out correctly.
     */
    public TaskList loadTaskList(List<String> tasksInFile) throws DukeException {
        List<Task> tasksLoaded = new ArrayList<>();

        for (String s : tasksInFile) {
            // The array of task content contains task's type + whether it is done + name (+ time).
            tasksLoaded.add(Parser.parseTask(s, STORAGE_FORMATTER));
        }
        return new TaskList(tasksLoaded);
    }

    /**
     * Stores a Task List in a predetermined filePath.
     *
     * @param taskList The taskList object to be saved.
     */
    public void save(TaskList taskList) {
        try {
            List<String> formattedTasks = taskList.formatTaskList();
            Files.write(this.filePath, formattedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
