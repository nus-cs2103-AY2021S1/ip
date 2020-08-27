package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.exception.CorruptedStorageException;
import duke.exception.InvalidDateInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * A class that represents the storage of the data
 */
public class Storage {

    private final Path filePath;
    private List<String> serialisedTasks;

    /**
     * Initialises a new instance.
     *
     * @param filePath The path to save the data to in the hard disk.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;

        try {
            // Create directories if it does yet exist
            Path parentPath = filePath.getParent();
            Files.createDirectories(parentPath);

            // Check if the file to be read exists. If not, create it.
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            this.serialisedTasks = Files.readAllLines(filePath);
        } catch (IOException e) {
            // TODO: add better error handling
            System.out.println("Something went wrong when loading tasks from the storage!");
        }
    }

    /**
     * Writes the list of tasks saved in the application to the hard disk.
     *
     * @throws IOException If there are issues reading/writing to the file.
     */
    private void writeToFile() throws IOException {
        String fileData = String.join("\n", this.serialisedTasks);
        Files.writeString(filePath, fileData, StandardCharsets.UTF_8, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Retrieves the task from the hard disk and loads them into the application.
     *
     * @return A list of saved <code>Task</code>s.
     * @throws CorruptedStorageException If something went wrong de-serialising the saved tasks,
     *                                   either due to missing fields, or unknown format.
     */
    public List<Task> loadTasks() throws CorruptedStorageException {
        List<Task> tasks = new ArrayList<>();

        for (String serialisedTask : this.serialisedTasks) {
            // "|" is a special regex character which needs to be escaped
            String[] tokens = serialisedTask.split(" \\| ");

            if (tokens.length < 3) {
                throw new CorruptedStorageException("Some tasks are missing fields!");
            }

            String taskType = tokens[0];
            boolean isDone = tokens[1].equals("1");
            String desc = tokens[2];

            switch (taskType) {
            case "T":
                tasks.add(new Todo(desc, isDone));

                break;
            case "D":
                if (tokens.length < 4) {
                    throw new CorruptedStorageException("Deadline task is missing due date!");
                }
                String by = tokens[3];

                try {
                    tasks.add(new Deadline(desc, by, isDone));
                } catch (InvalidDateInputException e) {
                    throw new CorruptedStorageException("Date was not stored properly!");
                }

                break;
            case "E":
                if (tokens.length < 4) {
                    throw new CorruptedStorageException("Event task is missing date!");
                }
                String at = tokens[3];

                try {
                    tasks.add(new Event(desc, at, isDone));
                } catch (InvalidDateInputException e) {
                    throw new CorruptedStorageException("Date was not stored properly!");
                }

                break;
            default:
                throw new CorruptedStorageException(
                        "Some unknown task type was stored in the database!");
            }

        }

        return tasks;
    }

    /**
     * Saves a new task to the hard disk.
     *
     * @param task The task to be saved.
     * @throws CorruptedStorageException If there are issues reading/writing to the file.
     */
    public void saveNewTask(Task task) throws CorruptedStorageException {
        this.serialisedTasks.add(task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }

    /**
     * Updates a task and write these changes to the hard disk.
     *
     * @param taskId The ID of the task to be updated.
     * @param task   The updated task.
     * @throws CorruptedStorageException If there are issues reading/writing to the file.
     */
    public void updateExistingTask(int taskId, Task task) throws CorruptedStorageException {
        this.serialisedTasks.set(taskId - 1, task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }

    /**
     * Deletes a task from the hard disk.
     *
     * @param taskId The ID of the task to be deleted.
     * @throws CorruptedStorageException If there are issues reading/writing to the file.
     */
    public void deleteExistingTask(int taskId) throws CorruptedStorageException {
        this.serialisedTasks.remove(taskId - 1);

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }
}
