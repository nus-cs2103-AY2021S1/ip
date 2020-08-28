import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of tasks
 */
public class TaskList {
    protected static List<Task> TO_DO_LIST = new ArrayList<>();
    /*public TaskList() {
        this.toDoList = new ArrayList<>();
    }*/

    /**
     * Adds a given task to the list.
     * @param task given task
     */
    public static void addToList(Task task) {
        TO_DO_LIST.add(task);
    }

    /**
     * Removes a task with given task ID from the list.
     * @param taskId given task ID
     */
    public static void removeFromList(int taskId) {
        TO_DO_LIST.remove(taskId - 1);
    }

    /**
     * Returns an ArrayList of tasks that match the User keyword given
     * @param keyword
     * @return
     */
    public static ArrayList<Task> searchList(String keyword) {
        ArrayList<Task> returnList = new ArrayList<>();
        for (Task t : toDoList
             ) {
            if(t.toString().contains(keyword)) {
                returnList.add(t);
            }
        }
        return returnList;
    }
}
