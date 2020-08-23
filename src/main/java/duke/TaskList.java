package duke;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * The class that is responsible for storing
 * the current state of the list of tasks
 * while Duke is still running.
 */
public class TaskList {

    private ArrayList<Task> taskStore;

    /**
     * Initializes the TaskList with an empty
     * list when there is no saved data.
     */
    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    /**
     * Initializes the TaskList with the existing
     * data that was loaded from the data file.
     * @param data The ArrayList of each line of raw data.
     * @throws DukeException If there was a parsing error. This
     * means that the saved data is corrupted in some way.
     */
    public TaskList(ArrayList<String> data) throws DukeException {
        this.taskStore = new ArrayList<>();
        for (String s : data) {
            Task t = Parser.parseTaskData(s);
            taskStore.add(t);
        }
    }

    /**
     * Adds a new Task to the taskList.
     * @param newTask The task to be added.
     */
    public void add(Task newTask) {
        taskStore.add(newTask);
    }

    /**
     * Marks an existing task as done.
     * @param taskNum The number of the task to be
     *                marked as done in the taskList.
     * @return The task that was marked as done.
     */
    public Task markTaskAsDone(int taskNum) {
        Task doneTask = taskStore.get(taskNum - 1).markAsDone();
        taskStore.set(taskNum - 1, doneTask);
        return doneTask;
    }

    /**
     * Deletes an existing task.
     * @param taskNum The number of the task to be
     *                deleted.
     * @return The task that was deleted.
     */
    public Task delete(int taskNum) {
        return taskStore.remove(taskNum - 1);
    }

    /**
     * Finds tasks that matches the search keyword.
     * @param keyword The search keyword.
     * @return An ArrayList of string representations of tasks that match the keyword.
     */
    public ArrayList<String> find(String keyword) {
        return mapToRepr(filterTasks(taskStore, task -> task.contains(keyword)), Task::toString);
    }

    /**
     * Gets the string representation of each task
     * in the taskList.
     * @return An ArrayList of string representation
     * of each task in the taskList.
     */
    public ArrayList<String> getListRepr() {
        return mapToRepr(taskStore, Task::toString);
    }

    /**
     * Translates the taskList to raw data to be stored.
     * @return An ArrayList of raw data where each element
     * corresponds to the raw data representation of each
     * task in the taskList.
     */
    public ArrayList<String> getData() {
        return mapToRepr(taskStore, Task::getData);
    }

    /**
     * Gets the number of tasks currently in the taskList.
     * @return A string that informs the user of the number
     * of tasks currently in the taskList.
     */
    public String getListStatus() {
        int storeSize = taskStore.size();
        return "There " + (storeSize > 1 ? "are " : "is ") + "now " + storeSize + " " +
                (storeSize > 1 ? "tasks " : "task ") + "in your list!";
    }

    private static ArrayList<String> mapToRepr(ArrayList<Task> taskStore, Function<Task, String> mapper) {
        return taskStore.stream().map(mapper).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Task> filterTasks(ArrayList<Task> taskStore, Predicate<Task> pred) {
        return taskStore.stream().filter(pred).collect(Collectors.toCollection(ArrayList::new));
    }
}
