package rogue.storage;

import rogue.model.task.Task;
import rogue.model.task.Todo;
import rogue.model.task.Deadline;
import rogue.model.task.Event;

import rogue.commons.util.DateTimeUtil;

import rogue.storage.exceptions.StorageException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class Storage {
    private final String FORMAT_TASK_FIELD_DELIMITER = "\\s\\|\\s";
    private final String VALUE_TASK_COMPLETE = "1";
    private final String ERROR_INCORRECT_FILE_FORMAT = "ERROR: Saved file does not have the correct format!\n";
    private final String ERROR_IO = "ERROR: Unable to %s tasks from file!";

    private Path filePath;

    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    public static Storage init(String fileName) {
        Path path = Paths.get(fileName);
        Path parentDirPath = path.getParent();

        try {
            if (parentDirPath != null) {
                Files.createDirectories(parentDirPath);
            }

            if (Files.notExists(path)) {
                path = Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println("ERROR: Unable to create file for saving and loading tasks!\n");
            e.printStackTrace();
            System.exit(1);
        }

        return new Storage(path);
    }

    public void save(List<Task> tasks) throws StorageException {
        try {
            List<String> taskSummary = tasksToString(tasks);
            Files.write(filePath, taskSummary, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new StorageException(String.format(ERROR_IO, "save"));
        }
    }

    public List<Task> load() throws StorageException {
        try {
            List<String> taskSummary = Files.readAllLines(filePath);
            return stringToTask(taskSummary);
        } catch (IOException e) {
            throw new StorageException(String.format(ERROR_IO, "load"));
        }
    }

    private List<String> tasksToString(List<Task> tasks) {
        List<String> taskSummary = new ArrayList<>();

        for (Task task : tasks) {
            taskSummary.add(task.summarize());
        }

        return taskSummary;
    }

    private List<Task> stringToTask(List<String> taskSummary) throws StorageException {
        List<Task> tasks = new ArrayList<>();

        for (String summary : taskSummary) {
            try {
                String[] args = summary.split(FORMAT_TASK_FIELD_DELIMITER);

                boolean isDone = (args[1].equals(VALUE_TASK_COMPLETE)) ? true : false;
                String description = args[2];

                switch (args[0]) {
                case "D":
                    LocalDate date = DateTimeUtil.stringAsDate(args[3]);
                    tasks.add(new Deadline(description, isDone, date));
                    break;
                case "E":
                    date = DateTimeUtil.stringAsDate(args[3]);
                    tasks.add(new Event(description, isDone, date));
                    break;
                default:
                    tasks.add(new Todo(description, isDone));
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                throw new StorageException(ERROR_INCORRECT_FILE_FORMAT);
            }
        }

        return tasks;
    }
}
