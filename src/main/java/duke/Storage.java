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

public class Storage {
    private final Path filePath;
    private List<String> tasks;

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

    public void write() throws IOException {
        String data = String.join("\n", this.tasks);
        Files.writeString(filePath, data, StandardCharsets.UTF_8, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void saveTask(Task task) throws DukeException {
        this.tasks.add(task.getSaveFormat());

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }

    public void updateTask(int id, Task task) throws DukeException {
        this.tasks.set(id - 1, task.getSaveFormat());

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }

    public void deleteTask(int id) throws DukeException {
        this.tasks.remove(id - 1);

        try {
            this.write();
        } catch (IOException e) {
            throw new DukeException("I couldn't save this task!");
        }
    }
}
