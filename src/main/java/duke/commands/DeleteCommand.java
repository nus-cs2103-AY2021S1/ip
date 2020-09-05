package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Delete task from tasklist.
 */
public class DeleteCommand extends Command {
    private String description;

    /**
     * Constructor to create DeleteCommand object.
     *
     * @param description task number to delete.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     *
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when task number does not exist
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        assert tasklist != null : "Tasklist cannot be null.";
        assert storage != null : "Storage cannot be null.";
        assert description != null : "Description cannot be null.";
        try {
            int index = Integer.parseInt(description.split(" ")[0]);
            if (tasklist.getSize() < index || index <= 0) {
                throw new DukeException("you don't have this task number.");
            }
            Task temp = tasklist.get(index - 1);
            tasklist.delete(index - 1);
            return ("Removed this task for you!\n"
                    + temp + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("you need to put a number.");
        }
    }
}
