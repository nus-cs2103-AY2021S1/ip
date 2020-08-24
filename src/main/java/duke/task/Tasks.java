package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.ReadFailedException;

/**
 * The Tasks, which stores a list containing tasks.
 */
public class Tasks {
    /**
     * The List of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Instantiates a new Tasks, initialise the task list.
     */
    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the task list.
     *
     * @return the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task.
     *
     * @param taskIndex the task index.
     * @return the task.
     * @throws IndexOutOfBoundsException If the task is not in the task list.
     */
    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return this.tasks.get(taskIndex);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns data of the task list.
     * Used to store the task list.
     *
     * @return the data.
     */
    public String getData() {
        String data = "";
        for (Task task : tasks) {
            data += task.getData() + "\n";
        }
        return data;
    }

    /**
     * Adds a task.
     * Create a task then add it to the task list.
     *
     * @param stringArr the string arr.
     * @throws ReadFailedException If the stringArr cannot be read.
     */
    public void addTask(String[] stringArr) throws ReadFailedException {
        Task task = Task.createTask(stringArr);
        this.tasks.add(task);
    }

    /**
     * Adds a task.
     *
     * @param task the task.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove a task.
     *
     * @param taskIndex the task index.
     * @throws IndexOutOfBoundsException If the task is not in the task list.
     */
    public void removeTask(int taskIndex) throws IndexOutOfBoundsException {
        this.tasks.remove(taskIndex);
    }

    /**
     * Returns a task list that has the date.
     *
     * @param date the date.
     * @return the task list.
     */
    public ArrayList<Task> findByDate(LocalDate date) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task : this.tasks) {
            if (Task.isDateEqual(task, date)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Returns a task list that has the description.
     *
     * @param description the description.
     * @return the task list.
     */
    public ArrayList<Task> findByDescription(String description) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.containsDescription(description)) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
