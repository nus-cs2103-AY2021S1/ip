package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>ListCommand</code> executes
 * a command to list all <code>Task</code> stored in database.
 */
public class ListCommand implements Command {

    /**
     * Executes a command to list all <code>Task</code> stored in database.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        List<Task> listOfTask = taskList.getList();
        assert listOfTask != null : "Failed to obtain list";
        if (listOfTask.isEmpty()) {
            return " You have no task to complete! *WOOF*\n";
        } else {
            StringBuilder s = new StringBuilder(" Here are the tasks in your list *Woof*:\n");

            for (Task task : listOfTask) {
                s.append("   ").append(listOfTask.indexOf(task) + 1).append(".")
                        .append(task.toString()).append("\n");
            }

            return s.toString();
        }
    }

    /**
     * Undo this command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String undo(Storage storage, Ui ui, TaskList taskList) {
        return " There's no need to undo this action! *woof*\n";
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        return " list : list all tasks\n";
    }

    /**
     * Compares this <code>ListCommand</code> to the specified object.
     * The result is true if and only if the argument is not null and is an object
     * that represents the same instance as this object.
     *
     * @param o Object to compare this <code>ListCommand</code> against
     * @return true if the given object is an instance of this <code>ListCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof ListCommand;
        }
    }
}
