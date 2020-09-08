package main.java.farrell.duke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of tasks and actions that can be performed on the list.
 */
public class TaskList {
    /** The list of tasks */
    private List<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the task at the specified index
     *
     * @param taskNumber Index of the task to get.
     * @return The task at the index.
     */
    public Task getTask(int taskNumber) {
        assert taskList.size() > taskNumber;
        return taskList.get(taskNumber - 1);
    }

    /**
     * Returns a list of all tasks being tracked.
     *
     * @return A list of Tasks.
     */
    public List<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskNumber Index of the task to delete.
     */
    public void deleteTask(int taskNumber) {
        assert taskList.size() > taskNumber;
        Task deleteTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
    }

    /**
     * Updates the completion status of the task at the specified index.
     *
     * @param taskNumber Index of the task to update.
     */
    public void updateDone(int taskNumber) {
        assert taskList.size() > taskNumber;
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone(true);
    }

    /**
     * Returns a formatted string representing a list of tasks filtered by a string.
     *
     * @param matchString The string to filter by.
     * @return The list of tasks as a formatted string
     */
    public String filteredToString(String matchString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(matchString)) {
                sb.append(i + 1)
                        .append(". ")
                        .append(taskList.get(i).toString());
                if (i < taskList.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns a formatted string representing a list of tasks sorted by description.
     * The list is sorted in ascending order by default.
     * @param descending Whether to sort the list by descending order instead.
     * @return The list of tasks as a formatted string.
     */
    public String sortByDescriptionToString(boolean descending) {
        Comparator<Task> comparator = descending
                ? Comparator.comparing(Task::getDescription).reversed()
                : Comparator.comparing(Task::getDescription);
        taskList.sort(comparator);
        return toString();
    }

    /**
     * Returns a formatted string representing a list of tasks sorted by time.
     * The list is sorted in descending order by default.
     * @param ascending Whether to sort the list by ascending order instead.
     * @return The list of tasks as a formatted string.
     * @throws DukeException
     */
    public String sortByTimeToString(boolean ascending) throws DukeException {
        List<TimedTask> tasksWithTime = new ArrayList<>();
        List<Task> noTimeTasks = new ArrayList<>();
        for (Task task : taskList) {
            switch (task.getTaskType()) {
            case DEADLINE:
            case EVENT:
                tasksWithTime.add((TimedTask) task);
                break;
            case TODO:
                noTimeTasks.add(task);
                break;
            default:
                throw new DukeException("Unknown task type found in list!");
            }
        }

        Comparator<TimedTask> comparator = ascending
                ? Comparator.comparing(TimedTask::getTime)
                : Comparator.comparing(TimedTask::getTime).reversed();
        tasksWithTime.sort(comparator);
        taskList.clear();
        taskList.addAll(tasksWithTime);
        taskList.addAll(noTimeTasks);

        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString());
            if (i < taskList.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
