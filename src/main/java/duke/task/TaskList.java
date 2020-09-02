package duke.task;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of tasks
 */
public class TaskList {
    private static List<Task> toDoList = new ArrayList<>();

    /**
     * Adds a given duke.task to the list.
     * @param task given duke.task
     */
    public static void addToList(Task task) {
        toDoList.add(task);
    }

    /**
     * Removes a duke.task with given duke.task ID from the list.
     * @param taskId given duke.task ID
     */
    public static void removeFromList(int taskId) {
        toDoList.remove(taskId - 1);
    }

    /**
     * Returns an ArrayList of tasks that match the User keyword given
     * @param keyword user keyword
     * @return ArrayList of tasks
     */
    public static ArrayList<Task> searchList(String keyword) {
        ArrayList<Task> returnList = new ArrayList<>();
        for (Task t : toDoList
             ) {
            if (t.toString().contains(keyword)) {
                returnList.add(t);
            }
        }
        return returnList;
    }

    public static List<Task> getList() {
        return toDoList;
    }
}
