package src.main.java.duke.data;


import src.main.java.duke.commands.Command;
import src.main.java.duke.commands.CommandResult;
import src.main.java.duke.data.task.Task;
import src.main.java.duke.data.task.TaskList;
import src.main.java.duke.data.task.TaskList.TaskNotFoundException;
import src.main.java.duke.parser.Parser;
import src.main.java.duke.storage.StorageFile;
import src.main.java.duke.storage.StorageFile.StorageOperationException;

/**
 * Represents the bot which contains a tasklist and interacts with the tasks.
 */
public class Duke {

    private final TaskList taskList;

    private StorageFile storageFile;

    /**
     * Creates an empty task list.
     */
    public Duke() {
        taskList = new TaskList();
    }
    /**
     * Constructs an task list with the given data.
     *
     * @param tasks external changes to this will not affect this task list
     */
    public Duke(TaskList tasks) {
        this.taskList = new TaskList(tasks);
    }

    /**
     * Sets the storage file for duke.
     * @param storageFile storageFile which is the own storage file
     * @return return the duke object
     */
    public Duke setStorageFile(StorageFile storageFile) {
        this.storageFile = storageFile;
        return this;
    }


    /**
     * Adds a task to the task list.
     * @param task task to be added to the task list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Adds a task to the task list.
     * @param newDescription new description of the task.
     * @param index index to update the task.
     * @return task that is removed.
     */
    public Task updateDescription(String newDescription, int index) {
        Task currentTask = taskList.getTask(index);
        currentTask.updateDescription(newDescription);
        return currentTask;
    }

    /**
     * Marks the task in the list done.
     * @param index index of the task to be mark done.
     * @return Return task which is mark done.
     */
    public Task markDone(int index) {
        assert index >= 0;
        taskList.getTask(index).markAsDone();
        return taskList.getTask(index);
    }

    /**
     * Returns true if an equivalent task exists in the task list.
     * @param task check if the task contains the task
     * @return True if the list contains the task
     */
    public boolean containsTask(Task task) {
        return taskList.doesContain(task);
    }

    /**
     * Removes the equivalent task from the task list.
     *
     * @param index index of the task to be removed
     * @return a task that is removed.
     * @throws TaskNotFoundException task cannot be found back to {@code DeleteCommand}
     */
    public Task removeTask(int index) throws TaskNotFoundException {
        return taskList.remove(index - 1);
    }

    /**
     * Returns a new UniqueTaskList of all tasks in the task list at the time of
     * the call.
     * @return the list of task that is immutable
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns true if duke is equal.
     * @param other another object to compare if it's equal
     */
    @Override
    public boolean equals(Object other) {
        boolean sameObject = other == this; // short circuit if same object
        boolean isSameInstance = other instanceof Duke; // instanceof handles nulls
        TaskList otherTaskList = ((Duke) other).taskList;
        boolean isEqual = this.taskList.equals(otherTaskList);
        return sameObject || isSameInstance && isEqual;
    }

    /**
     * Gets the response from the input string and parse it to execute
     *
     * @param input an user input
     * @return return a response to the user
     */
    public String getResponse(String input) {
        Command command = new Parser().parseCommand(input);
        return executeCommand(command).getFeedbackToUser();
    }

    /**
     * Executes the command
     * @return a command result which result from the command executed
     */
    private CommandResult executeCommand(Command command) {
        command.setData(this);
        CommandResult result = command.execute();
        try {
            storageFile.save(this);
        } catch (StorageOperationException e) {
            return new CommandResult("Could not save the file");
        }
        return result;
    }
}
