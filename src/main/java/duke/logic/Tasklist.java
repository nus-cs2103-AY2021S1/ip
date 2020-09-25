package duke.logic;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Represents a list of tasks. The Tasklist supports operations to add or delete tasks
 * and to mark tasks as done.
 */
public class Tasklist {

    private List<Task> taskList;

    /**
     * Creates an empty Tasklist.
     */
    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task to the Tasklist.
     *
     * @param task The task to be added to the Tasklist.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the number of Tasks currently stored in the Tasklist.
     *
     * @return The number of Tasks currently stored in the Tasklist.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the Task with the specified index within the Tasklist.
     *
     * @param taskNumber The index of the Task.
     * @return The Task with the specified index within the Tasklist.
     */
    public Task get(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Removes the Task with the specified index within the Tasklist.
     *
     * @param taskNumber The index of the Task to be removed.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Sorts the Tasks in the list by alphabetical order.
     */
    public void sort() {
        Comparator<Task> taskComparator = Comparator.comparing(task -> task.getDescription());
        taskList.sort(taskComparator);
    }

    /**
     * Returns the String representation of the list of all Tasks,
     * to be displayed on the UI.
     *
     * @return String representation of the list of all Tasks to be
     * displayed on the UI.
     */
    public String toDisplayString() {
        if (taskList.isEmpty()) {
            return "You have no tasks!";
        } else {
            String taskListString = "";
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                taskListString += taskNumber + "." + task.toDisplayString() + "\n";
                taskNumber++;
            }
            return taskListString.trim();
        }
    }

    /**
     * Returns the String representation of the list of all Tasks
     * that match the query String, to be displayed on the UI.
     *
     * @param query The search query String.
     * @return String representation of the list of all Tasks
     * that match the query String, to be displayed on the UI.
     */
    public String matchedTasksOnly(String query) {
        int index = 1;
        String matchedTasks = "";
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                matchedTasks += index + "." + task.toDisplayString() + "\n";
                index++;
            }
        }
        return matchedTasks.trim();
    }

    /**
     * Returns the String representation of the list of all Tasks,
     * to be saved in the storage of the Tasklist.
     *
     * @return String representation of the list of all Tasks to
     * be saved in the storage of the Tasklist.
     */
    public String toSavedString() {
        String taskListString = "";
        if (taskList.size() > 0) {
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                taskListString += task.toSavedString() + "\n";
                taskNumber++;
            }
        }
        return taskListString;
    }

}
