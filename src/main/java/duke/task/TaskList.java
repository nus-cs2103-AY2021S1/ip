package duke.task;

import duke.exceptions.NoSuchTaskException;

import java.util.List;
import java.util.ArrayList;

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
     * Get an ordered List object containing all Tasks.
     * List to be returned is ordered in increasing order of time since creation of the Task.
     *
     * @return a List object containing all Tasks
     */
    public List<Task> getListOfTasks() {
        return List.copyOf(taskList);
    }

    /**
     * Set the Task with the given taskNumber as completed.
     * taskNumber is determined based on numbers assigned to Tasks in the String returned from TaskList.tasksToString()
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
     * Delete the Task with the given taskNumber.
     * taskNumber is determined based on numbers assigned to Tasks in the String returned from TaskList.tasksToString()
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
     * Creates a String that sequentially lists the Tasks in the TaskList in increasing order of time since creation.
     * In the String, each Task is assigned a number that will be used to select that particular task.
     *
     * @return a String that displays all Tasks in the TaskList
     */
    public String tasksToString() {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        return tasks.toString();
    }

    public List<NumberedTask> getMatchingTasks(String keyword) {
        List<NumberedTask> result = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.toString().contains(keyword)) {
                result.add(new NumberedTask(i + 1, currentTask));
            }
        }
        return result;
    }
}
