package duke.task;
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
        assert taskId > 0 : "Task number provided cannot be less than 1!";
        toDoList.remove(taskId - 1);
    }

    /**
     * Returns an ArrayList of tasks that match the User keyword given
     * @param keywords user keyword
     * @return ArrayList of tasks
     */
    public static ArrayList<Task> searchList(String... keywords) {
        ArrayList<Task> returnList = new ArrayList<>();
        for (String s : keywords) {
            for (Task t : toDoList
            ) {
                if (t.toString().contains(s)) {
                    returnList.add(t);
                }
            }
        }
        return returnList;
    }

    public static List<Task> getList() {
        return toDoList;
    }
}
