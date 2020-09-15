package duke.storage;

import java.util.List;

import duke.task.Task;

/**
 * Represents a customisable list of tasks to be completed by the user.
 */

public class TaskList {
    protected static final String TASK_TODO_INDICATOR = "[T]";
    protected static final String TASK_DEADLINE_INDICATOR = "[D]";
    protected static final String TASK_EVENT_INDICATOR = "[E]";
    private static final int OFFSET_LIST_INDEX = 1;

    private final List<Task> listOfTasks;

    /**
     * Constructs a new TaskList object to handle adding and deleting tasks.
     *
     * @param tasks List of tasks made by the user.
     */
    public TaskList(List<Task> tasks) {
        this.listOfTasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask Task to be added.
     */
    public void addNewTask(Task newTask) {
        this.listOfTasks.add(newTask);
    }

    /**
     * Replaces a task with a new task at the specified index in the task list.
     *
     * @param newTask New task to replaced task at the specified index.
     * @param index Index of task in the task list.
     */
    public void updateTaskList(Task newTask, int index) {
        this.deleteTask(index);
        this.listOfTasks.add(index - OFFSET_LIST_INDEX, newTask);
    }
    /**
     * Returns the total number of tasks currently in the task list.
     *
     * @return Total number of tasks currently in the task list.
     */
    public int getTotalNumberOfTasks() {
        return this.listOfTasks.size();
    }

    /**
     * Retrieves the task of the specified list index.
     *
     * @param index Index of task to be retrieved.
     * @return Task stored at the specified index.
     */
    public Task getTask(int index) {
        return this.listOfTasks.get(index - OFFSET_LIST_INDEX);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.listOfTasks.remove(index - OFFSET_LIST_INDEX);
    }

    /**
     * Returns the an ordered list of tasks stored in the task list.
     *
     * @return An ordered list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();

        for (Task task: this.listOfTasks) {
            int listIndex = this.listOfTasks.indexOf(task) + 1;
            allTasks.append(listIndex + "." + task + "\n");
        }

        return allTasks.toString();
    }
}
