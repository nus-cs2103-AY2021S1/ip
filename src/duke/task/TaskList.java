package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class that represents a TaskList that stores all the Task under Duke
 */
public class TaskList {
    private List<Task> list;

    /**
     * TaskList Class constructor. Create a new empty TaskList.
     *
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * TaskList Class constructor. Create a TaskList with Tasks.
     *
     * @param add a list of Task to be added to TaskList
     */
    public TaskList(List<Task> add) {
        this.list = add;
    }

    /**
     * Method that return the specific task at the given index.
     * @param index index of task
     * @return  a specific task object
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Method that remove a specific task at the given index.
     * @param index index of task
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Method that return the number of Task in the TaskList
     * @return  the number of Task in the TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Method that add a Task to the TaskList.
     * @param task the Task to be added into the TaskList
     */
    public void add(Task task) {
        list.add(task);
    }

}
