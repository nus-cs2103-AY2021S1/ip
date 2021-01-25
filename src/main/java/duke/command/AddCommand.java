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
public class AddCommand implements UndoableCommand {
    private final Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes a command to add the specified task as requested by user.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to add the specified task
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        int totalTaskBefore = taskList.total();
        int totalTask = taskList.addTask(t);

        assert totalTask == totalTaskBefore + 1 : "Failed to add task!";

        storage.save(taskList);
        return " *WOOF* I have added:\n   " + t + "\n" + ui.displayTotal(totalTask);
    }

    /**
     * Undo this command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to remove the specified task
     */
    public String undo(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        String command = "delete" + (taskList.getList().indexOf(this.t) + 1);
        String s = new DeleteCommand(command).execute(storage, ui, taskList);
        storage.save(taskList);
        return s;
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        String header = " Add a task:\n";
        String todoCommand = " -> todo <name>\n";
        String deadlineCommand = " -> deadline <name> /by <YYYY/MM/DD {HH:MM}>\n";
        String eventCommand = " -> event <name> /at <YYYY/MM/DD\n    HH:MM-HH:MM>\n";
        return header + todoCommand + deadlineCommand + eventCommand;
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
