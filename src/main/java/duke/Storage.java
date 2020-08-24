package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import exception.DeadlineInvalidUsageException;
import exception.EventInvalidUsageException;
import exception.StorageException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage file that handles storage operations
 */
public class Storage {

    public static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";
    public final Path path;

    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Loads file from storage file
     *
     * @return a list of task saved in storage file
     * @throws StorageException on I/O error or parse error
     */
    public List<Task> load() throws StorageException {
        try {
            createFileIfNotExists(path);

            // read from file
            BufferedReader br = Files.newBufferedReader(path);

            List<Task> tasks = new ArrayList<>();
            br.lines().forEach(line -> {
                try {
                    Task task = parseStorageData(line);
                    tasks.add(task);
                } catch (StorageException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            return tasks;
        } catch (IOException ioe) {
            throw new StorageException("Error writing to file: " + path);
        }
    }

    /**
     * Save tasks to storage file
     *
     * @param tasks a list of task to save
     * @return true indicating storage is updated, or false indicating storage fails to update
     */
    public boolean save(TaskList tasks) {
        try {
            BufferedWriter bw = Files.newBufferedWriter(Path.of("data/duke.txt"));
            for (Task task : tasks.getAll()) {
                String storeFormat = String.format(
                        "%s | %d | %s",
                        task.getType(),
                        task.getStatus() ? 1 : 0,
                        task.getDescription()
                );
                bw.write(storeFormat);
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Create storage file (and parent folders)
     *
     * @param path file string
     * @throws IOException on I/O error
     */
    private void createFileIfNotExists(Path path) throws IOException {
        Path folderPath = Path.of("data");
        Path filePath = folderPath.resolve("duke.txt");

        // create folders containing the file and its parents
        Files.createDirectories(folderPath);

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Parse storage data by the following format task_type | task_status | task_description | task_date(optional)
     *
     * @param line lines in storage file
     * @return {@code Task} object represented by the line
     * @throws StorageException on parse error
     */
    private Task parseStorageData(String line) throws StorageException {
        // split by the pipe `|` token
        String[] tokens = line.split("(\\s)*(\\|)(\\s)*");
        Task task;

        try {
            switch (tokens[0]) {
            case "T":
                task = new Todo(tokens[2]);
                break;
            case "D":
                task = Deadline.create(tokens[2], tokens[3]);
                break;
            case "E":
                task = Event.create(tokens[2], tokens[3]);
                break;
            default:
                throw new StorageException("Unknown task identifier " + tokens[0] + "! Skipping...");
            }

            if (tokens[1].equals("1")) {
                task.markAsDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new StorageException("Data" + line + "in wrong format! Skipping...");
        } catch (EventInvalidUsageException | DeadlineInvalidUsageException ex) {
            throw new StorageException(ex.getMessage());
        }
    }
}
