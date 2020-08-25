package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    private String printout;

    public void execute(TaskList list, Storage storage) {
        this.printout = list.listItems();
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            ui.formattedPrint(ui.prependIndent(this.printout, 1));
        } else {
            throw new DukeException("This action has not been completed.");
        }
    }

    public boolean isExit() {
        return false;
    }

}
