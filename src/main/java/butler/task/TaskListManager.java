package butler.task;

import java.util.ArrayList;

import butler.exception.ButlerException;

/**
 * Represents a task list manager that manages the history of changes of a task list.
 */
public class TaskListManager {
    private ArrayList<TaskList> taskListHistory = new ArrayList<>();
    private TaskList currentTaskList;

    /**
     * Creates a manager for a task list.
     *
     * @param taskList Task list to be managed.
     */
    public TaskListManager(TaskList taskList) {
        this.currentTaskList = taskList;
    }

    /**
     * Adds the latest task list to the manager.
     * Updates the history of the task list.
     *
     * @param taskList Latest task list.
     */
    public void addLatestTaskList(TaskList taskList) {
        this.taskListHistory.add(currentTaskList);
        this.currentTaskList = taskList;
    }

    /**
     * Returns the current task list.
     *
     * @return Current task list.
     */
    public TaskList getCurrentTaskList() {
        return currentTaskList;
    }

    /**
     * Returns the task list from <code>undoCount</code> changes before.
     * Reverts the history of the task list.
     *
     * @param undoCount Number of commands to undo.
     * @return Task List from <code>undoCount</code> changes before.
     * @throws ButlerException if <code>undoCount</code> is more than the number of changes
     *                         in history or is negative.
     */
    public TaskList getPastTaskList(int undoCount) throws ButlerException {
        int size = taskListHistory.size();
        if (undoCount > size) {
            throw new ButlerException("There is not enough past changes to undo.");
        }
        if (undoCount <= 0) {
            throw new ButlerException("You need to reverse a positive number of changes.");
        }

        // Copies a new task history
        int newTaskHistorySize = size - undoCount;
        ArrayList<TaskList> newTaskListHistory = new ArrayList<>();
        for (int i = 0; i < newTaskHistorySize; i++) {
            newTaskListHistory.add(taskListHistory.get(i).copy());
        }

        // Update manager
        currentTaskList = taskListHistory.get(newTaskHistorySize);
        taskListHistory = newTaskListHistory;

        return currentTaskList;
    }
}
