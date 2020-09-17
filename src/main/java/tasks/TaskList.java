package tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import exception.InvalidInputException;

/**
 * Represents the List of all the tasks that logic.Duke
 * is handling this iteration.
 */
public class TaskList {

    private List<Task> taskList;
    private HashSet<String> tasksDescription = new HashSet<String>();

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
        Task removed = this.taskList.get(index);
        String removedDescription = removed.taskDesc;
        tasksDescription.remove(removedDescription);
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

    /**
     * Adds new task to Tasklist.
     *
     * @param task New Task object created for the user.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.tasksDescription.add(task.taskDesc);
    }

    /**
     * Ensures no duplicate tasks in Duke.
     *
     * @param input Description of task that user wants to add.
     * @throws InvalidInputException If the input of user is wrong.
     */
    public void checkDuplicates(String input) throws InvalidInputException {
        if (tasksDescription.contains(input)) {
            throw new InvalidInputException("It seems that there is already a task matching this description!"
                    + " To be safe, please be more specific in your description!");
        }
    }

    /**
     * Populates the HashSet that will be used to check
     * for duplicates in task description
     */
    public void createDuplicateChecker() {
        taskList.forEach(task -> tasksDescription.add(task.taskDesc));
    }
}
