package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.task.Todo;

/**
 * The Storage helper class.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Saves the given tasks into a file.
     *
     * @param tasks A list of tasks.
     * @throws DukeException if the file cannot be saved.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            Files.createDirectories(this.filePath.getParent());
            List<String> data = tasks.stream().map(Task::toSaveData).collect(Collectors.toList());
            Files.write(this.filePath, data);
        } catch (IOException e) {
            throw new DukeException("Unable to save file! Task will not be saved on exit.");
        }
    }

    /**
     * Loads tasks from a given file.
     *
     * @return A list of tasks.
     * @throws DukeException if the file cannot be read or does not follow the required format.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        if (Files.exists(this.filePath)) {
            try {
                List<String> lines = Files.readAllLines(this.filePath);

                for (String line : lines) {
                    String[] params = line.split("\\s\\|\\s");
                    String type = params[0];
                    String description = params[4];
                    TaskPriority priority = TaskPriority.valueOf(params[1]);
                    List<String> tags = Arrays.asList(params[2].substring(1, params[2].length() - 1).split(", "));
                    boolean isDone = params[3].equals("1");

                    if (!params[3].equals("0") && !params[3].equals("1")) {
                        throw new DukeException();
                    }

                    switch (type) {
                    case "T":
                        tasks.add(new Todo(description, priority, tags, isDone));

                        break;
                    case "D":
                        tasks.add(new Deadline(description, LocalDateTime.parse(params[5]), priority, tags, isDone));

                        break;
                    case "E":
                        tasks.add(new Event(description, LocalDateTime.parse(params[5]), priority, tags, isDone));

                        break;
                    default:
                        throw new DukeException();
                    }
                }
            } catch (DukeException | IOException | ArrayIndexOutOfBoundsException
                    | DateTimeParseException | IllegalArgumentException e) {
                throw new DukeException("Data file is corrupt. Ignoring saved tasks :-(");
            }
        }

        return tasks;
    }
}
