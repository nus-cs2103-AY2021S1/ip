package duke.task;

import java.util.ArrayList;

/**
 * TaskList contains the task list e.g.,
 * it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a {@code TaskList} with an array list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task into list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task with given index out of list.
     *
     * @param index The index of the task which needs to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Gets the task with certain index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets an array list containing all tasks.
     *
     * @return {@code taskList}.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Finds tasks whose content and tag include given conditions.
     *
     * @return A {@code TaskList} containing all of satisfied tasks.
     */
    public TaskList find(String condition) {
        ArrayList<Task> newTaskList = new ArrayList<Task>();
        taskList.stream()
                .filter(x->x.getContent().contains(condition) || x.getTag().contains(condition))
                .forEach(x->newTaskList.add(x));
        return new TaskList(newTaskList);
    }
}
