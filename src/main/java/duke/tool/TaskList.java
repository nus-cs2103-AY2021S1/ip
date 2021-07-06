package duke.tool;

import java.util.ArrayList;

import duke.exception.TaskExistException;
import duke.task.Task;



/**
 * Represents the task list stored in Duke system.
 */
public class TaskList {

    /**
     * List of tasks stored in system
     */
    private ArrayList<Task> taskList;

    /**
     * Creates a task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a task list from given list.
     *
     * @param tasks Task list that have stored tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
     * Returns the list size of task list.
     *
     * @return Size of current list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds new task into the current list.
     *
     * @param newTask New task to be added to the list.
     * @throws TaskExistException When task's description is the same as some task in list.
     */
    public void add(Task newTask) throws TaskExistException {
        if (this.containsExactDescription(newTask)) {
            throw new TaskExistException();
        }

        this.taskList.add(newTask);
    }

    /**
     * Deletes the certain task with given index.
     *
     * @param index Index of task to be deleted.
     * @return Task that has been deleted.
     */
    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Returns a task list.
     *
     * @return Task list of current object.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Marks the task with given element to done.
     *
     * @param index Index of the element.
     * @return Task that has been marked as done.
     */
    public Task markDone(int index) {
        Task targetTask = taskList.get(index);
        targetTask.markAsDone();
        return targetTask;
    }

    /**
     * Clear all task in current list.
     */
    public void clear() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns whehter there is exact same task in the list.
     *
     * @param targetTask Task that user want to add into list.
     * @return True if there is a task with exact description, false otherwise.
     */
    public boolean containsExactDescription(Task targetTask) {
        return taskList.stream()
                .anyMatch(task -> task.isExactDescription(targetTask));
    }

    /**
     * Gets the string representation of task list in the system.
     */
    public String getTaskListString() {
        //Check whether there are any task in the list or not
        if (taskList.isEmpty()) {
            return "You haven't added any task here !";
        }

        return taskList.stream()
                .reduce("Here are the tasks in your list: \n\t", (string, task) ->
                                string + buildTaskString(task), (string1, string2) ->
                        string1 + string2);
    }

    private String buildTaskString(Task task) {
        assert taskList.contains(task);

        return (taskList.indexOf(task) + 1) + ". "
                + task.toString() + "\n" + "\t";
    }
}
