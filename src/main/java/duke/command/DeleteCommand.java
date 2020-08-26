package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The {@code DeleteCommand} class represents a command to delete a {@link Task} in a {@link TaskList}.
 */
public class DeleteCommand extends Command {

    private int index;
    private Task deletedTask;
    private int remainingTaskCount;

    /**
     * Constructs a {@code DeleteCommand} with the specified {@code index}.
     *
     * @param index the index of the {@code Task} to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this {@code CompleteCommand}.
     * Deletes the {@code Task} in the specified {@code list} at the stored {@code index}.
     *
     * @param list
     * @param storage
     */
    public void execute(TaskList list, Storage storage) {
        this.deletedTask = list.deleteTask(index);
        this.remainingTaskCount = list.taskCount();
        super.completed = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code DeleteCommand}.
     *
     * @param ui the {@link Ui} instance to use for formatting.
     */
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

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
