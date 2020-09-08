package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Task lastModifiedTask;
    private int lastModifiedIndex;
    private Action mostRecentAction;
    private boolean canUndo;

    /**
     * Creates a TaskList which contains all the tasks added by the user so far.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        mostRecentAction = null;
        canUndo = false;
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        taskList.add(task);
        mostRecentAction = Action.ADDTASK;
        canUndo = true;
        lastModifiedTask = task;
        lastModifiedIndex = taskList.size() - 1;
    }

    /**
     * Removes the specified task from the list of tasks.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        lastModifiedIndex = index - 1;
        lastModifiedTask = taskList.get(lastModifiedIndex);
        taskList.remove(index - 1);
        mostRecentAction = Action.DELETETASK;
        canUndo = true;
    }

    /**
     * Marks the task at the specified 1-index as complete, and returns the completed task.
     * @param index The index of the task to be marked complete.
     * @return The task that has been marked as complete.
     */
    public Task markTaskComplete(int index) {
        lastModifiedIndex = index - 1;
        lastModifiedTask = taskList.get(lastModifiedIndex);
        Task completedTask = taskList.get(index - 1);
        completedTask.markAsDone();
        canUndo = true;
        mostRecentAction = Action.MARKTASKCOMPLETE;
        return completedTask;
    }

    /**
     * Returns the Task object corresponding to the task at the specified index.
     * @param index The index of the task to be obtained.
     * @return Task object corresponding to the task at the specified index.
     */
    public Task getTask(int index) {
        // index is bounded from 1 to len, so -1 to convert to array index
        return taskList.get(index - 1);
    }

    /**
     * Returns the entire list of tasks as an ArrayList.
     * @return ArrayList containing the current tasks stored so far.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Returns the number of Tasks stored in the TaskList.
     * @return The number of elements in the data structure which stores the Tasks.
     */
    public int numOfTasks() {
        return taskList.size();
    }

    /**
     * Returns a String object containing all the tasks, that is to be passed to the UI for
     * pretty printing.
     * @return Formatted String enumerating all the tasks in the task list.
     */
    public String getAllTasksAsString() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + ((i + 1) + ". " + taskList.get(i)) + "\n";
        }
        return output;
    }

    /**
     * Returns all the tasks whose string representation contain the specified keyword.
     * @param keyword The keyword to search for in the tasks.
     * @return Formatted String representing all the tasks which contain the specified keyword.
     */
    public String findTask(String keyword) {
        String output = "Here are the matching tasks in your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                output = output + ((i + 1) + ". " + taskList.get(i)) + "\n";
            }
        }
        return output;
    }

    /**
     * Removes the most recent task added to the TaskList.
     */
    public void undoAddTask() {
        assert lastModifiedIndex == taskList.size() - 1;
        taskList.remove(taskList.size() - 1);
        canUndo = false;
    }

    /**
     * Inserts the deleted task back into the TaskList.
     */
    public void undoDeleteTask() {
        assert lastModifiedIndex < taskList.size();
        taskList.add(lastModifiedIndex, lastModifiedTask);
        canUndo = false;
    }

    /**
     * Marks the recently completed task as incomplete.
     */
    public void undoMarkTaskComplete() {
        assert lastModifiedIndex < taskList.size();
        taskList.get(lastModifiedIndex).markAsIncomplete();
        canUndo = false;
    }

    /**
     * Undoes the previous action, restoring the internal tasklist to its previous state.
     */
    public void undo() {
        if (canUndo) {
            switch (mostRecentAction) {
            case ADDTASK:
                undoAddTask();
                break;
            case DELETETASK:
                undoDeleteTask();
                break;
            case MARKTASKCOMPLETE:
                undoMarkTaskComplete();
                break;
            default:
                break;
            }
        }
    }
}

enum Action {
    ADDTASK,
    DELETETASK,
    MARKTASKCOMPLETE
}
