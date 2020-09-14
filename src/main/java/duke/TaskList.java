package duke;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a task list with a stored tasks in a list.
 */
public class TaskList {
    private List<Task> storedTasks;

    /**
     * Initializes a task list containing the list of stored tasks.
     *
     * @param storedTasks List of stored tasks.
     */
    public TaskList(List<Task> storedTasks) {
        this.storedTasks = storedTasks;
    }

    /**
     * Gets the list of stored tasks.
     *
     * @return List of stored tasks.
     */
    public List<Task> getStoredTasks() {
        return storedTasks;
    }

    /**
     * Gets the number of stored tasks.
     *
     * @return Number of stored tasks.
     */
    public int getCount() {
        return storedTasks.size();
    }

    /**
     * Adds task into list of stored tasks.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        storedTasks.add(newTask);
        assert storedTasks.contains(newTask);
    }

    /**
     * Marks task as done.
     *
     * @param taskNumber Task number of task to be marked as done.
     * @throws DukeException When task number is wrong or if the task is already done.
     */
    public Task markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > storedTasks.size()) {
            throw new DukeException("Wrong task number!");
        }
        Task task = storedTasks.get(taskNumber - 1);
        if (task.isDone()) {
            throw new DukeException("This task is already done: " + task.getDescription());
        }
        task.markAsDone();
        assert task.isDone;
        return task;
    }

    /**
     * Deletes input task from stored_task.
     *
     * @param taskNumber Task number of task to be deleted.
     * @throws DukeException When task number is wrong.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > storedTasks.size()) {
            throw new DukeException("Wrong task number!");
        }
        Task taskToDelete = storedTasks.get(taskNumber - 1);
        storedTasks.remove(taskToDelete);
        assert !storedTasks.contains(taskToDelete);
        return taskToDelete;
    }

    /**
     * Generates result task list of tasks where the task description matches any of the search keywords.
     * This includes tasks with descriptions matching the search keywords only partially.
     *
     * @param searchKeywords Variable number of search keywords.
     * @return Result task list of tasks where the task description matches any of the search keywords.
     */
    public List<Task> generateResultTaskList(String... searchKeywords) {
        List<Task> resultTaskList = storedTasks
                .stream()
                .filter(task -> task.matchesKeywords(searchKeywords))
                .collect(Collectors.toList());
        return resultTaskList;
    }

}
