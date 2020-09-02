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
 * Duke class represents the bot which contains a tasklist and interacts with the tasks.
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
     * @peram storageFile storageFile which is the own storage file
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
     * Marks the task in the list done.
     * @param index index of the task to be mark done
     */
    public Task markDone(int index) {
        taskList.getTask(index).markAsDone();
        return taskList.getTask(index);
    }

    /**
     * Returns true if an equivalent task exists in the task list.
     */
    public boolean containsTask(Task key) {
        return taskList.contains(key);
    }

    /**
     * Removes the equivalent task from the task list.
     * @poram toRemove index of the task to be removed
     */
    public void removeTask(int toRemove) throws TaskNotFoundException {
        taskList.remove(toRemove - 1);
    }

    /**
     * Returns a new UniqueTaskList of all tasks in the task list at the time of
     * the call.
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
        return other == this // short circuit if same object
                || (other instanceof Duke // instanceof handles nulls
                        && this.taskList.equals(((Duke) other).taskList));
    }

    /**
     * Gets the response from the input string and parse it to execute
     *
     * @param input an user input
     * @return return a response to the user
     */
    public String getResponse(String input) throws StorageOperationException {
        Command command = new Parser().parseCommand(input);
        return executeCommand(command).getFeedbackToUser();

    }

    /**
     * Executes the command
     * @return a command result which result from the command executed
     */
    private CommandResult executeCommand(src.main.java.duke.commands.Command command)
            throws StorageOperationException {
        command.setData(this);
        CommandResult result = command.execute();
        storageFile.save(this);
        return result;
    }
}
