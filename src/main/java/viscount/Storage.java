package viscount;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import viscount.exception.ViscountIoException;
import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.TaskType;
import viscount.task.Todo;

/**
 * Represents Viscount's storage.
 *
 * Handles loading tasks from data file and writing tasks to the file.
 */
public class Storage {
    private static final String DATA_FILE_NAME = "viscount.txt";

    private String dataDirectoryPath;
    private String filePathString;

    /**
     * Instantiates a new storage object.
     *
     * @param dataDirectoryPath Directory path to the data file.
     */
    public Storage(String dataDirectoryPath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.filePathString = dataDirectoryPath + DATA_FILE_NAME;
    }

    /**
     * Saves task list data to disk.
     *
     * @param tasks Task list saved.
     * @throws ViscountIoException If exception occurs when writing to disk.
     */
    public void saveToDisk(List<Task> tasks) throws ViscountIoException {
        Path filePath = Paths.get(filePathString);
        List<String> savedData = new ArrayList<>();

        for (Task task : tasks) {
            savedData.add(task.toTaskData());
        }

        try {
            Files.write(filePath, savedData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ViscountIoException("saving");
        }
    }

    /**
     * Loads task list data from disk.
     *
     * @return Task list loaded.
     * @throws ViscountIoException If exception occurs when loading from disk or creating a new data file.
     */
    public List<Task> loadFromDisk() throws ViscountIoException {
        File directory = new File(dataDirectoryPath);

        if (!directory.exists()) {
            directory.mkdir();
        }

        Path filePath = Paths.get(filePathString);
        boolean doesFileExist = Files.exists(filePath);
        List<Task> tasks = new ArrayList<>();

        if (doesFileExist) {
            try {
                File f = new File(filePathString);
                Scanner sc = new Scanner(f);

                while (sc.hasNext()) {
                    String line = sc.nextLine();

                    if (line.isEmpty()) {
                        // If the data file has empty lines, skip them
                        continue;
                    } else {
                        List<String> taskData = Arrays.asList(line.split("\\|"));

                        TaskType taskType = TaskType.valueOf(taskData.get(0));
                        boolean isDone = !taskData.get(1).equals("0");
                        String taskDescription = taskData.get(2);

                        if (taskType == TaskType.Todo) {
                            tasks.add(new Todo(taskDescription, isDone));
                        } else if (taskType == TaskType.Deadline) {
                            LocalDateTime dueDate = Parser.parseDateTime(
                                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
                            tasks.add(new Deadline(taskDescription, isDone, dueDate));
                        } else if (taskType == TaskType.Event) {
                            LocalDateTime eventTime = Parser.parseDateTime(
                                    taskData.get(3), Parser.TASK_DATA_DATE_TIME_FORMATTER);
                            tasks.add(new Event(taskDescription, isDone, eventTime));
                        }
                    }
                }
            } catch (Exception e) {
                throw new ViscountIoException("loading");
            }
        } else {
            try {
                Files.write(filePath, new ArrayList<String>(), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                throw new ViscountIoException("creating a new file for");
            }
        }

        return tasks;
    }
}
