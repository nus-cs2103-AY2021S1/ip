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
     * Executes a command to list all <code>Task</code> stored in database..
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String execute(String command, Storage storage, Ui ui, TaskList taskList) {
        List<Task> listOfTask = taskList.getList();
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
