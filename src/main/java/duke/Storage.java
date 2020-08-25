package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

/**
 * The link between the {@link duke.task.TaskList} and a local .txt file.
 */
public class Storage {
    /**
     * The filepath whether the data will be stored on the local machine.
     */
    private final Path filePath;

    /**
     * A list of the tasks currently stored.
     */
    private List<String> tasks;

    /**
     * Initializes a new storage object.
     * Tries to create the needed directories and files if it doesn't exist yet.
     *
     * @param filePath the path at which data will be stored at.
     */
    Storage(Path filePath) {
        this.filePath = filePath;

        try {
            // create directory if directory doesn't exist
            Path parentPath = filePath.getParent();
            Files.createDirectories(parentPath);

            // create file if file doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            this.tasks = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("duke.Storage error");
        }
    }

    /**
     * Returns a list of tasks based on the data stored currently.
     * Handles the creation of the task objects by manipulating the strings the tasks are stored in.
     *
     * @return List of tasks.
     * @throws DukeException if program fails at any point.
     */
    public List<Task> getTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        for (String task : this.tasks) {
            String[] split = task.split(" \\| ");

            if (split.length < 3) {
                throw new DukeException("The storage is corrupt!");
            }

            String type = split[0];
            boolean isDone = split[1].equals("1");
            String desc = split[2];

            switch(type) {
            case "T":
                tasks.add(new Todo(desc, isDone));
                break;
            case "D":
                if (split.length < 4) {
                    throw new DukeException("The storage is corrupt!");
                }

                String by = split[3].trim();
                try {
                    tasks.add(new Deadline(desc, by, isDone));
                } catch (DukeException e) {
                    throw new DukeException("Date was not stored properly!");
                }
                break;
            case "E":
                if (split.length < 4) {
                    throw new DukeException("The storage is corrupt!");
                }

                String at = split[3].trim();
                try {
                    tasks.add(new Event(desc, at, isDone));
                } catch (DukeException e) {
                    throw new DukeException("Date was not stored properly!");
                }
                break;
            }

        }
        return tasks;
    }

    /**
     * Joins all the strings in the List of tasks in strings and separates them with a linebreak
     * before writing it to a file.
     *
     * @throws IOException If the program fails at any point.
     */
    public void write() throws IOException {
        String data = String.join("\n", this.tasks);
        Files.writeString(filePath, data, StandardCharsets.UTF_8, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Saves a new task to the .txt file.
     *
     * @param task new task to be saved.
     * @throws DukeException if the program fails to write.
     */
    public void saveTask(Task task) throws DukeException {
        this.tasks.add(task.getSaveFormat());

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }

    /**
     * Updates an existing task in the current tasks.
     *
     * @param id ID of task to update.
     * @param task Task to update.
     * @throws DukeException if the program fails to write.
     */
    public void updateTask(int id, Task task) throws DukeException {
        this.tasks.set(id - 1, task.getSaveFormat());

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }

    /**
     * Deletes a task from the current tasks.
     *
     * @param id ID of the task to delete.
     * @throws DukeException If the program fails to write.
     */
    public void deleteTask(int id) throws DukeException {
        this.tasks.remove(id - 1);

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }
}
