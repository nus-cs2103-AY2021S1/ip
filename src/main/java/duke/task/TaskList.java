package duke.task;

import duke.exceptions.NoSuchTaskException;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Contains the user's Tasks while the app is running.
 */
public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets an unordered List object containing all Tasks stored in this TaskList.
     *
     * @return a List object containing all Tasks
     */
    public List<Task> getListOfTasks() {
        return List.copyOf(taskList);
    }

    /**
     * Sets the Task with the given taskNumber as completed.
     * taskNumber is determined by the implementation of the specific TaskList
     *
     * @param taskNumber taskNumber of the Task to be completed
     * @return new Task object that represents a completed state of the original Task
     * @throws NoSuchTaskException if invalid taskNumber is provided
     */
    public Task completeTask(int taskNumber) throws NoSuchTaskException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task completedTask = taskList.get(taskNumber - 1).markCompleted();
        taskList.set(taskNumber - 1, completedTask);
        return completedTask;
    }

    /**
     * Deletes the Task with the given taskNumber.
     * taskNumber is determined by the implementation of the specific TaskList
     *
     * @param taskNumber taskNumber of the Task to be deleted
     * @return the Task that was deleted
     * @throws NoSuchTaskException if invalid taskNumber is provided
     */
    public Task deleteTask(int taskNumber) throws NoSuchTaskException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task toRemove = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        return toRemove;
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return !(taskNumber < 1 || taskNumber > taskList.size());
    }

    /**
     * Returns a List containing the mapping of all Tasks stored in this TaskList to their
     * assigned taskNumber.
     *
     * @return a List of NumberedTasks created from all Tasks stored in this TaskList
     */
    public List<NumberedTask> tasksToString() {
        return IntStream.range(0, taskList.size())
                .mapToObj(i -> {
                    Task currentTask = taskList.get(i);
                    return new NumberedTask(i + 1, currentTask);
                })
                .collect(Collectors.toList());
    }

    /**
     * Returns a List containing the mapping of all Tasks stored in this TaskList and whose description matches a given
     * keyword, to their assigned taskNumber.
     *
     * @param keyword keyword to find Tasks by
     * @return a List of NumberedTasks created from Tasks stored in this TaskList that match the given keyword
     */
    public List<NumberedTask> getMatchingTasks(String keyword) {
        return IntStream.range(0, taskList.size())
                .filter(i -> {
                    Task currentTask = taskList.get(i);
                    return currentTask.toString().contains(keyword);
                })
                .mapToObj(i -> new NumberedTask(i + 1, taskList.get(i)))
                .collect(Collectors.toList());
    }
}
