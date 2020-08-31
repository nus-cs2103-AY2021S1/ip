package sparrow.storage;

import sparrow.data.task.Task;
import sparrow.data.task.Todo;
import sparrow.data.task.Deadline;
import sparrow.data.task.Event;
import sparrow.data.task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the file used to store the task list.
 */
public class Storage {

    private static final String DEFAULT_FILE_PATH = "Sparrow.txt";

    private final Path path;

    public Storage() throws Exception {
        this(DEFAULT_FILE_PATH);
    }

    public Storage(String filePath) throws Exception {
        path = Path.of(filePath);
        if (!isValidPath(path)) {
            throw new Exception("Invalid file extension");
        }

    }

    private boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Returns list of tasks loaded from hard disk.
     * If no file found, returns an empty list.
     * @return Task list.
     */
    public TaskList loadFromFile() {
        File f = new File(path.toString());

        if (f.exists()) {
            try {
                return decodeTaskList(Files.readAllLines(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new TaskList();
    }

    public void saveToFile(TaskList tasks) {
        try {
            Files.write(path, encodeTaskList(tasks));
        } catch (IOException e) {
            // TODO create exception for this
            System.out.println("Error saving to file" + e.toString());
        }
    }

    public TaskList decodeTaskList(List<String> encodedTaskList) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            if (!encodedTask.isBlank()) {
                tasks.add(stringToTask(encodedTask));
            }
        }
        return new TaskList(tasks);
    }

    public List<String> encodeTaskList(TaskList tasks) {
        List<String> encodedTaskList = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            encodedTaskList.add(taskToString(task));
        }
        return encodedTaskList;
    }

    /**
     * Converts user input into a Task.
     * @param input User input to be converted.
     * @return Task object.
     */
    public Task stringToTask(String input) {
        Task task = null;
        String[] inputArr = input.split("\\s+\\|\\s+", 4);
        boolean isTaskDone = Integer.parseInt(inputArr[1]) == 1;
        switch (inputArr[0]) {
        case "T":
            Todo todo = new Todo(inputArr[2]);
            if (isTaskDone) {
                todo.markAsDone();
            }
            task = todo;
            break;
        case "D":
            LocalDate dueDate = stringToDate(inputArr[3]);
            Deadline deadline = new Deadline(inputArr[2], dueDate);
            if (isTaskDone) {
                deadline.markAsDone();
            }
            task = deadline;
            break;
        case "E":
            LocalDate date = stringToDate(inputArr[3]);
            Event event = new Event(inputArr[2], date);
            if (isTaskDone) {
                event.markAsDone();
            }
            task = event;
            break;
        default:
            System.out.println("No matching task found, shouldn't end up here");
        }

        if (task == null) {
            System.out.println("No task created");
        }
        return task;
    }

    /**
     * Converts a Task into a String for storage.
     * @param task Task to be converted.
     * @return String representation of Task.
     */
    public String taskToString(Task task) {
        StringBuilder sb = new StringBuilder(task.getDescription());
        if (task.getIsDone()) {
            sb.insert(0, "1 | ");
        } else {
            sb.insert(0, "0 | ");
        }

        if (task instanceof Todo) {
            sb.insert(0,"T | ");
        } else if (task instanceof Deadline) {
            sb.insert(0,"D | ");
            sb.append(" | ").append(((Deadline) task).getDueDate());
        } else if (task instanceof  Event) {
            sb.insert(0,"E | ");
            sb.append(" | ").append(((Event) task).getDate());
        }

        return sb.toString();
    }


    /**
     * Converts String representation of date to LocalDate.
     * @param dateStr String representation of a date.
     * @return LocalDate object.
     * @throws DateTimeParseException If input String cannot be parsed.
     */
    public LocalDate stringToDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }
}
