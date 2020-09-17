package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.dukeexception.LoadFailureException;
import duke.dukeexception.SaveFailureException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
/**
 * Represents the hard disk storage used by the bot.
 * While this class does not store the data within the class objects,
 * each <code>Storage</code> object keeps a link to an actual file where
 * data is stored, and handles loading and writing to this file.
 */
public class Storage {
    public static final String DONE = "1";
    public static final String NOT_DONE = "0";
    public static final String TODO_TASK = "T";
    public static final String DEADLINE_TASK = "D";
    public static final String EVENT_TASK = "E";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h.m a");

    /** File where data is stored */
    private final File taskFile;

    /**
     * Public constructor.
     *
     * @param filePath Relative file path.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Loads saved data from <code>taskFile</code>.
     *
     * @return List of tasks to be passed to a <code>TaskList</code> object
     * @throws DukeException If file cannot be created, read or parsed.
     */
    public List<Task> load() throws LoadFailureException {
        if (!this.taskFile.exists()) {
            createFile();
        }

        try {
            Scanner sc = new Scanner(this.taskFile);

            return loadTasksFromFile(sc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LoadFailureException("Cannot find the file. Restart?");
        }
    }

    private void createFile() throws LoadFailureException {
        File directory = this.taskFile.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try {
            this.taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LoadFailureException("Somehow cannot make a new file. Help me restart?");
        }
    }

    private List<Task> loadTasksFromFile(Scanner sc) throws LoadFailureException {
        List<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String storedTask = sc.nextLine();
            Task task = parseFromStorage(storedTask);
            list.add(task);
        }

        return list;
    }

    /**
     * Updates file by adding a new task.
     *
     * @param task Task to be added to file.
     * @param isFirstTask Whether task to be added is the first in the file.
     * @throws DukeException If task cannot be parsed or file cannot be written to.
     */
    public void update(Task task, boolean isFirstTask) throws SaveFailureException {
        try {
            FileWriter fileWriter = null;
            if (isFirstTask) {
                fileWriter = new FileWriter(this.taskFile);
                fileWriter.write(parseToStorage(task));
            } else {
                fileWriter = new FileWriter(this.taskFile, true);
                fileWriter.write("\n" + parseToStorage(task));
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveFailureException("Restart?");
        }
    }

    /**
     * Updates file with list when tasks are marked or deleted.
     *
     * @param list Updated list given by <code>TaskList</code> object.
     * @throws DukeException If tasks cannot be parsed or file cannot be written to.
     */
    public void update(List<Task> list) throws SaveFailureException {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile);

            String fileContents = "";
            if (list.size() > 0) {
                fileContents = parseToStorage(list.get(0));

                for (int i = 1; i < list.size(); i++) {
                    fileContents += "\n" + parseToStorage(list.get(i));
                }
            }

            fileWriter.write(fileContents);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveFailureException("Restart?");
        }
    }

    /**
     * Parses <code>Task</code> objects into strings to be written to the
     * file on the hard disk.
     *
     * @param task <code>Task</code> object to be parsed
     * @return String representation of the task. Note that this string is
     *          different from the string returned by <code>task.toString()</code>.
     *          e.g. "[T][✓] Homework" from <code>task.toString()</code> will be
     *          represented as "T | 1 | Homework".
     * @throws DukeException If the type of the task cannot be recognised.
     */
    protected String parseToStorage(Task task) throws SaveFailureException {
        String taskTypeString = "";
        String status = task.isDone() ? DONE : NOT_DONE;
        String taskName = task.getTaskName();
        String taskDescription = "";

        if (task instanceof Todo) {
            taskTypeString = TODO_TASK;
            taskDescription = taskName;
        } else if (task instanceof Deadline) {
            taskTypeString = DEADLINE_TASK; // + date
            taskDescription = taskName + " | " + ((Deadline) task).getDateTime().format(FORMATTER);
        } else if (task instanceof Event) {
            taskTypeString = EVENT_TASK;
            taskDescription = taskName + " | " + ((Event) task).getDateTime().format(FORMATTER);
        } else {
            throw new SaveFailureException("Cannot recognise the task types!");
        }

        return taskTypeString + " | " + status + " | " + taskDescription;
    }

    /**
     * Parses string representation of task stored in the file on the hard disk
     * to create a <code>Task</code> object.
     *
     * @param storedTask String representation of the task
     * @return Task represented by the string input.
     * @throws LoadFailureException If the string is in the wrong format.
     */
    protected Task parseFromStorage(String storedTask) throws LoadFailureException {
        String[] taskElements = storedTask.split(" \\| ", 4);

        String taskTypeString = taskElements[0];
        String taskName = "";
        LocalDateTime dateTime = null;

        if (taskElements.length >= 3) {
            taskName = taskElements[2];
        }

        if (taskElements.length >= 4) {
            try {
                dateTime = LocalDateTime.parse(taskElements[3], FORMATTER);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
                throw new LoadFailureException("Look like date and time wrong format.");
            }
        }

        Task task = createTask(taskTypeString, taskName, dateTime);

        if (taskElements[1].equals(DONE)) {
            task.markDone();
        }

        return task;
    }

    /**
     * Creates task using elements parsed from source file.
     *
     * @param taskTypeString String indicating task type.
     * @param taskName Name of the task (aka Description).
     * @param dateTime LocalDateTime object indicating date and time of task (if applicable).
     * @return Task created according the specified elements.
     * @throws LoadFailureException If the taskTypeString does not refer to any task type.
     */
    private Task createTask(String taskTypeString,
                            String taskName,
                            LocalDateTime dateTime) throws LoadFailureException {
        if (taskTypeString.equals(TODO_TASK)) {
            return new Todo(taskName);
        } else if (taskTypeString.equals(DEADLINE_TASK)) {
            return new Deadline(taskName, dateTime);
        } else if (taskTypeString.equals(EVENT_TASK)) {
            return new Event(taskName, dateTime);
        } else {
            throw new LoadFailureException("Cannot identify the type of tasks in file."
                    + "\nProbably wrong format? I overwrite ah!");
        }
    }
}
