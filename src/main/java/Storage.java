import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Task> loadTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        for (String serialisedTask : this.serialisedTasks) {
            // "|" is a special regex character which needs to be escaped
            String[] tokens = serialisedTask.split(" \\| ");

            if (tokens.length < 3) {
                throw new DukeException("Storage is corrupted!");
            }

            String taskType = tokens[0];
            boolean isDone = tokens[1] == "1";
            String desc = tokens[2];

            switch (taskType) {
                case "T":
                    tasks.add(new Todo(desc, isDone));

                    break;
                case "D":
                    if (tokens.length < 4) {
                        throw new DukeException("Storage is corrupted! Missing due date for Deadline");
                    }
                    String by = tokens[3];
                    tasks.add(new Deadline(desc, by, isDone));

                    break;
                case "E":
                    if (tokens.length < 4) {
                        throw new DukeException("Storage is corrupted! Missing date for Event");
                    }
                    String at = tokens[3];
                    tasks.add(new Event(desc, at, isDone));

                    break;
            }
        }

        return tasks;
    }

    public void writeToFile() throws IOException {
        String fileData = this.serialisedTasks.stream().collect(Collectors.joining("\n"));
        Files.writeString(filePath, fileData, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void saveNewTask(Task task) throws DukeException {
        this.serialisedTasks.add(task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new DukeException("Error saving new task to storage!");
        }
    }

    public void updateExistingTask(int taskId, Task task) throws DukeException {
        this.serialisedTasks.set(taskId - 1, task.serialise());

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new DukeException("Error saving updated task to storage!");
        }
    }

    public void deleteExistingTask(int taskId) throws DukeException {
        this.serialisedTasks.remove(taskId - 1);

        try {
            this.writeToFile();
        } catch (IOException e) {
            throw new DukeException("Error saving deleted task to storage!");
        }
    }
}
