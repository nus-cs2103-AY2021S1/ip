package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.dukeexception.DukeException;
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
     * Loads list of tasks from <code>taskFile</code>.
     *
     * @return List of tasks to be passed to a <code>TaskList</code> object
     * @throws DukeException If file cannot be created, read or parsed.
     */
    public List<Task> load() throws DukeException {
        if (!this.taskFile.exists()) {
            File dir = this.taskFile.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            try {
                this.taskFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace(); // todo
            }
        }

        Scanner sc = null;
        try {
            sc = new Scanner(this.taskFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // todo
        }

        if (sc != null) {
            List<Task> list = new ArrayList<>();
            while (sc.hasNext()) {
                String storedTask = sc.nextLine();
                Task task = parseFromStorage(storedTask);
                list.add(task);
            }
            return list;
        } else {
            return new ArrayList<Task>(); //todo
        }
    }

    /**
     * Updates file by added a new task.
     *
     * @param task Task to be added to file.
     * @throws DukeException If task cannot be parsed or file cannot be written to.
     */
    public void update(Task task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.taskFile, true);
            fileWriter.write("\n" + parseToStorage(task));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates file with list when tasks are marked or deleted.
     *
     * @param list Updated list given by <code>TaskList</code> object.
     * @throws DukeException If tasks cannot be parsed or file cannot be written to.
     */
    public void update(List<Task> list) throws DukeException {
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
            e.printStackTrace(); // todo
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
    protected String parseToStorage(Task task) throws DukeException {
        String taskType = "";
        String status = task.isDone() ? DONE : NOT_DONE;
        String taskName = task.getTaskName();
        String taskDescription = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h.m a");

        if (task instanceof Todo) {
            taskType = TODO_TASK;
            taskDescription = taskName;
        } else if (task instanceof Deadline) {
            taskType = DEADLINE_TASK; // + date
            taskDescription = taskName + " | " + ((Deadline) task).getDateTime().format(formatter);
        } else if (task instanceof Event) {
            taskType = EVENT_TASK;
            taskDescription = taskName + " | " + ((Event) task).getDateTime().format(formatter);
        } else {
            throw new DukeException("Cannot recognise type");
        }

        return taskType + " | " + status + " | " + taskDescription;
    }

    /**
     * Parses string representation of task stored in the file on the hard disk
     * to create a <code>Task</code> object.
     *
     * @param storedTask String representation of the task
     * @return Task represented by the string input.
     * @throws DukeException If the string is in the wrong format.
     */
    protected Task parseFromStorage(String storedTask) throws DukeException {
        String[] taskElements = storedTask.split(" \\| ", 4);

        String taskType = taskElements[0];
        String taskName = "";
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h.m a");

        if (taskElements.length >= 3) {
            taskName = taskElements[2];
        }

        if (taskElements.length >= 4) {
            dateTime = LocalDateTime.parse(taskElements[3], formatter);
        }

        Task task = null;
        if (taskType == TODO_TASK) {
            task = new Todo(taskName);
        } else if (taskType == DEADLINE_TASK) {
            task = new Deadline(taskName, dateTime);
        } else if (taskType == EVENT_TASK) {
            task = new Event(taskName, dateTime);
        }

        if (taskElements[1].equals(DONE)) {
            task.markDone();
        }

        if (task != null) {
            return task;
        } else {
            throw new DukeException("Cannot read tasks from file.");
        }
    }
}
