package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;
import duke.Ui;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    private String description;

    /**
     * Constructor to create DateCommand object.
     *
     * @param description task number to mark as done.
     */
    public DoneCommand(String description) {
        this.description = description;
    }

    /**
     * Mark task number as done.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when task number does not exist.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(description.split(" ")[0]);
            if (tasklist.getSize() < index || index <= 0) {
                throw new DukeException("you don't have this task number.");
            }
            tasklist.get(index - 1).markAsDone();
            ui.showMessage("Marked this task as done for you!\n" + tasklist.get(index - 1)
                    + "\nYou have " + tasklist.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("you need to put a number.");
        }
    }
}
