package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the list of tasks and the relative operations.
 */
public class TaskList {

    /**
     * List of tasks
     */
    private List<Task> taskList;

    /**
     * Storage of data file
     */
    private Storage storage;

    /**
     * Initializes the task list object with a storage.
     * Loads tasks from storage to list of tasks.
     *
     * @param storage Storage object of data file.
     */
    public TaskList(Storage storage) {
        this.taskList = storage.load();
        this.storage = storage;
    }

    /**
     * Gives the count of tasks in the list of tasks.
     *
     * @return Number of tasks in the list.
     */
    public int taskCount() {
        return this.taskList.size();
    }

    /**
     * Adds a new Task to task list.
     * Updates the data file.
     *
     * @param newTask New task to be added.
     */
    public void addNewTask(Task newTask) {
        this.taskList.add(newTask);
        this.storage.save(taskList);
    }

    /**
     * Gives the task at a specific position of task list.
     *
     * @param index Index of wanting task.
     * @return Task founded at the given index.
     * @throws IndexOutOfBoundsException If the index given is out of bound.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index);
    }

    /**
     * Marks the task at a specific position as done.
     * Have checked index before calling this method, needn't throw exceptions.
     *
     * @param index Index of marking task.
     */
    public void markDone(int index) {
        this.taskList.get(index).done();
        this.storage.save(taskList);
    }

    /**
     * Deletes a task at specific position.
     * Have checked index before calling this method, needn't throw exceptions.
     *
     * @param index Index of deleting task.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
        this.storage.save(taskList);
    }

    /**
     * Finds all tasks that contain given keyword.
     * Gives a list of Task that contains searching results.
     *
     * @param keyWord Keyword needed to match.
     * @return A List of result tasks.
     */
    public List<Task> find(String keyWord) {
        List<Task> resultList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getContent().contains(keyWord)) {
                resultList.add(taskList.get(i));
            }
        }
        return resultList;
    }

    /**
     * Gives String representation of the list of tasks.
     *
     * @return String representation of the list of tasks.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= taskList.size(); i++) {
            result += (i + ". " + taskList.get(i - 1).toString() + "\n");
        }
        return result;
    }
}
