package duke.taskList;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Creates a taskList
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task into the taskList
     *
     * @param task
     */
    public void addTask(Task task){
        taskList.add(task);
    }

    /**
     * Returns the size of the taskList
     *
     * @return int, size of the taskList
     */
    public int getSize(){
        return taskList.size();
    }

    /**
     * Returns the task at the given position
     *
     * @param i, position of the task
     * @return Task at the given position
     */
    public Task getTask(int i){
        return taskList.get(i);
    }

    /**
     * Removes the task at the given position
     *
     * @param i, position of the task
     * @return Task removed at the given position
     */
    public Task removeTask(int i){
        Task deletedTask = taskList.remove(i);
        return deletedTask;
    }
}
