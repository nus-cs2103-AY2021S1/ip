package duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Saves and loads tasks by serializing and deserializing respectively.
 */
public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get(System.getProperty("user.dir"), "data.txt");
    }

    /**
     * Returns a Task object that has been deserialized from its string representation.
     *
     * @param serializedTask Serialized Task object (String representation of Task object)
     * @return Task object
     */
    public Task deserializeTask(String serializedTask) throws RuntimeException {
        String[] tokens = serializedTask.split(" \\| ");

        assert tokens.length > 2 : "task should be split to at least 3 - index, status, description";

        String taskIndex = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String description = tokens[2];

        try {
            switch (taskIndex) {
            case ("T"):
                return new ToDo(description, isDone);
            case ("D"):
                return new Deadline(description, isDone, new SimpleDateFormat("y-M-d").parse(tokens[3]));
            case ("E"):
                return new Event(description, isDone, new SimpleDateFormat("y-M-d").parse(tokens[3]));
            default:
                throw new RuntimeException("Task is invalid. File might be corrupted.");
            }
        } catch (ParseException e) {
            throw new RuntimeException("Date can't be parsed.");
        }
    }

    /**
     * Returns a string representation of Task object.
     *
     * @param task Task object
     * @return Task string
     */
    public String serializeTask(Task task) {
        if (task instanceof Task) {
            char status = task.getIsDone() ? '1' : '0';
            String description = task.getDescription();

            if (task instanceof ToDo) {
                return String.format("T | %c | %s\n", status, description);
            } else if (task instanceof Deadline) {
                return String.format("D | %c | %s | %s\n", status, description, ((Deadline) task).getDoByStr());
            } else if (task instanceof Event) {
                return String.format("E | %c | %s | %s\n", status, description, ((Event) task).getTimeStr());
            } else {
                return String.format("T | %c | %s\n", status, description);
            }
        } else {
            throw new RuntimeException("Task is invalid. File might be corrupted.");
        }
    }

    /**
     * Returns deserialized tasks in drive.
     *
     * @return List of deserialized tasks
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String fileContent = Files.readString(filePath);
            Stream.of(fileContent.split("\n"))
                    .filter((task) -> !task.trim().equals(""))
                    .forEach((task) -> tasks.add(deserializeTask(task)));
        } catch (IOException e) {
            System.out.println("");
        }
        return tasks;
    }

    /**
     * Save serialized tasks in drive.
     *
     * @param tasks List of tasks to serialize
     */
    public void saveTasks(List<Task> tasks) {
        try {
            String serializedTasks = tasks.stream().map(this::serializeTask)
                    .collect(Collectors.joining());
            Files.writeString(this.filePath, serializedTasks, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Failed to save tasks to hard disk.");
        }
    }
}
