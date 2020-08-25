package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class CompleteCommand extends Command {

    private int index;
    private Task completedTask;

    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        completedTask = list.markAsComplete(index);
        super.completed = true;
    }

    @Override
    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = String.format("Nice! I've marked this task as complete:\n  %s\n", completedTask.toString());
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Complete command was not completed.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
