package duke.task;

import duke.exception.ReadFailedException;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * The Tasks, which stores a list containing tasks.
 */
public class Tasks {
    /**
     * The List of tasks.
     */
    private ArrayList<Task> tasks;

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
        for (Task task: tasks) {
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
        for (Task task: this.tasks) {
            if (task.hasDate()) {
                boolean isEqual = task.type == TaskType.DEADLINE
                        ? ((Deadline) task).isDateEqual(date)
                        : ((Event) task).isDateEqual(date);
                if (isEqual) taskList.add(task);
            }
        }
        return taskList;
    }
}
