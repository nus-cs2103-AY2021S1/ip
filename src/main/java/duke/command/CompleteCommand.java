package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class CompleteCommand extends Command {

    private int index;
    private Task completedTask;

    public CompleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list, Storage storage) {
        this.completedTask = list.markAsComplete(index);
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            String feedback = String.format("Nice! I've marked this task as complete:\n  %s\n", completedTask.toString());
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new DukeException("This action has not been completed.");
        }
    }

    public boolean isExit() {
        return false;
    }

}
