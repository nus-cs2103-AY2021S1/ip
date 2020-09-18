package duke.data;

import java.util.ArrayList;

import duke.exception.DuplicateTaskException;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * DukeTaskList manages tasks in Duke.
 */
public class DukeTaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a DukeTaskList.
     */
    public DukeTaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the task with the index.
     * @param index the task index
     * @return the task
     */
    public Task getTask(int index) {
        indexCheck(index);
        return tasks.get(index - 1);
    }

    /**
     * Adds the input task to the current task list.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert task != null : "task cannot be null";

        if (hasDuplicate(task)) {
            String errMessage = ExceptionMessage.getDuplicateTaskMessage(task);
            throw new DuplicateTaskException(errMessage);
        }

        tasks.add(task);
    }

    private boolean hasDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.getDescription().equals(task.getDescription())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes the task with the index from the current task list.
     * @param index the index of the task
     * @return the task deleted
     */
    public Task deleteTask(int index) {
        Task taskDelete = getTask(index);
        tasks.remove(index - 1);

        return taskDelete;
    }

    /**
     * Gets the current number of tasks saved in the task list.
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Overrides all current tasks using the tasks in the input ArrayList of Tasks.
     * @param tasks the ArrayList of tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the ArrayList of current tasks.
     * @return the ArrayList of current tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void indexCheck(int index) {
        if (index - 1 >= getSize() || index < 1) {
            throw new InvalidIndexException(ExceptionMessage.getInvalidIndexMessage(index + ""));
        }
    }

    /**
     * Finds tasks containing the input keyword.
     * @param keyword the keyword
     * @return the list of tasks containing the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert !keyword.isBlank() : "keyword for findTasks cannot be empty";

        ArrayList<Task> tasksFound = new ArrayList<>();

        for (Task task : tasks) {
            if (task.containsKeyWord(keyword)) {
                tasksFound.add(task);
            }
        }

        return tasksFound;
    }
}
