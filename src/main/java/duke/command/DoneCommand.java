package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private int index;
    private Task completedTask;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list, Storage storage) {
        this.completedTask = list.markAsDone(index);
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            String feedback = String.format("Nice! I've marked this task as done:\n  %s\n", completedTask.toString());
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new DukeException("This action has not been completed.");
        }
    }

    public boolean isExit() {
        return false;
    }

}
