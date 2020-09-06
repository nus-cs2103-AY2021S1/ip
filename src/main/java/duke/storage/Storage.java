package duke.storage;

import duke.exceptions.DukeException;
import duke.exceptions.IllegalValueException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Storage {

    /** Default file path used if user does not provide one */
    public static final String DEFAULT_STORAGE_FILEPATH = "data/save.txt";

    public static final String SEPARATOR = ",";

    /**
     * Task data storage argument format.
     * <p>
     * <ol>T, D, E</ol>
     * <ol>isDone (1 for done, 0 for not done)</ol>
     * <ol>description</ol>
     * <ol>date ("null" if not applicable)</ol>
     * </p>
     */
    public static final Pattern TASK_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventType>[TDE])" + SEPARATOR
                    + "(?<isDone>[01])" + SEPARATOR
                    + "(?<description>.+[^,]$?)" + SEPARATOR
                    + "(?<date>.+)");

    public final Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.path = Path.of(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with .txt");
        }
    }

    /**
     * Checks if save path is valid.
     * @param path save path.
     * @return true if path ends with .txt.
     */
    private static boolean isValidPath(Path path) {
        return path.toString().endsWith(".txt");
    }

    /**
     * Saves tasks from a {@code TaskList} into a external file.
     * @param taskList Task list.
     * @throws StorageOperationException if IOException occurs.
     */
    public void saveTasks(TaskList taskList) throws StorageOperationException {
        List<String> dataLines = new ArrayList<>();
        for (int i = 0 ; i < taskList.getSize() ; i++) {
            Task task = taskList.get(i);
            String line = String.join(",", task.getSaveData());
            dataLines.add(line);
        }
        try {
            Files.write(path, dataLines);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing file: " + path);
        }
    }

    /**
     * Loads tasks from external file.
     * @return {@code TaskList} containing list of tasks.
     * @throws StorageOperationException if IOException or illegal save format is found.
     */
    public TaskList loadTasks() throws StorageOperationException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        try {
            // read the file
            List<String> dataLines = Files.readAllLines(path);
            return TaskDecoder.DecodeTasksFromSave(dataLines);
            // should be handled above so it's an assertion instead of an exception
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario should already have been handled earlier.");
            // io exception for example no permission to read a file, some process closed the stream
        } catch (IOException ioe) {
            throw new StorageOperationException("Error reading file: " + path);
            // illegalValueException is when arguments cannot form a task
        } catch (IllegalValueException ive) {
            throw new StorageOperationException(ive.getMessage());
        }
    }

    /**
     * Signals that storage file path is invalid.
     */
    public static class InvalidStorageFilePathException extends DukeException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occurred while trying to
     * convert and read/write data between the application
     * and the duke.storage file.
     */
    public static class StorageOperationException extends DukeException {
        public StorageOperationException(String message) {
            super(message);
        }
    }
}
