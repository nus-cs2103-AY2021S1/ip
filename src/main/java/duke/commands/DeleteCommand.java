package duke.commands;

import duke.*;
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
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when task number does not exist
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(description.split(" ")[0]);
            if (tasklist.getSize() < index || index <= 0) {
                throw new DukeException("you don't have this task number.");
            }
            Task temp = tasklist.get(index - 1);
            tasklist.delete(index - 1);
            ui.showMessage("Removed this task for you!\n"
                    + temp + "\nYou have "
                    + tasklist.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("you need to put a number.");
        }
    }
}
