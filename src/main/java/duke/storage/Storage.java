package duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.data.task.ToDo;

/**
 * Used to load and save data files to the
 * file path specified.
 */
public class Storage {
    private final String dataDirectory = "data";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and parses data files into a list of tasks.
     * @return A list of provided in the data file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            return tasks;
        }
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String taskEntry = scanner.nextLine();
                String[] taskInformation = taskEntry.split("\\|");
                if (taskEntry.startsWith("[T]")) {
                    loadToDo(tasks, taskInformation);
                } else if (taskEntry.startsWith("[D]")) {
                    loadDeadline(tasks, taskInformation);
                } else if (taskEntry.startsWith("[E]")) {
                    loadEvent(tasks, taskInformation);
                }
            }
        } catch (IOException ioException) {
            System.out.println("An error has occurred");
        }
        return tasks;
    }

    /**
     * Parses text data into an Event and adds the event into the task list.
     * @param tasks Task list.
     * @param taskInfo String array split by delimiter "|".
     *                 The elements are in the order: task identifier, description, date.
     */
    private void loadEvent(List<Task> tasks, String[] taskInfo) {
        String description = taskInfo[2].trim();
        Task task = new Event(description, LocalDate.parse(taskInfo[3].trim()));
        if (taskInfo[0].contains("1")) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Parses text data into a Deadline and adds the Deadline into the task list.
     * @param tasks Task list.
     * @param taskInfo String array split by delimiter "|".
     *                 The elements are in the order: task identifier, description, date.
     */
    private void loadDeadline(List<Task> tasks, String[] taskInfo) {
        String description = taskInfo[2].trim();
        Task task = new Deadline(description, LocalDate.parse(taskInfo[3].trim()));
        if (taskInfo[0].contains("1")) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Parses text data into a To Do and adds it into the task list.
     * @param tasks Task list.
     * @param taskInfo String array split by delimiter "|".
     *                 The elements are in the order: task identifier, description.
     */
    private void loadToDo(List<Task> tasks, String[] taskInfo) {
        String description = taskInfo[2].trim();
        Task task = new ToDo(description);
        if (taskInfo[0].contains("1")) {
            task.markAsDone();
        }
        tasks.add(task);
    }

    /**
     * Formats a task and appends a given task to the end of the data file.
     * @param task The task that will be appended to the data file.
     */
    public String save(Task task) {
        if (task instanceof ToDo) {
            return writeToDo(task);
        } else if (task instanceof Event || task instanceof Deadline) {
            return writeEventOrDeadline(task);
        }
        return "";
    }

    private String writeEventOrDeadline(Task task) {
        String[] strings = task.toString().split("\\|");
        String isDone = task.getIsDone() ? "1" : "0";
        String description = task.getDescription();
        String date = "";
        if (task instanceof Event) {
            date += ((Event) task).getDate().toString();
        } else if (task instanceof Deadline) {
            date += ((Deadline) task).getDueDate().toString();
        }
        return strings[0] + " | " + isDone + " | " + description + "| " + date + "\n";
    }

    private String writeToDo(Task task) {
        String[] strings = task.toString().split("\\|");
        String isDone = task.getIsDone() ? "1" : "0";
        String description = task.getDescription();
        return strings[0] + " | " + isDone + " | " + description + "\n";
    }

    /**
     * Stores all tasks in data file.
     * @param taskList List of tasks.
     */
    public void writeAllTasks(TaskList taskList) {
        List<String> fileData = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            String taskDetails = save(task);
            fileData.add(taskDetails);
        }
        try {
            Files.write(Path.of(filePath), fileData, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    private void initialiseDataDirectory() {
        File directory = new File(dataDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

}
