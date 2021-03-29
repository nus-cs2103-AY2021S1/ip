package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.UI;
import bob.exception.BobIOException;
import bob.task.Task;

/**
 * This command when executed adds a task to the TaskList and calls
 * the UI class and Storage class to print out the appropriate messages
 * and update stored data respectively.
 */
public class AddCommand extends Command {
    /** The task to be added to the TaskList when the command is executed. */
    private Task task;

    /** Constructs an AddCommand with a task assigned to the task parameter. */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to TaskList and update the Storage data accordingly.
     * It also calls on the provided UI to return the appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobIOException if Storage's text file does not exist.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobIOException {
        assert task != null : "A task should be provided";
        assert tasks != null : "A tasklist should be provided";
        assert storage != null : "Storage should be provided";
        assert ui != null : "A UI should be provided";

        tasks.add(task);
        storage.appendToStorage(task.saveFormat() + System.lineSeparator());
        storage.flushWriter();
        return ui.addTask(task);
    }

    /**
     * Returns a boolean value to indicate if this command is equal to an object by determining if
     * task parameters are equal.
     *
     * @param o
     * @return true or false if AddCommand is equal or not equal to the object respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand c = (AddCommand) o;
            return c.task.equals(this.task);
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the AddCommand which includes the string representation of the to-be-added task.
     *
     * @return string representation of AddCommand.
     */
    @Override
    public String toString() {
        return "Adds " + task.toString() + " to list.";
    }
}
