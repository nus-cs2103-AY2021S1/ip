package duke.lists;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * List to keep track of tasks
 */
public class TaskList {
    
    private final ArrayList<Task> taskList;

    /**
     * Initializes TaskList to a new list of tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes TaskList to the specified list of tasks
     * 
     * @param taskList list of tasks
     */
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the specified task to the end of the list
     * 
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Adds the specified task to the index specified
     * 
     * @param index index in list where task is added
     * @param task task to be added
     */
    public void addTaskAtIndex (int index, Task task) {
        taskList.add(index, task);
    }
    
    /**
     * Removes a task from the list
     * 
     * @param index position of task in the list to be removed
     */
    public Task removeTask (int index) {
        assert index >= 0;
        return taskList.remove (index);
    }

    /**
     * Sets whether a task, specified by the index, has been done
     * 
     * @param index position of task in the list 
     */
    public void setDone (int index, boolean isDone) {
        assert index >= 0;
        taskList.get(index).setDone(isDone);
    }

    /**
     * Returns the number of tasks in the list
     * 
     * @return number of tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index of the list
     * 
     * @param index position in the list to retrieve the task
     * @return Task in the list
     */
    public Task getTask(int index) {
        assert index >= 0;
        return taskList.get(index);
    }

    /**
     * Filters the task list to find task descriptions which contain the specified string
     * 
     * @param filter string to search for in task descriptions
     * @return New taskList which only contains tasks with descriptions that contain the specified string
     */
    public TaskList filterList(String filter) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < this.size(); i++) {
            if (this.getTask(i).getDescription().contains(filter)) {
                filteredList.addTask(this.getTask(i));
            }
        }
        return filteredList;
    }
}
