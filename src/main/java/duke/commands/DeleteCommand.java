package duke.commands;

import duke.*;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

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
