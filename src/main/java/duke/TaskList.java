package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of list of tasks added by user.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Initializes task list object with an empty ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    /**
     * Initializes task list object with specified Task List.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds tasks to task list.
     * 
     * @param task to be added to task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Remove task from task list.
     * 
     * @param index index of task to be removed.
     * @return Task object which is removed.
     */
    public Task removeTask(int index) {
        // 1 index list
        return taskList.remove(index - 1);
    }

    /**
     * Gets all the tasks in the task list.
     * 
     * @return List Object with all tasks in the TaskList.
     */
    public List<Task> getAll() {
        return this.taskList;
    }

    /**
     * Get task from task list.
     * 
     * @param taskIndex index of task to be retrieved.
     * @return Task Object retrieved based on index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    /**
     * Gets size of task list.
     * 
     * @return number of items int taskList.
     */
    public int getSize() {
       return taskList.size();
    }

    /**
     * Clears all tasks from taskList.
     */
    public void clearAll() {
        taskList.clear();
    }

    /**
     * String representation of taskList.
     *
     * @return String object representing taskList.
     */
    @Override
    public String toString() {
            String formattedListString = "";
            for (int i = 0; i < taskList.size(); i ++) {
                formattedListString+= String.format("%d. %s\n", i + 1, taskList.get(i));
            }
            return formattedListString;
    }
}
