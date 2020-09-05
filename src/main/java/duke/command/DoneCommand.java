package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>DoneCommand</code> executes
 * a command to specify a specific <code>Task</code> as done.
 */
public class DoneCommand implements Command {
    private final String command;
    private Task doneTask;

    public DoneCommand(String command) {
        this.command = command;
    }

    /**
     * Executes a command to mark a specified task as completed.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to mark the specified task as completed
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            int indOfDescription = command.indexOf("done");
            int taskInd = Integer.parseInt(command.substring(indOfDescription + 4).trim());
            String s = taskList.markDone(taskInd - 1);
            doneTask = taskList.getList().get(taskInd - 1);
            storage.save(taskList);
            return s;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            String message = " *Woof!* This task does not exist!\n";
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
     * @throws DukeException if system fails to unmark the specified task
     */
    public String undo(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (doneTask == null) {
            throw new DukeException(" *Woof!* This task does not exist!\n");
        }
        String s = this.doneTask.undoDone();
        storage.save(taskList);
        return s;
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        return " done <task number> : mark as completed\n";
    }

    /**
     * Compares this <code>DoneCommand</code> to the specified object.
     * The result is true if and only if the argument is not null and is an object
     * that represents the same instance as this object.
     *
     * @param o Object to compare this <code>DoneCommand</code> against
     * @return true if the given object is an instance of this <code>DoneCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof DoneCommand;
        }
    }

}
