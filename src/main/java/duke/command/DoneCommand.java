package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>DoneCommand</code> executes
 * a command to specify a specific <code>Task</code> as done.
 */
public class DoneCommand implements Command {

    /**
     * Executes a command to mark a specified task as completed.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if system fails to mark the specified task as completed
     */
    public String execute(String command, Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            int taskInd = Integer.parseInt(command.substring(5));
            String s = taskList.markDone(taskInd - 1);
            storage.save(taskList);
            return s;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException(ui.noSuchTask());
        }
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
