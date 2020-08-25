package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.TaskList;

public class DeleteAllCommand extends Command {

    public void execute(TaskList list, Storage storage) {
        list.clearList();
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = "I've cleared all your tasks.\nYou sure are efficient.";
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Delete all command was not completed.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
