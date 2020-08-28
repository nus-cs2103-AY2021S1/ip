import java.util.ArrayList;
import java.util.List;
public class TaskList {
    protected static List<Task> toDoList = new ArrayList<>();
    /*public TaskList() {
        this.toDoList = new ArrayList<>();
    }*/
    public static void addToList(Task task) {
        toDoList.add(task);
    }

    public static void removeFromList(int taskId) {
        toDoList.remove(taskId - 1);
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
