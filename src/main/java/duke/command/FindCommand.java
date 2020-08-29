package duke.command;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Implements the <code>Command</code> interface. <code>FindCommand</code> runs
 * a search in the list of <code>Task</code> for <code>Task</code> with specific
 * keywords specified by user.
 */
public class FindCommand implements Command {
    private final String taskSearch;

    public FindCommand(String taskSearch) {
        this.taskSearch = taskSearch.trim();
    }

    /**
     * Executes a command to run a search on the list of <code>Task</code> for <code>Task</code>
     * with specific keywords.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to execute search
     */
    public String execute(String command, Storage storage, Ui ui, TaskList taskList) throws DukeException {
        try {
            String searchName = taskSearch.substring(taskSearch.indexOf("find") + 5).trim();
            List<Task> results = taskList.searchTask(searchName.split("\\s+"));

            if (results.isEmpty()) {
                return " I can't seem to find any task with this keyword! *woof*\n";
            } else {
                StringBuilder s = new StringBuilder(" Here is the list of matching tasks in your storage:\n");
                for (Task t : results) {
                    s.append("   ").append(results.indexOf(t) + 1).append(".")
                            .append(t.toString()).append("\n");
                }
                return s.toString();
            }
        } catch (IndexOutOfBoundsException e) {
            String message = " Please enter a keyword that you wish to search for...\n"
                    + " Or you can just enter *list* to see all your task! *Woof woof!*\n";
            throw new DukeException(message);
        }
    }

    /**
     * Compares this <code>FindCommand</code> to the specified object.
     * The result is true if and only if the argument is not null and is an object
     * that represents the same instance as this object.
     *
     * @param o Object to compare this <code>FindCommand</code> against
     * @return true if the given object is an instance of this <code>FindCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o instanceof FindCommand;
        }
    }
}
