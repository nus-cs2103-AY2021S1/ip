package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The <code>ArrayList</code> containing all the Tasks that Duke is storing.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor to create a new TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor, used when there are existing tasks being loaded from the user's local storage.
     *
     * @param tasks is the List of Tasks that is created from loading the Storage's tasks.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        this.taskList = tasks;
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param newTask the task to be added.
     */
    public void add(Task newTask) {
        assert newTask != null : "New task cannot be null";
        this.taskList.add(newTask);
    }

    /**
     * Removes the specified task from the TaskList.
     *
     * @param index the index of the Task to be deleted from the TaskList.
     * @return the Task that has been deleted.
     */
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    /**
     * The number of Tasks in the TaskList.
     *
     * @return an <code>Integer</code> representing the number of Tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves a Task from the TaskList at a specified index.
     *
     * @param index the Task in the TaskList to be retrieved.
     * @return the Task that is retrieved.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints out all the Tasks in the TaskList that contains the keyword specified by the user.
     *
     * @param keyword the key to filter the Tasks in the TaskList by.
     * @return String representing all the filtered items in the TaskList.
     */
    public String showSpecifiedItems(String keyword) {
        List<Task> currList = this.taskList;
        List<Task> filteredList = currList.stream().filter((task) -> task.hasKeyword(keyword))
                .collect(Collectors.toList());

        return new TaskList(filteredList).toString();
    }

    /**
     * Prints a string representation of the number of tasks currently in the TaskList.
     */
    public String printNumTasks() {
        return "\nNow you have " + taskList.size()
                + (taskList.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    private List<Task> getInnerList() {
        return this.taskList;
    }

    /**
     * Switches the old TaskList with a new TaskList.
     *
     * @param newTaskList the new TaskList to replace the old TaskList with.
     */
    public void replace(TaskList newTaskList) {
        this.taskList = newTaskList.getInnerList();
    }

    /**
     * Creates a deep copy of the TaskList.
     *
     * @return a new instance of TaskList copied from the previous instance.
     */
    public TaskList deepCopy() {
        List<Task> newList = new ArrayList<>();
        List<Task> currList = this.taskList;
        for (Task t : currList) {
            Task copiedTask = t.deepCopy();
            newList.add(copiedTask);
        }
        return new TaskList(newList);
    }

    /**
     * Returns a string representation of the Tasks in the list.
     *
     * @return a string of all the tasks in the list.
     */
    @Override
    public String toString() {
        return IntStream.range(0, this.taskList.size())
                .mapToObj(index -> String.format("%d. %s", index + 1, this.taskList.get(index)))
                .collect(Collectors.joining("\n"))
                .trim();
    }
}
