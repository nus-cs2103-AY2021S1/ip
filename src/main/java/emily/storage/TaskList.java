package emily.storage;

import emily.task.Task;
import java.util.ArrayList;

/**
 * Modifies the arraylist of tasks and contains the most updated list
 * of tasks
 */
public class TaskList {
    private ArrayList<Task> taskArrayList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public void addTask(Task t) {
        taskArrayList.add(t);
    }

    /**
     * Removes the corresponding task based on the index
     *
     * @param index provided by the user
     */
    public void deleteTask(int index) {
        assert index<=this.taskArrayList.size();
        taskArrayList.remove(index);
    }

    /**
     * Looks through the list of tasks.
     * Provides a list of tasks containing the given keyword.
     *
     * @param keyword matches the task description
     * @return a list of task the user is finding
     */
    public ArrayList<Task> findSameKeyword(String keyword) {
        ArrayList<Task> ls = new ArrayList<>();
        for (Task t : this.taskArrayList) {
            String d = t.getDescription();
            if (d.contains(keyword)) {
                ls.add(t);
            }
        }
        return ls;
    }
}
