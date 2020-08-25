package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int index;
    private Task deletedTask;
    private int remainingTaskCount;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list, Storage storage) {
        this.deletedTask = list.deleteTask(index);
        this.remainingTaskCount = list.taskCount();
        super.completed = true;
    }

    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in your list.",
                    deletedTask.toString(),
                    remainingTaskCount);
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Delete command was not completed.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
