package duke.util;

import duke.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store and manage Task objects.
 */
public class TaskList {

    List<Task> taskList;

    /**
     * Create an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a TaskList from the given list of representations.
     *
     * @param stringList List of string representations
     */
    public TaskList(List<String> stringList) {
        taskList = new ArrayList<>();
        stringList.forEach(this::parseLine);
    }

    /**
     * Returns the list of Tasks.
     *
     * @return List of Tasks
     */
    public List<Task> getList() {
        return taskList;
    }

    /**
     * Returns the size of the list.
     *
     * @return size of list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Creates a Task from the given representation and adds it to the list.
     *
     * @param line string repesentation of the Task
     */
    private void parseLine(String line) {
        String[] lineSplit = line.split("\\|");
        boolean done = lineSplit[2].equals("1");
        if (lineSplit[0].equals("T")) {
            addTask(lineSplit[1], TaskType.TODO, "", done);
        } else if (lineSplit[0].equals("D")) {
            addTask(lineSplit[1], TaskType.DEADLINE, lineSplit[3], done);
        } else {
            addTask(lineSplit[1], TaskType.EVENT, lineSplit[3], done);
        }
    }

    /**
     * Adds a Task to the list.
     *
     * @param task Task name
     * @param type TaskType of the of Task to add
     * @param ddl Task deadline
     * @param done whether task has been marked done
     */
    private void addTask(String task, TaskType type, String ddl, boolean done) {
        Task newTask;
        if (type == TaskType.TODO) {
            newTask = new Todo(task);
        } else if (type == TaskType.DEADLINE) {
            newTask = new Deadline(task, LocalDate.parse(ddl));
        } else {
            newTask = new Event(task, LocalDate.parse(ddl));
        }

        if (done) {
            newTask.markDone();
        }
        taskList.add(newTask);
    }

    /**
     * Add a Todo with the given name.
     *
     * @param task Todo name
     * @return Task object added
     */
    public Task addTodo(String task) {
        return addTask(new Todo(task));
    }

    /**
     * Add a Deadline or Event with given attributes.
     *
     * @param taskAttr Task attributes
     * @param isEvent whether Task is an Event
     * @return Task object added
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    public Task addDDLTask(String taskAttr, boolean isEvent) throws DukeException {
        Task newTask;
        String[] taskSplit;
        if (isEvent) {
            taskSplit = taskAttr.split("/at");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for an event. Use /at followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Event(taskSplit[0].strip(), dateTime);
        } else {
            taskSplit = taskAttr.split("/by");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for a deadline. Use /by followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Deadline(taskSplit[0].strip(), dateTime);
        }
        return addTask(newTask);
    }

    private Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Returns array of Task description strings.
     *
     * @return array of Task description strings
     */
    public String[] listTasks() {
        String[] taskOutputs = new String[taskList.size() + 1];
        taskOutputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskOutputs[i + 1] = (i + 1) + ". " + taskList.get(i).toString();
        }
        return taskOutputs;
    }

    /**
     * Marks the given Task as done.
     *
     * @param position position of the Task in the list, as seen by the user.
     * @return Task marked done
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    public Task markDone(int position) throws DukeException {
        if (position < 1 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        Task task = taskList.get(position - 1);
        task.markDone();
        return task;
    }

    /**
     * Deletes the given Task.
     *
     * @param position position of the Task in the list, as seen by the user
     * @return Task deleted
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    public Task deleteTask(int position) throws DukeException {
        if (position < 1 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        return taskList.remove(position - 1);
    }

    /**
     * Validates the given string as a date and converts it to a LocalDate.
     *
     * @param time date string
     * @return LocalDate for the given date
     * @throws DukeException Duke-related exception due to erroneous inputs
     */
    private LocalDate validateDateTime(String time) throws DukeException {
        if (time.equals("")) {
            throw new DukeException("Task date cannot be empty.");
        }
        LocalDate parsed;
        try {
            parsed = LocalDate.parse(time);
            return parsed;
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date entered. Use format YYYY-MM-DD");
        }
    }

    /**
     * Find tasks that contain the given key.
     *
     * @param key key to search for
     * @return string representations of Tasks founc
     */
    public List<Task> findTasks(String key) {
        List<Task> found = new ArrayList<>();
        for (Task task : taskList) {
            if (task.search(key)) {
                found.add(task);
            }
        }
        return found;
    }

    public void massMarkDone(List<Task> tasks) {
        for (Task task : tasks) {
            task.markDone();
        }
    }

    public void massDelete(List<Task> tasks) {
        boolean removeSuccess = taskList.removeAll(tasks);
        assert removeSuccess : "Tasks found were not removed";
    }
}
