package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private String description;

    public DoneCommand(String description) {
        this.description = description;
    }

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
