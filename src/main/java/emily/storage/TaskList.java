package emily.storage;

import java.util.ArrayList;

import emily.task.Deadline;
import emily.task.Event;
import emily.task.Task;

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
        assert index <= this.taskArrayList.size();
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

    /**
     * Find tasks with the given date
     *
     * @param keydate that the user wish to view the task on
     * @return an arraylist of task with the given date
     */
    public ArrayList<Task> findSameDate(String keydate) {
        ArrayList<Task> ls = new ArrayList<>();
        for (Task t : this.taskArrayList) {
            String d = "";
            if (t instanceof Deadline) {
                d = ((Deadline) t).getBy();
            } else if (t instanceof Event) {
                d = ((Event) t).getAt();
            } else {
                assert false : "List has an unknown task type";
            }

            if (d.contains(keydate)) {
                ls.add(t);
            }
        }
        return ls;
    }
}
