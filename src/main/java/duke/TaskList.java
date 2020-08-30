package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList is responsible to store the array of lists temporarily,
 * and able to add new task into the list.
 */
public class TaskList {

    private static List<Task> taskLists;
    private static List<Task> tempLists = new ArrayList<>();

    /**
     * Constructor of Tasklist.
     */
    public TaskList() {
        taskLists = new ArrayList<>();
    }

    public static List<Task> getTaskLists() {
        return taskLists;
    }

    public static List<Task> getTempLists() {
        return tempLists;
    }

    /**
     * Marks task item as done.
     *
     * @param index number of item in the list.
     */
    public void markDone(int index) {
        taskLists.get(index).markAsDone();
    }

    /**
     * Searches the keyword in tempLists and returns each item in tempLists if exist.
     *
     * @param description keyword.
     */
    public static void searchKeyword(String description) {
        tempLists.clear();
        for (Task task : taskLists) {
            if (task.getDescription().contains(description)) {
                tempLists.add(task);
            }
        }
    }

    /**
     * Adds task into the list.
     *
     * @param task task to be added into list.
     */
    public void add(Task task) {
        taskLists.add(task);
    }

}
