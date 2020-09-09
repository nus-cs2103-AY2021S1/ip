package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

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

     * @param task to be added to task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Remove task from task list.
     *
     * @param index index of task to be removed offset by -1 due to 1 indexed based list.
     * @return Task object which is removed.
     */
    public Task removeTask(int index) {
        // 1 index list
        final int OFFSET = 1;
        return taskList.remove(index - OFFSET);
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
     * Get task from task list offset by one
     *
     * @param taskIndex index of task to be retrieved.
     * @return Task Object retrieved based on index.
     */
    public Task getTask(int taskIndex) {
        final int OFFSET = 1;
        return taskList.get(taskIndex - OFFSET);
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
     * @return
     */
    public String clearAll() {
        taskList.clear();
        return "Task list cleared";
    }

    /**
     * String representation of taskList.
     *
     * @return String object representing taskList.
     */
    @Override
    public String toString() {
        String formattedListString = "";
        final int OFFSET = 1;
        for (int i = 0; i < taskList.size(); i++) {
            formattedListString += String.format("%d. %s\n", i + OFFSET, taskList.get(i));
        }
        return formattedListString;
    }

    /**
     * Returns boolean indicating presence of duplicate taskA.s
     *
     * @param task task to check against if there are duplicates of.
     * @return true if duplicates exists.
     */
    public boolean findIfDuplicateExists(Task task) {
        return taskList.stream().anyMatch(taskItem -> taskItem.checkIfDuplicate(task));
    }
}
