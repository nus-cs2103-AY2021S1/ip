package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * duke.TaskList is responsible to store the array of lists temporarily,
 * and able to add new task into the list.
 */
public class TaskList {

    public static List<Task> taskLists;
    public static List<Task> tempLists = new ArrayList<>();

    /**
     * Constructor of tasklist.
     */
    public TaskList() {
        taskLists = new ArrayList<>();
    }

    /**
     * Marks task item as done.
     *
     * @param index number of item in the list.
     */
    public void markDone(int index) {
        taskLists.get(index).markAsDone();
    }

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
