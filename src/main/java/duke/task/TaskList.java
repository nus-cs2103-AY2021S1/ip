package duke.task;

import duke.exception.InvalidIndexException;

import java.util.ArrayList;

/**
 * represents the task list and deals with operations to alter the task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * class constructor if no array list of tasks is given
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * class constructor if an array list of tasks is given
     * @param tasks the array list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * outputs a string representation of the number of tasks that the array list contains
     * @return a string describing the number of tasks that the array list contains
     */
    public String numberOfTasks() {
        return "you have [" +
                this.tasks.size() + "] task(s) in your list";
    }

    /**
     * marks the given task as done based on its task number
     * @param taskNumber the index of the task to be marked as done
     * @return the task after it has been marked as done
     * @throws InvalidIndexException if the given index of the task does not exist
     */
    public Task done(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * deletes the given task based on its task number
     * @param taskNumber the index of the task to be deleted
     * @return the task that was deleted
     * @throws InvalidIndexException if the given index of the task does not exist
     */
    public Task delete(int taskNumber) throws InvalidIndexException {
        Task task = getTaskNumber(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task;
    }

    /**
     * adds the given task to the task list
     * @param task the task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * returns the list of tasks that matches the given query string
     * @param queryString the given query to search for in the task list
     * @return an array list containing the tasks that match the query string
     */
    public ArrayList<Task> getMatchingTasks(String queryString) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.toString().contains(queryString)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * returns the tasks stored in the task list in the form of an array list
     * @return the array list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * deletes all of the tasks in the list
     */
    public void deleteAll() {
        tasks.clear();
    }

    private Task getTaskNumber(int taskNumber) throws InvalidIndexException {
        try {
            return this.tasks.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            String message = "sorry! the task number: " +
                    (taskNumber + 1) +
                    " does not exist in your list";
            throw new InvalidIndexException(message);
        }
    }
}
