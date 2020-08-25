import java.util.ArrayList;
import java.util.List;

/**
 * Represents the List of all the tasks that Duke
 * is handling this iteration
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns this Arraylist
     * @return the Arraylist that the class has
     */
    public List<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Removes Task at the given index.
     *
     * @param index Represents the index of the item to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public List<Task> findTasks(String keyword) {
        List<Task> newList = new ArrayList<>();
        for( int m = 0; m < taskList.size(); m++) {
            if(taskList.get(m).taskDesc.contains(keyword)) {
                newList.add(taskList.get(m));
            }
        }
        return newList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
