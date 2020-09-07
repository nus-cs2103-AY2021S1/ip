package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the List of all the tasks that logic.Duke
 * is handling this iteration.
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns this Arraylist.
     *
     * @return the Arraylist that the class has.
     */
    public List<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Removes tasks.Task at the given index.
     *
     * @param index Represents the index of the item to be removed.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns a List that contains the task that matches
     * the keyword that the user is finding.
     *
     * @param keyword Keyword that the user is finding.
     * @return List of tasks that matches the keyword given.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> filteredTasks = taskList.stream().filter(task -> task.taskDesc.contains(keyword)).collect(
                Collectors.toList());
        return filteredTasks;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
