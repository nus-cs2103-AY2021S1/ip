package rogue.storage;

import rogue.commons.util.DateTimeUtil;
import rogue.model.task.Deadline;
import rogue.model.task.Event;
import rogue.model.task.Task;
import rogue.model.task.Todo;
import rogue.storage.exceptions.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with file I/O and persisting information across multiple executions.
 */
public class Storage {
    /** Separator for each field in file. */
    private static final String FORMAT_TASK_FIELD_DELIMITER = "\\s\\|\\s";

    /** The value corresponding to whether a task is completed. */
    private static final String VALUE_TASK_COMPLETE = "1";

    /** Message for when the save file does not conform to the expected format. */
    private static final String ERROR_INCORRECT_FILE_FORMAT =
            "ERROR: Saved file does not have the correct format!\n";

    /** Message for when an error is encountered when performing I/O operations. */
    private static final String ERROR_IO = "ERROR: Unable to %s tasks from file!";

    private Path filePath;

    /**
     * Constructs the {@code Storage}.
     *
     * @param filePath The relative or absolute path where persistent information is stored.
     */
    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Factory method for producing the {@code Storage}.
     *
     * @param filePath The relative or absolute path where persistent information is stored.
     */
    public static Storage init(String filePath) {
        Path path = Paths.get(filePath);
        Path parentDirPath = path.getParent();

        try {
            // Creates parent directories if they do not exist
            if (parentDirPath != null) {
                Files.createDirectories(parentDirPath);
            }

            if (Files.notExists(path)) {
                path = Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert Files.exists(path) : "Save file must be available!";

        return new Storage(path);
    }

    /**
     * Writes the summary of each {@code Task} to the file.
     *
     * All prior content in the file will be replaced.
     *
     * @param tasks A list of tasks.
     * @throws StorageException if the file cannot be written to.
     */
    public void save(List<Task> tasks) throws StorageException {
        try {
            List<String> taskSummary = convertTasksToString(tasks);
            // Overwrites content in existing file
            Files.write(filePath, taskSummary, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new StorageException(String.format(ERROR_IO, "save"));
        }
    }

    /**
     * Reads the summary of each {@code Task} from the file.
     *
     * Converts each summary into a {@code Task}.
     *
     * @return A list of tasks
     */
    public List<Task> load() throws StorageException {
        try {
            List<String> taskSummary = Files.readAllLines(filePath);
            return convertStringToTasks(taskSummary);
        } catch (IOException e) {
            throw new StorageException(String.format(ERROR_IO, "load"));
        }
    }

    /**
     * Retrieves the summary from each {@code Task} in the list.
     *
     * @param tasks A list of tasks.
     * @return A list of extracted summary
     */
    private List<String> convertTasksToString(List<Task> tasks) {
        List<String> taskSummary = new ArrayList<>();

        for (Task task : tasks) {
            assert task != null : "A task cannot be null!";

            taskSummary.add(task.summarize());
        }

        return taskSummary;
    }

    /**
     * Converts each entry in the task summary to a {@code Task}.
     *
     * If any of the entries cannot be parsed, an exception is thrown.
     *
     * @param taskSummary
     * @return A list of tasks
     * @throws StorageException if any summary is invalid.
     */
    private List<Task> convertStringToTasks(List<String> taskSummary) throws StorageException {
        List<Task> tasks = new ArrayList<>();

        for (String summary : taskSummary) {
            try {
                String[] args = summary.split(FORMAT_TASK_FIELD_DELIMITER);

                assert args.length >= 3 : "A task has a minimum of 3 fields!";

                boolean isDone = (args[1].equals(VALUE_TASK_COMPLETE)) ? true : false;
                String description = args[2];

                switch (args[0]) {
                case "D":
                    LocalDate date = DateTimeUtil.parseStringToDate(args[3]);
                    tasks.add(new Deadline(description, isDone, date));
                    break;
                case "E":
                    date = DateTimeUtil.parseStringToDate(args[3]);
                    tasks.add(new Event(description, isDone, date));
                    break;
                default:
                    tasks.add(new Todo(description, isDone));
                    break;
                }
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new StorageException(ERROR_INCORRECT_FILE_FORMAT);
            }
        }

        return tasks;
    }
}
