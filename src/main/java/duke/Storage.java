package duke;

import duke.exception.CorruptedStorageException;
import duke.exception.InvalidDateInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final Path filePath;
    private List<String> serialisedTasks;

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
            }
        }

        return tasks;
    }

    public void writeToFile() throws IOException {
        String fileData = String.join("\n", this.serialisedTasks);
        Files.writeString(filePath, fileData, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void saveNewTask(Task task) throws CorruptedStorageException {
        this.serialisedTasks.add(task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }

    public void updateExistingTask(int taskId, Task task) throws CorruptedStorageException {
        this.serialisedTasks.set(taskId - 1, task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }

    public void deleteExistingTask(int taskId) throws CorruptedStorageException {
        this.serialisedTasks.remove(taskId - 1);

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new CorruptedStorageException("Couldn't save new task to storage!");
        }
    }
}
