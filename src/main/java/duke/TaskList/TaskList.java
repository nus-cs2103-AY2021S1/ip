package duke.TaskList;

import duke.Tasks.Task;

import java.util.ArrayList;

/**
 * A TaskList class creates a taskList object that
 * Adds a Task into the arrayList
 * Returns the size of the ArrayList
 * Returns a Task at a certain index
 * Removes a Task at a certain index
 */

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a taskList object that has an ArrayList
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a Task into the ArrayList
     * @param task
     */
    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Returns the size of the ArrayList
     * @return size of ArrayList
     */
    public int getSize(){
        return taskList.size();
    }

    /**
     * Returns Task at the specific Index
     * @param i
     * @return Task
     */
    public Task getTask(int i){
        return taskList.get(i);
    }

    /**
     * Removes and Returns Task at the specific Index
     * @param i
     * @return Task
     */
    public Task removeTask(int i){
        Task deletedTask = taskList.remove(i);
        return deletedTask;
    }
}
