package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.TaskList;

public class ListCommand extends Command {

    private String printout;

    @Override
    public void execute(TaskList list, Storage storage) {
        printout = list.listItems();
        super.completed = true;
    }

    @Override
    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            ui.formattedPrint(ui.prependIndent(printout, 1));
        } else {
            throw new IncompleteDukeCommandException("List command was not completed.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
