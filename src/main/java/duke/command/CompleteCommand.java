package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The {@code CompleteCommand} class represents a command to mark a {@link Task} in a {@link TaskList} as complete.
 */
public class CompleteCommand extends Command {

    private int index;
    private Task completedTask;

    /**
     * Constructs a {@code CompleteCommand} with the specified {@code index}.
     *
     * @param index the index of the {@code Task} to mark as complete.
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this {@code CompleteCommand}.
     * Marks the {@code Task} in the specified {@code list} at the stored {@code index} as complete.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public void execute(TaskList list, Storage storage) {
        this.completedTask = list.markAsComplete(index);
        super.completed = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code CompleteCommand}.
     *
     * @param ui the {@link Ui} instance to use for formatting.
     * @throws IncompleteDukeCommandException if this {@code CompleteCommand} was not executed.
     */
    public void printFeedback(Ui ui) throws IncompleteDukeCommandException {
        if (super.completed) {
            String feedback = String.format("Nice! I've marked this task as complete:\n  %s\n", completedTask.toString());
            ui.formattedPrint(ui.prependIndent(feedback, 1));
        } else {
            throw new IncompleteDukeCommandException("Complete command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

}
