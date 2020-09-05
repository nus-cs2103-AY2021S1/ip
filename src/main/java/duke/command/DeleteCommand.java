package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>DeleteCommand</code> executes
 * a command to delete a specific task as specified by the user.
 */
public class DeleteCommand implements Command {
    private final String command;
    private Task deletedTask;

    public DeleteCommand(String command) {
        this.command = command;
    }
    /**
     * Executes a command to delete the specified task as requested by user.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to delete the specified task
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            int totalTaskBefore = taskList.total();
            int ind = Integer.parseInt(command.substring(6).trim()) - 1;
            Task t = taskList.deleteTask(ind);
            deletedTask = t;
            assert taskList.total() == totalTaskBefore - 1 : "Failed to delete task!";
            storage.save(taskList);
            return " *WOOF* I have removed:\n   " + t + "\n" + ui.displayTotal(taskList.total());
        } catch (IndexOutOfBoundsException e) {
            String message = " *Woof!* This task does not exist!\n";
            throw new DukeException(message);
        } catch (NumberFormatException e) {
            String message = " *Woof!* Please enter an integer value! I can't really read...\n";
            throw new DukeException(message);
        }
    }

    /**
     * Undo this command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to add the specified task
     */
    public String undo(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (deletedTask == null) {
            throw new DukeException(" *Woof!* This task does not exist!\n");
        }
        Command undoCommand = new AddCommand(deletedTask);
        String s = undoCommand.execute(storage, ui, taskList);
        storage.save(taskList);
        return s;
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        return " delete <task number> : deletes a task\n";
    }

    /**
     * Compares this <code>DeleteCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>DeleteCommand</code> against
     * @return true if the given object is an instance of this <code>DeleteCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof DeleteCommand;
        }
    }
}
