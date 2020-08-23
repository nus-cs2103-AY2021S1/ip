package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>FindCommand</code> runs
 * a search in the list of <code>Task</code> for <code>Task</code> with specific
 * keywords specified by user.
 */
public class FindCommand implements Command{
    String taskSearch;

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
     * @throws DukeException if system fails to execute search
     */
    public void execute(String command, Storage storage, Ui ui) throws DukeException {
        storage.findRelevantTask(taskSearch);
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
        } else return o instanceof FindCommand;
    }
}
