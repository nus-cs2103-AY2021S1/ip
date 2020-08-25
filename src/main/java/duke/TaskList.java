package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents a list of tasks. It contains several operations that can be performed on the list.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> taskList;

    /**
     * Initialises an empty TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Initialises a TaskList which contains tasks provided by the List of Tasks
     * @param taskList List containing Tasks with which to fill the TaskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Provides the number of tasks in the TaskList
     * @return Size of TaskList
     */
    public int numTasks() {
        return taskList.size();
    }

    /**
     * Adds a new Task to the TaskList
     * @param task Task to be added to the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves a task from the TaskList at the specified index
     * @param index The index of the Task to be retrieved
     * @return Task object stored at index in the TaskList
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Removes a task from the TaskList at the specified index
     * @param index The index of the Task to be removed
     * @return Task object that has been removed
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
