package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>AddCommand</code> executes a command
 * to add a specific task as specified by the user.
 */
public class AddCommand implements Command {
    private final Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes a command to add the specified task as requested by user.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to add the specified task
     */
    public String execute(String command, Storage storage, Ui ui, TaskList taskList) throws DukeException {
        int totalTask = taskList.addTask(t);
        storage.save(taskList);
        return " *WOOF* I have added:\n   " + t + "\n" + ui.displayTotal(totalTask);
    }

    /**
     * Compares this <code>AddCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>AddCommand</code> against
     * @return true if the given object is an instance of this <code>AddCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof AddCommand;
        }
    }
}
