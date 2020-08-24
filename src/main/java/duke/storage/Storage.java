package duke.storage;

import duke.exceptions.InvalidFilePathException;
import duke.exceptions.StorageException;
import duke.exceptions.TaskListTranslatorException;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** Represents the storage of the system that handles reading and writing to files. */
public class Storage {

    private final Path path;
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    /** Constructs a Storage object with the default file path. */
    public Storage() {
        this(DEFAULT_FILEPATH);
    }

    /** Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path of the file where the tasks are stored.
     * @throws InvalidFilePathException If the file path is invalid (does not end with ".txt").
     */
    public Storage(String filePath) throws InvalidFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidFilePathException();
        }
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /** Loads the file contents.
     *
     * @return The {@link TaskList} containing the tasks in the file or an empty {@link TaskList}
     * if the file does not exist.
     * @throws TaskListTranslatorException If the file contents are in an invalid format.
     */
    public TaskList load() throws TaskListTranslatorException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        try {
            return TaskListTranslator.decode(Files.readAllLines(path));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario has been handled earlier.");
        } catch (IOException e) {
            return new TaskList();
        }
    }

    /** Saves a {@link TaskList} into the file.
     *
     * @param taskList The {@link TaskList} to be saved.
     * @throws StorageException If there is a problem writing to the file.
     */
    public void save(TaskList taskList) throws StorageException {
        try {
            List<String> encodedTaskList = TaskListTranslator.encode(taskList);
            Files.write(path, encodedTaskList);
        } catch (IOException e) {
            throw new StorageException();
        }
    }

}