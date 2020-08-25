package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public void execute(TaskList list, Storage storage) {
        storage.save(list);
        super.completed = true;
    }

    public void printFeedback(Ui ui) {
        if (super.completed) {
            ui.exit();
        } else {
            throw new IncompleteDukeCommandException("Exit command was not completed.");
        }
    }

    public boolean isExit() {
        return true;
    }
}
